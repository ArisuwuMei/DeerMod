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
    protected ModEnglishLanguageProvider(FabricDataOutput dataOutput)
    {
        super(dataOutput, "en_us");
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder)
    {
        translationBuilder.add(ModItems.ANTLERS.get(), "Antlers");
        translationBuilder.add(ModItems.COOKED_VENISON.get(), "Cooked Venison");
        translationBuilder.add(ModItems.DEER_BANNER_PATTERN.get(), "Deer Banner Pattern");
        translationBuilder.add(ModItems.DEER_CRACKERS.get(), "Deer Crackers");
        translationBuilder.add(ModItems.DEER_CRACKERS_ON_A_STICK.get(), "Deer Crackers on a Stick");
        translationBuilder.add(ModItems.DEER_SPAWN_EGG.get(), "Deer Spawn Egg");
        translationBuilder.add(ModItems.VENISON.get(), "Venison");

        translationBuilder.add(ModEntities.DEER.get(), "Deer");

        translateBanner(translationBuilder, ModBannerPatterns.DEER, this::translateDyeColor, "%s Deer");

        translationBuilder.add("painting.deermod.luvdeer.author", "Arisuwu Mei");
        translationBuilder.add("painting.deermod.luvdeer.title", "LUVDEER");
    }

    protected String translateDyeColor(DyeColor dyeColor)
    {
        return UCharacter.toTitleCase(
            dyeColor.getName().replace('_', ' '),
            BreakIterator.getWordInstance(Locale.ENGLISH)
        );
    }
}
