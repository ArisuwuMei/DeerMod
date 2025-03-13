package mei.arisuwu.deermod;

import mei.arisuwu.deermod.entity.deer.DeerEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import java.util.function.Supplier;

public abstract class ModEntities
{
    public static Supplier<EntityType<DeerEntity>> DEER;

    public ModEntities()
    {
        DEER = registerEntityType("deer", EntityType.Builder.of(DeerEntity::new, MobCategory.CREATURE));
    }

    public abstract <T extends Entity> Supplier<EntityType<T>> registerEntityType(String name, EntityType.Builder<T> builder);
}
