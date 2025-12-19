package mei.arisuwu.deermod;

import mei.arisuwu.deermod.entity.deer.DeerEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import java.util.function.Supplier;

public class ModEntities
{
    public static Supplier<EntityType<DeerEntity>> DEER;

    public ModEntities()
    {
        DEER = registerEntityType("deer", EntityType.Builder.of(DeerEntity::new, MobCategory.CREATURE));
    }

    public <T extends Entity> Supplier<EntityType<T>> registerEntityType(String name, EntityType.Builder<T> builder)
    {
        var entityType = Registry.register(
            BuiltInRegistries.ENTITY_TYPE, ModResourceLocation.of(name),
            builder.build(name)
        );
        return () -> entityType;
    }
}
