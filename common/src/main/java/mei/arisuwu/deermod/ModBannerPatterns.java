package mei.arisuwu.deermod;

import net.minecraft.block.entity.BannerPattern;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

@SuppressWarnings("unused")
public class ModBannerPatterns
{
    public static final RegistryKey<BannerPattern> DEER = RegistryKey.of(RegistryKeys.BANNER_PATTERN, ModIdentifier.of("deer"));

    public ModBannerPatterns()
    {
        register(DEER, "der");
    }

    @SuppressWarnings({"UnusedReturnValue", "SameParameterValue"})
    protected BannerPattern register(RegistryKey<BannerPattern> registryKey, String id)
    {
        return Registry.register(Registries.BANNER_PATTERN, registryKey, new BannerPattern(id));
    }
}
