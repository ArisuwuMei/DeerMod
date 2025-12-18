package mei.arisuwu.deermod.forge;

import mei.arisuwu.deermod.ModBannerPatterns;
import mei.arisuwu.deermod.ModEntities;
import mei.arisuwu.deermod.ModModelLayers;
import mei.arisuwu.deermod.ModPaintingVariants;
import mei.arisuwu.deermod.entity.deer.DeerEntity;
import mei.arisuwu.deermod.entity.deer.DeerEntityModel;
import mei.arisuwu.deermod.entity.deer.DeerEntityRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

import static mei.arisuwu.deermod.Mod.MOD_ID;

@Mod("deermod")
public class ForgeMod
{
    public ForgeMod(FMLJavaModLoadingContext context)
    {
        new ForgeEntities(context);
        new ForgeItems(context);
        new ForgeModCreativeTabs(context);
        new ForgeModBannerPatterns(context);
        new ForgeModPaintingVariants(context);
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            EntityRenderers.register(ModEntities.DEER.get(), DeerEntityRenderer::new);
        }
    }


    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class Events
    {
        @SubscribeEvent
        public static void onRegisteringLayers(EntityRenderersEvent.RegisterLayerDefinitions event)
        {
            event.registerLayerDefinition(ModModelLayers.DEER, DeerEntityModel::getTexturedModelData);
        }

        @SubscribeEvent
        public static void onRegisteringAttributes(EntityAttributeCreationEvent event)
        {
            event.put(ModEntities.DEER.get(), DeerEntity.createAttributes().build());
        }

        @SubscribeEvent
        public static void onRegisteringSpawnPlacements(SpawnPlacementRegisterEvent event)
        {
            event.register(
                ModEntities.DEER.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules,
                SpawnPlacementRegisterEvent.Operation.REPLACE
            );
        }
    }
}
