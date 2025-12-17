package mei.arisuwu.deermod.datagen;

import mei.arisuwu.deermod.ModItems;
import mei.arisuwu.deermod.ModResourceLocation;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.advancements.critereon.EntityFlagsPredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ModEntityLootTableProvider extends SimpleFabricLootTableProvider
{
    protected ModEntityLootTableProvider(FabricDataOutput output, @NotNull CompletableFuture<HolderLookup.Provider> registryLookup)
    {
        super(output, LootContextParamSets.ENTITY);
    }

    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> output)
    {
        output.accept(ModResourceLocation.of("deer"), new LootTable.Builder()
            .withPool(new LootPool.Builder()
                .add(LootItem.lootTableItem(ModItems.ANTLERS.get())
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(0F, 1F)))
                )
            )
            .withPool(new LootPool.Builder()
                .add(LootItem.lootTableItem(ModItems.VENISON.get())
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1F, 2F)))
                    .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(
                        LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity()
                            .flags(EntityFlagsPredicate.Builder.flags().setOnFire(true).build())
                    )))
                    .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                )
            ));
    }
}
