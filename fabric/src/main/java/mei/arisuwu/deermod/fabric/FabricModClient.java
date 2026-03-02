package mei.arisuwu.deermod.fabric;

import mei.arisuwu.deermod.ModEntities;
import mei.arisuwu.deermod.ModModelLayers;
import mei.arisuwu.deermod.entity.deer.DeerModel;
import mei.arisuwu.deermod.entity.deer.DeerRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.renderer.entity.EntityRenderers;

@SuppressWarnings("unused")
public final class FabricModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModelLayerRegistry.registerModelLayer(ModModelLayers.DEER, DeerModel::getTexturedModelData);
        EntityRenderers.register(ModEntities.DEER.get(), DeerRenderer::new);
    }
}
