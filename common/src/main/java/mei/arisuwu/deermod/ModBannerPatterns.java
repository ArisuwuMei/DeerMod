package mei.arisuwu.deermod;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.entity.BannerPattern;

@SuppressWarnings("unused")
public class ModBannerPatterns
{
    public static final ResourceKey<BannerPattern> DEER = create("deer");

    public ModBannerPatterns()
    {
        register(DEER, "deer");
    }

    private static ResourceKey<BannerPattern> create(String name)
    {
        return ResourceKey.create(Registries.BANNER_PATTERN, ModResourceLocation.of(name));
    }

    private static void register(ResourceKey<BannerPattern> resourceKey, String id)
    {
        Registry.register(BuiltInRegistries.BANNER_PATTERN, resourceKey, new BannerPattern(id));
    }
}
