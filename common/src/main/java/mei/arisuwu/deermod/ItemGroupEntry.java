package mei.arisuwu.deermod;

import java.util.List;
import java.util.function.Supplier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public class ItemGroupEntry
{
    public enum Position
    {
        HEAD, BEFORE, AFTER, TAIL
    }

    public final Position position;
    public final ItemLike existingItem;
    public final List<Supplier<? extends ItemLike>> newItems;

    private ItemGroupEntry(Position position, ItemLike existingItem, List<Supplier<? extends ItemLike>> newItems)
    {
        this.position = position;
        this.existingItem = existingItem;
        this.newItems = newItems;
    }

    public ItemStack getExitingItemStack()
    {
        return existingItem.asItem().getDefaultInstance();
    }

    public List<ItemStack> getNewItemStacks()
    {
        return newItems.stream()
            .map(Supplier::get)
            .map(ItemLike::asItem)
            .map(Item::getDefaultInstance)
            .toList();
    }

    @SafeVarargs
    static ItemGroupEntry head(Supplier<? extends ItemLike>... newItems)
    {
        return new ItemGroupEntry(Position.HEAD, null, List.of(newItems));
    }

    @SafeVarargs
    static ItemGroupEntry before(ItemLike existingItem, Supplier<? extends ItemLike>... newItems)
    {
        return new ItemGroupEntry(Position.BEFORE, existingItem, List.of(newItems));
    }

    @SafeVarargs
    static ItemGroupEntry after(ItemLike existingItem, Supplier<? extends ItemLike>... newItems)
    {
        return new ItemGroupEntry(Position.AFTER, existingItem, List.of(newItems));
    }

    @SafeVarargs
    static ItemGroupEntry tail(Supplier<? extends ItemLike>... newItems)
    {
        return new ItemGroupEntry(Position.TAIL, null, List.of(newItems));
    }
}
