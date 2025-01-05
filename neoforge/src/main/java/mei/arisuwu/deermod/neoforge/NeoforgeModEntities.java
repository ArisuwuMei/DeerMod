package mei.arisuwu.deermod.neoforge;

import mei.arisuwu.deermod.Mod;
import mei.arisuwu.deermod.ModEntities;
import mei.arisuwu.deermod.ModIdentifier;
import mei.arisuwu.deermod.entity.deer.DeerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class NeoforgeModEntities extends ModEntities
{
    private static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
        DeferredRegister.create(RegistryKeys.ENTITY_TYPE, Mod.MOD_ID);

    public NeoforgeModEntities(IEventBus eventBus)
    {
        ENTITY_TYPES.register(eventBus);
    }

    @Override
    public <T extends Entity> Supplier<EntityType<T>> registerEntityType(String name, EntityType.Builder<T> builder)
    {
        var e = ENTITY_TYPES.register(
            name,
            () -> EntityType.Builder
                .create(DeerEntity::new, SpawnGroup.CREATURE)
                .build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, ModIdentifier.of(name)))
        );

        return () -> (EntityType<T>) e.get();
    }
}
