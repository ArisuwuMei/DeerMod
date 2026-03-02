package mei.arisuwu.deermod.fabric;

import mei.arisuwu.deermod.CreativeTabsEntry;
import mei.arisuwu.deermod.ModCreativeTabs;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

import java.util.Set;

public class FabricModCreativeTabs extends ModCreativeTabs
{
    public FabricModCreativeTabs()
    {
        super();
        entriesMap.forEach(this::addEntries);
    }

    public void addEntries(ResourceKey<CreativeModeTab> tab, Set<CreativeTabsEntry> newEntries)
    {
        CreativeModeTabEvents.modifyOutputEvent(tab).register(entries -> {
            newEntries.forEach(newEntry -> {
                switch (newEntry.position)
                {
                    case HEAD -> newEntry.newItems.reversed().forEach(newItem -> entries.prepend(newItem.get()));
                    case BEFORE -> entries.insertBefore(newEntry.existingItem, newEntry.getNewItemStacks());
                    case AFTER -> entries.insertAfter(newEntry.existingItem, newEntry.getNewItemStacks());
                    case TAIL -> entries.acceptAll(newEntry.getNewItemStacks());
                }
            });
        });
    }
}
