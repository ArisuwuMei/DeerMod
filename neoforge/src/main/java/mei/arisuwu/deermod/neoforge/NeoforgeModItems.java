package mei.arisuwu.deermod.neoforge;

import mei.arisuwu.deermod.ModIdentifier;
import mei.arisuwu.deermod.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
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
                var entries = event.getEntries();
                newEntries.forEach(newEntry -> {
                    switch (newEntry.position)
                    {
                        case HEAD -> newEntry.getNewItemStacks().reversed().forEach(itemStack -> entries.putFirst(
                            itemStack, ItemGroup.StackVisibility.PARENT_AND_SEARCH_TABS
                        ));
                        case BEFORE -> newEntry.getNewItemStacks().forEach(itemStack -> entries.putBefore(
                            newEntry.getExitingItemStack(), itemStack, ItemGroup.StackVisibility.PARENT_AND_SEARCH_TABS
                        ));
                        case AFTER -> newEntry.getNewItemStacks().reversed().forEach(itemStack -> entries.putAfter(
                            newEntry.getExitingItemStack(), itemStack, ItemGroup.StackVisibility.PARENT_AND_SEARCH_TABS
                        ));
                        case TAIL -> event.addAll(newEntry.getNewItemStacks());
                    }
                });
            }
        });
    }

    @Override
    protected Supplier<Item> registerItem(String name, Function<Item.Settings, Item> factory, Item.Settings settings)
    {
        return ITEMS.registerItem(name, factory, settings);
    }
}
