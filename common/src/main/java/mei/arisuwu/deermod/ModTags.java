package mei.arisuwu.deermod;

import net.minecraft.item.Item;

import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.biome.Biome;

public class ModTags
{
    public static final TagKey<Biome> SPAWNS_DEERS = TagKey.of(RegistryKeys.BIOME, ModIdentifier.of("spawns_deers"));
    public static final TagKey<Item> DEER_FOOD = TagKey.of(RegistryKeys.ITEM, ModIdentifier.of("deer_food"));
}
