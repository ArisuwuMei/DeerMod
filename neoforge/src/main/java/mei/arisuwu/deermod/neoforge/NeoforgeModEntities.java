package mei.arisuwu.deermod.neoforge;

import mei.arisuwu.deermod.Mod;
import mei.arisuwu.deermod.ModEntities;
import mei.arisuwu.deermod.ModResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class NeoforgeModEntities extends ModEntities
{
    private static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, Mod.MOD_ID);

    public NeoforgeModEntities(IEventBus eventBus)
    {
        ENTITY_TYPES.register(eventBus);
    }

    @Override
    public <T extends Entity> Supplier<EntityType<T>> registerEntityType(String name, EntityType.Builder<T> builder)
    {
        return ENTITY_TYPES.register(name, () -> builder.build(ResourceKey.create(Registries.ENTITY_TYPE, ModResourceLocation.of(name))));
    }
}
