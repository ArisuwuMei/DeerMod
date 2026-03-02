package mei.arisuwu.deermod.fabric;

import mei.arisuwu.deermod.ModConfig;
import mei.arisuwu.deermod.ModEntities;
import mei.arisuwu.deermod.ModItems;
import mei.arisuwu.deermod.ModTags;
import mei.arisuwu.deermod.entity.deer.DeerEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;


public final class FabricMod implements ModInitializer {
    @Override
    public void onInitialize() {
        new ModEntities();
        new ModItems();
        new FabricModCreativeTabs();
        FabricDefaultAttributeRegistry.register(ModEntities.DEER.get(), DeerEntity.createAttributes());
        addDeerEntitySpawn();
    }

    private void addDeerEntitySpawn() {
        var config = ModConfig.load(FabricLoader.getInstance().getConfigDir().resolve("deermod.json"));

        BiomeModifications.addSpawn(
            BiomeSelectors.tag(ModTags.DEERS_HABITAT_BIOMES),
            MobCategory.CREATURE, ModEntities.DEER.get(),
            config.habitatBiomesDeerSpawnSettings().spawnRate(),
            config.habitatBiomesDeerSpawnSettings().minGroupSize(),
            config.habitatBiomesDeerSpawnSettings().maxGroupSize()
        );

        BiomeModifications.addSpawn(
            BiomeSelectors.tag(ModTags.DEERS_ESCAPADE_BIOMES),
            MobCategory.CREATURE, ModEntities.DEER.get(),
            config.escapadeBiomesDeerSpawnSettings().spawnRate(),
            config.escapadeBiomesDeerSpawnSettings().minGroupSize(),
            config.escapadeBiomesDeerSpawnSettings().maxGroupSize()
        );

        SpawnPlacements.register(
            ModEntities.DEER.get(), SpawnPlacementTypes.ON_GROUND,
            Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules
        );
    }
}
