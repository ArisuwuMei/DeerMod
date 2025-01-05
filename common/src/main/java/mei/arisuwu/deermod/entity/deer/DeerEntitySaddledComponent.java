package mei.arisuwu.deermod.entity.deer;

import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;

public class DeerEntitySaddledComponent
{
    private final DataTracker dataTracker;
    private final TrackedData<Integer> boostTime;
    private boolean boosted;
    private int boostedTime;

    public DeerEntitySaddledComponent(DataTracker dataTracker, TrackedData<Integer> boostTime)
    {
        this.dataTracker = dataTracker;
        this.boostTime = boostTime;
    }

    public void boost()
    {
        boosted = true;
        boostedTime = 0;
    }

    public boolean boost(Random random)
    {
        if (boosted) return false;

        boosted = true;
        boostedTime = 0;
        dataTracker.set(boostTime, random.nextInt(841) + 140);
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
            1 + 1.15f * MathHelper.sin((float)boostedTime / (float)getBoostTime() * (float)Math.PI) : 1;
    }

    public int getBoostTime()
    {
        return dataTracker.get(boostTime);
    }
}
