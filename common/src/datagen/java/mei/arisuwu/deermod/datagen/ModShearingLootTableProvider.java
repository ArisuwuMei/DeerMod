package mei.arisuwu.deermod.datagen;

import mei.arisuwu.deermod.ModItems;
import mei.arisuwu.deermod.ModLootTables;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ModShearingLootTableProvider// extends SimpleFabricLootTableProvider
{
//    public ModShearingLootTableProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup)
//    {
//        super(output, registryLookup, LootContextParamSets.SHEARING);
//    }
//
//    @Override
//    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output)
//    {
//        output.accept(ModLootTables.DEER_SHEARING, new LootTable.Builder()
//            .withPool(new LootPool.Builder().add(LootItem.lootTableItem(ModItems.ANTLERS.get())))
//            .setParamSet(LootContextParamSets.SHEARING)
//        );
//    }
}
