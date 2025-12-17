package mei.arisuwu.deermod.datagen;

import mei.arisuwu.deermod.ModBannerPatterns;
import mei.arisuwu.deermod.ModEntities;
import mei.arisuwu.deermod.ModItems;
import mei.arisuwu.deermod.ModPaintingVariants;
import mei.arisuwu.deermod.entity.deer.DeerEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;

public class ModDataGeneratorInit implements ModInitializer
{
    @Override
    public void onInitialize()
    {
        new ModEntities();
        new ModItems();
        new ModBannerPatterns();
        new ModPaintingVariants();
        FabricDefaultAttributeRegistry.register(ModEntities.DEER.get(), DeerEntity.createAttributes());
    }
}
