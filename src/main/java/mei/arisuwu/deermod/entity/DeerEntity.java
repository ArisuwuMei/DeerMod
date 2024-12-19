package mei.arisuwu.deermod.entity;

import mei.arisuwu.deermod.ModEntities;
import mei.arisuwu.deermod.ModLootTables;
import mei.arisuwu.deermod.ModTags;
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
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.util.GeckoLibUtil;

public class DeerEntity extends AnimalEntity implements GeoEntity, Shearable
{
    public static DefaultAttributeContainer.Builder createAttributes()
    {
        return AnimalEntity.createAnimalAttributes()
                .add(EntityAttributes.MAX_HEALTH, 8.0)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.25f);
    }

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
        goalSelector.add(3, new TemptGoal(this, 1.25, stack -> stack.isIn(ModTags.DEER_FOOD), false));
        goalSelector.add(4, eatGrassGoal = new EatGrassGoal(this));
        goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f, 1));
        goalSelector.add(6, new LookAroundGoal(this));
        goalSelector.add(7, new WanderAroundFarGoal(this, 1));
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder)
    {
        super.initDataTracker(builder);
        builder.add(SHEARED, false);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt)
    {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("Sheared",dataTracker.get(SHEARED));
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt)
    {
        super.readCustomDataFromNbt(nbt);
        dataTracker.set(SHEARED, nbt.getBoolean("Sheared"));
    }

    @Override
    public boolean isBreedingItem(ItemStack stack)
    {
        return stack.isIn(ModTags.DEER_FOOD);
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity)
    {
        return ModEntities.DEER.create(world, SpawnReason.BREEDING);
    }

    // ANIMATION SETUP

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache()
    {
        return cache;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar)
    {
        controllerRegistrar.add(DefaultAnimations.genericWalkIdleController(this));
        controllerRegistrar.add(new AnimationController<>(this, "EatGrass", this::eatGrassAnimationHandler));
    }

    // SHEARING

    private static final TrackedData<Boolean> SHEARED =
            DataTracker.registerData(DeerEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand)
    {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.isOf(Items.SHEARS))
        {
            if (getWorld() instanceof ServerWorld serverWorld && isShearable())
            {
                sheared(serverWorld, SoundCategory.PLAYERS, itemStack);
                emitGameEvent(GameEvent.SHEAR, player);
                itemStack.damage(1, player, getSlotForHand(hand));
                return ActionResult.SUCCESS_SERVER;
            }
            return ActionResult.CONSUME;
        }
        return super.interactMob(player, hand);
    }

    @Override
    public void sheared(ServerWorld world, SoundCategory shearedSoundCategory, ItemStack shears)
    {
        world.playSoundFromEntity(null, this, SoundEvents.ENTITY_SHEEP_SHEAR, shearedSoundCategory, 1.0f, 1.0f);
        forEachShearedItem(
                world,
                ModLootTables.DEER_SHEARING,
                shears,
                (serverWorld, itemStack) -> {
                    for (int i = 0; i < itemStack.getCount(); i++)
                    {
                        ItemEntity itemEntity = dropStack(serverWorld, itemStack.copyWithCount(1), 1.0f);
                        if (itemEntity == null) continue;

                        itemEntity.setVelocity(itemEntity.getVelocity().add(
                                    (random.nextFloat() - random.nextFloat()) * 0.1f,
                                    random.nextFloat() * 0.5f,
                                    (random.nextFloat() - random.nextFloat()) * 0.1f
                                )
                        );
                    }
                }
        );
        setSheared(true);
    }

    @Override
    public boolean isShearable()
    {
        return isAlive() && !isBaby() && !isSheared();
    }

    public boolean isSheared()
    {
        return dataTracker.get(SHEARED);
    }

    public void setSheared(boolean sheared)
    {
        dataTracker.set(SHEARED, sheared);
    }

    // EATING GRASS

    private EatGrassGoal eatGrassGoal;
    private int eatGrassTimer = 0;

    @Override
    protected void mobTick(ServerWorld world)
    {
        eatGrassTimer = eatGrassGoal.getTimer();
        super.mobTick(world);
    }

    @Override
    public void tickMovement()
    {
        if (getWorld().isClient())
            eatGrassTimer = Math.max(0, eatGrassTimer - 1);

        super.tickMovement();
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

    private PlayState eatGrassAnimationHandler(AnimationState<DeerEntity> animationState)
    {
        if (eatGrassTimer > 0 && animationState.getController().getAnimationState().equals(AnimationController.State.STOPPED))
            animationState.setAnimation(RawAnimation.begin().thenPlay("misc.eat_grass"));

        animationState.getController().forceAnimationReset();
        return PlayState.CONTINUE;
    }
}
