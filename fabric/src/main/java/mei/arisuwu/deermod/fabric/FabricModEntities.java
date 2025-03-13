package mei.arisuwu.deermod.fabric;

import mei.arisuwu.deermod.ModEntities;
import mei.arisuwu.deermod.ModIdentifier;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import java.util.function.Supplier;

public class FabricModEntities extends ModEntities
{
    @Override
    public <T extends Entity> Supplier<EntityType<T>> registerEntityType(String name, EntityType.Builder<T> builder)
    {
        var entityType = Registry.register(
            BuiltInRegistries.ENTITY_TYPE, ModIdentifier.of(name),
            builder.build(ResourceKey.create(Registries.ENTITY_TYPE, ModIdentifier.of(name)))
        );
        return () -> entityType;
    }
}
