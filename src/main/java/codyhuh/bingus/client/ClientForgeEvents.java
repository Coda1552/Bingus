package codyhuh.bingus.client;

import codyhuh.bingus.BingusMod;
import codyhuh.bingus.client.models.MouseModel;
import codyhuh.bingus.client.renders.MouseRenderer;
import codyhuh.bingus.client.renders.layers.CarriedMouseLayer;
import codyhuh.bingus.common.entities.Mouse;
import codyhuh.bingus.registry.ModEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.OcelotModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = BingusMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientForgeEvents {

    @SubscribeEvent
    public static void addRenderLayers(EntityRenderersEvent.AddLayers e) {
        var cat = e.getRenderer(EntityType.CAT);
        cat.addLayer(new CarriedMouseLayer<>((RenderLayerParent) cat));

        var ocelot = e.getRenderer(EntityType.OCELOT);
        ocelot.addLayer(new CarriedMouseLayer<>((RenderLayerParent) ocelot));
    }

    @SubscribeEvent
    public static void preRenderMouse(RenderLivingEvent.Pre<Mouse, MouseModel<Mouse>> event) {
        if (ClientProxy.blockedEntityRenders.contains(event.getEntity().getUUID())) {
            MinecraftForge.EVENT_BUS.post(new RenderLivingEvent.Post<>(event.getEntity(), event.getRenderer(), event.getPartialTick(), event.getPoseStack(), event.getMultiBufferSource(), event.getPackedLight()));
            event.setCanceled(true);

            ClientProxy.blockedEntityRenders.remove(event.getEntity().getUUID());
        }
    }
}
