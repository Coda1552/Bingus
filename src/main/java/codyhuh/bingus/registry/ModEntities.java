package codyhuh.bingus.registry;

import codyhuh.bingus.BingusMod;
import codyhuh.bingus.common.entities.Mouse;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, BingusMod.MOD_ID);

    public static final RegistryObject<EntityType<Mouse>> MOUSE = ENTITIES.register("mouse", () -> EntityType.Builder.of(Mouse::new, MobCategory.CREATURE).sized(0.25F, 0.25F).build("mouse"));
}
