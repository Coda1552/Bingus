package codyhuh.bingus;

import codyhuh.bingus.registry.CatVariantRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BingusMod.MOD_ID)
public class BingusMod {
    public static final String MOD_ID = "bingus";

    public BingusMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        CatVariantRegistry.VARIANTS.register(bus);

        MinecraftForge.EVENT_BUS.register(this);
    }
}
