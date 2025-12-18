package mei.arisuwu.deermod.forge;

import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ItemSteerable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import java.util.function.Supplier;

public class ForgeFoodOnAStickItem<T extends Entity & ItemSteerable> extends Item
{
    private final Supplier<EntityType<T>> canInteractWith;
    private final int consumeItemDamage;

    public ForgeFoodOnAStickItem(Properties properties, Supplier<EntityType<T>> entityTypeSupplier, int consumeItemDamage)
    {
        super(properties);
        this.canInteractWith = entityTypeSupplier;
        this.consumeItemDamage = consumeItemDamage;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
    {
        var itemStack = player.getItemInHand(hand);
        var entity = player.getControlledVehicle();

        if (level.isClientSide()
        || !player.isPassenger()
        || !(entity instanceof ItemSteerable itemSteerable))
            return InteractionResultHolder.pass(itemStack);

        if (entity.getType() == this.canInteractWith.get() && itemSteerable.boost())
        {
            itemStack.hurtAndBreak(this.consumeItemDamage, player, p -> p.broadcastBreakEvent(hand));

            if (itemStack.isEmpty()) {
                ItemStack itemStack1 = new ItemStack(Items.FISHING_ROD);
                itemStack1.setTag(itemStack.getTag());
                return InteractionResultHolder.success(itemStack1);
            }

            return InteractionResultHolder.success(itemStack);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResultHolder.pass(itemStack);
    }
}
