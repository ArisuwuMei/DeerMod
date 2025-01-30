package mei.arisuwu.deermod.entity.deer;

import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.QuadrupedEntityModel;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.render.entity.model.SinglePartEntityModelWithChildTransform;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class DeerEntityModel extends SinglePartEntityModelWithChildTransform<DeerEntity>
{
    private final ModelPart root;
    private final ModelPart neck;
    private final ModelPart head;
    private final ModelPart antlers;
    private final ModelPart body;
    private final ModelPart rightFrontLeg;
    private final ModelPart leftFrontLeg;
    private final ModelPart rightHindLeg;
    private final ModelPart leftHindLeg;
    private final ModelPart saddle;
    private float neckAngle;

    public DeerEntityModel(ModelPart root)
    {
        super(0.6f, 16f);
        this.root = root;
        this.neck = root.getChild("neck");
        this.head = this.neck.getChild("head");
        this.antlers = this.head.getChild("antlers");
        this.body = root.getChild("body");
        this.rightFrontLeg = this.body.getChild("right_front_leg");
        this.leftFrontLeg = this.body.getChild("left_front_leg");
        this.rightHindLeg = this.body.getChild("right_hind_leg");
        this.leftHindLeg = this.body.getChild("left_hind_leg");
        this.saddle = root.getChild("saddle");
    }

    public static TexturedModelData getTexturedModelData()
    {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData neck = modelPartData.addChild("neck", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 9.0F, -11.0F));

        ModelPartData neck_r1 = neck.addChild("neck_r1", ModelPartBuilder.create().uv(32, 43).cuboid(-2.0F, -8.0F, -1.5F, 4.0F, 10.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.5F, -0.5F, 0.192F, 0.0F, 0.0F));

        ModelPartData head = neck.addChild("head", ModelPartBuilder.create().uv(32, 31).cuboid(-3.0F, -3.0F, -4.0F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F))
            .uv(14, 47).cuboid(-2.0F, 0.0F, -7.0F, 4.0F, 3.0F, 3.0F, new Dilation(0.0F))
            .uv(0, 54).cuboid(-1.0F, 0.0F, -8.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -13.0F, -1.0F));

        ModelPartData right_ear = head.addChild("right_ear", ModelPartBuilder.create(), ModelTransform.pivot(-4.0F, -2.0F, 1.0F));

        ModelPartData ear_r1 = right_ear.addChild("ear_r1", ModelPartBuilder.create().uv(6, 54).cuboid(-4.0F, -2.0F, -1.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F))
            .uv(22, 53).cuboid(-2.0F, -2.0F, -1.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.6981F));

        ModelPartData left_ear = head.addChild("left_ear", ModelPartBuilder.create(), ModelTransform.pivot(4.0F, -2.0F, 1.0F));

        ModelPartData ear_r2 = left_ear.addChild("ear_r2", ModelPartBuilder.create().uv(6, 54).mirrored().cuboid(2.0F, -2.0F, -1.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
            .uv(22, 53).mirrored().cuboid(-1.0F, -2.0F, -1.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.3927F, -0.6981F));

        ModelPartData antlers = head.addChild("antlers", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 28.0F, 12.0F));

        ModelPartData antlers_l = antlers.addChild("antlers_l", ModelPartBuilder.create().uv(28, 47).mirrored().cuboid(0.0F, -2.0F, -23.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
            .uv(28, 47).mirrored().cuboid(1.0F, -4.0F, -22.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
            .uv(28, 47).mirrored().cuboid(2.0F, -5.0F, -21.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
            .uv(28, 50).mirrored().cuboid(2.0F, -7.0F, -22.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
            .uv(28, 47).mirrored().cuboid(3.0F, -6.0F, -21.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
            .uv(28, 47).mirrored().cuboid(4.0F, -7.0F, -22.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
            .uv(28, 50).mirrored().cuboid(5.0F, -8.0F, -23.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
            .uv(28, 50).mirrored().cuboid(0.0F, -5.0F, -21.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(1.0F, -31.0F, 11.0F));

        ModelPartData antlers_r = antlers.addChild("antlers_r", ModelPartBuilder.create().uv(28, 47).cuboid(-2.0F, -33.0F, -12.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
            .uv(28, 47).cuboid(-3.0F, -35.0F, -11.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
            .uv(28, 47).cuboid(-4.0F, -36.0F, -10.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
            .uv(28, 50).cuboid(-4.0F, -38.0F, -11.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
            .uv(28, 47).cuboid(-5.0F, -37.0F, -10.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
            .uv(28, 47).cuboid(-6.0F, -38.0F, -11.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
            .uv(28, 50).cuboid(-7.0F, -39.0F, -12.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
            .uv(28, 50).cuboid(-2.0F, -36.0F, -11.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 47).cuboid(-2.0F, -21.0F, 8.0F, 4.0F, 4.0F, 3.0F, new Dilation(0.0F))
            .uv(14, 53).cuboid(-1.0F, -23.0F, 10.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F))
            .uv(0, 0).cuboid(-4.0F, -19.0F, -13.0F, 8.0F, 9.0F, 22.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData right_front_leg = body.addChild("right_front_leg", ModelPartBuilder.create().uv(48, 43).cuboid(-0.5F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.5F, -10.0F, -10.0F));
        ModelPartData left_front_leg = body.addChild("left_front_leg", ModelPartBuilder.create().uv(48, 43).mirrored().cuboid(-1.5F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(3.5F, -10.0F, -10.0F));
        ModelPartData right_hind_leg = body.addChild("right_hind_leg", ModelPartBuilder.create().uv(48, 43).cuboid(-0.5F, 0.0F, -2.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.5F, -10.0F, 8.0F));
        ModelPartData left_hind_leg = body.addChild("left_hind_leg", ModelPartBuilder.create().uv(48, 43).mirrored().cuboid(-1.5F, 0.0F, -2.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(3.5F, -10.0F, 8.0F));
        ModelPartData saddle = modelPartData.addChild("saddle", ModelPartBuilder.create().uv(0, 31).cuboid(-4.0F, -5.0F, -5.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.22F)), ModelTransform.pivot(0.0F, 10.0F, 0.0F));

        return TexturedModelData.of(modelData, 64, 64);
    }

    private static final Animation EAT_GRASS = Animation.Builder.create(2.0F)
        .addBoneAnimation("neck", new Transformation(Transformation.Targets.ROTATE,
            new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.4167F, AnimationHelper.createRotationalVector(90.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
            new Keyframe(0.75F, AnimationHelper.createRotationalVector(105.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.9167F, AnimationHelper.createRotationalVector(95.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.0833F, AnimationHelper.createRotationalVector(105.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.25F, AnimationHelper.createRotationalVector(95.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.4167F, AnimationHelper.createRotationalVector(105.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
        )).build();


    @Override
    public void animateModel(DeerEntity entity, float limbAngle, float limbDistance, float tickDelta)
    {
        super.animateModel(entity, limbAngle, limbDistance, tickDelta);
        this.neckAngle = entity.getNeckAngle(tickDelta);
    }

    @Override
    public void setAngles(DeerEntity deerEntity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch)
    {
        //this.neck.resetTransform();

        this.head.pitch = headPitch * (float) (Math.PI / 180.0);
        this.head.yaw = headYaw * (float) (Math.PI / 180.0);
        this.rightHindLeg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
        this.leftHindLeg.pitch = MathHelper.cos(limbAngle * 0.6662F + (float) Math.PI) * 1.4F * limbDistance;
        this.rightFrontLeg.pitch = MathHelper.cos(limbAngle * 0.6662F + (float) Math.PI) * 1.4F * limbDistance;
        this.leftFrontLeg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;

        this.antlers.visible = !deerEntity.isSheared() && !deerEntity.isBaby();
        this.saddle.visible = deerEntity.isSaddled();

        //this.updateAnimation(deerEntity.eatGrassAnimationState, EAT_GRASS, animationProgress, 0.1f);
        //this.animate(EAT_GRASS);
        this.neck.setAngles(neckAngle, 0, 0);
    }

    @Override
    public ModelPart getPart()
    {
        return this.root;
    }
}