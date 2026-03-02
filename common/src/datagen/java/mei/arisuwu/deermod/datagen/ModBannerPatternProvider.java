package mei.arisuwu.deermod.datagen;

import mei.arisuwu.deermod.ModBannerPatterns;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModBannerPatternProvider extends FabricDynamicRegistryProvider {
    public ModBannerPatternProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        entries.add(registries.lookupOrThrow(Registries.BANNER_PATTERN), ModBannerPatterns.DEER);
    }

    @Override
    public @NotNull String getName() {
        return "Banner Patterns";
    }
}
