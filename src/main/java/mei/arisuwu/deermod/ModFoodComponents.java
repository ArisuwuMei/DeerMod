package mei.arisuwu.deermod;

import net.minecraft.component.type.FoodComponent;

public class ModFoodComponents
{
    public static final FoodComponent VENISON = new FoodComponent.Builder()
            .nutrition(3).saturationModifier(0.3F).build();
    public static final FoodComponent COOKED_VENISON = new FoodComponent.Builder()
            .nutrition(8).saturationModifier(0.8F).build();
}
