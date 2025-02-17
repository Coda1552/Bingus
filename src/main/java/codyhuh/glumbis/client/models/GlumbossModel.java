package codyhuh.glumbis.client.models;

import codyhuh.glumbis.GlumbisMod;
import codyhuh.glumbis.common.entities.Glumboss;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class GlumbossModel<T extends Glumboss> extends HierarchicalModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(GlumbisMod.MOD_ID, "glumboss"), "main");
	private final ModelPart glumboss;
	private final ModelPart cat;
	private final ModelPart head;
	private final ModelPart hairs;
	private final ModelPart leftear;
	private final ModelPart rightear;
	private final ModelPart rightpaw;
	private final ModelPart leftpaw;
	private final ModelPart sock;
	private final ModelPart sock_main;
	private final ModelPart sock_leg;
	private final ModelPart sock_foot;

	public GlumbossModel(ModelPart root) {
		this.glumboss = root.getChild("glumboss");
		this.cat = this.glumboss.getChild("cat");
		this.head = this.cat.getChild("head");
		this.hairs = this.head.getChild("hairs");
		this.leftear = this.head.getChild("leftear");
		this.rightear = this.head.getChild("rightear");
		this.rightpaw = this.cat.getChild("rightpaw");
		this.leftpaw = this.cat.getChild("leftpaw");
		this.sock = this.glumboss.getChild("sock");
		this.sock_main = this.sock.getChild("sock_main");
		this.sock_leg = this.sock_main.getChild("sock_leg");
		this.sock_foot = this.sock_main.getChild("sock_foot");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition glumboss = partdefinition.addOrReplaceChild("glumboss", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cat = glumboss.addOrReplaceChild("cat", CubeListBuilder.create(), PartPose.offset(0.5F, -17.0F, -1.0F));

		PartDefinition head = cat.addOrReplaceChild("head", CubeListBuilder.create().texOffs(116, 56).addBox(-13.5F, -24.0F, -9.0F, 27.0F, 29.0F, 19.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition hairs = head.addOrReplaceChild("hairs", CubeListBuilder.create().texOffs(124, 104).addBox(-13.5F, -13.0F, 0.0F, 27.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 4.0F));

		PartDefinition leftear = head.addOrReplaceChild("leftear", CubeListBuilder.create().texOffs(124, 145).addBox(-6.0F, -8.0F, 0.0F, 14.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(8.5F, -24.0F, -2.0F));

		PartDefinition rightear = head.addOrReplaceChild("rightear", CubeListBuilder.create().texOffs(124, 137).addBox(-8.0F, -8.0F, 0.0F, 14.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.5F, -24.0F, -2.0F));

		PartDefinition rightpaw = cat.addOrReplaceChild("rightpaw", CubeListBuilder.create().texOffs(123, 119).addBox(-4.5F, 0.0F, -7.0F, 11.0F, 6.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(130, 132).addBox(-4.5F, 6.0F, -7.0F, 11.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-12.0F, -8.0F, -9.0F));

		PartDefinition leftpaw = cat.addOrReplaceChild("leftpaw", CubeListBuilder.create().texOffs(123, 119).addBox(-4.5F, 0.0F, -7.0F, 11.0F, 6.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(130, 132).addBox(-4.5F, 6.0F, -7.0F, 11.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(10.0F, -8.0F, -9.0F));

		PartDefinition sock = glumboss.addOrReplaceChild("sock", CubeListBuilder.create().texOffs(0, 112).addBox(-15.0F, -4.0F, -14.0F, 31.0F, 10.0F, 31.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -19.0F, -1.0F));

		PartDefinition sock_main = sock.addOrReplaceChild("sock_main", CubeListBuilder.create(), PartPose.offset(0.0F, 17.0F, 1.0F));

		PartDefinition sock_leg = sock_main.addOrReplaceChild("sock_leg", CubeListBuilder.create().texOffs(0, 56).addBox(-14.0F, -14.0F, -14.0F, 29.0F, 27.0F, 29.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 0.0F));

		PartDefinition sock_foot = sock_main.addOrReplaceChild("sock_foot", CubeListBuilder.create().texOffs(0, 0).addBox(-16.0F, 8.0F, -24.0F, 33.0F, 16.0F, 40.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(Glumboss entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public ModelPart root() {
		return glumboss;
	}
}