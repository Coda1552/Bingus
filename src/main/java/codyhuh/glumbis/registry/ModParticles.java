package codyhuh.glumbis.registry;

import codyhuh.glumbis.GlumbisMod;
import codyhuh.glumbis.client.particle.StaticElectricityParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, GlumbisMod.MOD_ID);

    public static final RegistryObject<SimpleParticleType> STATIC_LIGHTNING = PARTICLES.register("static_lightning", () -> new SimpleParticleType(false));

    @Mod.EventBusSubscriber(modid = GlumbisMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegisterParticleFactories {


    }
}
