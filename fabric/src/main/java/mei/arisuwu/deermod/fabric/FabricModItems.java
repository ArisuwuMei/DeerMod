package mei.arisuwu.deermod.fabric;

import mei.arisuwu.deermod.ModIdentifier;
import mei.arisuwu.deermod.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.function.Function;
import java.util.function.Supplier;


public class FabricModItems extends mei.arisuwu.deermod.ModItems
{
    public FabricModItems()
    {
        super();
        addItemsToGroups();
    }

    public void addItemsToGroups()
    {
//        MOD_ITEMS_GROUPS.forEach((group, items) -> {
//            items.forEach(item ->
//                ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item.get()))
//            );
//        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.addAfter(Items.COOKED_MUTTON, ModItems.VENISON.get(), ModItems.COOKED_VENISON.get());
            entries.addAfter(Items.BREAD, ModItems.DEER_CRACKERS.get());
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.addBefore(Items.BONE, ModItems.ANTLERS.get());
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.addAfter(Items.CARROT_ON_A_STICK, ModItems.DEER_CRACKERS_ON_A_STICK.get());
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(entries -> {
            entries.addBefore(Items.DOLPHIN_SPAWN_EGG, ModItems.DEER_SPAWN_EGG.get());
        });
    }

    @Override
    protected Supplier<Item> registerItem(String name, Function<Item.Settings, Item> factory, Item.Settings settings)
    {
        var item = Items.register(RegistryKey.of(RegistryKeys.ITEM, ModIdentifier.of(name)), factory, settings);
        return () -> item;
    }
}
