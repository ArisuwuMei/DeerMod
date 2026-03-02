package mei.arisuwu.deermod;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.entity.BannerPattern;

public class ModTags {
    public static final TagKey<Biome> DEERS_HABITAT_BIOMES = TagKey.create(Registries.BIOME, Mod.identifier("deers_habitat_biomes"));
    public static final TagKey<Biome> DEERS_ESCAPADE_BIOMES = TagKey.create(Registries.BIOME, Mod.identifier("deers_escapade_biomes"));

    public static final TagKey<Item> DEER_FOOD = TagKey.create(Registries.ITEM, Mod.identifier("deer_food"));
    public static final TagKey<BannerPattern> DEER_PATTERN_ITEM = TagKey.create(Registries.BANNER_PATTERN, Mod.identifier("pattern_item/deer"));
}
