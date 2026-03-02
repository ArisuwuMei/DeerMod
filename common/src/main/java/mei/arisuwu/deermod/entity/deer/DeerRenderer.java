package mei.arisuwu.deermod.entity.deer;

import com.mojang.blaze3d.vertex.PoseStack;
import mei.arisuwu.deermod.Mod;
import mei.arisuwu.deermod.ModModelLayers;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class DeerRenderer extends MobRenderer<DeerEntity, DeerRenderState, DeerModel>
{
    private final static float BASE_SHADOW_RADIUS = 0.75f;
    private final static float BABY_MULTIPLIER = 0.6f;

    public DeerRenderer(EntityRendererProvider.Context context)
    {
        super(context, new DeerModel(context.bakeLayer(ModModelLayers.DEER)), BASE_SHADOW_RADIUS);
    }

    @Override
    public @NotNull Identifier getTextureLocation(DeerRenderState state)
    {
        return Mod.identifier("textures/entity/deer/deer.png");
    }

    @Override
    public @NotNull DeerRenderState createRenderState()
    {
        return new DeerRenderState();
    }

    @Override
    public void extractRenderState(DeerEntity deerEntity, DeerRenderState state, float partialTicks)
    {
        super.extractRenderState(deerEntity, state, partialTicks);
        state.hasRedNose = deerEntity.hasRedNose();
        state.sheared = deerEntity.isSheared();
        state.saddled = deerEntity.isSaddled();
        state.eatGrassAnimationState.copyFrom(deerEntity.eatGrassAnimationState);
    }

    @Override
    public void submit(DeerRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState)
    {
        if(state.isBaby)
            poseStack.scale(BABY_MULTIPLIER, BABY_MULTIPLIER, BABY_MULTIPLIER);
        else
            poseStack.scale(1, 1, 1);

        super.submit(state, poseStack, submitNodeCollector, cameraRenderState);
    }
}
