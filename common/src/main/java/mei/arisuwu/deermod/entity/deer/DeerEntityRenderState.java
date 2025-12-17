package mei.arisuwu.deermod.entity.deer;

//import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;

public class DeerEntityRenderState// extends LivingEntityRenderState
{
    public boolean hasRedNose;
    public boolean sheared;
    public boolean saddled;
    public AnimationState eatGrassAnimationState = new AnimationState();
}
