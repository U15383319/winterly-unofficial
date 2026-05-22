package winterly.worldgen;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import winterly.block.IcicleBlock;
import winterly.config.WinterlyForgeConfig;
import winterly.registry.WinterlyBlocks;

public class CryomarbleFeature extends Feature<NoneFeatureConfiguration> {
    public CryomarbleFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        if (!WinterlyForgeConfig.GENERATE_CRYOMARBLE.get()) {
            return false;
        }
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();

        for (int i = -20; i < 20; i++) {
            BlockPos candidate = new BlockPos(origin.getX(), i, origin.getZ());
            if (level.isEmptyBlock(candidate)) {
                origin = candidate;
                break;
            }
        }
        if (context.random().nextInt(16) != 0) {
            return false;
        }

        int spawned = 0;
        for (int x = -3; x < 3; x++) {
            for (int z = -3; z < 3; z++) {
                for (int y = -3; y < 3; y++) {
                    BlockPos pos = origin.offset(x, y, z);
                    if (context.random().nextInt(Math.abs(x) + Math.abs(y) + Math.abs(z) + 1) == 0
                            && level.isEmptyBlock(pos)
                            && level.getBlockState(pos.below()).isSolid()) {
                        if (spawned < 3) {
                            level.setBlock(pos, WinterlyBlocks.RAW_CRYOMARBLE_SHARD.get().defaultBlockState().setValue(IcicleBlock.FACING, Direction.UP), Block.UPDATE_ALL);
                            spawned++;
                        } else {
                            return true;
                        }
                    }
                }
            }
        }
        return true;
    }
}
