package mei.arisuwu.deermod;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public abstract class ModCreativeTabs
{
    public static Map<ResourceKey<CreativeModeTab>, Set<ItemGroupEntry>> ENTRIES = new HashMap<>();

    static {
        ENTRIES.put(
            getItemGroup("food_and_drinks"),
            Set.of(
                ItemGroupEntry.after(Items.COOKED_MUTTON, ModItems.VENISON, ModItems.COOKED_VENISON),
                ItemGroupEntry.after(Items.BREAD, ModItems.DEER_CRACKERS)
            )
        );

        ENTRIES.put(
            getItemGroup("ingredients"),
            Set.of(
                ItemGroupEntry.before(Items.BONE, ModItems.ANTLERS),
                ItemGroupEntry.after(Items.FLOWER_BANNER_PATTERN, ModItems.DEER_BANNER_PATTERN)
            )
        );

        ENTRIES.put(
            getItemGroup("tools_and_utilities"),
            Set.of(ItemGroupEntry.after(Items.CARROT_ON_A_STICK, ModItems.DEER_CRACKERS_ON_A_STICK))
        );

        ENTRIES.put(
            getItemGroup("spawn_eggs"),
            Set.of(ItemGroupEntry.before(Items.DOLPHIN_SPAWN_EGG, ModItems.DEER_SPAWN_EGG))
        );
    }

    protected static ResourceKey<CreativeModeTab> getItemGroup(String name)
    {
        return BuiltInRegistries.CREATIVE_MODE_TAB.registryKeySet().stream()
            .filter(key -> key.location().equals(ResourceLocation.withDefaultNamespace(name)))
            .findAny()
            .orElseThrow(() -> new NoSuchElementException("No item group present with id: " + name));
    }
}
