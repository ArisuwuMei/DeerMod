package mei.arisuwu.deermod.entity.deer;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;

public class DeerItemSteering
{
    private final SynchedEntityData entityData;
    private final EntityDataAccessor<Integer> boostTime;
    private boolean boosted;
    private int boostedTime;

    public DeerItemSteering(SynchedEntityData entityData, EntityDataAccessor<Integer> boostTime)
    {
        this.entityData = entityData;
        this.boostTime = boostTime;
    }

    public void boost()
    {
        boosted = true;
        boostedTime = 0;
    }

    public boolean boost(RandomSource random)
    {
        if (boosted) return false;

        boosted = true;
        boostedTime = 0;
        entityData.set(boostTime, random.nextInt(841) + 140);
        return true;
    }

    public void tickBoost()
    {
        if (boosted && boostedTime++ > getBoostTime())
            boosted = false;
    }

    public float getMovementSpeedMultiplier()
    {
        return boosted ?
            1 + 1.15f * Mth.sin((float)boostedTime / (float)getBoostTime() * (float)Math.PI) : 1;
    }

    public int getBoostTime()
    {
        return entityData.get(boostTime);
    }
}
