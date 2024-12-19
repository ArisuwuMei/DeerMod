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

    public static final Item DEER_SPAWN_EGG = registerItem(
            "deer_spawn_egg",
            settings -> new SpawnEggItem(ModEntities.DEER, settings)
    );

    public static void addItemsToGroups()
    {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS)
                .register(entries -> entries.add(DEER_SPAWN_EGG));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK)
                .register(entries -> {
                    entries.add(VENISON);
                    entries.add(COOKED_VENISON);
                });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
                .register(entries -> entries.add(ANTLERS));
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
