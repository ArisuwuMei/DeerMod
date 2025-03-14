package mei.arisuwu.deermod.fabric;

import mei.arisuwu.deermod.ModEntities;
import mei.arisuwu.deermod.ModItems;
import mei.arisuwu.deermod.ModTags;
import mei.arisuwu.deermod.entity.deer.DeerEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;


public final class FabricMod implements ModInitializer
{
    @Override
    public void onInitialize()
    {
        new ModEntities();
        new ModItems();
        FabricDefaultAttributeRegistry.register(ModEntities.DEER.get(), DeerEntity.createAttributes());
        addDeerEntitySpawn();
    }

    private void addDeerEntitySpawn()
    {
        BiomeModifications.addSpawn(
            BiomeSelectors.tag(ModTags.SPAWNS_DEERS),
            MobCategory.CREATURE, ModEntities.DEER.get(), 50, 2, 6
        );

        SpawnPlacements.register(
            ModEntities.DEER.get(), SpawnPlacementTypes.ON_GROUND,
            Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules
        );
    }
}
