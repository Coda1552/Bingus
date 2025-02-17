package codyhuh.glumbis.client.renders.layers;

import codyhuh.glumbis.GlumbisMod;
import codyhuh.glumbis.common.entities.Mouse;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.CrashReport;
import net.minecraft.CrashReportCategory;
import net.minecraft.ReportedException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.OcelotModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;

public class CarriedMouseLayer<T extends Mob, M extends OcelotModel<T>> extends RenderLayer<T, M> {

    public CarriedMouseLayer(RenderLayerParent<T, M> render) {
        super(render);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource pBuffer, int pPackedLight, T entity, float pLimbSwing, float pLimbSwingAmount, float partialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        float bodyYaw = entity.yBodyRotO + (entity.yBodyRot - entity.yBodyRotO) * partialTicks;

        if (entity.isVehicle()) {
            for (Entity passenger : entity.getPassengers()) {
                if (passenger instanceof Mouse mouse) {
                    GlumbisMod.PROXY.releaseRenderingEntity(mouse.getUUID());

                    poseStack.pushPose();

                    ModelPart head = getParentModel().head;

                    double y = mouse.getBbHeight() + 0.1D;

                    head.translateAndRotate(poseStack);

                    poseStack.translate(0.0D, y, -0.185D);

                    poseStack.mulPose(Axis.XN.rotationDegrees(90F));
                    poseStack.mulPose(Axis.ZP.rotationDegrees(90F));
                    poseStack.mulPose(Axis.YN.rotationDegrees(360F - bodyYaw));

                    poseStack.scale(0.75F, 0.75F, 0.75F);
                    renderPassenger(mouse, 0, partialTicks, poseStack, pBuffer, pPackedLight);

                    poseStack.popPose();

                    GlumbisMod.PROXY.blockRenderingEntity(mouse.getUUID());
                }
            }

        }
    }

    public static <E extends Entity> void renderPassenger(E entityIn, float yaw, float partialTicks, PoseStack matrixStack, MultiBufferSource bufferIn, int packedLight) {
        EntityRenderer<? super E> render = null;
        EntityRenderDispatcher manager = Minecraft.getInstance().getEntityRenderDispatcher();
        try {
            render = manager.getRenderer(entityIn);

            if (render != null) {
                try {
                    render.render(entityIn, yaw, partialTicks, matrixStack, bufferIn, packedLight);
                } catch (Throwable throwable1) {
                    throw new ReportedException(CrashReport.forThrowable(throwable1, "Rendering entity in world"));
                }
            }
        } catch (Throwable throwable3) {
            CrashReport crashreport = CrashReport.forThrowable(throwable3, "Rendering entity in world");
            CrashReportCategory crashreportcategory = crashreport.addCategory("Entity being rendered");
            entityIn.fillCrashReportCategory(crashreportcategory);
            CrashReportCategory crashreportcategory1 = crashreport.addCategory("Renderer details");
            crashreportcategory1.setDetail("Assigned renderer", render);
            crashreportcategory1.setDetail("Rotation", Float.valueOf(yaw));
            crashreportcategory1.setDetail("Delta", Float.valueOf(partialTicks));
            throw new ReportedException(crashreport);
        }
    }

}