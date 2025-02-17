package codyhuh.glumbis.client.renders;

import codyhuh.glumbis.GlumbisMod;
import codyhuh.glumbis.client.models.MouseModel;
import codyhuh.glumbis.common.entities.Mouse;
import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class MouseRenderer extends MobRenderer<Mouse, MouseModel<Mouse>> {
    public static final Map<Integer, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), (hashMap) -> {
        hashMap.put(0, new ResourceLocation(GlumbisMod.MOD_ID, "textures/entity/mouse/gray.png"));
        hashMap.put(1, new ResourceLocation(GlumbisMod.MOD_ID, "textures/entity/mouse/tan.png"));
        hashMap.put(2, new ResourceLocation(GlumbisMod.MOD_ID, "textures/entity/mouse/brown.png"));
        hashMap.put(3, new ResourceLocation(GlumbisMod.MOD_ID, "textures/entity/mouse/black.png"));
        hashMap.put(4, new ResourceLocation(GlumbisMod.MOD_ID, "textures/entity/mouse/white.png"));
    });
    public static final ResourceLocation BABY_TEXTURE = new ResourceLocation(GlumbisMod.MOD_ID, "textures/entity/mouse/pinky.png");

    public MouseRenderer(EntityRendererProvider.Context cntxt) {
        super(cntxt, new MouseModel<>(cntxt.bakeLayer(MouseModel.LAYER_LOCATION)), 0.2F);
    }

    public ResourceLocation getTextureLocation(Mouse entity) {
        return entity.isBaby() ? BABY_TEXTURE : TEXTURES.getOrDefault(entity.getVariant(), TEXTURES.get(0));
    }
}