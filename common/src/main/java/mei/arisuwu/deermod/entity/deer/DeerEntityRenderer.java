package mei.arisuwu.deermod.entity.deer;

import com.mojang.blaze3d.vertex.PoseStack;
import mei.arisuwu.deermod.ModResourceLocation;
import mei.arisuwu.deermod.ModModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class DeerEntityRenderer extends MobRenderer<DeerEntity, DeerEntityRenderState, DeerEntityModel>
{
    private final static float BASE_SHADOW_RADIUS = 0.75f;
    private final static float BABY_MULTIPLIER = 0.6f;

    public DeerEntityRenderer(EntityRendererProvider.Context context)
    {
        super(context, new DeerEntityModel(context.bakeLayer(ModModelLayers.DEER)), BASE_SHADOW_RADIUS);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(DeerEntityRenderState state)
    {
        return ModResourceLocation.of("textures/entity/deer/deer.png");
    }

    @Override
    public @NotNull DeerEntityRenderState createRenderState()
    {
        return new DeerEntityRenderState();
    }

    @Override
    public void extractRenderState(DeerEntity deerEntity, DeerEntityRenderState deerEntityRenderState, float delta)
    {
        super.extractRenderState(deerEntity, deerEntityRenderState, delta);
        deerEntityRenderState.sheared = deerEntity.isSheared();
        deerEntityRenderState.saddled = deerEntity.isSaddled();
        deerEntityRenderState.eatGrassAnimationState.copyFrom(deerEntity.eatGrassAnimationState);
    }

    @Override
    public void submit(DeerEntityRenderState livingEntityRenderState, PoseStack matrixStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState)
    {
        if(livingEntityRenderState.isBaby)
            matrixStack.scale(BABY_MULTIPLIER, BABY_MULTIPLIER, BABY_MULTIPLIER);
        else
            matrixStack.scale(1, 1, 1);

        super.submit(livingEntityRenderState, matrixStack, submitNodeCollector, cameraRenderState);
    }
}
