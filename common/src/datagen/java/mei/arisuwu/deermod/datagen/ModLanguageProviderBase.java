package mei.arisuwu.deermod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BannerPattern;

import java.util.function.Function;

public abstract class ModLanguageProviderBase extends FabricLanguageProvider
{
    protected ModLanguageProviderBase(FabricDataOutput dataOutput, String languageCode)
    {
        super(dataOutput, languageCode);
    }

    protected void translateBanner(TranslationBuilder translationBuilder, ResourceKey<BannerPattern> bannerPatternKey, Function<DyeColor, String> colorTranslator, String format)
    {
        for (DyeColor dyeColor : DyeColor.values())
        {
            var translationKey = String.format(
                "block.minecraft.banner.%s.%s.%s",
                bannerPatternKey.location().getNamespace(),
                bannerPatternKey.location().getPath(),
                dyeColor.getName()
            );
            var translationValue = String.format(format, colorTranslator.apply(dyeColor));

            translationBuilder.add(translationKey, translationValue);
        }
    }
}
