package mei.arisuwu.deermod.datagen;

import mei.arisuwu.deermod.ModIdentifier;
import mei.arisuwu.deermod.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.*;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider
{
    public ModModelProvider(FabricDataOutput output)
    {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) { }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator)
    {
        itemModelGenerator.generateFlatItem(ModItems.ANTLERS.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COOKED_VENISON.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.DEER_CRACKERS.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.DEER_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.VENISON.get(), ModelTemplates.FLAT_ITEM);

        generateLayeredItem(
            ModItems.DEER_BANNER_PATTERN.get(),
            ModelLocationUtils.getModelLocation(Items.PIGLIN_BANNER_PATTERN),
            ModIdentifier.of("item/deer_banner_pattern_overlay"),
            itemModelGenerator
        );

        generateLayeredItem(
            ModItems.DEER_CRACKERS_ON_A_STICK.get(),
            Identifier.withDefaultNamespace("item/handheld_rod"),
            ModelLocationUtils.getModelLocation(Items.FISHING_ROD),
            ModIdentifier.of("item/deer_crackers_on_a_stick_overlay"),
            itemModelGenerator
        );
    }

    private static void generateLayeredItem(Item item, Identifier parentModel, Identifier layer0, Identifier layer1, ItemModelGenerators itemModelGenerator)
    {
        var modelTemplate = new ModelTemplate(
            Optional.of(parentModel), Optional.empty(), TextureSlot.LAYER0, TextureSlot.LAYER1
        );

        var model = ItemModelUtils.plainModel(modelTemplate.create(
            item,
            TextureMapping.layered(layer0, layer1),
            itemModelGenerator.modelOutput
        ));

        itemModelGenerator.itemModelOutput.accept(item, model);
    }

    private static void generateLayeredItem(Item item, Identifier layer0, Identifier layer1, ItemModelGenerators itemModelGenerator)
    {
        generateLayeredItem(item, Identifier.withDefaultNamespace("item/generated"), layer0, layer1, itemModelGenerator);
    }
}
