package codyhuh.bingus.common;

import codyhuh.bingus.BingusMod;
import codyhuh.bingus.common.entities.Mouse;
import codyhuh.bingus.registry.ModEntities;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BingusMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonModEvents {

    @SubscribeEvent
    public static void createAttributes(EntityAttributeCreationEvent e) {
        e.put(ModEntities.MOUSE.get(), Mouse.createMouseAttributes().build());
    }
}
