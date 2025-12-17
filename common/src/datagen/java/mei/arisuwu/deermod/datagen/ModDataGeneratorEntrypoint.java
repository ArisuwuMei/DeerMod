package mei.arisuwu.deermod.datagen;

import com.mojang.serialization.Lifecycle;
import mei.arisuwu.deermod.ModBannerPatterns;
import mei.arisuwu.deermod.ModPaintingVariants;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;


public class ModDataGeneratorEntrypoint implements DataGeneratorEntrypoint
{
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator)
    {
        var pack = fabricDataGenerator.createPack();

        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModRecipeProvider::new);

        pack.addProvider(ModEntityLootTableProvider::new);
        //pack.addProvider(ModShearingLootTableProvider::new);

        pack.addProvider(ModEnglishLanguageProvider::new);
        pack.addProvider(ModItalianLanguageProvider::new);

        pack.addProvider(ModTagProvider.ModItemTagProvider::new);
        pack.addProvider(ModTagProvider.ModEntityTypeTagProvider::new);
        pack.addProvider(ModTagProvider.ModBiomeTagProvider::new);
        pack.addProvider(ModTagProvider.ModPaintingVariantTagsProvider::new);
        pack.addProvider(ModTagProvider.ModBannerPatternTagsProvider::new);
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder)
    {
        //registryBuilder.add(Registries.PAINTING_VARIANT, Lifecycle.stable(), ModPaintingVariants::bootstrap);
        //registryBuilder.add(Registries.BANNER_PATTERN, ModBannerPatterns::bootstrap);
    }
}
