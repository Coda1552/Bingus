package codyhuh.bingus.client;

import codyhuh.bingus.BingusMod;
import codyhuh.bingus.client.models.MouseModel;
import codyhuh.bingus.client.renders.MouseRenderer;
import codyhuh.bingus.registry.ModEntities;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = BingusMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientModEvents {

    @SubscribeEvent
    public static void registerClient(FMLClientSetupEvent e) {
    }

    @SubscribeEvent
    public static void registerRenders(EntityRenderersEvent.RegisterRenderers e) {
        e.registerEntityRenderer(ModEntities.MOUSE.get(), MouseRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions e) {
        e.registerLayerDefinition(MouseModel.LAYER_LOCATION, MouseModel::createBodyLayer);
    }

}
