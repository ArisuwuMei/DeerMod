package mei.arisuwu.deermod;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

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

    public static Map<RegistryKey<ItemGroup>, Set<ItemGroupEntry>> MOD_ITEM_GROUP_ENTRIES = new HashMap<>();

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
            settings -> new OnAStickItem<>(ModEntities.DEER.get(), 4, settings),
            new Item.Settings().maxDamage(100)
        );

        DEER_BANNER_PATTERN = registerItem(
            "deer_banner_pattern",
            settings -> new BannerPatternItem(ModTags.DEER_PATTERN_ITEM, settings),
            new Item.Settings().maxCount(1)
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

    protected abstract Supplier<Item> registerItem(String name, Function<Item.Settings, Item> factory, Item.Settings settings);


    private Supplier<Item> registerFoodItem(String name, FoodComponent foodComponent)
    {
        return registerItem(name, new Item.Settings().food(foodComponent));
    }

    private Supplier<Item> registerItem(@SuppressWarnings("SameParameterValue") String name, Function<Item.Settings, Item> factory)
    {
        return registerItem(name, factory, new Item.Settings());
    }

    private Supplier<Item> registerItem(String name, Item.Settings settings)
    {
        return registerItem(name, Item::new, settings);
    }

    @SuppressWarnings("SameParameterValue")
    private Supplier<Item> registerItem(String name)
    {
        return registerItem(name, Item::new, new Item.Settings());
    }

    protected RegistryKey<ItemGroup> getItemGroup(String name)
    {
        return Registries.ITEM_GROUP.getKeys().stream()
            .filter(key -> key.getValue().equals(Identifier.ofVanilla(name)))
            .findAny()
            .orElseThrow(() -> new NoSuchElementException("No item group present with id: " + name));
    }
}
