package winterly.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;
import top.theillusivec4.curios.api.client.ICurioRenderer;
import winterly.Winterly;
import winterly.client.model.SantaHatModel;
import winterly.client.model.ScarfModel;
import winterly.item.SantaHatItem;
import winterly.item.ScarfItem;
import winterly.registry.WinterlyBlocks;
import winterly.registry.WinterlyItems;

@Mod.EventBusSubscriber(modid = Winterly.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class WinterlyClient {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(WinterlyModelLayers.SANTA_HAT_LAYER, SantaHatModel::createLayer);
        event.registerLayerDefinition(WinterlyModelLayers.SCARF_LAYER, ScarfModel::createLayer);
    }

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            registerBlockRenderLayers();
            registerHat(WinterlyItems.RED_SANTA_HAT.get());
            registerHat(WinterlyItems.BLUE_SANTA_HAT.get());
            registerScarf(WinterlyItems.WHITE_SCARF.get());
            registerScarf(WinterlyItems.RED_SCARF.get());
            registerScarf(WinterlyItems.GREEN_SCARF.get());
            registerScarf(WinterlyItems.BLUE_SCARF.get());
            registerScarf(WinterlyItems.RAINBOW_SCARF.get());
        });
    }

    private static void registerBlockRenderLayers() {
        ItemBlockRenderTypes.setRenderLayer(WinterlyBlocks.RED_GIFT_BOX.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WinterlyBlocks.ORANGE_GIFT_BOX.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WinterlyBlocks.YELLOW_GIFT_BOX.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WinterlyBlocks.GREEN_GIFT_BOX.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WinterlyBlocks.CYAN_GIFT_BOX.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WinterlyBlocks.BLUE_GIFT_BOX.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WinterlyBlocks.PURPLE_GIFT_BOX.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WinterlyBlocks.BLACK_GIFT_BOX.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WinterlyBlocks.WHITE_GIFT_BOX.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(WinterlyBlocks.GARLAND_LIGHTS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WinterlyBlocks.RAINY_GARLAND_LIGHTS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WinterlyBlocks.SNOWGUY.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WinterlyBlocks.SNOWBALL_WALL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WinterlyBlocks.ICICLE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WinterlyBlocks.RAW_CRYOMARBLE_SHARD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WinterlyBlocks.FROZEN_GRASS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WinterlyBlocks.FROZEN_FLOWER.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(WinterlyBlocks.ICICLE_BLOCK.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(WinterlyBlocks.PACKED_ICICLE_BLOCK.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(WinterlyBlocks.ICICLE_PANE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(WinterlyBlocks.ICICLE_BARS.get(), RenderType.cutout());
    }

    private static void registerHat(Item item) {
        CuriosRendererRegistry.register(item, () -> new SantaHatCurioRenderer(((SantaHatItem) item).color()));
    }

    private static void registerScarf(Item item) {
        CuriosRendererRegistry.register(item, () -> new ScarfCurioRenderer(((ScarfItem) item).color()));
    }

    private abstract static class WinterlyCurioRenderer implements ICurioRenderer {
        protected final String color;

        protected WinterlyCurioRenderer(String color) {
            this.color = color;
        }

        protected static boolean copyHumanoid(RenderLayerParent<? extends LivingEntity, ?> parent, java.util.function.Consumer<HumanoidModel<?>> consumer) {
            if (parent.getModel() instanceof HumanoidModel<?> humanoidModel) {
                consumer.accept(humanoidModel);
                return true;
            }
            return false;
        }
    }

    private static final class SantaHatCurioRenderer extends WinterlyCurioRenderer {
        private SantaHatModel model;

        private SantaHatCurioRenderer(String color) {
            super(color);
        }

        @Override
        public <T extends LivingEntity, M extends net.minecraft.client.model.EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack poseStack, RenderLayerParent<T, M> parent, MultiBufferSource source, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
            if (model == null) {
                model = new SantaHatModel(Minecraft.getInstance().getEntityModels().bakeLayer(WinterlyModelLayers.SANTA_HAT_LAYER));
            }
            if (copyHumanoid(parent, humanoid -> model.hat.copyFrom(humanoid.head))) {
                VertexConsumer buffer = source.getBuffer(RenderType.entityCutout(Winterly.id("textures/entity/" + color + "_santa_hat.png")));
                model.renderToBuffer(poseStack, buffer, light, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
            }
        }
    }

    private static final class ScarfCurioRenderer extends WinterlyCurioRenderer {
        private ScarfModel model;

        private ScarfCurioRenderer(String color) {
            super(color);
        }

        @Override
        public <T extends LivingEntity, M extends net.minecraft.client.model.EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack poseStack, RenderLayerParent<T, M> parent, MultiBufferSource source, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
            if (model == null) {
                model = new ScarfModel(Minecraft.getInstance().getEntityModels().bakeLayer(WinterlyModelLayers.SCARF_LAYER));
            }
            if (copyHumanoid(parent, humanoid -> model.scarf.copyFrom(humanoid.body))) {
                VertexConsumer buffer = source.getBuffer(RenderType.entityCutout(Winterly.id("textures/entity/" + color + "_scarf.png")));
                model.renderToBuffer(poseStack, buffer, light, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
            }
        }
    }

    private WinterlyClient() {
    }
}
