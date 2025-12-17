package mei.arisuwu.deermod;

import net.minecraft.world.food.FoodProperties;

public class ModFoodComponents
{
    public static final FoodProperties VENISON = new FoodProperties.Builder()
        .nutrition(3).saturationMod(0.3f).build();

    public static final FoodProperties COOKED_VENISON = new FoodProperties.Builder()
        .nutrition(8).saturationMod(0.8f).build();

    public static final FoodProperties DEER_CRACKERS = new FoodProperties.Builder()
        .nutrition(2).saturationMod(0.2f).build();
}
