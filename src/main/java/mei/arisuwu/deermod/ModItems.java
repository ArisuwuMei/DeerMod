package mei.arisuwu.deermod;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.function.Function;


public class ModItems
{
    public static final Item VENISON =
        registerItem("venison", new Item.Settings().food(ModFoodComponents.VENISON));

    public static final Item COOKED_VENISON =
        registerItem("cooked_venison", new Item.Settings().food(ModFoodComponents.COOKED_VENISON));

    public static final Item ANTLERS = registerItem("antlers");

    public static final Item DEER_SPAWN_EGG =
        registerItem("deer_spawn_egg", settings -> new SpawnEggItem(ModEntities.DEER, settings));

    public static final Item DEER_CRACKERS =
        registerItem("deer_crackers", new Item.Settings().food(ModFoodComponents.DEER_CRACKERS));

    public static final Item DEER_CRACKERS_ON_A_STICK =
        registerItem("deer_crackers_on_a_stick",settings -> new OnAStickItem<>(ModEntities.DEER, 4, settings));

    public static void addItemsToGroups()
    {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS)
            .register(entries -> entries.add(DEER_SPAWN_EGG));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK)
            .register(entries -> {
                entries.add(VENISON);
                entries.add(COOKED_VENISON);
                entries.add(DEER_CRACKERS);
            });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
            .register(entries -> entries.add(ANTLERS));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS)
            .register(entries -> entries.add(DEER_CRACKERS_ON_A_STICK));
    }

    private static Item registerItem(String name, Function<Item.Settings, Item> factory, Item.Settings settings)
    {
        return Items.register(RegistryKey.of(RegistryKeys.ITEM, ModIdentifier.of(name)), factory, settings);
    }

    private static Item registerItem(String name, Function<Item.Settings, Item> factory)
    {
        return registerItem(name, factory, new Item.Settings());
    }

    private static Item registerItem(String name, Item.Settings settings)
    {
        return registerItem(name, Item::new, settings);
    }

    private static Item registerItem(String name)
    {
        return registerItem(name, Item::new, new Item.Settings());
    }
}
