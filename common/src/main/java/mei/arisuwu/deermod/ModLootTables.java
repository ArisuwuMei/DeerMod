package mei.arisuwu.deermod;

import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class ModLootTables
{
    public static final RegistryKey<LootTable> DEER_SHEARING =
            RegistryKey.of(RegistryKeys.LOOT_TABLE, ModIdentifier.of("shearing/deer"));
    public static final RegistryKey<LootTable> DEER =
            RegistryKey.of(RegistryKeys.LOOT_TABLE, ModIdentifier.of("entities/deer"));

}
