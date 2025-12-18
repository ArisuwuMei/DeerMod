package mei.arisuwu.deermod.forge;

import mei.arisuwu.deermod.Mod;
import mei.arisuwu.deermod.ModBannerPatterns;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;

public class ForgeModBannerPatterns extends ModBannerPatterns
{
    private static final DeferredRegister<BannerPattern> BANNER_PATTERNS = DeferredRegister.create(Registries.BANNER_PATTERN, Mod.MOD_ID);

    public ForgeModBannerPatterns(FMLJavaModLoadingContext context)
    {
        super();
        BANNER_PATTERNS.register(context.getModEventBus());
    }

    @Override
    protected void register(ResourceKey<BannerPattern> resourceKey, String id)
    {
        BANNER_PATTERNS.register(resourceKey.location().getPath(), () -> new BannerPattern(id));
    }
}
