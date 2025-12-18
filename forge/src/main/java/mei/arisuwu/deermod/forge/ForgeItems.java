package mei.arisuwu.deermod.forge;

import mei.arisuwu.deermod.Mod;
import mei.arisuwu.deermod.ModEntities;
import mei.arisuwu.deermod.ModItems;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Function;
import java.util.function.Supplier;

public class ForgeItems extends ModItems
{
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Mod.MOD_ID);

    public ForgeItems(FMLJavaModLoadingContext context)
    {
        ITEMS.register(context.getModEventBus());
    }

    @Override
    protected Supplier<Item> registerItem(String name, Function<Item.Properties, Item> factory, Supplier<Item.Properties> settings)
    {
        Function<Item.Properties, Item> patchedFactory = switch (name) {
            case "deer_spawn_egg" ->properties -> new ForgeSpawnEggItem(
                ModEntities.DEER, -4688839, -334136, properties
            );
            case "deer_crackers_on_a_stick" -> properties -> new ForgeFoodOnAStickItem<>(
                properties, ModEntities.DEER, 4
            );
            default -> factory;
        };

        return ITEMS.register(name, () -> patchedFactory.apply(settings.get()));
    }
}
