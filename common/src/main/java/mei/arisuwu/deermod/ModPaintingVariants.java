package mei.arisuwu.deermod;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.decoration.PaintingVariant;

public class ModPaintingVariants
{
    public static ResourceKey<PaintingVariant> LUVDEER = create("luvdeer");

    public ModPaintingVariants()
    {
        register(LUVDEER, new PaintingVariant(16, 16));
    }

    protected PaintingVariant register(ResourceKey<PaintingVariant> resourceKey, PaintingVariant paintingVariant)
    {
        return Registry.register(BuiltInRegistries.PAINTING_VARIANT, resourceKey, paintingVariant);
    }

    private static ResourceKey<PaintingVariant> create(String name)
    {
        return ResourceKey.create(Registries.PAINTING_VARIANT, ModResourceLocation.of(name));
    }
}
