package winterly.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import winterly.Winterly;

public final class WinterlyCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Winterly.MOD_ID);

    public static final RegistryObject<CreativeModeTab> ITEMS = CREATIVE_MODE_TABS.register("items", () ->
            CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.winterly.items"))
                    .icon(() -> WinterlyBlocks.SNOWGUY.get().asItem().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        WinterlyItems.ITEMS.getEntries().forEach(item -> output.accept(item.get()));
                        WinterlyBlocks.BLOCK_ITEMS.getEntries().forEach(item -> output.accept(item.get()));
                    })
                    .build());

    private WinterlyCreativeTabs() {
    }
}
