package mei.arisuwu.deermod;

import net.minecraft.resources.ResourceLocation;

public class ModIdentifier
{
    public static ResourceLocation of(String name)
    {
        return ResourceLocation.fromNamespaceAndPath(Mod.MOD_ID, name);
    }
}
