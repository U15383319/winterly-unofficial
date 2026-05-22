package winterly.worldgen;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import winterly.config.WinterlyForgeConfig;
import winterly.registry.WinterlyBlocks;

public class UndergroundIcicleFeature extends Feature<NoneFeatureConfiguration> {
    public UndergroundIcicleFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        if (!WinterlyForgeConfig.GENERATE_UNDERGROUND_ICICLES.get()) {
            return false;
        }
        boolean generated = false;
        int randomY = context.random().nextInt(100) - 50;
        WorldGenLevel level = context.level();

        for (int x = 0; x < 10; x++) {
            for (int z = 0; z < 10; z++) {
                for (int y = 0; y < 10; y++) {
                    BlockPos pos = new BlockPos(context.origin().getX() + x, randomY + y, context.origin().getZ() + z);
                    if (context.random().nextInt(4) == 0 && level.isEmptyBlock(pos) && level.getBlockState(pos.above()).isSolid()) {
                        level.setBlock(pos, WinterlyBlocks.ICICLE.get().defaultBlockState(), Block.UPDATE_ALL);
                        generated = true;
                    }
                }
            }
        }
        return generated;
    }
}
