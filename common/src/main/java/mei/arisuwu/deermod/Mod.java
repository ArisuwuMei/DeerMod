package mei.arisuwu.deermod;

import net.minecraft.resources.Identifier;

public final class Mod
{
    public static final String MOD_ID = "deermod";

    public static Identifier identifier(String name) { return Identifier.fromNamespaceAndPath(MOD_ID, name); }
}
