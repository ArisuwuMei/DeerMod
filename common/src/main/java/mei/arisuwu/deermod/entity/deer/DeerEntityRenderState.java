package mei.arisuwu.deermod.entity.deer;

import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.AnimationState;

public class DeerEntityRenderState extends LivingEntityRenderState
{
    public boolean sheared;
    public boolean saddled;
    public AnimationState eatGrassAnimationState = new AnimationState();
}
