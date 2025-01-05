package mei.arisuwu.deermod.entity.deer;

import mei.arisuwu.deermod.ModIdentifier;
import mei.arisuwu.deermod.ModModelLayers;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class DeerEntityRenderer extends MobEntityRenderer<DeerEntity, DeerEntityRenderState, DeerEntityModel>
{
    private final static float BASE_SHADOW_RADIUS = 0.75f;
    private final static float BABY_MULTIPLIER = 0.6f;

    public DeerEntityRenderer(EntityRendererFactory.Context context)
    {
        super(context, new DeerEntityModel(context.getPart(ModModelLayers.DEER)), BASE_SHADOW_RADIUS);
    }

    @Override
    public Identifier getTexture(DeerEntityRenderState state)
    {
        return ModIdentifier.of("textures/entity/deer/deer.png");
    }

    @Override
    public DeerEntityRenderState createRenderState()
    {
        return new DeerEntityRenderState();
    }

    @Override
    public void updateRenderState(DeerEntity deerEntity, DeerEntityRenderState deerEntityRenderState, float delta)
    {
        super.updateRenderState(deerEntity, deerEntityRenderState, delta);
        deerEntityRenderState.sheared = deerEntity.isSheared();
        deerEntityRenderState.saddled = deerEntity.isSaddled();
        deerEntityRenderState.eatGrassAnimationState.copyFrom(deerEntity.eatGrassAnimationState);
    }

    @Override
    public void render(DeerEntityRenderState livingEntityRenderState, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i)
    {
        if(livingEntityRenderState.baby)
            matrixStack.scale(BABY_MULTIPLIER, BABY_MULTIPLIER, BABY_MULTIPLIER);
        else
            matrixStack.scale(1, 1, 1);

        super.render(livingEntityRenderState, matrixStack, vertexConsumerProvider, i);
    }
}
