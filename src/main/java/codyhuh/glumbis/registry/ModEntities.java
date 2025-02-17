package codyhuh.glumbis.registry;

import codyhuh.glumbis.GlumbisMod;
import codyhuh.glumbis.common.entities.Glumboss;
import codyhuh.glumbis.common.entities.Glump;
import codyhuh.glumbis.common.entities.Mouse;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, GlumbisMod.MOD_ID);

    public static final RegistryObject<EntityType<Mouse>> MOUSE = ENTITIES.register("mouse", () -> EntityType.Builder.of(Mouse::new, MobCategory.CREATURE).sized(0.25F, 0.25F).build("mouse"));

    public static final RegistryObject<EntityType<Glumboss>> GLUMBOSS = ENTITIES.register("glumboss", () -> EntityType.Builder.of(Glumboss::new, MobCategory.MONSTER).sized(2.4f, 3.8f).build("glumboss"));
    public static final RegistryObject<EntityType<Glump>> GLUMP = ENTITIES.register("glump", () -> EntityType.Builder.of(Glump::new, MobCategory.MONSTER).sized(0.6f, 0.9f).build("glump"));
}
