package mei.arisuwu.deermod.datagen;

import mei.arisuwu.deermod.ModItems;
import mei.arisuwu.deermod.ModLootTables;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.AnyOfCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;

public class ModEntityLootTableProvider extends SimpleFabricLootTableProvider
{
    private final CompletableFuture<HolderLookup.Provider> registryLookup;

    protected ModEntityLootTableProvider(FabricDataOutput output, @NotNull CompletableFuture<HolderLookup.Provider> registryLookup)
    {
        super(output, registryLookup, LootContextParamSets.ENTITY);
        this.registryLookup = registryLookup;
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output)
    {
        try
        {
            var r = registryLookup.get();
            output.accept(ModLootTables.DEER, new LootTable.Builder()
                .setParamSet(lootContextType)
                .withPool(new LootPool.Builder()
                    .add(LootItem.lootTableItem(ModItems.ANTLERS.get())
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0F, 1F)))
                    )
                )
                .withPool(new LootPool.Builder()
                    .add(LootItem.lootTableItem(ModItems.VENISON.get())
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1F, 2F)))
//                        .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(
//                            LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity()
//                                .flags(EntityFlagsPredicate.Builder.flags().setOnFire(true))
//                        )))
                        .apply(SmeltItemFunction.smelted().when(shouldSmeltLoot(r)))
                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(r, UniformGenerator.between(0.0F, 1.0F)))
                    )
                )
            );
        }
        catch (InterruptedException | ExecutionException e)
        {
            throw new RuntimeException(e);
        }
    }

    protected final AnyOfCondition.Builder shouldSmeltLoot(HolderLookup.Provider registries) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = registries.lookupOrThrow(Registries.ENCHANTMENT);
        return AnyOfCondition.anyOf(
            LootItemEntityPropertyCondition.hasProperties(
                LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true))
            ),
            LootItemEntityPropertyCondition.hasProperties(
                LootContext.EntityTarget.DIRECT_ATTACKER,
                EntityPredicate.Builder.entity()
                    .equipment(
                        EntityEquipmentPredicate.Builder.equipment()
                            .mainhand(
                                ItemPredicate.Builder.item()
                                    .withSubPredicate(
                                        ItemSubPredicates.ENCHANTMENTS,
                                        ItemEnchantmentsPredicate.enchantments(
                                            List.of(new EnchantmentPredicate(registrylookup.getOrThrow(EnchantmentTags.SMELTS_LOOT), MinMaxBounds.Ints.ANY))
                                        )
                                    )
                            )
                    )
            )
        );
    }
}
