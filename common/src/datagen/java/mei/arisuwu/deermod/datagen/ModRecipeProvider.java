package mei.arisuwu.deermod.datagen;

import mei.arisuwu.deermod.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider
{
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture)
    {
        super(output);
    }

    @Override
    public @NotNull String getName()
    {
        return "Recipes";
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> consumer)
    {
        SimpleCookingRecipeBuilder.smelting(
                Ingredient.of(ModItems.VENISON.get()), RecipeCategory.FOOD,
                ModItems.COOKED_VENISON.get(), 0.35f, 200)
            .unlockedBy("has_venison", has(ModItems.VENISON.get()))
            .save(consumer);

        SimpleCookingRecipeBuilder.smoking(
                Ingredient.of(ModItems.VENISON.get()), RecipeCategory.FOOD,
                ModItems.COOKED_VENISON.get(), 0.35F, 100)
            .unlockedBy("has_venison", has(ModItems.VENISON.get()))
            .save(consumer, "deermod:cooked_from_venison_smoking");

        SimpleCookingRecipeBuilder.campfireCooking(
                Ingredient.of(ModItems.VENISON.get()), RecipeCategory.FOOD,
                ModItems.COOKED_VENISON.get(), 0.35F, 600)
            .unlockedBy("has_venison", has(ModItems.VENISON.get()))
            .save(consumer, "deermod:cooked_venison_from_campfire_cooking");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.BONE_MEAL, 3)
            .group("bonemeal")
            .requires(ModItems.ANTLERS.get())
            .unlockedBy("has_antlers", has(ModItems.ANTLERS.get()))
            .save(consumer, "deermod:bonemeal_from_antlers");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.DEER_BANNER_PATTERN.get())
            .requires(Items.PAPER)
            .requires(ModItems.ANTLERS.get())
            .unlockedBy("has_antlers", has(ModItems.ANTLERS.get()))
            .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DEER_CRACKERS.get())
            .define('W', Items.WHEAT)
            .define('S', Items.STRING)
            .pattern(" S ")
            .pattern("WWW")
            .pattern(" S ")
            .unlockedBy("has_wheat", has(Items.WHEAT))
            .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DEER_CRACKERS_ON_A_STICK.get())
            .define('R', Items.FISHING_ROD)
            .define('C', ModItems.DEER_CRACKERS.get())
            .pattern("R ")
            .pattern(" C")
            .unlockedBy("has_deer_crackers", has(ModItems.DEER_CRACKERS.get()))
            .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DEER_SPAWN_EGG.get())
            .define('C', ModItems.DEER_CRACKERS.get())
            .define('E', Items.EGG)
            .pattern("CCC")
            .pattern("CEC")
            .pattern("CCC")
            .unlockedBy("has_deer_crackers", has(ModItems.DEER_CRACKERS.get()))
            .save(consumer);
    }
}
