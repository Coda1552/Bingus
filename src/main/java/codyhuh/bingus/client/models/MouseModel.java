package codyhuh.bingus.client.models;

import codyhuh.bingus.BingusMod;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

import java.util.Collections;

public class MouseModel<T extends Entity> extends AgeableListModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(BingusMod.MOD_ID, "mouse"), "main");
	private final ModelPart Root;
	private final ModelPart Body;
	private final ModelPart RightFrontLeg;
	private final ModelPart LeftFrontLeg;
	private final ModelPart RightBackLeg;
	private final ModelPart LeftBackLeg;
	private final ModelPart RightEar;
	private final ModelPart LeftEar;
	private final ModelPart Tail;

	public MouseModel(ModelPart root) {
		this.Root = root.getChild("Root");
		this.Body = this.Root.getChild("Body");
		this.RightFrontLeg = this.Body.getChild("RightFrontLeg");
		this.LeftFrontLeg = this.Body.getChild("LeftFrontLeg");
		this.RightBackLeg = this.Body.getChild("RightBackLeg");
		this.LeftBackLeg = this.Body.getChild("LeftBackLeg");
		this.RightEar = this.Body.getChild("RightEar");
		this.LeftEar = this.Body.getChild("LeftEar");
		this.Tail = this.Root.getChild("Tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Root = partdefinition.addOrReplaceChild("Root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition Body = Root.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -2.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.25F, 0.0F));

		PartDefinition RightFrontLeg = Body.addOrReplaceChild("RightFrontLeg", CubeListBuilder.create(), PartPose.offset(-1.5F, 1.0F, 0.0F));

		PartDefinition RightFrontLeg_r1 = RightFrontLeg.addOrReplaceChild("RightFrontLeg_r1", CubeListBuilder.create().texOffs(10, 9).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition LeftFrontLeg = Body.addOrReplaceChild("LeftFrontLeg", CubeListBuilder.create(), PartPose.offset(1.5F, 1.0F, 0.0F));

		PartDefinition LeftFrontLeg_r1 = LeftFrontLeg.addOrReplaceChild("LeftFrontLeg_r1", CubeListBuilder.create().texOffs(10, 9).mirror().addBox(-0.5F, 0.0F, -2.0F, 1.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition RightBackLeg = Body.addOrReplaceChild("RightBackLeg", CubeListBuilder.create(), PartPose.offset(-1.5F, 1.0F, 3.0F));

		PartDefinition RightBackLeg_r1 = RightBackLeg.addOrReplaceChild("RightBackLeg_r1", CubeListBuilder.create().texOffs(10, 11).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition LeftBackLeg = Body.addOrReplaceChild("LeftBackLeg", CubeListBuilder.create(), PartPose.offset(1.5F, 1.0F, 3.0F));

		PartDefinition LeftBackLeg_r1 = LeftBackLeg.addOrReplaceChild("LeftBackLeg_r1", CubeListBuilder.create().texOffs(10, 11).mirror().addBox(-0.5F, 0.0F, -2.0F, 1.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition RightEar = Body.addOrReplaceChild("RightEar", CubeListBuilder.create(), PartPose.offset(-1.5F, -2.0F, -1.0F));

		PartDefinition RightEar_r1 = RightEar.addOrReplaceChild("RightEar_r1", CubeListBuilder.create().texOffs(10, 13).addBox(0.0F, -2.0F, -1.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition LeftEar = Body.addOrReplaceChild("LeftEar", CubeListBuilder.create(), PartPose.offset(1.5F, -2.0F, -1.0F));

		PartDefinition LeftEar_r1 = LeftEar.addOrReplaceChild("LeftEar_r1", CubeListBuilder.create().texOffs(10, 13).mirror().addBox(0.0F, -2.0F, -1.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition Tail = Root.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(0, 9).addBox(0.0F, -0.5F, 0.0F, 0.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.75F, 3.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		if (young) {
			Root.yRot = Mth.sin(ageInTicks * 0.5F) * 0.15F;
			Root.zRot = Mth.sin(ageInTicks * 0.5F) * 0.075F;
		}
		else {
			Root.yRot = 0.0F;
			Root.zRot = 0.0F;
		}
	}

	@Override
	protected Iterable<ModelPart> headParts() {
		return Collections.emptyList();
	}

	@Override
	protected Iterable<ModelPart> bodyParts() {
		return ImmutableList.of(Root);
	}
}