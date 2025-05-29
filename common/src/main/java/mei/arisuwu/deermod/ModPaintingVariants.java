package mei.arisuwu.deermod;

import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.decoration.PaintingVariant;

import java.util.Optional;

public class ModPaintingVariants
{
    public static ResourceKey<PaintingVariant> LUVDEER = create("luvdeer");

    public static void bootstrap(BootstrapContext<PaintingVariant> context)
    {
        context.register(
            LUVDEER,
            new PaintingVariant(
                1,
                1,
                LUVDEER.location(),
                Optional.of(Component.translatable(LUVDEER.location().toLanguageKey("painting", "title")).withStyle(ChatFormatting.YELLOW)),
                Optional.of(Component.translatable(LUVDEER.location().toLanguageKey("painting", "author")).withStyle(ChatFormatting.GRAY))
            )
        );
    }


    private static ResourceKey<PaintingVariant> create(String name)
    {
        return ResourceKey.create(Registries.PAINTING_VARIANT, ModResourceLocation.of(name));
    }
}
