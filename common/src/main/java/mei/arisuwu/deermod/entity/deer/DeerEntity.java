package mei.arisuwu.deermod.entity.deer;

import mei.arisuwu.deermod.ModResourceLocation;
import mei.arisuwu.deermod.ModItems;
import mei.arisuwu.deermod.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("resource")
public class DeerEntity extends Animal implements Shearable, ItemSteerable, Saddleable
{
    public static AttributeSupplier.Builder createAttributes()
    {
        return Animal.createMobAttributes()
            .add(Attributes.MAX_HEALTH, 8.0)
            .add(Attributes.MOVEMENT_SPEED, 0.25f);
    }

    public DeerEntity(EntityType<? extends Animal> entityType, Level world)
    {
        super(entityType, world);
    }

    @Override
    protected void registerGoals()
    {
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(0, new ClimbOnTopOfPowderSnowGoal(this, level()));
        goalSelector.addGoal(1, new PanicGoal(this, 2.0));
        goalSelector.addGoal(2, new BreedGoal(this, 1.0));
        goalSelector.addGoal(3, new TemptGoal(
            this, 1.25,
            Ingredient.of(ModItems.DEER_CRACKERS_ON_A_STICK.get()),
            false
        ));
        goalSelector.addGoal(3, new TemptGoal(
            this, 1.25,
            Ingredient.of(ModTags.DEER_FOOD),
            false
        ));
        goalSelector.addGoal(4, eatGrassGoal = new EatBlockGoal(this));
        goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0f, 1));
        goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1));
    }

    @Override
    public void defineSynchedData()
    {
        super.defineSynchedData();
        entityData.define(RED_NOSE, false);
        entityData.define(SHEARED, false);
        entityData.define(SADDLED, false);
        entityData.define(BOOST_TIME, 0);
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> data)
    {
        if (BOOST_TIME.equals(data) && level().isClientSide())
            saddledComponent.boost();

        super.onSyncedDataUpdated(data);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag)
    {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putBoolean("RedNose", hasRedNose());
        compoundTag.putBoolean("Sheared", isSheared());
        compoundTag.putBoolean("Saddled", isSaddled());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag)
    {
        super.readAdditionalSaveData(compoundTag);
        setRedNose(compoundTag.getBoolean("RedNose"));
        setSheared(compoundTag.getBoolean("Sheared"));
        setSaddled(compoundTag.getBoolean("Saddled"));
    }

    @Override
    public boolean isFood(ItemStack stack)
    {
        return stack.is(ModTags.DEER_FOOD);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel world, AgeableMob entity)
    {
        return (AgeableMob) BuiltInRegistries.ENTITY_TYPE.get(ModResourceLocation.of("deer")).create(world);
    }

    @Override
    public @NotNull InteractionResult mobInteract(Player player, InteractionHand hand)
    {
        ItemStack itemStack = player.getItemInHand(hand);

        if (itemStack.is(Items.RED_DYE) || itemStack.is(Items.BLACK_DYE))
        {
            if (level() instanceof ServerLevel serverWorld)
            {
                setRedNose(itemStack.is(Items.RED_DYE));
                itemStack.shrink(1);
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.CONSUME;
        }

        if (itemStack.is(Items.SHEARS) && readyForShearing())
        {
            if (level() instanceof ServerLevel serverWorld)
            {
                shear(SoundSource.PLAYERS);
                gameEvent(GameEvent.SHEAR, player);
                itemStack.hurtAndBreak(1, player, p-> p.broadcastBreakEvent(hand));
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.CONSUME;
        }

        if (!isFood(itemStack) && isSaddled() && !isVehicle() && !player.isSecondaryUseActive())
        {
            if (!level().isClientSide())
                player.startRiding(this);

            return InteractionResult.SUCCESS;
        }

        if (itemStack.is(Items.SADDLE))
            itemStack.interactLivingEntity(player, this, hand);

        return super.mobInteract(player, hand);
    }


    // RED NOSE MECHANICS

    private static final EntityDataAccessor<Boolean> RED_NOSE = SynchedEntityData.defineId(DeerEntity.class, EntityDataSerializers.BOOLEAN);

    public boolean hasRedNose()
    {
        return entityData.get(RED_NOSE);
    }

    protected void setRedNose(boolean b)
    {
        entityData.set(RED_NOSE, b);
    }


    // SHEARING

    private static final EntityDataAccessor<Boolean> SHEARED = SynchedEntityData.defineId(DeerEntity.class, EntityDataSerializers.BOOLEAN);

    @Override
    public void shear(SoundSource shearedSoundCategory)
    {
        level().playSound(null, this, SoundEvents.SHEEP_SHEAR, shearedSoundCategory, 1.0f, 1.0f);
        spawnAtLocation(ModItems.ANTLERS.get(), 1);
        setSheared(true);
    }

    @Override
    public boolean readyForShearing()
    {
        return isAlive() && !isBaby() && !isSheared();
    }

    public boolean isSheared()
    {
        return entityData.get(SHEARED);
    }

    public void setSheared(boolean sheared)
    {
        entityData.set(SHEARED, sheared);
    }


    // EATING GRASS

    public final AnimationState eatGrassAnimationState = new AnimationState();
    private EatBlockGoal eatGrassGoal;
    private int eatGrassTimer = 0;

    @Override
    protected void customServerAiStep()
    {
        eatGrassTimer = eatGrassGoal.getEatAnimationTick();
        super.customServerAiStep();
    }

    @Override
    public void aiStep()
    {
        if (level().isClientSide())
            eatGrassTimer = Math.max(0, eatGrassTimer - 1);

        super.aiStep();
    }

    @Override
    public void tick()
    {
        super.tick();
        if (level().isClientSide())
            updateEatGrassAnimation();
    }

    @Override
    public void handleEntityEvent(byte status)
    {
        if (status == EntityEvent.EAT_GRASS)
            eatGrassTimer = 40;

        super.handleEntityEvent(status);
    }

    @Override
    public void ate()
    {
        super.ate();
        setSheared(false);
        if (isBaby()) ageUp(60);
    }

    private void updateEatGrassAnimation()
    {
        if (eatGrassTimer > 0)
            eatGrassAnimationState.startIfStopped(tickCount);
        else
            eatGrassAnimationState.stop();
    }


    // SADDLE MECHANICS

    private static final EntityDataAccessor<Integer> BOOST_TIME = SynchedEntityData.defineId(DeerEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> SADDLED = SynchedEntityData.defineId(DeerEntity.class, EntityDataSerializers.BOOLEAN);
    private final DeerEntitySaddledComponent saddledComponent = new DeerEntitySaddledComponent(entityData, BOOST_TIME);

    @Override
    public boolean isSaddleable()
    {
        return isAlive() && !isBaby();
    }

    @Override
    public boolean isSaddled()
    {
        return entityData.get(SADDLED);
    }

    public void setSaddled(boolean b)
    {
        entityData.set(SADDLED, b);
    }

    @Override
    public void equipSaddle(@Nullable SoundSource soundSource)
    {
        entityData.set(SADDLED, true);
    }

    @Override
    protected void tickRidden(Player controllingPlayer, Vec3 movementInput)
    {
        super.tickRidden(controllingPlayer, movementInput);
        setRot(controllingPlayer.getYRot(), controllingPlayer.getXRot() * 0.5F);
        yRotO = yBodyRot = yHeadRot = getYRot();
        saddledComponent.tickBoost();
    }

    @Override
    public @Nullable LivingEntity getControllingPassenger()
    {
        return isSaddled() && getFirstPassenger() instanceof Player player && player.isHolding(ModItems.DEER_CRACKERS_ON_A_STICK.get())
            ? player
            : super.getControllingPassenger();
    }

    @Override
    protected @NotNull Vec3 getRiddenInput(Player controllingPlayer, Vec3 movementInput)
    {
        return new Vec3(0.0, 0.0, 1.0);
    }

    @Override
    protected float getRiddenSpeed(Player controllingPlayer)
    {
        return (float)(getAttributeValue(Attributes.MOVEMENT_SPEED) * 0.4f * saddledComponent.getMovementSpeedMultiplier());
    }

    @Override
    public double getPassengersRidingOffset()
    {
        return super.getPassengersRidingOffset() - 0.55f;
    }

    @Override
    public boolean boost()
    {
        return saddledComponent.boost(getRandom());
    }

    @Override
    public @NotNull Vec3 getDismountLocationForPassenger(LivingEntity passenger) {
        Direction direction = this.getMotionDirection();
        if (direction.getAxis() != Direction.Axis.Y)
        {
            int[][] is = DismountHelper.offsetsForDirection(direction);
            BlockPos blockPos = this.blockPosition();
            BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

            for (Pose entityPose : passenger.getDismountPoses())
            {
                AABB box = passenger.getLocalBoundsForPose(entityPose);

                for (int[] js : is)
                {
                    mutable.set(blockPos.getX() + js[0], blockPos.getY(), blockPos.getZ() + js[1]);
                    double d = this.level().getBlockFloorHeight(mutable);
                    if (DismountHelper.isBlockFloorValid(d))
                    {
                        Vec3 vec3d = Vec3.upFromBottomCenterOf(mutable, d);
                        if (DismountHelper.canDismountTo(this.level(), passenger, box.move(vec3d)))
                        {
                            passenger.setPose(entityPose);
                            return vec3d;
                        }
                    }
                }
            }
        }
        return super.getDismountLocationForPassenger(passenger);
    }
}
