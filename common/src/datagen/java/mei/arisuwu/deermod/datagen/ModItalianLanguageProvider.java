package mei.arisuwu.deermod.datagen;

import mei.arisuwu.deermod.*;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.DyeColor;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ModItalianLanguageProvider extends ModLanguageProviderBase
{
    protected ModItalianLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup)
    {
        super(dataOutput,"it_it", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder)
    {
        translationBuilder.add(ModItems.ANTLERS.get(), "Corna");
        translationBuilder.add(ModItems.COOKED_VENISON.get(), "Carne di cervo cotta");
        translationBuilder.add(ModItems.DEER_BANNER_PATTERN.get(), "Motivo per stendardo");
        translationBuilder.add(ModItems.DEER_BANNER_PATTERN.get().getDescriptionId() + ".desc", "Cervo");
        translationBuilder.add(ModItems.DEER_CRACKERS.get(), "Biscotto per cervi");
        translationBuilder.add(ModItems.DEER_CRACKERS_ON_A_STICK.get(), "Bastone e biscotto per cervi");
        translationBuilder.add(ModItems.DEER_SPAWN_EGG.get(), "Uovo generatore di cervo");
        translationBuilder.add(ModItems.VENISON.get(), "Carne di cervo cruda");

        translationBuilder.add(ModTags.DEER_FOOD, "Cibo per cervi");

        translationBuilder.add(ModEntities.DEER.get(), "Cervo");

        translatePaintingVariant(ModPaintingVariants.LUVDEER, "LUVDEER", "Arisuwu Mei", translationBuilder);

        var map = Map.ofEntries(
            Map.entry(DyeColor.BLACK, "nero"),
            Map.entry(DyeColor.BLUE, "blu"),
            Map.entry(DyeColor.BROWN, "marrone"),
            Map.entry(DyeColor.CYAN, "ciano"),
            Map.entry(DyeColor.GRAY, "grigio"),
            Map.entry(DyeColor.GREEN, "verde"),
            Map.entry(DyeColor.LIGHT_BLUE, "azzurro"),
            Map.entry(DyeColor.LIGHT_GRAY, "grigio chiaro"),
            Map.entry(DyeColor.LIME, "lime"),
            Map.entry(DyeColor.MAGENTA, "magenta"),
            Map.entry(DyeColor.ORANGE, "arancione"),
            Map.entry(DyeColor.PINK, "rosa"),
            Map.entry(DyeColor.PURPLE, "viola"),
            Map.entry(DyeColor.RED, "rosso"),
            Map.entry(DyeColor.WHITE, "bianco"),
            Map.entry(DyeColor.YELLOW, "giallo")
        );

        translateBanner(ModBannerPatterns.DEER, color -> "Cervo " + color, registryLookup, translationBuilder);
    }

    @Override
    protected String translateDyeColor(DyeColor dyeColor)
    {
        return switch (dyeColor) {
            case BLACK -> "nero";
            case BLUE -> "blu";
            case BROWN -> "marrone";
            case CYAN -> "ciano";
            case GRAY -> "grigio";
            case GREEN -> "verde";
            case LIGHT_BLUE -> "azzurro";
            case LIGHT_GRAY -> "grigio chiaro";
            case LIME -> "lime";
            case MAGENTA -> "magenta";
            case ORANGE -> "arancione";
            case PINK -> "rosa";
            case PURPLE -> "viola";
            case RED -> "rosso";
            case WHITE -> "bianco";
            case YELLOW -> "giallo";
        };
    }
}
