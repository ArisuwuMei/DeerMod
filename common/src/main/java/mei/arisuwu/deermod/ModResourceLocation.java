package mei.arisuwu.deermod;

import net.minecraft.resources.ResourceLocation;

public class ModResourceLocation
{
    public static ResourceLocation of(String name)
    {
        return ResourceLocation.tryBuild(Mod.MOD_ID, name);
    }
}
