package mei.arisuwu.deermod.datagen;

import com.ibm.icu.lang.UCharacter;
import com.ibm.icu.text.BreakIterator;
import mei.arisuwu.deermod.*;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.DyeColor;
import org.jspecify.annotations.NonNull;

import java.util.Locale;
import java.util.concurrent.CompletableFuture;

public class ModEnglishLanguageProvider extends ModLanguageProviderBase
{
    protected ModEnglishLanguageProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture)
    {
        super(output, "en_us", registriesFuture);
    }

    @Override
    public void generateTranslations(HolderLookup.@NonNull Provider registries, TranslationBuilder builder)
    {
        builder.add(ModItems.ANTLERS.get(), "Antlers");
        builder.add(ModItems.COOKED_VENISON.get(), "Cooked Venison");
        builder.add(ModItems.DEER_BANNER_PATTERN.get(), "Deer Banner Pattern");
        builder.add(ModItems.DEER_CRACKERS.get(), "Deer Crackers");
        builder.add(ModItems.DEER_CRACKERS_ON_A_STICK.get(), "Deer Crackers on a Stick");
        builder.add(ModItems.DEER_SPAWN_EGG.get(), "Deer Spawn Egg");
        builder.add(ModItems.VENISON.get(), "Venison");

        builder.add(ModTags.DEER_FOOD, "Deer Food");

        builder.add(ModEntities.DEER.get(), "Deer");

        translateBanner(ModBannerPatterns.DEER, color -> color + " Deer", registries, builder);

        translatePaintingVariant(ModPaintingVariants.LUVDEER, "LUVDEER", "Arisuwu Mei", registries, builder);
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
