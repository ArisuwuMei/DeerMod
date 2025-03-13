package mei.arisuwu.deermod.fabric;

import mei.arisuwu.deermod.ModIdentifier;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import java.util.function.Function;
import java.util.function.Supplier;


public class FabricModItems extends mei.arisuwu.deermod.ModItems
{
    public FabricModItems()
    {
        super();
        addItemsToTabs();
    }

    public void addItemsToTabs()
    {
        MOD_ITEM_GROUP_ENTRIES.forEach((tab, newEntries) -> {
            ItemGroupEvents.modifyEntriesEvent(tab).register(entries -> {
                newEntries.forEach(newEntry -> {
                    switch (newEntry.position)
                    {
                        case HEAD -> newEntry.newItems.reversed().forEach(newItem -> entries.prepend(newItem.get()));
                        case BEFORE -> entries.addBefore(newEntry.existingItem, newEntry.getNewItemStacks());
                        case AFTER -> entries.addAfter(newEntry.existingItem, newEntry.getNewItemStacks());
                        case TAIL -> entries.acceptAll(newEntry.getNewItemStacks());
                    }
                });
            });
        });
    }

    @Override
    protected Supplier<Item> registerItem(String name, Function<Item.Properties, Item> factory, Item.Properties settings)
    {
        var item = Items.registerItem(ResourceKey.create(Registries.ITEM, ModIdentifier.of(name)), factory, settings);
        return () -> item;
    }
}
