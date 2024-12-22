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
    private final static float BASE_SHADOW_RADIUS = 0.75f;
    private final static float BABY_MULTIPLIER = 0.6f;

    public DeerEntityRenderer(EntityRendererFactory.Context context)
    {
        super(context, new DeerEntityModel());
    }

    @Override
    public void preRender(MatrixStack poseStack, DeerEntity entity, BakedGeoModel model, @Nullable VertexConsumerProvider bufferSource, @Nullable VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int renderColor)
    {
        if (entity.isBaby())
        {
            poseStack.scale(BABY_MULTIPLIER, BABY_MULTIPLIER, BABY_MULTIPLIER);
            model.getBone("antlers").ifPresent(geoBone -> geoBone.setHidden(true));
            shadowRadius = BASE_SHADOW_RADIUS * BABY_MULTIPLIER;
        }
        else
        {
            poseStack.scale(1f, 1f, 1f);
            model.getBone("antlers").ifPresent(geoBone -> geoBone.setHidden(entity.isSheared()));
            shadowRadius = BASE_SHADOW_RADIUS;
        }

        super.preRender(poseStack, entity, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, renderColor);
    }
}
