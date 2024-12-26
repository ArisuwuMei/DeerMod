package mei.arisuwu.deermod;

import mei.arisuwu.deermod.entity.DeerEntityModel;
import mei.arisuwu.deermod.entity.DeerEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

@SuppressWarnings("unused")
public class ModClient implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        EntityModelLayerRegistry.registerModelLayer(ModEntities.ModelLayers.DEER, DeerEntityModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.DEER, DeerEntityRenderer::new);
    }
}
