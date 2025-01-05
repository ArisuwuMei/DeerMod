package mei.arisuwu.deermod;

import net.minecraft.component.type.FoodComponent;

public class ModFoodComponents
{
    public static final FoodComponent VENISON = new FoodComponent.Builder()
        .nutrition(3).saturationModifier(0.3f).build();

    public static final FoodComponent COOKED_VENISON = new FoodComponent.Builder()
        .nutrition(8).saturationModifier(0.8f).build();

    public static final FoodComponent DEER_CRACKERS = new FoodComponent.Builder()
        .nutrition(2).saturationModifier(0.2f).build();
}
