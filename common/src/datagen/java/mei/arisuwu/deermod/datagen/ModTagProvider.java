package mei.arisuwu.deermod.datagen;

import mei.arisuwu.deermod.*;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.entity.BannerPattern;

import java.util.concurrent.CompletableFuture;

public class ModTagProvider
{
    public static class ModItemTagProvider extends FabricTagProvider.ItemTagProvider
    {
        public ModItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture)
        {
            super(output, registriesFuture);
        }

        @Override
        protected void addTags(HolderLookup.Provider wrapperLookup)
        {
            getOrCreateTagBuilder(ModTags.DEER_FOOD)
                .add(Items.WHEAT)
                .add(Items.SWEET_BERRIES)
                .add(Items.CARROT)
                .add(ModItems.DEER_CRACKERS.get());

            getOrCreateTagBuilder(ItemTags.MEAT)
                .add(ModItems.COOKED_VENISON.get())
                .add(ModItems.VENISON.get());
        }
    }

    public static class ModEntityTypeTagProvider extends FabricTagProvider.EntityTypeTagProvider
    {
        public ModEntityTypeTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture)
        {
            super(output, registriesFuture);
        }

        @Override
        protected void addTags(HolderLookup.Provider wrapperLookup)
        {
            getOrCreateTagBuilder(EntityTypeTags.CAN_EQUIP_SADDLE)
                .add(ModEntities.DEER.get());
        }
    }

    public static class ModBiomeTagProvider extends FabricTagProvider<Biome>
    {
        protected ModBiomeTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture)
        {
            super(output, Registries.BIOME, registriesFuture);
        }

        @Override
        protected void addTags(HolderLookup.Provider wrapperLookup)
        {
            getOrCreateTagBuilder(ModTags.DEERS_ESCAPADE_BIOMES)
                .add(Biomes.PLAINS)
                .add(Biomes.SUNFLOWER_PLAINS)
                .add(Biomes.SAVANNA)
                .add(Biomes.SAVANNA_PLATEAU);

            getOrCreateTagBuilder(ModTags.DEERS_HABITAT_BIOMES)
                .add(Biomes.CHERRY_GROVE)
                .forceAddTag(BiomeTags.IS_TAIGA)
                .forceAddTag(BiomeTags.IS_FOREST)
                .forceAddTag(BiomeTags.IS_JUNGLE);
        }
    }

    public static class ModPaintingVariantTagsProvider extends FabricTagProvider<PaintingVariant>
    {
        public ModPaintingVariantTagsProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture)
        {
            super(output, Registries.PAINTING_VARIANT, registriesFuture);
        }

        @Override
        protected void addTags(HolderLookup.Provider wrapperLookup)
        {
            getOrCreateTagBuilder(PaintingVariantTags.PLACEABLE)
                .add(ModPaintingVariants.LUVDEER);
        }
    }

    public static class ModBannerPatternTagsProvider extends FabricTagProvider<BannerPattern>
    {
        public ModBannerPatternTagsProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture)
        {
            super(output, Registries.BANNER_PATTERN, registriesFuture);
        }

        @Override
        protected void addTags(HolderLookup.Provider wrapperLookup)
        {
            getOrCreateTagBuilder(ModTags.DEER_PATTERN_ITEM)
                .add(ModBannerPatterns.DEER);
        }
    }
}
