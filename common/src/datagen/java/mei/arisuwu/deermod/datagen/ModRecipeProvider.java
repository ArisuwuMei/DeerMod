package mei.arisuwu.deermod.datagen;

import mei.arisuwu.deermod.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider
{
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture)
    {
        super(output, registriesFuture);
    }

    @Override
    protected @NotNull RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput)
    {
        return new RecipeProvider(provider, recipeOutput) {

            @Override
            public void buildRecipes()
            {
                SimpleCookingRecipeBuilder.smelting(
                    Ingredient.of(ModItems.VENISON.get()),
                    RecipeCategory.FOOD,
                    ModItems.COOKED_VENISON.get(), 0.35f, 200
                )
                    .unlockedBy("has_venison", has(ModItems.VENISON.get()))
                    .save(recipeOutput);

                cookRecipes("smoking", RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new, 100);
                cookRecipes("campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE, CampfireCookingRecipe::new, 600);

                shapeless(RecipeCategory.MISC, Items.BONE_MEAL, 3)
                    .group("bonemeal")
                    .requires(ModItems.ANTLERS.get())
                    .unlockedBy("has_antlers", has(ModItems.ANTLERS.get()))
                    .save(recipeOutput, "deermod:bonemeal_from_antlers");

                shapeless(RecipeCategory.MISC, ModItems.DEER_BANNER_PATTERN.get())
                    .requires(Items.PAPER)
                    .requires(ModItems.ANTLERS.get())
                    .unlockedBy("has_antlers", has(ModItems.ANTLERS.get()))
                    .save(output);

                shaped(RecipeCategory.MISC, ModItems.DEER_CRACKERS.get())
                    .define('W', Items.WHEAT)
                    .define('S', Items.STRING)
                    .pattern(" S ")
                    .pattern("WWW")
                    .pattern(" S ")
                    .unlockedBy("has_wheat", has(Items.WHEAT))
                    .save(recipeOutput);

                shaped(RecipeCategory.MISC, ModItems.DEER_CRACKERS_ON_A_STICK.get())
                    .define('R', Items.FISHING_ROD)
                    .define('C', ModItems.DEER_CRACKERS.get())
                    .pattern("R ")
                    .pattern(" C")
                    .unlockedBy("has_deer_crackers", has(ModItems.DEER_CRACKERS.get()))
                    .save(output);

                shaped(RecipeCategory.MISC, ModItems.DEER_SPAWN_EGG.get())
                    .define('C', ModItems.DEER_CRACKERS.get())
                    .define('E', Items.EGG)
                    .pattern("CCC")
                    .pattern("CEC")
                    .pattern("CCC")
                    .unlockedBy("has_deer_crackers", has(ModItems.DEER_CRACKERS.get()))
                    .save(recipeOutput);
            }

            @Override
            public <T extends AbstractCookingRecipe> void cookRecipes(String cookingMethod, RecipeSerializer<T> recipeSerializer, AbstractCookingRecipe.Factory<T> factory, int cookingTime)
            {
                simpleCookingRecipe(cookingMethod, recipeSerializer, factory, cookingTime, ModItems.VENISON.get(), ModItems.COOKED_VENISON.get(), 0.35F);
            }
        };
    }

    @Override
    public @NotNull String getName()
    {
        return "UWU";
    }
}
