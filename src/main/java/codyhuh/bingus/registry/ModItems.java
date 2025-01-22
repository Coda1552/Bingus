package codyhuh.bingus.registry;

import codyhuh.bingus.BingusMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BingusMod.MOD_ID);

    // Misc.
    public static final RegistryObject<Item> MOUSE_SPAWN_EGG = ITEMS.register("mouse_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.MOUSE, 0x717272, 0xffa9a9, new Item.Properties()));
}
