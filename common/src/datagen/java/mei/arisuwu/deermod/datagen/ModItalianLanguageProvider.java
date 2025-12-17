package mei.arisuwu.deermod.datagen;

import mei.arisuwu.deermod.ModBannerPatterns;
import mei.arisuwu.deermod.ModEntities;
import mei.arisuwu.deermod.ModItems;
import mei.arisuwu.deermod.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.DyeColor;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class ModItalianLanguageProvider extends ModLanguageProviderBase
{
    protected ModItalianLanguageProvider(FabricDataOutput dataOutput)
    {
        super(dataOutput,"it_it");
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder)
    {
        translationBuilder.add(ModItems.ANTLERS.get(), "Corna");
        translationBuilder.add(ModItems.COOKED_VENISON.get(), "Carne di cervo cotta");
        translationBuilder.add(ModItems.DEER_BANNER_PATTERN.get(), "Motivo con cervo");
        translationBuilder.add(ModItems.DEER_CRACKERS.get(), "Biscotto per cervi");
        translationBuilder.add(ModItems.DEER_CRACKERS_ON_A_STICK.get(), "Bastone e biscotto per cervi");
        translationBuilder.add(ModItems.DEER_SPAWN_EGG.get(), "Uovo generatore di cervo");
        translationBuilder.add(ModItems.VENISON.get(), "Carne di cervo cruda");

        translationBuilder.add(ModEntities.DEER.get(), "Cervo");

        translationBuilder.add("painting.deermod.luvdeer.author", "Arisuwu Mei");
        translationBuilder.add("painting.deermod.luvdeer.title", "LUVDEER");

        translateBanner(translationBuilder, ModBannerPatterns.DEER, this::translateMasculineDyeColor, "Cervo %s");
    }

    protected String translateMasculineDyeColor(DyeColor dyeColor)
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
