package mei.arisuwu.deermod;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

public class ModLootTables
{
    public static final ResourceKey<LootTable> DEER_SHEARING =
            ResourceKey.create(Registries.LOOT_TABLE, ModIdentifier.of("shearing/deer"));
    public static final ResourceKey<LootTable> DEER =
            ResourceKey.create(Registries.LOOT_TABLE, ModIdentifier.of("entities/deer"));

}
