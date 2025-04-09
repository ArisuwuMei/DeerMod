package mei.arisuwu.deermod.neoforge;

import static net.minecraft.world.item.CreativeModeTab.TabVisibility.*;

import mei.arisuwu.deermod.ModCreativeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import java.util.Set;

public class NeoforgeModCreativeTabs extends ModCreativeTabs
{
    public NeoforgeModCreativeTabs(IEventBus eventBus)
    {
        super();
        eventBus.addListener(this::addEntriesToTab);
    }

    private void addEntriesToTab(BuildCreativeModeTabContentsEvent event)
    {
        entriesMap.getOrDefault(event.getTabKey(), Set.of()).forEach(entryMapping -> {
            switch (entryMapping.position)
            {
                case HEAD -> entryMapping.getNewItemStacks().reversed().forEach(itemStack -> event.insertFirst(
                    itemStack, PARENT_AND_SEARCH_TABS
                ));
                case BEFORE -> entryMapping.getNewItemStacks().forEach(itemStack -> event.insertBefore(
                    entryMapping.getExitingItemStack(), itemStack, PARENT_AND_SEARCH_TABS
                ));
                case AFTER -> entryMapping.getNewItemStacks().reversed().forEach(itemStack -> event.insertAfter(
                    entryMapping.getExitingItemStack(), itemStack, PARENT_AND_SEARCH_TABS
                ));
                case TAIL -> event.acceptAll(entryMapping.getNewItemStacks());
            }
        });
    }
}
