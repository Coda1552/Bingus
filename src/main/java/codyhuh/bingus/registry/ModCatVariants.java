package codyhuh.bingus.registry;

import codyhuh.bingus.BingusMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.CatVariant;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCatVariants {
    public static final DeferredRegister<CatVariant> VARIANTS = DeferredRegister.create(Registries.CAT_VARIANT, BingusMod.MOD_ID);

    public static final RegistryObject<CatVariant> BINGUS = VARIANTS.register("bingus", () -> new CatVariant(new ResourceLocation(BingusMod.MOD_ID, "textures/entity/cat/bingus.png")));
    public static final RegistryObject<CatVariant> CREAMY = VARIANTS.register("creamy", () -> new CatVariant(new ResourceLocation(BingusMod.MOD_ID, "textures/entity/cat/creamy.png")));
    public static final RegistryObject<CatVariant> BRONZE = VARIANTS.register("bronze", () -> new CatVariant(new ResourceLocation(BingusMod.MOD_ID, "textures/entity/cat/bronze.png")));
}
