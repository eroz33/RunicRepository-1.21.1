// Made with Blockbench 4.12.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class grimoire<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "grimoire"), "main");
	private final ModelPart Cover;
	private final ModelPart Pages;
	private final ModelPart PageL;
	private final ModelPart PageR;

	public grimoire(ModelPart root) {
		this.Cover = root.getChild("Cover");
		this.Pages = root.getChild("Pages");
		this.PageL = this.Pages.getChild("PageL");
		this.PageR = this.Pages.getChild("PageR");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Cover = partdefinition.addOrReplaceChild("Cover", CubeListBuilder.create(), PartPose.offset(0.7523F, 16.8938F, 0.0F));

		PartDefinition cube_r1 = Cover.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 9).addBox(-5.4935F, 0.4344F, -7.5246F, 11.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.0F, 0.8727F));

		PartDefinition cube_r2 = Cover.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 32).addBox(-5.4732F, 0.6093F, -1.0F, 11.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.8727F));

		PartDefinition cube_r3 = Cover.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 0).addBox(-5.4935F, 0.4344F, -0.4754F, 11.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, 0.0F, 0.8727F));

		PartDefinition Pages = partdefinition.addOrReplaceChild("Pages", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition PageL = Pages.addOrReplaceChild("PageL", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r4 = PageL.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 25).addBox(-4.5268F, -0.4863F, -0.1635F, 9.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7523F, -7.1062F, 0.0F, 2.6616F, 0.0F, 0.8727F));

		PartDefinition PageR = Pages.addOrReplaceChild("PageR", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r5 = PageR.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 18).addBox(-4.4574F, 0.5068F, -0.8071F, 9.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7523F, -7.1062F, 0.0F, 0.4363F, 0.0F, 0.8727F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Cover.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Pages.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}