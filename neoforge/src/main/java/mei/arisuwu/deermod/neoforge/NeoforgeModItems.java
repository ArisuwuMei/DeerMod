package mei.arisuwu.deermod.neoforge;

import mei.arisuwu.deermod.ModIdentifier;
import mei.arisuwu.deermod.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
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
    }

    public void addItemsToGroups(BuildCreativeModeTabContentsEvent event)
    {
//        MOD_ITEMS_GROUPS.forEach((group, items) -> {
//            if (event.getTabKey() == group)
//                items.forEach(itemSupplier -> event.add(itemSupplier.get()));
//        });


        if (event.getTabKey() == ItemGroups.FOOD_AND_DRINK)
        {
            insertAfter(event, Items.COOKED_MUTTON, ModItems.VENISON.get());
            insertAfter(event, ModItems.VENISON.get(), ModItems.COOKED_VENISON.get());
            insertAfter(event, Items.BREAD, ModItems.DEER_CRACKERS.get());
        }

        if (event.getTabKey() == ItemGroups.INGREDIENTS)
        {
            insertBefore(event, Items.BONE, ModItems.ANTLERS.get());
        }

        if (event.getTabKey() == ItemGroups.TOOLS)
        {
            insertAfter(event, Items.CARROT_ON_A_STICK, ModItems.DEER_CRACKERS_ON_A_STICK.get());
        }

        if (event.getTabKey() == ItemGroups.SPAWN_EGGS)
        {
            insertBefore(event, Items.DOLPHIN_SPAWN_EGG, ModItems.DEER_SPAWN_EGG.get());
        }

    }

    private void insertAfter(BuildCreativeModeTabContentsEvent event, Item existing, Item next)
    {
        event.insertAfter(existing.getDefaultStack(), next.getDefaultStack(), ItemGroup.StackVisibility.PARENT_AND_SEARCH_TABS);
    }

    private void insertBefore(BuildCreativeModeTabContentsEvent event, Item existing, Item previous)
    {
        event.insertBefore(existing.getDefaultStack(), previous.getDefaultStack(), ItemGroup.StackVisibility.PARENT_AND_SEARCH_TABS);
    }

    @Override
    protected Supplier<Item> registerItem(String name, Function<Item.Settings, Item> factory, Item.Settings settings)
    {
        var registryKey = RegistryKey.of(RegistryKeys.ITEM, ModIdentifier.of(name));
        return ITEMS.registerItem(name, factory, settings.registryKey(registryKey));
    }
}
