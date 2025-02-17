package codyhuh.glumbis.common;

import codyhuh.glumbis.GlumbisMod;
import codyhuh.glumbis.common.entities.Mouse;
import codyhuh.glumbis.registry.ModEntities;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GlumbisMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonModEvents {

    @SubscribeEvent
    public static void createAttributes(EntityAttributeCreationEvent e) {
        e.put(ModEntities.MOUSE.get(), Mouse.createMouseAttributes().build());
    }
}
