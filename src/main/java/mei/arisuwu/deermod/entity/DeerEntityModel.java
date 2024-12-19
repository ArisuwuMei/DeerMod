package mei.arisuwu.deermod.entity;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;
import software.bernie.geckolib.renderer.GeoRenderer;

import static mei.arisuwu.deermod.Mod.MOD_ID;

public class DeerEntityModel extends GeoModel<DeerEntity>
{
    @Override
    public Identifier getModelResource(DeerEntity deerEntity, @Nullable GeoRenderer<DeerEntity> geoRenderer)
    {
        return Identifier.of(MOD_ID, "geo/deer.geo.json");
    }

    @Override
    public Identifier getTextureResource(DeerEntity deerEntity, @Nullable GeoRenderer<DeerEntity> geoRenderer)
    {
        return Identifier.of(MOD_ID, "textures/deer.png");
    }

    @Override
    public Identifier getAnimationResource(DeerEntity deerEntity)
    {
        return Identifier.of(MOD_ID, "animations/deer.animation.json");
    }

    @Override
    public void setCustomAnimations(DeerEntity animatable, long instanceId, AnimationState<DeerEntity> animationState)
    {
        GeoBone headBone = getAnimationProcessor().getBone("head");

        if (headBone == null) return;

        EntityModelData data = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
        headBone.setRotX(data.headPitch() * MathHelper.RADIANS_PER_DEGREE);
        headBone.setRotY(data.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
    }
}
