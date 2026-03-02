package mei.arisuwu.deermod.entity.deer;

import net.minecraft.client.animation.*;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.util.Mth;

import static net.minecraft.client.animation.AnimationChannel.Interpolations;
import static net.minecraft.client.animation.AnimationChannel.Targets;

public class DeerModel extends EntityModel<DeerRenderState> {
    private final ModelPart head;
    private final ModelPart redNose;
    private final ModelPart antlers;
    private final ModelPart rightFrontLeg;
    private final ModelPart leftFrontLeg;
    private final ModelPart rightHindLeg;
    private final ModelPart leftHindLeg;
    private final ModelPart saddle;
    private final KeyframeAnimation eatGrassAnimation;

    public DeerModel(ModelPart root) {
        super(root);
        var neck = root.getChild("neck");
        this.head = neck.getChild("head");
        this.redNose = this.head.getChild("red_nose");
        this.antlers = this.head.getChild("antlers");
        var body = root.getChild("body");
        this.rightFrontLeg = body.getChild("right_front_leg");
        this.leftFrontLeg = body.getChild("left_front_leg");
        this.rightHindLeg = body.getChild("right_hind_leg");
        this.leftHindLeg = body.getChild("left_hind_leg");
        this.saddle = root.getChild("saddle");

        this.eatGrassAnimation = EAT_GRASS.bake(root);
    }

    public static LayerDefinition getTexturedModelData() {
        var mesh = new MeshDefinition();
        var rootPart = mesh.getRoot();
        var neck = rootPart.addOrReplaceChild("neck", CubeListBuilder.create(), PartPose.offset(0.0F, 9.0F, -11.0F));

        var neck_r1 = neck.addOrReplaceChild("neck_r1", CubeListBuilder.create().texOffs(32, 43).addBox(-2.0F, -10.0F, -1.5F, 4.0F, 12.0F, 4.0F), PartPose.offsetAndRotation(0.0F, -2.5F, -0.5F, 0.2356F, 0.0F, 0.0F));

        var head = neck.addOrReplaceChild("head", CubeListBuilder.create()
            .texOffs(32, 31).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F)
            .texOffs(14, 47).addBox(-2.0F, 0.0F, -6.0F, 4.0F, 3.0F, 3.0F), PartPose.offset(0.0F, -13.0F, -2.0F));

        var nose = head.addOrReplaceChild("nose", CubeListBuilder.create()
            .texOffs(0, 54).addBox(-1.0F, -2.0F, -2.0F, 2.0F, 2.0F, 1.0F), PartPose.offset(0.0F, 2.0F, -5.0F));

