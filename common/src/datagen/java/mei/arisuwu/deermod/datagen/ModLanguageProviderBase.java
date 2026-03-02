package mei.arisuwu.deermod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.decoration.painting.PaintingVariant;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BannerPattern;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public abstract class ModLanguageProviderBase extends FabricLanguageProvider {
    protected ModLanguageProviderBase(FabricPackOutput output, String languageCode, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, languageCode, registriesFuture);
    }

    protected void translateBanner(ResourceKey<BannerPattern> bannerPatternKey, Function<DyeColor, String> colorTranslator, String valueFormat, HolderLookup.Provider registries, TranslationBuilder builder) {
        var bannerPattern = registries.lookupOrThrow(Registries.BANNER_PATTERN)
            .getOrThrow(bannerPatternKey).value();

        for (DyeColor dyeColor : DyeColor.values()) {
            var translationKey = String.format("%s.%s", bannerPattern.translationKey(), dyeColor.getName());
            var translationValue = String.format(valueFormat, colorTranslator.apply(dyeColor));

            builder.add(translationKey, translationValue);
        }

    }

    protected void translatePaintingVariant(ResourceKey<PaintingVariant> paintingVariantKey, String title, String author, HolderLookup.Provider registries, TranslationBuilder builder) {
        var paintingVariant = registries.lookupOrThrow(Registries.PAINTING_VARIANT)
            .getOrThrow(paintingVariantKey)
            .value();

        if (title != null) paintingVariant.title()
            .map(Component::getContents)
            .map(TranslatableContents.class::cast)
            .map(TranslatableContents::getKey)
            .ifPresent(key -> builder.add(key, title));

        if (author != null) paintingVariant.author()
            .map(Component::getContents)
            .map(TranslatableContents.class::cast)
            .map(TranslatableContents::getKey)
            .ifPresent(key -> builder.add(key, author));
    }
}
