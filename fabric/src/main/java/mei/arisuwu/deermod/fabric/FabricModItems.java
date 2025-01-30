package mei.arisuwu.deermod.fabric;

import mei.arisuwu.deermod.ModIdentifier;
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
        MOD_ITEM_GROUP_ENTRIES.forEach((group, newEntries) -> {
            ItemGroupEvents.modifyEntriesEvent(group).register(entries -> {
                newEntries.forEach(newEntry -> {
                    switch (newEntry.position)
                    {
                        case HEAD -> newEntry.newItems.reversed().forEach(newItem -> entries.prepend(newItem.get()));
                        case BEFORE -> entries.addBefore(newEntry.existingItem, newEntry.getNewItemStacks());
                        case AFTER -> entries.addAfter(newEntry.existingItem, newEntry.getNewItemStacks());
                        case TAIL -> entries.addAll(newEntry.getNewItemStacks());
                    }
                });
            });
        });
    }

    @Override
    protected Supplier<Item> registerItem(String name, Function<Item.Settings, Item> factory, Item.Settings settings)
    {
        var item = Items.register(RegistryKey.of(RegistryKeys.ITEM, ModIdentifier.of(name)), factory.apply(settings));
        return () -> item;
    }
}
