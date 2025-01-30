package mei.arisuwu.deermod.entity.deer;

import mei.arisuwu.deermod.ModIdentifier;
import mei.arisuwu.deermod.ModModelLayers;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class DeerEntityRenderer extends MobEntityRenderer<DeerEntity, DeerEntityModel>
{
    private final static float BASE_SHADOW_RADIUS = 0.75f;
    private final static float BABY_MULTIPLIER = 0.6f;

    public DeerEntityRenderer(EntityRendererFactory.Context context)
    {
        super(context, new DeerEntityModel(context.getPart(ModModelLayers.DEER)), BASE_SHADOW_RADIUS);
    }

    @Override
    public Identifier getTexture(DeerEntity entity)
    {
        return ModIdentifier.of("textures/entity/deer/deer.png");
    }


//    @Override
//    public void render(DeerEntityRenderState livingEntityRenderState, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i)
//    {
//        if(livingEntityRenderState.baby)
//            matrixStack.scale(BABY_MULTIPLIER, BABY_MULTIPLIER, BABY_MULTIPLIER);
//        else
//            matrixStack.scale(1, 1, 1);
//
//        super.render(livingEntityRenderState, matrixStack, vertexConsumerProvider, i);
//    }
}
