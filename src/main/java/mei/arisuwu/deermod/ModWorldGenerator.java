package mei.arisuwu.deermod;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.Heightmap;

public class ModWorldGenerator
{
    public static void init()
    {
        addDeerEntitySpawn();
    }

    private static void addDeerEntitySpawn()
    {
        BiomeModifications.addSpawn(
                BiomeSelectors.tag(ModTags.SPAWNS_DEERS),
                SpawnGroup.CREATURE, ModEntities.DEER, 50, 2, 6
        );

        SpawnRestriction.register(
                ModEntities.DEER, SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn
        );
    }
}
