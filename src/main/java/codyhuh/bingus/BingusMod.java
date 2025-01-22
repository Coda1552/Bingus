package codyhuh.bingus;

import codyhuh.bingus.client.ClientProxy;
import codyhuh.bingus.common.CommonProxy;
import codyhuh.bingus.registry.ModCatVariants;
import codyhuh.bingus.registry.ModEntities;
import codyhuh.bingus.registry.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BingusMod.MOD_ID)
public class BingusMod {
    public static final String MOD_ID = "bingus";
    public static CommonProxy PROXY = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new); // Proxy code from Alex's Caves

    public BingusMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCatVariants.VARIANTS.register(bus);
        ModEntities.ENTITIES.register(bus);
        ModItems.ITEMS.register(bus);

        MinecraftForge.EVENT_BUS.register(this);
    }
}
