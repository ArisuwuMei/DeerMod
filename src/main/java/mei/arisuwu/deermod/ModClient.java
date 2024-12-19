package mei.arisuwu.deermod;


import mei.arisuwu.deermod.entity.DeerEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class ModClient implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        EntityRendererRegistry.register(ModEntities.DEER, DeerEntityRenderer::new);
    }
}
