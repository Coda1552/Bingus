package codyhuh.glumbis.registry;

import codyhuh.glumbis.GlumbisMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, GlumbisMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TAB = CREATIVE_TABS.register("wheezies_woods_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup." + GlumbisMod.MOD_ID))
            .icon(ModItems.MOUSE_SPAWN_EGG.get().asItem()::getDefaultInstance)
            .displayItems((itemDisplayParameters, output) -> {
                ModItems.ITEMS.getEntries().forEach(itemRegistryObject -> output.accept(itemRegistryObject.get()));
            })
            .build()
    );

}
