package mei.arisuwu.deermod;

import mei.arisuwu.deermod.entity.deer.DeerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;

import java.util.function.Supplier;

public abstract class ModEntities
{
    public static Supplier<EntityType<DeerEntity>> DEER;

    public ModEntities()
    {
        DEER = registerEntityType("deer", EntityType.Builder.create(DeerEntity::new, SpawnGroup.CREATURE));
    }

    public abstract <T extends Entity> Supplier<EntityType<T>> registerEntityType(String name, EntityType.Builder<T> builder);
}
