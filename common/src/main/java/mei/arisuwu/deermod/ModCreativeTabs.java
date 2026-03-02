package mei.arisuwu.deermod;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static mei.arisuwu.deermod.CreativeTabsEntry.after;
import static mei.arisuwu.deermod.CreativeTabsEntry.before;

public abstract class ModCreativeTabs {
    protected final Map<ResourceKey<CreativeModeTab>, Set<CreativeTabsEntry>> entriesMap = new HashMap<>();

    protected ModCreativeTabs() {
        entriesMap.put(
            CreativeModeTabs.FOOD_AND_DRINKS,
            Set.of(
                after(Items.COOKED_MUTTON, ModItems.VENISON, ModItems.COOKED_VENISON),
                after(Items.BREAD, ModItems.DEER_CRACKERS)
            )
        );

        entriesMap.put(
            CreativeModeTabs.INGREDIENTS,
            Set.of(
                before(Items.BONE, ModItems.ANTLERS),
                after(Items.FLOWER_BANNER_PATTERN, ModItems.DEER_BANNER_PATTERN)
            )
        );

        entriesMap.put(
            CreativeModeTabs.TOOLS_AND_UTILITIES,
            Set.of(after(Items.CARROT_ON_A_STICK, ModItems.DEER_CRACKERS_ON_A_STICK))
        );

        entriesMap.put(
            CreativeModeTabs.SPAWN_EGGS,
            Set.of(before(Items.DOLPHIN_SPAWN_EGG, ModItems.DEER_SPAWN_EGG))
        );
    }
}
