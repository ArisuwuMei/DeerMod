package mei.arisuwu.deermod;

import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;

import java.util.List;
import java.util.function.Supplier;

public class ItemGroupEntry
{
    public enum Position
    {
        HEAD, BEFORE, AFTER, TAIL
    }

    public final Position position;
    public final ItemConvertible existingItem;
    public final List<Supplier<? extends ItemConvertible>> newItems;

    private ItemGroupEntry(Position position, ItemConvertible existingItem, List<Supplier<? extends ItemConvertible>> newItems)
    {
        this.position = position;
        this.existingItem = existingItem;
        this.newItems = newItems;
    }

    public ItemStack getExitingItemStack()
    {
        return existingItem.asItem().getDefaultStack();
    }

    public List<ItemStack> getNewItemStacks()
    {
        return newItems.stream()
            .map(Supplier::get)
            .map(ItemConvertible::asItem)
            .map(Item::getDefaultStack)
            .toList();
    }

    @SafeVarargs
    static ItemGroupEntry head(Supplier<? extends ItemConvertible>... newItems)
    {
        return new ItemGroupEntry(Position.HEAD, null, List.of(newItems));
    }

    @SafeVarargs
    static ItemGroupEntry before(ItemConvertible existingItem, Supplier<? extends ItemConvertible>... newItems)
    {
        return new ItemGroupEntry(Position.BEFORE, existingItem, List.of(newItems));
    }

    @SafeVarargs
    static ItemGroupEntry after(ItemConvertible existingItem, Supplier<? extends ItemConvertible>... newItems)
    {
        return new ItemGroupEntry(Position.AFTER, existingItem, List.of(newItems));
    }

    @SafeVarargs
    static ItemGroupEntry tail(Supplier<? extends ItemConvertible>... newItems)
    {
        return new ItemGroupEntry(Position.TAIL, null, List.of(newItems));
    }
}
