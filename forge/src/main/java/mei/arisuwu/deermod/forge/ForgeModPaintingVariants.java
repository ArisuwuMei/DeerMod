package mei.arisuwu.deermod.forge;

import mei.arisuwu.deermod.Mod;
import mei.arisuwu.deermod.ModPaintingVariants;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ForgeModPaintingVariants extends ModPaintingVariants
{
    private static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS = DeferredRegister.create(ForgeRegistries.Keys.PAINTING_VARIANTS, Mod.MOD_ID);
    public ForgeModPaintingVariants(FMLJavaModLoadingContext context)
    {
        super();
        PAINTING_VARIANTS.register(context.getModEventBus());
    }

    @Override
    protected PaintingVariant register(ResourceKey<PaintingVariant> resourceKey, PaintingVariant paintingVariant)
    {
        PAINTING_VARIANTS.register(resourceKey.location().getPath(), () -> paintingVariant);
        return null;
    }
}
