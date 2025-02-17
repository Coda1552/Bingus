package codyhuh.glumbis;

import codyhuh.glumbis.client.ClientProxy;
import codyhuh.glumbis.common.CommonProxy;
import codyhuh.glumbis.registry.ModCatVariants;
import codyhuh.glumbis.registry.ModEntities;
import codyhuh.glumbis.registry.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(GlumbisMod.MOD_ID)
public class GlumbisMod {
    public static final String MOD_ID = "glumbis";
    public static CommonProxy PROXY = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new); // Proxy code from Alex's Caves

    public GlumbisMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCatVariants.VARIANTS.register(bus);
        ModEntities.ENTITIES.register(bus);
        ModItems.ITEMS.register(bus);

        MinecraftForge.EVENT_BUS.register(this);
    }
}
