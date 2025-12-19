package mei.arisuwu.deermod.entity.deer;

import mei.arisuwu.deermod.ModResourceLocation;
import mei.arisuwu.deermod.ModModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class DeerEntityRenderer extends MobRenderer<DeerEntity, DeerEntityModel<DeerEntity>>
{
    private final static float BASE_SHADOW_RADIUS = 0.75f;

    public DeerEntityRenderer(EntityRendererProvider.Context context)
    {
        super(context, new DeerEntityModel<>(context.bakeLayer(ModModelLayers.DEER)), BASE_SHADOW_RADIUS);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(DeerEntity entity)
    {
        return ModResourceLocation.of("textures/entity/deer/deer.png");
    }
}
