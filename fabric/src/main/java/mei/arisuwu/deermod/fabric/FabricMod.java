package mei.arisuwu.deermod.fabric;

import mei.arisuwu.deermod.ModBannerPatterns;
import mei.arisuwu.deermod.ModEntities;
import mei.arisuwu.deermod.ModTags;
import mei.arisuwu.deermod.entity.deer.DeerEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.Heightmap;


public final class FabricMod implements ModInitializer
{
    @Override
    public void onInitialize()
    {
        new FabricModEntities();
        new FabricModItems();
        new ModBannerPatterns();
        FabricDefaultAttributeRegistry.register(ModEntities.DEER.get(), DeerEntity.createAttributes());
        addDeerEntitySpawn();
    }

    private void addDeerEntitySpawn()
    {
        BiomeModifications.addSpawn(
            BiomeSelectors.tag(ModTags.SPAWNS_DEERS),
            SpawnGroup.CREATURE, ModEntities.DEER.get(), 50, 2, 6
        );

        SpawnRestriction.register(
            ModEntities.DEER.get(), SpawnLocationTypes.ON_GROUND,
            Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn
        );
    }
}
