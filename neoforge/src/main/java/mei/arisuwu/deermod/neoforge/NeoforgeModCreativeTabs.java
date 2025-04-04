package mei.arisuwu.deermod.neoforge;

import mei.arisuwu.deermod.ModCreativeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import java.util.Set;

public class NeoforgeModCreativeTabs extends ModCreativeTabs
{
    public NeoforgeModCreativeTabs(IEventBus eventBus)
    {
        eventBus.addListener(this::addEntriesToTab);
    }

    private void addEntriesToTab(BuildCreativeModeTabContentsEvent event)
    {
        ENTRIES.getOrDefault(event.getTabKey(), Set.of()).forEach(newEntry -> {
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
}
