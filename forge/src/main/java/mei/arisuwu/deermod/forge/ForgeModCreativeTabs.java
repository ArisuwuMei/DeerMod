package mei.arisuwu.deermod.forge;

import com.google.common.collect.Lists;
import mei.arisuwu.deermod.ModCreativeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.Set;

import static net.minecraft.world.item.CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS;

public class ForgeModCreativeTabs extends ModCreativeTabs
{
    protected ForgeModCreativeTabs(FMLJavaModLoadingContext context)
    {
        super();
        context.getModEventBus().addListener(this::addEntriesToTab);
    }

    private void addEntriesToTab(BuildCreativeModeTabContentsEvent event)
    {
        var allEntries = event.getEntries();

        entriesMap.getOrDefault(event.getTabKey(), Set.of()).forEach(entryMapping -> {
            switch (entryMapping.position)
            {
                case BEFORE -> entryMapping.getNewItemStacks().forEach(itemStack -> allEntries.putBefore(
                    entryMapping.getExitingItemStack(), itemStack, PARENT_AND_SEARCH_TABS
                ));
                case AFTER -> Lists.reverse(entryMapping.getNewItemStacks()).forEach(itemStack -> allEntries.putAfter(
                    entryMapping.getExitingItemStack(), itemStack, PARENT_AND_SEARCH_TABS
                ));
                case TAIL -> event.acceptAll(entryMapping.getNewItemStacks());
            }
        });
    }
}
