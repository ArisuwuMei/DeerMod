package mei.arisuwu.deermod;

import net.minecraft.item.FoodComponent;

public class ModFoodComponents
{
    public static final FoodComponent VENISON = new FoodComponent.Builder()
        .hunger(3).saturationModifier(0.3f).build();

    public static final FoodComponent COOKED_VENISON = new FoodComponent.Builder()
        .hunger(8).saturationModifier(0.8f).build();

    public static final FoodComponent DEER_CRACKERS = new FoodComponent.Builder()
        .hunger(2).saturationModifier(0.2f).build();
}
