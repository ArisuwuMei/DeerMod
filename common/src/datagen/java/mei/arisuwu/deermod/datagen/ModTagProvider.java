package mei.arisuwu.deermod.datagen;

import mei.arisuwu.deermod.*;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraft.world.entity.decoration.painting.PaintingVariant;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.entity.BannerPattern;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModTagProvider
{
    public static class ModItemTagProvider extends FabricTagsProvider.ItemTagsProvider
    {
        public ModItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture)
        {
            super(output, registriesFuture);
        }

        @Override
        protected void addTags(HolderLookup.@NonNull Provider registries)
        {
            valueLookupBuilder(ModTags.DEER_FOOD)
                .add(Items.WHEAT)
                .add(Items.SWEET_BERRIES)
                .add(Items.CARROT)
                .add(ModItems.DEER_CRACKERS.get());

            valueLookupBuilder(ItemTags.MEAT)
                .add(ModItems.COOKED_VENISON.get())
                .add(ModItems.VENISON.get());

            valueLookupBuilder(ItemTags.LOOM_PATTERNS)
                .add(ModItems.DEER_BANNER_PATTERN.get());
        }
    }

    public static class ModEntityTypeTagProvider extends FabricTagsProvider.EntityTypeTagsProvider
    {
        public ModEntityTypeTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture)
        {
            super(output, registriesFuture);
        }

        @Override
        protected void addTags(HolderLookup.@NonNull Provider registries)
        {
            valueLookupBuilder(EntityTypeTags.POWDER_SNOW_WALKABLE_MOBS)
                .add(ModEntities.DEER.get());

            valueLookupBuilder(EntityTypeTags.CAN_EQUIP_SADDLE)
                .add(ModEntities.DEER.get());
        }
    }

    public static class ModBiomeTagProvider extends FabricTagsProvider<Biome>
    {
        protected ModBiomeTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture)
        {
            super(output, Registries.BIOME, registriesFuture);
        }

        @Override
        protected void addTags(HolderLookup.@NonNull Provider registries)
        {
            builder(ModTags.DEERS_ESCAPADE_BIOMES)
                .add(Biomes.PLAINS)
                .add(Biomes.SUNFLOWER_PLAINS)
                .add(Biomes.CHERRY_GROVE)
                .add(Biomes.SAVANNA)
                .add(Biomes.SAVANNA_PLATEAU);

            builder(ModTags.DEERS_HABITAT_BIOMES)
                .add(Biomes.CHERRY_GROVE)
                .forceAddTag(BiomeTags.IS_TAIGA)
                .forceAddTag(BiomeTags.IS_FOREST)
                .forceAddTag(BiomeTags.IS_JUNGLE);
        }
    }

    public static class ModPaintingVariantTagsProvider extends FabricTagsProvider<PaintingVariant>
    {
        public ModPaintingVariantTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture)
        {
            super(output, Registries.PAINTING_VARIANT, registriesFuture);
        }

        @Override
        protected void addTags(HolderLookup.@NonNull Provider registries)
        {
            builder(PaintingVariantTags.PLACEABLE)
                .add(ModPaintingVariants.LUVDEER);
        }
    }

    public static class ModBannerPatternTagsProvider extends FabricTagsProvider<BannerPattern>
    {
        public ModBannerPatternTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture)
        {
            super(output, Registries.BANNER_PATTERN, registriesFuture);
        }

        @Override
        protected void addTags(HolderLookup.@NonNull Provider registries)
        {
            builder(ModTags.DEER_PATTERN_ITEM)
                .add(ModBannerPatterns.DEER);
        }
    }
}
