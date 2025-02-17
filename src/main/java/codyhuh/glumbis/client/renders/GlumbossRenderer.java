package codyhuh.glumbis.client.renders;

import codyhuh.glumbis.GlumbisMod;
import codyhuh.glumbis.client.models.GlumbossModel;
import codyhuh.glumbis.common.entities.Glumboss;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class GlumbossRenderer extends MobRenderer<Glumboss, GlumbossModel<Glumboss>> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(GlumbisMod.MOD_ID, "textures/entity/glumboss/glumboss.png");
    public static final ResourceLocation CHARGED = new ResourceLocation(GlumbisMod.MOD_ID, "textures/entity/glumboss/glumboss_charged.png");

    public GlumbossRenderer(EntityRendererProvider.Context cntxt) {
        super(cntxt, new GlumbossModel<>(cntxt.bakeLayer(GlumbossModel.LAYER_LOCATION)), 1.0F);
    }

    public ResourceLocation getTextureLocation(Glumboss entity) {
        return TEXTURE;
    }
}