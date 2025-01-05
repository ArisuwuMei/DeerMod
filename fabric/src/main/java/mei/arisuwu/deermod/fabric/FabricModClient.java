package mei.arisuwu.deermod.fabric;

import mei.arisuwu.deermod.ModEntities;
import mei.arisuwu.deermod.ModModelLayers;
import mei.arisuwu.deermod.entity.deer.DeerEntityModel;
import mei.arisuwu.deermod.entity.deer.DeerEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

@SuppressWarnings("unused")
public final class FabricModClient implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.DEER, DeerEntityModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.DEER.get(), DeerEntityRenderer::new);
    }

}
