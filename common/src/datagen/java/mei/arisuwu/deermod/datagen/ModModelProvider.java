package mei.arisuwu.deermod.datagen;

import mei.arisuwu.deermod.ModItems;
import mei.arisuwu.deermod.ModResourceLocation;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.world.item.Items;

public class ModModelProvider extends FabricModelProvider
{
    public ModModelProvider(FabricDataOutput output)
    {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.delegateItemModel(
            ModItems.DEER_SPAWN_EGG.get(),
            ModelLocationUtils.decorateItemModelLocation("template_spawn_egg")
        );
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator)
    {
        itemModelGenerator.generateFlatItem(ModItems.ANTLERS.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COOKED_VENISON.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.DEER_CRACKERS.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.VENISON.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.DEER_BANNER_PATTERN.get(), Items.PIGLIN_BANNER_PATTERN, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateLayeredItem(
            ModelLocationUtils.getModelLocation(ModItems.DEER_CRACKERS_ON_A_STICK.get()),
            ModelLocationUtils.getModelLocation(Items.FISHING_ROD),
            ModResourceLocation.of("item/deer_crackers_on_a_stick_overlay")
        );
//
//        generateLayeredItem(
//            ModItems.DEER_CRACKERS_ON_A_STICK.get(),
//            ResourceLocation.tryBuild(ResourceLocation.DEFAULT_NAMESPACE, "item/handheld_rod"),
//            ModelLocationUtils.getModelLocation(Items.FISHING_ROD),
//            ModResourceLocation.of("item/deer_crackers_on_a_stick_overlay"),
//            itemModelGenerator
//        );
    }

//    private static void generateLayeredItem(Item item, ResourceLocation parentModel, ResourceLocation layer0, ResourceLocation layer1, ItemModelGenerators itemModelGenerator)
//    {
//        var modelTemplate = new ModelTemplate(
//            Optional.of(parentModel), Optional.empty(), TextureSlot.LAYER0, TextureSlot.LAYER1
//        );
//
//        var model = ItemModelUtils.plainModel(modelTemplate.create(
//            item,
//            TextureMapping.layered(layer0, layer1),
//            itemModelGenerator.modelOutput
//        ));
//
//        itemModelGenerator.itemModelOutput.accept(item, model);
//        itemModelGenerator.output.accept(item);
//    }
//
//    private static void generateLayeredItem(Item item, ResourceLocation layer0, ResourceLocation layer1, ItemModelGenerators itemModelGenerator)
//    {
//        generateLayeredItem(item, ResourceLocation.withDefaultNamespace("item/generated"), layer0, layer1, itemModelGenerator);
//    }
}
