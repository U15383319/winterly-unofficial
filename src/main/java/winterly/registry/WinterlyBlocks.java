package winterly.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import winterly.Winterly;
import winterly.block.FrozenFlowerBlock;
import winterly.block.FrozenGrassBlock;
import winterly.block.GarlandLightsBlock;
import winterly.block.GiftBoxBlock;
import winterly.block.IcicleBlock;
import winterly.block.SnowballWallBlock;
import winterly.block.SnowguyBlock;
import winterly.block.base.BasePaneBlock;
import winterly.block.base.BaseStairsBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public final class WinterlyBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Winterly.MOD_ID);
    public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Winterly.MOD_ID);

    public static final List<RegistryObject<Block>> GIFT_BOXES = new ArrayList<>();

    public static final RegistryObject<Block> ICICLE = block("icicle", () -> new IcicleBlock(copy(Blocks.ICE).pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> ICICLE_BLOCK = block("icicle_block", () -> new GlassBlock(copy(Blocks.PACKED_ICE).noOcclusion()));
    public static final RegistryObject<Block> PACKED_ICICLE_BLOCK = block("packed_icicle_block", () -> new GlassBlock(copy(Blocks.PACKED_ICE).noOcclusion()));
    public static final RegistryObject<Block> ICICLE_PANE = block("icicle_pane", () -> new BasePaneBlock(copy(Blocks.PACKED_ICE).noOcclusion()));
    public static final RegistryObject<Block> ICICLE_BARS = block("icicle_bars", () -> new BasePaneBlock(copy(Blocks.ICE).noOcclusion()));
    public static final RegistryObject<Block> CRYOMARBLE_BLOCK = block("cryomarble_block", () -> new Block(copy(Blocks.DIAMOND_BLOCK)));
    public static final RegistryObject<Block> SNOWGUY = block("snowguy", () -> new SnowguyBlock(copy(Blocks.WHITE_WOOL).sound(SoundType.SNOW)));
    public static final RegistryObject<Block> SNOWBALL_WALL = block("snowball_wall", () -> new SnowballWallBlock(copy(Blocks.WHITE_WOOL).sound(SoundType.SNOW).noOcclusion()));
    public static final RegistryObject<Block> DENSE_SNOW = block("dense_snow", () -> new Block(copy(Blocks.WHITE_WOOL).sound(SoundType.SNOW)));
    public static final RegistryObject<Block> DENSE_SNOW_STAIRS = block("dense_snow_stairs", () -> new BaseStairsBlock(Blocks.SNOW_BLOCK.defaultBlockState(), copy(Blocks.WHITE_WOOL).sound(SoundType.SNOW)));
    public static final RegistryObject<Block> DENSE_SNOW_SLAB = block("dense_snow_slab", () -> new SlabBlock(copy(Blocks.WHITE_WOOL).sound(SoundType.SNOW)));
    public static final RegistryObject<Block> SNOW_BRICKS = block("snow_bricks", () -> new Block(copy(Blocks.WHITE_WOOL).sound(SoundType.SNOW)));
    public static final RegistryObject<Block> SNOW_BRICK_STAIRS = block("snow_brick_stairs", () -> new BaseStairsBlock(Blocks.SNOW_BLOCK.defaultBlockState(), copy(Blocks.WHITE_WOOL).sound(SoundType.SNOW)));
    public static final RegistryObject<Block> SNOW_BRICK_SLAB = block("snow_brick_slab", () -> new SlabBlock(copy(Blocks.WHITE_WOOL).sound(SoundType.SNOW)));
    public static final RegistryObject<Block> FROZEN_GRASS = block("frozen_grass", () -> new FrozenGrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).replaceable().noCollission().randomTicks().strength(0.1F).requiresCorrectToolForDrops().sound(SoundType.SNOW).pushReaction(PushReaction.DESTROY).isViewBlocking((state, world, pos) -> state.getValue(FrozenGrassBlock.LAYERS) >= 8)));
    public static final RegistryObject<Block> FROZEN_FLOWER = block("frozen_flower", () -> new FrozenFlowerBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).replaceable().noCollission().randomTicks().strength(0.1F).requiresCorrectToolForDrops().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY).isViewBlocking((state, world, pos) -> state.getValue(FrozenFlowerBlock.LAYERS) >= 8)));
    public static final RegistryObject<Block> RAW_CRYOMARBLE_SHARD = block("raw_cryomarble_shard", () -> new IcicleBlock(copy(Blocks.WHITE_WOOL).sound(SoundType.GLASS).lightLevel(state -> 12)));

    public static final RegistryObject<Block> RED_GIFT_BOX = gift("red_gift_box", () -> new GiftBoxBlock(copy(Blocks.RED_WOOL).pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> ORANGE_GIFT_BOX = gift("orange_gift_box", () -> new GiftBoxBlock(copy(Blocks.RED_WOOL).pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> YELLOW_GIFT_BOX = gift("yellow_gift_box", () -> new GiftBoxBlock(copy(Blocks.RED_WOOL).pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> GREEN_GIFT_BOX = gift("green_gift_box", () -> new GiftBoxBlock(copy(Blocks.RED_WOOL).pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> CYAN_GIFT_BOX = gift("cyan_gift_box", () -> new GiftBoxBlock(copy(Blocks.RED_WOOL).pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> BLUE_GIFT_BOX = gift("blue_gift_box", () -> new GiftBoxBlock(copy(Blocks.RED_WOOL).pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> PURPLE_GIFT_BOX = gift("purple_gift_box", () -> new GiftBoxBlock(copy(Blocks.RED_WOOL).pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> BLACK_GIFT_BOX = gift("black_gift_box", () -> new GiftBoxBlock(copy(Blocks.RED_WOOL).pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> WHITE_GIFT_BOX = gift("white_gift_box", () -> new GiftBoxBlock(copy(Blocks.RED_WOOL).pushReaction(PushReaction.DESTROY)));

    public static final RegistryObject<Block> GARLAND_LIGHTS = block("garland_lights", () -> new GarlandLightsBlock(copy(Blocks.GREEN_WOOL).pushReaction(PushReaction.DESTROY).noCollission().sound(SoundType.CANDLE)));
    public static final RegistryObject<Block> RAINY_GARLAND_LIGHTS = block("rainy_garland_lights", () -> new GarlandLightsBlock(copy(Blocks.WHITE_WOOL).pushReaction(PushReaction.DESTROY).noCollission().sound(SoundType.CANDLE)));

    private static RegistryObject<Block> gift(String name, Supplier<Block> block) {
        RegistryObject<Block> registryObject = block(name, block);
        GIFT_BOXES.add(registryObject);
        return registryObject;
    }

    private static RegistryObject<Block> block(String name, Supplier<Block> block) {
        RegistryObject<Block> registryObject = BLOCKS.register(name, block);
        BLOCK_ITEMS.register(name, () -> new BlockItem(registryObject.get(), new Item.Properties()));
        return registryObject;
    }

    private static BlockBehaviour.Properties copy(Block block) {
        return BlockBehaviour.Properties.copy(block);
    }

    private WinterlyBlocks() {
    }
}
