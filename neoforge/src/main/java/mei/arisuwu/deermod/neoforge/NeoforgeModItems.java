package mei.arisuwu.deermod.neoforge;

import mei.arisuwu.deermod.ModItems;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
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

    @Override
    protected Supplier<Item> registerItem(String name, Function<Item.Properties, Item> factory, Supplier<Item.Properties> settings)
    {
        return ITEMS.registerItem(name, factory, settings);
    }
}
