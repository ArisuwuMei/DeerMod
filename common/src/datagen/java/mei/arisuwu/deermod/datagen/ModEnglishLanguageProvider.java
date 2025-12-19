package mei.arisuwu.deermod.datagen;

import com.ibm.icu.lang.UCharacter;
import com.ibm.icu.text.BreakIterator;
import mei.arisuwu.deermod.*;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.DyeColor;

import java.util.Locale;
import java.util.concurrent.CompletableFuture;

public class ModEnglishLanguageProvider extends ModLanguageProviderBase
{
    protected ModEnglishLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup)
    {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder)
    {
        translationBuilder.add(ModItems.ANTLERS.get(), "Antlers");
        translationBuilder.add(ModItems.COOKED_VENISON.get(), "Cooked Venison");
        translationBuilder.add(ModItems.DEER_BANNER_PATTERN.get(), "Banner Pattern");
        translationBuilder.add(ModItems.DEER_BANNER_PATTERN.get().getDescriptionId() + ".desc", "Deer");
        translationBuilder.add(ModItems.DEER_CRACKERS.get(), "Deer Crackers");
        translationBuilder.add(ModItems.DEER_CRACKERS_ON_A_STICK.get(), "Deer Crackers on a Stick");
        translationBuilder.add(ModItems.DEER_SPAWN_EGG.get(), "Deer Spawn Egg");
        translationBuilder.add(ModItems.VENISON.get(), "Venison");

        translationBuilder.add(ModTags.DEER_FOOD, "Deer Food");

        translationBuilder.add(ModEntities.DEER.get(), "Deer");

        translateBanner(ModBannerPatterns.DEER, color -> color + " Deer", registryLookup, translationBuilder);

        translatePaintingVariant(ModPaintingVariants.LUVDEER, "LUVDEER", "Arisuwu Mei", translationBuilder);
    }

    @Override
    protected String translateDyeColor(DyeColor dyeColor)
    {
        return UCharacter.toTitleCase(
            dyeColor.getName().replace('_', ' '),
            BreakIterator.getWordInstance(Locale.ENGLISH)
        );
    }
}
