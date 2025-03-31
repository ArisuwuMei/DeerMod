package mei.arisuwu.deermod.fabric;

import mei.arisuwu.deermod.ModEntities;
import mei.arisuwu.deermod.ModItems;
import mei.arisuwu.deermod.ModTags;
import mei.arisuwu.deermod.entity.deer.DeerEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
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
        addItemsToCreativeTabs();
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

    public void addItemsToCreativeTabs()
    {
        ModItems.MOD_ITEM_GROUP_ENTRIES.forEach((tab, newEntries) -> {
            ItemGroupEvents.modifyEntriesEvent(tab).register(entries -> {
                newEntries.forEach(newEntry -> {
                    switch (newEntry.position)
                    {
                        case HEAD -> newEntry.newItems.reversed().forEach(newItem -> entries.prepend(newItem.get()));
                        case BEFORE -> entries.addBefore(newEntry.existingItem, newEntry.getNewItemStacks());
                        case AFTER -> entries.addAfter(newEntry.existingItem, newEntry.getNewItemStacks());
                        case TAIL -> entries.acceptAll(newEntry.getNewItemStacks());
                    }
                });
            });
        });
    }
}
