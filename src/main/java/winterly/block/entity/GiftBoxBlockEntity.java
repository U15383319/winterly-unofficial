package winterly.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import winterly.data.GiftBoxData;
import winterly.registry.WinterlyBlockEntities;

import java.util.ArrayList;

public class GiftBoxBlockEntity extends BlockEntity {
    public ArrayList<ItemStack> stacks = new ArrayList<>();

    public GiftBoxBlockEntity(BlockPos pos, BlockState state) {
        super(WinterlyBlockEntities.GIFT_BOX_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("giftBoxData", GiftBoxData.toNbt(this));
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains("giftBoxData")) {
            stacks = GiftBoxData.fromNbt(tag.getCompound("giftBoxData")).stacks;
        }
    }
}
