package mei.arisuwu.deermod.neoforge;

import mei.arisuwu.deermod.ModEntities;
import mei.arisuwu.deermod.ModModelLayers;
import mei.arisuwu.deermod.entity.deer.DeerEntity;
import mei.arisuwu.deermod.entity.deer.DeerEntityModel;
import mei.arisuwu.deermod.entity.deer.DeerEntityRenderer;
import net.minecraft.client.render.entity.EntityRenderers;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.Heightmap;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

import static mei.arisuwu.deermod.Mod.MOD_ID;

@net.neoforged.fml.common.Mod(MOD_ID)
public final class NeoforgeMod
{
    public NeoforgeMod(IEventBus modBus, ModContainer container)
    {
        mei.arisuwu.deermod.Mod.init();

        new NeoforgeModEntities(modBus);
        new NeoforgeModItems(modBus);
    }

    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            EntityRenderers.register(ModEntities.DEER.get(), DeerEntityRenderer::new);
        }
    }

    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD)
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
        public static void onRegisteringSpawnPlacements(RegisterSpawnPlacementsEvent event)
        {
            event.register(
                ModEntities.DEER.get(), SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn,
                RegisterSpawnPlacementsEvent.Operation.REPLACE
            );
        }
    }
}
