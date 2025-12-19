package mei.arisuwu.deermod.datagen;

import com.ibm.icu.lang.UCharacter;
import com.ibm.icu.text.BreakIterator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BannerPattern;
import org.apache.commons.lang3.NotImplementedException;

import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Function;

public abstract class ModLanguageProviderBase extends FabricLanguageProvider
{
    protected ModLanguageProviderBase(FabricDataOutput dataOutput, String languageCode, CompletableFuture<HolderLookup.Provider> registryLookup)
    {
        super(dataOutput, languageCode, registryLookup);
    }

    protected void translateBanner(ResourceKey<BannerPattern> bannerPatternKey, Function<String, String> combineColor, HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder)
    {
        var bannerPattern = registryLookup.lookupOrThrow(Registries.BANNER_PATTERN)
            .getOrThrow(bannerPatternKey).value();

        for (DyeColor dyeColor : DyeColor.values())
        {
            var translationKey = String.format("%s.%s", bannerPattern.translationKey(), dyeColor.getName());
            var translationValue = combineColor.apply(translateDyeColor(dyeColor));

            translationBuilder.add(translationKey, translationValue);
        }

    }

    protected void translatePaintingVariant(ResourceKey<PaintingVariant> paintingVariantKey, String title, String author, TranslationBuilder translationBuilder)
    {
        translationBuilder.add(paintingVariantKey.location().toLanguageKey("painting", "author"), author);
        translationBuilder.add(paintingVariantKey.location().toLanguageKey("painting", "title"), title);
    }

    protected String translateDyeColor(DyeColor dyeColor)
    {
        throw new NotImplementedException();
    }
}
