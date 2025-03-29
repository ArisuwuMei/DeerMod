package mei.arisuwu.deermod.neoforge;

import mei.arisuwu.deermod.ModResourceLocation;
import mei.arisuwu.deermod.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.Supplier;

import static mei.arisuwu.deermod.Mod.MOD_ID;

public class NeoforgeModItems extends ModItems
{
    private static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MOD_ID);

    public NeoforgeModItems(IEventBus eventBus)
    {
        super();
        ITEMS.register(eventBus);
        eventBus.addListener(this::addItemsToGroups);
    }

    public void addItemsToGroups(BuildCreativeModeTabContentsEvent event)
    {
        MOD_ITEM_GROUP_ENTRIES.forEach((group, newEntries) -> {
            if (group == event.getTabKey())
            {
                newEntries.forEach(newEntry -> {
                    switch (newEntry.position)
                    {
                        case HEAD -> newEntry.getNewItemStacks().reversed().forEach(itemStack -> event.insertFirst(
                            itemStack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
                        ));
                        case BEFORE -> newEntry.getNewItemStacks().forEach(itemStack -> event.insertBefore(
                            newEntry.getExitingItemStack(), itemStack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
                        ));
                        case AFTER -> newEntry.getNewItemStacks().reversed().forEach(itemStack -> event.insertAfter(
                            newEntry.getExitingItemStack(), itemStack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
                        ));
                        case TAIL -> event.acceptAll(newEntry.getNewItemStacks());
                    }
                });
            }
        });
    }

    @Override
    protected Supplier<Item> registerItem(String name, Function<Item.Properties, Item> factory, Item.Properties settings)
    {
        var registryKey = ResourceKey.create(Registries.ITEM, ModResourceLocation.of(name));
        return ITEMS.registerItem(name, factory, settings.setId(registryKey));
    }
}
