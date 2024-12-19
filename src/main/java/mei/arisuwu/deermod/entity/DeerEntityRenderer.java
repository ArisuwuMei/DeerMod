package mei.arisuwu.deermod.entity;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class DeerEntityRenderer extends GeoEntityRenderer<DeerEntity>
{
    public DeerEntityRenderer(EntityRendererFactory.Context context)
    {
        super(context, new DeerEntityModel());
    }

    @Override
    public void preRender(MatrixStack poseStack, DeerEntity entity, BakedGeoModel model, @Nullable VertexConsumerProvider bufferSource, @Nullable VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int renderColor)
    {
        if (entity.isBaby())
        {
            poseStack.scale(0.6f, 0.6f, 0.6f);
            model.getBone("antlers").ifPresent(geoBone -> geoBone.setHidden(true));
        }
        else
        {
            poseStack.scale(1f, 1f, 1f);
            model.getBone("antlers").ifPresent(geoBone -> geoBone.setHidden(entity.isSheared()));
        }

        super.preRender(poseStack, entity, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, renderColor);
    }
}
