package mei.arisuwu.deermod;

import mei.arisuwu.deermod.entity.DeerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class ModEntities
{
    public static final EntityType<DeerEntity> DEER = registerEntity("deer", EntityType.Builder.create(DeerEntity::new, SpawnGroup.CREATURE));

    private static <T extends Entity> EntityType<T> registerEntity(String path, EntityType.Builder<T> entityBuilder)
    {
        return Registry.register(
                Registries.ENTITY_TYPE,
                ModIdentifier.of(path),
                entityBuilder.build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, ModIdentifier.of(path)))
        );
    }
}
