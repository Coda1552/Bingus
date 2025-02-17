package codyhuh.glumbis.registry;

import codyhuh.glumbis.GlumbisMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, GlumbisMod.MOD_ID);

    public static final RegistryObject<SoundEvent> GLUMBOSS_AMBIENT = create("glumboss.ambient");
    public static final RegistryObject<SoundEvent> GLUMBOSS_HURT = create("glumboss.hurt");
    public static final RegistryObject<SoundEvent> GLUMBOSS_DEATH = create("glumboss.death");
    public static final RegistryObject<SoundEvent> GLUMBOSS_CHARGE = create("glumboss.charge");
    public static final RegistryObject<SoundEvent> GLUMBOSS_SLAM = create("glumboss.slam");

    public static final RegistryObject<SoundEvent> GLUMP_FLY = create("glump.fly");
    public static final RegistryObject<SoundEvent> GLUMP_HURT = create("glump.hurt");
    public static final RegistryObject<SoundEvent> GLUMP_EXPLODE = create("glump.explode");

    public static final RegistryObject<SoundEvent> BIG_SOCK_JUMP = create("big_sock.jump");
    public static final RegistryObject<SoundEvent> BIG_SOCK_SLAM = create("big_sock.slam");

    private static RegistryObject<SoundEvent> create(String name) {
        return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(GlumbisMod.MOD_ID, name)));
    }
}
