package mei.arisuwu.deermod.neoforge;

import mei.arisuwu.deermod.ModEntities;
import mei.arisuwu.deermod.ModModelLayers;
import mei.arisuwu.deermod.entity.deer.DeerEntity;
import mei.arisuwu.deermod.entity.deer.DeerModel;
import mei.arisuwu.deermod.entity.deer.DeerRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

import static mei.arisuwu.deermod.Mod.MOD_ID;

@net.neoforged.fml.common.Mod(MOD_ID)
public final class NeoforgeMod
{
    public NeoforgeMod(IEventBus modBus)
    {
        new NeoforgeModEntities(modBus);
        new NeoforgeModItems(modBus);
        new NeoforgeModCreativeTabs(modBus);
    }

    @EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT)
    public static class ClientEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            EntityRenderers.register(ModEntities.DEER.get(), DeerRenderer::new);
        }
    }

    @EventBusSubscriber(modid = MOD_ID)
    public static class Events
    {
        @SubscribeEvent
        public static void onRegisteringLayers(EntityRenderersEvent.RegisterLayerDefinitions event)
        {
            event.registerLayerDefinition(ModModelLayers.DEER, DeerModel::getTexturedModelData);
        }

        @SubscribeEvent
        public static void onRegisteringAttributes(EntityAttributeCreationEvent event)
        {
            event.put(ModEntities.DEER.get(), DeerEntity.createAttributes().build());
        }

        @SubscribeEvent
        public static void onRegisteringSpawnPlacements(RegisterSpawnPlacementsEvent event)
        {
            event.register(
                ModEntities.DEER.get(), SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.REPLACE
            );
        }
    }
}
