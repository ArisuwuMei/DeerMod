package mei.arisuwu.deermod;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.FoodOnAStickItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class ModItems
{
    public static Supplier<Item> VENISON;
    public static Supplier<Item> COOKED_VENISON;
    public static Supplier<Item> ANTLERS;
    public static Supplier<Item> DEER_SPAWN_EGG;
    public static Supplier<Item> DEER_CRACKERS;
    public static Supplier<Item> DEER_CRACKERS_ON_A_STICK;
    public static Supplier<Item> DEER_BANNER_PATTERN;

    public static Map<ResourceKey<CreativeModeTab>, Set<ItemGroupEntry>> MOD_ITEM_GROUP_ENTRIES = new HashMap<>();

    public ModItems()
    {
        DEER_SPAWN_EGG =
            registerItem("deer_spawn_egg", settings -> new SpawnEggItem(ModEntities.DEER.get(), settings));

        ANTLERS = registerItem("antlers");
        VENISON = registerFoodItem("venison", ModFoodComponents.VENISON);
        COOKED_VENISON = registerFoodItem("cooked_venison", ModFoodComponents.COOKED_VENISON);
        DEER_CRACKERS = registerFoodItem("deer_crackers", ModFoodComponents.DEER_CRACKERS);

        DEER_CRACKERS_ON_A_STICK = registerItem(
            "deer_crackers_on_a_stick",
            settings -> new FoodOnAStickItem<>(ModEntities.DEER.get(), 4, settings),
            new Item.Properties().durability(100)
        );

        DEER_BANNER_PATTERN = registerItem(
            "deer_banner_pattern",
            new Item.Properties()
                .stacksTo(1)
                .component(DataComponents.PROVIDES_BANNER_PATTERNS, ModTags.DEER_PATTERN_ITEM)
        );

        MOD_ITEM_GROUP_ENTRIES.put(
            getItemGroup("food_and_drinks"),
            Set.of(
                ItemGroupEntry.after(Items.COOKED_MUTTON, ModItems.VENISON, ModItems.COOKED_VENISON),
                ItemGroupEntry.after(Items.BREAD, ModItems.DEER_CRACKERS)
            )
        );

        MOD_ITEM_GROUP_ENTRIES.put(
            getItemGroup("ingredients"),
            Set.of(
                ItemGroupEntry.before(Items.BONE, ModItems.ANTLERS),
                ItemGroupEntry.after(Items.FLOWER_BANNER_PATTERN, ModItems.DEER_BANNER_PATTERN)
            )
        );

        MOD_ITEM_GROUP_ENTRIES.put(
            getItemGroup("tools_and_utilities"),
            Set.of(ItemGroupEntry.after(Items.CARROT_ON_A_STICK, ModItems.DEER_CRACKERS_ON_A_STICK))
        );

        MOD_ITEM_GROUP_ENTRIES.put(
            getItemGroup("spawn_eggs"),
            Set.of(ItemGroupEntry.before(Items.DOLPHIN_SPAWN_EGG, ModItems.DEER_SPAWN_EGG))
        );
    }

    protected abstract Supplier<Item> registerItem(String name, Function<Item.Properties, Item> factory, Item.Properties settings);


    private Supplier<Item> registerFoodItem(String name, FoodProperties foodComponent)
    {
        return registerItem(name, new Item.Properties().food(foodComponent));
    }

    private Supplier<Item> registerItem(@SuppressWarnings("SameParameterValue") String name, Function<Item.Properties, Item> factory)
    {
        return registerItem(name, factory, new Item.Properties());
    }

    private Supplier<Item> registerItem(String name, Item.Properties settings)
    {
        return registerItem(name, Item::new, settings);
    }

    @SuppressWarnings("SameParameterValue")
    private Supplier<Item> registerItem(String name)
    {
        return registerItem(name, Item::new, new Item.Properties());
    }

    protected ResourceKey<CreativeModeTab> getItemGroup(String name)
    {
        return BuiltInRegistries.CREATIVE_MODE_TAB.registryKeySet().stream()
            .filter(key -> key.location().equals(ResourceLocation.withDefaultNamespace(name)))
            .findAny()
            .orElseThrow(() -> new NoSuchElementException("No item group present with id: " + name));
    }
}
