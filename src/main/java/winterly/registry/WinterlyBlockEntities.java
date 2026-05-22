package winterly.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import winterly.Winterly;
import winterly.block.entity.GiftBoxBlockEntity;

public final class WinterlyBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Winterly.MOD_ID);

    public static final RegistryObject<BlockEntityType<GiftBoxBlockEntity>> GIFT_BOX_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("gift_box", () -> BlockEntityType.Builder.of(
                    GiftBoxBlockEntity::new,
                    WinterlyBlocks.GIFT_BOXES.stream().map(RegistryObject::get).toArray(Block[]::new)
            ).build(null));

    private WinterlyBlockEntities() {
    }
}
