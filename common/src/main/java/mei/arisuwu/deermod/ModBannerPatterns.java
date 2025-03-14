package mei.arisuwu.deermod;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.entity.BannerPattern;

@SuppressWarnings("unused")
public class ModBannerPatterns
{
    public static final ResourceKey<BannerPattern> DEER = ResourceKey.create(Registries.BANNER_PATTERN, ModResourceLocation.of("deer"));
}