        var red_nose = head.addOrReplaceChild("red_nose", CubeListBuilder.create()
            .texOffs(0, 57).addBox(-1.0F, -2.0F, -2.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.01f)), PartPose.offset(0.0F, 2.0F, -5.0F));

        var right_ear = head.addOrReplaceChild("right_ear", CubeListBuilder.create(), PartPose.offset(-4.0F, -2.0F, 1.0F));

        var ear_r1 = right_ear.addOrReplaceChild("ear_r1", CubeListBuilder.create()
            .texOffs(6, 54).addBox(-4.0F, -2.0F, -1.0F, 2.0F, 2.0F, 1.0F)
            .texOffs(22, 53).addBox(-2.0F, -2.0F, -1.0F, 3.0F, 3.0F, 1.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.6981F));

        var left_ear = head.addOrReplaceChild("left_ear", CubeListBuilder.create(), PartPose.offset(4.0F, -2.0F, 1.0F));

        var ear_r2 = left_ear.addOrReplaceChild("ear_r2", CubeListBuilder.create()
            .texOffs(6, 54).mirror().addBox(2.0F, -2.0F, -1.0F, 2.0F, 2.0F, 1.0F).mirror(false)
            .texOffs(22, 53).mirror().addBox(-1.0F, -2.0F, -1.0F, 3.0F, 3.0F, 1.0F).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3927F, -0.6981F));

        var antlers = head.addOrReplaceChild("antlers", CubeListBuilder.create(), PartPose.offset(0.0F, 28.0F, 12.0F));

        antlers.addOrReplaceChild("antlers_l", CubeListBuilder.create()
            .texOffs(28, 47).mirror().addBox(0.0F, -2.0F, -23.0F, 1.0F, 2.0F, 1.0F).mirror(false)
            .texOffs(28, 47).mirror().addBox(1.0F, -4.0F, -22.0F, 1.0F, 2.0F, 1.0F).mirror(false)
            .texOffs(28, 47).mirror().addBox(2.0F, -5.0F, -21.0F, 1.0F, 2.0F, 1.0F).mirror(false)
            .texOffs(28, 50).mirror().addBox(2.0F, -7.0F, -22.0F, 1.0F, 1.0F, 1.0F).mirror(false)
            .texOffs(28, 47).mirror().addBox(3.0F, -6.0F, -21.0F, 1.0F, 2.0F, 1.0F).mirror(false)
            .texOffs(28, 47).mirror().addBox(4.0F, -7.0F, -22.0F, 1.0F, 2.0F, 1.0F).mirror(false)
            .texOffs(28, 50).mirror().addBox(5.0F, -8.0F, -23.0F, 1.0F, 1.0F, 1.0F).mirror(false)
            .texOffs(28, 50).mirror().addBox(0.0F, -5.0F, -21.0F, 1.0F, 1.0F, 1.0F).mirror(false), PartPose.offset(1.0F, -31.0F, 11.0F));

        antlers.addOrReplaceChild("antlers_r", CubeListBuilder.create()
            .texOffs(28, 47).addBox(-2.0F, -33.0F, -12.0F, 1.0F, 2.0F, 1.0F)
            .texOffs(28, 47).addBox(-3.0F, -35.0F, -11.0F, 1.0F, 2.0F, 1.0F)
            .texOffs(28, 47).addBox(-4.0F, -36.0F, -10.0F, 1.0F, 2.0F, 1.0F)
            .texOffs(28, 50).addBox(-4.0F, -38.0F, -11.0F, 1.0F, 1.0F, 1.0F)
            .texOffs(28, 47).addBox(-5.0F, -37.0F, -10.0F, 1.0F, 2.0F, 1.0F)
            .texOffs(28, 47).addBox(-6.0F, -38.0F, -11.0F, 1.0F, 2.0F, 1.0F)
            .texOffs(28, 50).addBox(-7.0F, -39.0F, -12.0F, 1.0F, 1.0F, 1.0F)
            .texOffs(28, 50).addBox(-2.0F, -36.0F, -11.0F, 1.0F, 1.0F, 1.0F), PartPose.ZERO);

        var body = rootPart.addOrReplaceChild("body", CubeListBuilder.create()
            .texOffs(0, 47).addBox(-2.0F, -21.0F, 8.0F, 4.0F, 4.0F, 3.0F)
            .texOffs(14, 53).addBox(-1.0F, -23.0F, 10.0F, 2.0F, 4.0F, 2.0F)
            .texOffs(0, 0).addBox(-4.0F, -19.0F, -13.0F, 8.0F, 9.0F, 22.0F), PartPose.offset(0.0F, 24.0F, 0.0F));

        body.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(48, 43).addBox(-0.5F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F), PartPose.offset(-3.5F, -10.0F, -10.0F));

        body.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(48, 43).mirror().addBox(-1.5F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F).mirror(false), PartPose.offset(3.5F, -10.0F, -10.0F));

        body.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(48, 43).addBox(-0.5F, 0.0F, -2.0F, 2.0F, 10.0F, 2.0F), PartPose.offset(-3.5F, -10.0F, 8.0F));

        body.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(48, 43).mirror().addBox(-1.5F, 0.0F, -2.0F, 2.0F, 10.0F, 2.0F).mirror(false), PartPose.offset(3.5F, -10.0F, 8.0F));

        rootPart.addOrReplaceChild("saddle", CubeListBuilder.create().texOffs(0, 31).addBox(-4.0F, -5.0F, -5.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.22F)), PartPose.offset(0.0F, 10.0F, 0.0F));

        return LayerDefinition.create(mesh, 64, 64);
    }

    private static final AnimationDefinition EAT_GRASS = AnimationDefinition.Builder.withLength(2.0F)
        .addAnimation("neck", new AnimationChannel(Targets.ROTATION,
            new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), Interpolations.LINEAR),
            new Keyframe(0.4167F, KeyframeAnimations.degreeVec(90.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
            new Keyframe(0.75F, KeyframeAnimations.degreeVec(105.0F, 0.0F, 0.0F), Interpolations.LINEAR),
            new Keyframe(0.9167F, KeyframeAnimations.degreeVec(95.0F, 0.0F, 0.0F), Interpolations.LINEAR),
            new Keyframe(1.0833F, KeyframeAnimations.degreeVec(105.0F, 0.0F, 0.0F), Interpolations.LINEAR),
            new Keyframe(1.25F, KeyframeAnimations.degreeVec(95.0F, 0.0F, 0.0F), Interpolations.LINEAR),
            new Keyframe(1.4167F, KeyframeAnimations.degreeVec(105.0F, 0.0F, 0.0F), Interpolations.LINEAR),
            new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
        )).build();

    @Override
    public void setupAnim(DeerRenderState state) {
        super.setupAnim(state);
        this.saddle.visible = state.saddled;
        this.head.xRot = state.xRot * (float)(Math.PI / 180.0);
        this.head.yRot = state.yRot * (float)(Math.PI / 180.0);
        float f = state.walkAnimationPos;
        float g = state.walkAnimationSpeed;
        this.rightHindLeg.xRot = Mth.cos(f * 0.6662F) * 1.4F * g;
        this.leftHindLeg.xRot = Mth.cos(f * 0.6662F + (float)Math.PI) * 1.4F * g;
        this.rightFrontLeg.xRot = Mth.cos(f * 0.6662F + (float)Math.PI) * 1.4F * g;
        this.leftFrontLeg.xRot = Mth.cos(f * 0.6662F) * 1.4F * g;
        this.redNose.visible = state.hasRedNose;
        this.antlers.visible = !state.sheared && !state.isBaby;
        this.eatGrassAnimation.apply(state.eatGrassAnimationState, state.ageInTicks);
    }
}