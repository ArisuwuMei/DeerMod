package mei.arisuwu.deermod.datagen;

import mei.arisuwu.deermod.Mod;
import mei.arisuwu.deermod.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.block.model.Material;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider
{
    public ModModelProvider(FabricPackOutput output)
    {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators generators) { }

    @Override
    public void generateItemModels(ItemModelGenerators generators)
    {
        generators.generateFlatItem(ModItems.ANTLERS.get(), ModelTemplates.FLAT_ITEM);
        generators.generateFlatItem(ModItems.COOKED_VENISON.get(), ModelTemplates.FLAT_ITEM);
        generators.generateFlatItem(ModItems.DEER_CRACKERS.get(), ModelTemplates.FLAT_ITEM);
        generators.generateFlatItem(ModItems.DEER_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        generators.generateFlatItem(ModItems.VENISON.get(), ModelTemplates.FLAT_ITEM);

        generateLayeredItem(
            ModItems.DEER_BANNER_PATTERN.get(),
            ModelLocationUtils.getModelLocation(Items.PIGLIN_BANNER_PATTERN),
            Mod.identifier("item/deer_banner_pattern_overlay"),
            generators
        );

        generateLayeredItem(
            ModItems.DEER_CRACKERS_ON_A_STICK.get(),
            Identifier.withDefaultNamespace("item/handheld_rod"),
            ModelLocationUtils.getModelLocation(Items.FISHING_ROD),
            Mod.identifier("item/deer_crackers_on_a_stick_overlay"),
            generators
        );
    }

    private static void generateLayeredItem(Item item, Identifier parentModel, Identifier layer0, Identifier layer1, ItemModelGenerators generators)
    {
        var modelTemplate = new ModelTemplate(
            Optional.of(parentModel), Optional.empty(), TextureSlot.LAYER0, TextureSlot.LAYER1
        );

        var model = ItemModelUtils.plainModel(modelTemplate.create(
            item,
            TextureMapping.layered(new Material(layer0), new Material(layer1)),
            generators.modelOutput
        ));

        generators.itemModelOutput.accept(item, model);
    }

    private static void generateLayeredItem(Item item, Identifier layer0, Identifier layer1, ItemModelGenerators generators)
    {
        generateLayeredItem(item, Identifier.withDefaultNamespace("item/generated"), layer0, layer1, generators);
    }
}
