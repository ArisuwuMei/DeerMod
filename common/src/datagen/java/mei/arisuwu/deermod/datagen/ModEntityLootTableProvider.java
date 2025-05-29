package mei.arisuwu.deermod.datagen;

import mei.arisuwu.deermod.ModEntities;
import mei.arisuwu.deermod.ModItems;
import mei.arisuwu.deermod.ModLootTables;
import mei.arisuwu.deermod.ModResourceLocation;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricEntityLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModEntityLootTableProvider extends FabricEntityLootTableProvider
{
    protected ModEntityLootTableProvider(FabricDataOutput output, @NotNull CompletableFuture<HolderLookup.Provider> registryLookup)
    {
        super(output, registryLookup);
    }

    @Override
    public void generate()
    {
        add(ModEntities.DEER.get(), ModLootTables.DEER, new LootTable.Builder()
            .withPool(new LootPool.Builder()
                .add(LootItem.lootTableItem(ModItems.ANTLERS.get())
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(0F, 1F)))
                )
            )
            .withPool(new LootPool.Builder()
                .add(LootItem.lootTableItem(ModItems.VENISON.get())
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1F, 2F)))
                    .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
                )
            )
        );
    }
}
