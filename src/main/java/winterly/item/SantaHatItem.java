package winterly.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class SantaHatItem extends Item implements ICurioItem {
    private final String color;

    public SantaHatItem(Properties properties, String color) {
        super(properties);
        this.color = color;
    }

    public String color() {
        return color;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("tag.winterly.cosmetic").withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.literal(" "));
        super.appendHoverText(stack, level, tooltip, flag);
    }
}
