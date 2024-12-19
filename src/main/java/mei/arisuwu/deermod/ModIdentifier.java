package mei.arisuwu.deermod;

import net.minecraft.util.Identifier;

public class ModIdentifier
{
    public static Identifier of(String name)
    {
        return Identifier.of(Mod.MOD_ID, name);
    }
}
