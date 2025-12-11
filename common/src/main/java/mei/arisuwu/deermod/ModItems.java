package mei.arisuwu.deermod;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.FoodOnAStickItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;

import java.util.function.Function;
import java.util.function.Supplier;

public class ModItems
{
    public static Supplier<Item> VENISON;
    public static Supplier<Item> COOKED_VENISON;
    public static Supplier<Item> ANTLERS;
    public static Supplier<Item> DEER_SPAWN_EGG;
    public static Supplier<Item> DEER_CRACKERS;
    public static Supplier<Item> DEER_CRACKERS_ON_A_STICK;
    public static Supplier<Item> DEER_BANNER_PATTERN;

    public ModItems()
    {
        DEER_SPAWN_EGG = registerItem(
            "deer_spawn_egg", SpawnEggItem::new, new Item.Properties().spawnEgg(ModEntities.DEER.get())
        );

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
    }

    protected Supplier<Item> registerItem(String name, Function<Item.Properties, Item> factory, Item.Properties settings)
    {
        var item = Items.registerItem(ResourceKey.create(Registries.ITEM, ModIdentifier.of(name)), factory, settings);
        return () -> item;
    }


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
}
