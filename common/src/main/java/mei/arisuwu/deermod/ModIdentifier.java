package mei.arisuwu.deermod;

import net.minecraft.resources.Identifier;

public class ModIdentifier
{
    public static Identifier of(String name)
    {
        return Identifier.fromNamespaceAndPath(Mod.MOD_ID, name);
    }
}
