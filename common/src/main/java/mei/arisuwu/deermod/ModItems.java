package mei.arisuwu.deermod;

import net.minecraft.item.*;
import net.minecraft.registry.RegistryKey;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
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

//    public static Map<RegistryKey<ItemGroup>, Set<Supplier<Item>>> MOD_ITEMS_GROUPS = new HashMap<>();

    public ModItems()
    {
        VENISON = registerItem("venison", new Item.Settings().food(ModFoodComponents.VENISON));
        COOKED_VENISON = registerItem("cooked_venison", new Item.Settings().food(ModFoodComponents.COOKED_VENISON));
        ANTLERS = registerItem("antlers");
        DEER_SPAWN_EGG = registerItem("deer_spawn_egg", settings -> new SpawnEggItem(ModEntities.DEER.get(), settings.maxDamage(100)));
        DEER_CRACKERS = registerItem("deer_crackers", new Item.Settings().food(ModFoodComponents.DEER_CRACKERS));
        DEER_CRACKERS_ON_A_STICK = registerItem("deer_crackers_on_a_stick",settings -> new OnAStickItem<>(ModEntities.DEER.get(), 4, settings));

//        MOD_ITEMS_GROUPS.put(ItemGroups.FOOD_AND_DRINK, Set.of(VENISON, COOKED_VENISON, DEER_CRACKERS));
//        MOD_ITEMS_GROUPS.put(ItemGroups.SPAWN_EGGS, Set.of(DEER_SPAWN_EGG));
//        MOD_ITEMS_GROUPS.put(ItemGroups.INGREDIENTS, Set.of(ANTLERS));
//        MOD_ITEMS_GROUPS.put(ItemGroups.TOOLS, Set.of(DEER_CRACKERS_ON_A_STICK));
    }

    protected abstract Supplier<Item> registerItem(String name, Function<Item.Settings, Item> factory, Item.Settings settings);

    private Supplier<Item> registerItem(String name, Function<Item.Settings, Item> factory)
    {
        return registerItem(name, factory, new Item.Settings());
    }

    private Supplier<Item> registerItem(String name, Item.Settings settings)
    {
        return registerItem(name, Item::new, settings);
    }

    private Supplier<Item> registerItem(String name)
    {
        return registerItem(name, Item::new, new Item.Settings());
    }
}
