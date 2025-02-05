package mei.arisuwu.deermod.entity.deer;

import mei.arisuwu.deermod.*;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class DeerEntity extends AnimalEntity implements Shearable, ItemSteerable, Saddleable
{
    public static DefaultAttributeContainer.Builder createAttributes()
    {
        return AnimalEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 8.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25f);
    }

    public static final TrackedData<Byte> DEER_FLAGS = DataTracker.registerData(DeerEntity.class, TrackedDataHandlerRegistry.BYTE);
    private static final int SHEARED_FLAG = 1;
    private static final int SADDLED_FLAG = 2;

    public DeerEntity(EntityType<? extends AnimalEntity> entityType, World world)
    {
        super(entityType, world);
    }

    @Override
    protected void initGoals()
    {
        goalSelector.add(0, new SwimGoal(this));
        goalSelector.add(1, new EscapeDangerGoal(this, 2.0));
        goalSelector.add(2, new AnimalMateGoal(this, 1.0));
        goalSelector.add(3,
            new TemptGoal(this, 1.25, stack -> stack.isOf(ModItems.DEER_CRACKERS_ON_A_STICK.get()), false));
        goalSelector.add(3,
            new TemptGoal(this, 1.25, stack -> stack.isIn(ModTags.DEER_FOOD), false));
        goalSelector.add(4, eatGrassGoal = new EatGrassGoal(this));
        goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f, 1));
        goalSelector.add(6, new LookAroundGoal(this));
        goalSelector.add(7, new WanderAroundFarGoal(this, 1));
    }

    private boolean getDeerFlag(int bitmask)
    {
        return (dataTracker.get(DEER_FLAGS) & bitmask) != 0;
    }

    private void setDeerFlag(int bitmask, boolean value)
    {
        byte b = this.dataTracker.get(DEER_FLAGS);
        if (value)
            dataTracker.set(DEER_FLAGS, (byte)(b | bitmask));
        else
            dataTracker.set(DEER_FLAGS, (byte)(b & ~bitmask));
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder)
    {
        super.initDataTracker(builder);
        builder.add(DEER_FLAGS, (byte)0);
        builder.add(BOOST_TIME, 0);
    }

    @Override
    public void onTrackedDataSet(TrackedData<?> data)
    {
        if (BOOST_TIME.equals(data) && getWorld().isClient)
            saddledComponent.boost();

        super.onTrackedDataSet(data);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt)
    {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("Sheared", isSheared());
        nbt.putBoolean("Saddled", isSaddled());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt)
    {
        super.readCustomDataFromNbt(nbt);
        setSheared(nbt.getBoolean("Sheared"));
        setDeerFlag(SADDLED_FLAG, nbt.getBoolean("Saddled"));
    }

    @Override
    public boolean isBreedingItem(ItemStack stack)
    {
        return stack.isIn(ModTags.DEER_FOOD);
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity)
    {
        return ModEntities.DEER.get().create(world);
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand)
    {
        ItemStack itemStack = player.getStackInHand(hand);

        if (itemStack.isOf(Items.SHEARS))
        {
            if (getWorld() instanceof ServerWorld && isShearable())
            {
                sheared(SoundCategory.PLAYERS);
                emitGameEvent(GameEvent.SHEAR, player);
                itemStack.damage(1, player, getSlotForHand(hand));
                return ActionResult.SUCCESS;
            }
            return ActionResult.CONSUME;
        }

        if (isSaddled() && !hasPassengers() && !player.shouldCancelInteraction())
        {
            if (!getWorld().isClient)
                player.startRiding(this);

            return ActionResult.SUCCESS;
        }

        if (player.isSneaking() && isSaddled() && !hasPassengers())
        {
            if (getWorld() instanceof ServerWorld serverWorld)
            {
                setSaddled(false);
                dropItem(Items.SADDLE);
                return ActionResult.SUCCESS;
            }
            return ActionResult.CONSUME;
        }

        if (itemStack.isOf(Items.SADDLE))
            itemStack.useOnEntity(player, this, hand);

        return super.interactMob(player, hand);
    }


    // SHEARING

    @Override
    public void sheared(SoundCategory shearedSoundCategory)
    {
        this.getWorld().playSoundFromEntity(null, this, SoundEvents.ENTITY_SHEEP_SHEAR, shearedSoundCategory, 1.0F, 1.0F);

        ItemEntity itementity = this.dropItem(ModItems.ANTLERS.get(), 1);
        if (itementity != null) {
            itementity.setVelocity(
                itementity.getVelocity()
                    .add(
                        (this.random.nextFloat() - this.random.nextFloat()) * 0.1F,
                        this.random.nextFloat() * 0.05F,
                        (this.random.nextFloat() - this.random.nextFloat()) * 0.1F
                    )
            );
        }
        this.setSheared(true);
    }

    @Override
    public boolean isShearable()
    {
        return isAlive() && !isBaby() && !isSheared();
    }

    public boolean isSheared()
    {
        return getDeerFlag(SHEARED_FLAG);
    }

    public void setSheared(boolean sheared)
    {
        setDeerFlag(SHEARED_FLAG, sheared);
    }

    public float getNeckAngle(float tickDelta)
    {
        if (this.eatGrassTimer <= 0)
            return 0.0F;
        else
        {
            float x = MathHelper.PI * (40f - eatGrassTimer + tickDelta) / 40f;
            final float amplitude = 2;
            return amplitude * 4 * (
                    MathHelper.sin(x) +
                    MathHelper.sin(3 * x) / 3.2f +
                    MathHelper.sin(5 * x) / 5.84f +
                    MathHelper.sin(7 * x) / 9.5f +
                    MathHelper.sin(9 * x) / 10f
                ) / MathHelper.PI;
        }
    }

    // EATING GRASS

    public final AnimationState eatGrassAnimationState = new AnimationState();
    private EatGrassGoal eatGrassGoal;
    private int eatGrassTimer = 0;

    @Override
    protected void mobTick()
    {
        eatGrassTimer = eatGrassGoal.getTimer();
        super.mobTick();
    }

    @Override
    public void tickMovement()
    {
        if (getWorld().isClient())
            eatGrassTimer = Math.max(0, eatGrassTimer - 1);

        super.tickMovement();
    }

    @Override
    public void tick()
    {
        super.tick();

        if (this.getWorld().isClient())
            updateEatGrassAnimation();
    }

    @Override
    public void handleStatus(byte status)
    {
        if (status == EntityStatuses.SET_SHEEP_EAT_GRASS_TIMER_OR_PRIME_TNT_MINECART)
            eatGrassTimer = 40;

        super.handleStatus(status);
    }

    @Override
    public void onEatingGrass()
    {
        super.onEatingGrass();
        setSheared(false);
        if (isBaby()) growUp(60);
    }

    private void updateEatGrassAnimation()
    {
        if (eatGrassTimer > 0)
            eatGrassAnimationState.startIfNotRunning(age);
        else
            eatGrassAnimationState.stop();

    }


    // SADDLE MECHANICS

    private static final TrackedData<Integer> BOOST_TIME = DataTracker.registerData(DeerEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private final DeerEntitySaddledComponent saddledComponent = new DeerEntitySaddledComponent(dataTracker, BOOST_TIME);

    @Override
    public boolean canBeSaddled()
    {
        return isAlive() && !isBaby();
    }

    @Override
    public boolean isSaddled()
    {
        return getDeerFlag(SADDLED_FLAG);
    }

    public void setSaddled(boolean value)
    {
        setDeerFlag(SADDLED_FLAG, value);
    }

    @Override
    public void saddle(ItemStack stack, @Nullable SoundCategory soundCategory)
    {
        setSaddled(true);
    }

    @Override
    protected void tickControlled(PlayerEntity controllingPlayer, Vec3d movementInput)
    {
        super.tickControlled(controllingPlayer, movementInput);
        setRotation(controllingPlayer.getYaw(), controllingPlayer.getPitch() * 0.5F);
        prevYaw = bodyYaw = headYaw = getYaw();
        saddledComponent.tickBoost();
    }

    @Override
    public @Nullable LivingEntity getControllingPassenger()
    {
        return isSaddled() && getFirstPassenger() instanceof PlayerEntity player && player.isHolding(ModItems.DEER_CRACKERS_ON_A_STICK.get())
            ? player
            : super.getControllingPassenger();
    }

    @Override
    protected Vec3d getControlledMovementInput(PlayerEntity controllingPlayer, Vec3d movementInput)
    {
        return new Vec3d(0.0, 0.0, 1.0);
    }

    @Override
    protected float getSaddledSpeed(PlayerEntity controllingPlayer)
    {
        return (float)(getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED) * 0.4f * saddledComponent.getMovementSpeedMultiplier());
    }

    @Override
    public Vec3d getPassengerRidingPos(Entity passenger)
    {
        return super.getPassengerRidingPos(passenger).add(0, -0.55f, 0);
    }

    @Override
    protected void dropInventory()
    {
        super.dropInventory();

        if (isSaddled()) dropItem(Items.SADDLE);
    }

    @Override
    public boolean consumeOnAStickItem()
    {
        return saddledComponent.boost(getRandom());
    }

    @Override
    public Vec3d updatePassengerForDismount(LivingEntity passenger) {
        Direction direction = this.getMovementDirection();
        if (direction.getAxis() != Direction.Axis.Y)
        {
            int[][] is = Dismounting.getDismountOffsets(direction);
            BlockPos blockPos = this.getBlockPos();
            BlockPos.Mutable mutable = new BlockPos.Mutable();

            for (EntityPose entityPose : passenger.getPoses())
            {
                Box box = passenger.getBoundingBox(entityPose);

                for (int[] js : is)
                {
                    mutable.set(blockPos.getX() + js[0], blockPos.getY(), blockPos.getZ() + js[1]);
                    double d = this.getWorld().getDismountHeight(mutable);
                    if (Dismounting.canDismountInBlock(d))
                    {
                        Vec3d vec3d = Vec3d.ofCenter(mutable, d);
                        if (Dismounting.canPlaceEntityAt(this.getWorld(), passenger, box.offset(vec3d)))
                        {
                            passenger.setPose(entityPose);
                            return vec3d;
                        }
                    }
                }
            }

        }
        return super.updatePassengerForDismount(passenger);
    }
}
