package mei.arisuwu.deermod;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.entity.BannerPattern;

public class ModTags
{
    public static final TagKey<Biome> SPAWNS_DEERS = TagKey.create(Registries.BIOME, ModIdentifier.of("spawns_deers"));
    public static final TagKey<Item> DEER_FOOD = TagKey.create(Registries.ITEM, ModIdentifier.of("deer_food"));
    public static final TagKey<BannerPattern> DEER_PATTERN_ITEM = TagKey.create(Registries.BANNER_PATTERN, ModIdentifier.of("pattern_item/deer"));
}
