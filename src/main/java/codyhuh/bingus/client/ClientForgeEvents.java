package codyhuh.bingus.client;

import codyhuh.bingus.BingusMod;
import codyhuh.bingus.client.models.MouseModel;
import codyhuh.bingus.common.entities.Mouse;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BingusMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientForgeEvents {

    @SubscribeEvent
    public static void preRenderMouse(RenderLivingEvent.Pre<Mouse, MouseModel<Mouse>> event) {
        if (ClientProxy.blockedEntityRenders.contains(event.getEntity().getUUID())) {
            MinecraftForge.EVENT_BUS.post(new RenderLivingEvent.Post<>(event.getEntity(), event.getRenderer(), event.getPartialTick(), event.getPoseStack(), event.getMultiBufferSource(), event.getPackedLight()));
            event.setCanceled(true);

            ClientProxy.blockedEntityRenders.remove(event.getEntity().getUUID());
        }
    }
}
