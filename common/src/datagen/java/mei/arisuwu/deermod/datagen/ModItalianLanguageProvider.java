package mei.arisuwu.deermod.datagen;

import mei.arisuwu.deermod.ModBannerPatterns;
import mei.arisuwu.deermod.ModEntities;
import mei.arisuwu.deermod.ModItems;
import mei.arisuwu.deermod.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.DyeColor;
import org.jspecify.annotations.NonNull;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ModItalianLanguageProvider extends ModLanguageProviderBase {
    protected ModItalianLanguageProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, "it_it", registriesFuture);
    }

    @Override
    public void generateTranslations(HolderLookup.@NonNull Provider registries, TranslationBuilder builder) {
        builder.add(ModItems.ANTLERS.get(), "Corna");
        builder.add(ModItems.COOKED_VENISON.get(), "Carne di cervo cotta");
        builder.add(ModItems.DEER_BANNER_PATTERN.get(), "Motivo con cervo");
        builder.add(ModItems.DEER_CRACKERS.get(), "Biscotto per cervi");
        builder.add(ModItems.DEER_CRACKERS_ON_A_STICK.get(), "Bastone e biscotto per cervi");
        builder.add(ModItems.DEER_SPAWN_EGG.get(), "Uovo generatore di cervo");
        builder.add(ModItems.VENISON.get(), "Carne di cervo cruda");

        builder.add(ModTags.DEER_FOOD, "Cibo per cervi");

        builder.add(ModEntities.DEER.get(), "Cervo");

        builder.add("painting.deermod.luvdeer.author", "Arisuwu Mei");
        builder.add("painting.deermod.luvdeer.title", "LUVDEER");

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

        translateBanner(ModBannerPatterns.DEER, this::translateDyeColor, "Cervo %s", registries, builder);
    }

    @Override
    protected String translateDyeColor(DyeColor dyeColor) {
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
