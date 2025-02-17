package codyhuh.glumbis.client;

import codyhuh.glumbis.GlumbisMod;
import codyhuh.glumbis.client.models.GlumbossModel;
import codyhuh.glumbis.client.models.MouseModel;
import codyhuh.glumbis.client.particle.StaticElectricityParticle;
import codyhuh.glumbis.client.renders.GlumbossRenderer;
import codyhuh.glumbis.client.renders.MouseRenderer;
import codyhuh.glumbis.client.renders.layers.CarriedMouseLayer;
import codyhuh.glumbis.registry.ModEntities;
import codyhuh.glumbis.registry.ModParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = GlumbisMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientModEvents {

    @SubscribeEvent
    public static void addRenderLayers(EntityRenderersEvent.AddLayers e) {
        var cat = e.getRenderer(EntityType.CAT);
        if (cat != null) {
            cat.addLayer(new CarriedMouseLayer(cat));
        }
        var ocelot = e.getRenderer(EntityType.OCELOT);
        if (ocelot != null) {
            ocelot.addLayer(new CarriedMouseLayer(ocelot));
        }
    }

    @SubscribeEvent
    public static void registerClient(FMLClientSetupEvent e) {
    }

    @SubscribeEvent
    public static void registerRenders(EntityRenderersEvent.RegisterRenderers e) {
        e.registerEntityRenderer(ModEntities.MOUSE.get(), MouseRenderer::new);
        e.registerEntityRenderer(ModEntities.GLUMBOSS.get(), GlumbossRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions e) {
        e.registerLayerDefinition(MouseModel.LAYER_LOCATION, MouseModel::createBodyLayer);
        e.registerLayerDefinition(GlumbossModel.LAYER_LOCATION, GlumbossModel::createBodyLayer);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerParticleTypes(RegisterParticleProvidersEvent event) {
        ParticleEngine engine = Minecraft.getInstance().particleEngine;
        event.registerSpriteSet(ModParticles.STATIC_LIGHTNING.get(), StaticElectricityParticle.Provider::new);
    }

}
