package winterly.block;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.locale.Language;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import winterly.block.entity.GiftBoxBlockEntity;
import winterly.config.WinterlyForgeConfig;
import winterly.data.GiftBoxData;

import java.util.List;

public class GiftBoxBlock extends Block implements EntityBlock {
    public static final VoxelShape SHAPE = box(4, 0, 4, 12, 8, 12);

    public GiftBoxBlock(BlockBehaviour.Properties properties) {
        super(properties.sound(SoundType.WOOL));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new GiftBoxBlockEntity(pos, state);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if (level.getBlockEntity(pos) instanceof GiftBoxBlockEntity entity && stack.hasTag()) {
            entity.load(stack.getOrCreateTag());
        }
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        if (!level.isClientSide && blockEntity instanceof GiftBoxBlockEntity entity) {
            if (!EnchantmentHelper.getEnchantments(tool).containsKey(Enchantments.SILK_TOUCH)) {
                if (entity.stacks.isEmpty()) {
                    popResource(level, pos, new ItemStack(asItem()));
                } else {
                    entity.stacks.forEach(stack -> popResource(level, pos, stack));
                }
            } else {
                ItemStack box = new ItemStack(this);
                if (!entity.stacks.isEmpty()) {
                    box.setTag(entity.saveWithoutMetadata());
                }
                popResource(level, pos, box);
            }
        }
        super.playerDestroy(level, player, pos, state, blockEntity, tool);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide && level.getBlockEntity(pos) instanceof GiftBoxBlockEntity entity) {
            ItemStack stack = player.getItemInHand(hand);
            if (!stack.isEmpty() && entity.stacks.size() < WinterlyForgeConfig.MAX_GIFT_BOX_CAPACITY.get()) {
                if (stack.getItem() instanceof BlockItem blockItem
                        && (blockItem.getBlock() instanceof ShulkerBoxBlock || blockItem.getBlock() instanceof GiftBoxBlock)) {
                    return InteractionResult.PASS;
                }
                entity.stacks.add(stack.copy());
                player.setItemInHand(hand, ItemStack.EMPTY);
                level.playSound(null, pos, net.minecraft.sounds.SoundEvents.BUNDLE_INSERT, net.minecraft.sounds.SoundSource.BLOCKS, 1, 1);
                return InteractionResult.SUCCESS;
            }
        }
        return super.use(state, level, pos, player, hand, hit);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter level, List<Component> tooltip, TooltipFlag flag) {
        if (stack.getOrCreateTag().contains("giftBoxData")) {
            GiftBoxData gift = GiftBoxData.fromNbt(stack.getOrCreateTag().getCompound("giftBoxData"));
            gift.stacks.forEach(item -> tooltip.add(Component.literal("- " + item.getHoverName().getString() + " x" + item.getCount()).withStyle(ChatFormatting.GRAY)));
        } else {
            Language language = Language.getInstance();
            String key = "description.winterly.gift_box.";
            for (int i = 0; i <= 32; i++) {
                if (language.has(key + i)) {
                    tooltip.add(Component.translatable(key + i).setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY)));
                }
                if (!language.has(key + (i + 1))) {
                    break;
                }
            }
        }
        super.appendHoverText(stack, level, tooltip, flag);
    }
}
