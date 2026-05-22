package winterly.data;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import winterly.block.entity.GiftBoxBlockEntity;

import java.util.ArrayList;
import java.util.Optional;

public class GiftBoxData {
    public ArrayList<ItemStack> stacks = new ArrayList<>();

    public static GiftBoxData fromNbt(CompoundTag tag) {
        GiftBoxData gift = new GiftBoxData();
        int size = tag.getInt("size");
        for (int i = 0; i < size; i++) {
            CompoundTag entryTag = tag.getCompound(String.valueOf(i));
            Optional<Item> item = BuiltInRegistries.ITEM.getOptional(new ResourceLocation(entryTag.getString("item")));
            item.ifPresent(value -> {
                ItemStack stack = new ItemStack(value, entryTag.getInt("count"));
                if (entryTag.contains("nbt")) {
                    stack.setTag(entryTag.getCompound("nbt"));
                }
                gift.stacks.add(stack);
            });
        }
        return gift;
    }

    public static CompoundTag toNbt(GiftBoxBlockEntity entity) {
        CompoundTag tag = new CompoundTag();
        tag.putInt("size", entity.stacks.size());
        for (int i = 0; i < entity.stacks.size(); i++) {
            ItemStack stack = entity.stacks.get(i);
            CompoundTag entryTag = new CompoundTag();
            entryTag.putString("item", BuiltInRegistries.ITEM.getKey(stack.getItem()).toString());
            if (stack.hasTag()) {
                entryTag.put("nbt", stack.getOrCreateTag());
            }
            entryTag.putInt("count", stack.getCount());
            tag.put(Integer.toString(i), entryTag);
        }
        return tag;
    }
}
