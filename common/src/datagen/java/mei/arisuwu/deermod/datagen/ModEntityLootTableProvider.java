package mei.arisuwu.deermod.datagen;

import mei.arisuwu.deermod.ModEntities;
import mei.arisuwu.deermod.ModItems;
import mei.arisuwu.deermod.ModLootTables;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricEntityLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModEntityLootTableProvider extends FabricEntityLootSubProvider
{
    protected ModEntityLootTableProvider(FabricPackOutput output, @NotNull CompletableFuture<HolderLookup.Provider> registriesFuture)
    {
        super(output, registriesFuture);
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
