package mei.arisuwu.deermod;

import net.minecraft.block.entity.BannerPattern;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class ModBannerPatterns
{
    public static final RegistryKey<BannerPattern> DEER = RegistryKey.of(RegistryKeys.BANNER_PATTERN, ModIdentifier.of("deer"));
}
