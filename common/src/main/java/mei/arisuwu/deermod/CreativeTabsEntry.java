package mei.arisuwu.deermod;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.function.Supplier;

public class CreativeTabsEntry {
    public enum Position {
        HEAD, BEFORE, AFTER, TAIL
    }

    public final Position position;
    public final ItemLike existingItem;
    public final List<Supplier<? extends ItemLike>> newItems;

    private CreativeTabsEntry(Position position, ItemLike existingItem, List<Supplier<? extends ItemLike>> newItems) {
        this.position = position;
        this.existingItem = existingItem;
        this.newItems = newItems;
    }

    public ItemStack getExitingItemStack() {
        return existingItem.asItem().getDefaultInstance();
    }

    public List<ItemStack> getNewItemStacks() {
        return newItems.stream()
            .map(Supplier::get)
            .map(ItemLike::asItem)
            .map(Item::getDefaultInstance)
            .toList();
    }

    @SafeVarargs
    static CreativeTabsEntry head(Supplier<? extends ItemLike>... newItems) {
        return new CreativeTabsEntry(Position.HEAD, null, List.of(newItems));
    }

    @SafeVarargs
    static CreativeTabsEntry before(ItemLike existingItem, Supplier<? extends ItemLike>... newItems) {
        return new CreativeTabsEntry(Position.BEFORE, existingItem, List.of(newItems));
    }

    @SafeVarargs
    static CreativeTabsEntry after(ItemLike existingItem, Supplier<? extends ItemLike>... newItems) {
        return new CreativeTabsEntry(Position.AFTER, existingItem, List.of(newItems));
    }

    @SafeVarargs
    static CreativeTabsEntry tail(Supplier<? extends ItemLike>... newItems) {
        return new CreativeTabsEntry(Position.TAIL, null, List.of(newItems));
    }
}
