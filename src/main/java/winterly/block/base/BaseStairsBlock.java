package winterly.block.base;

import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class BaseStairsBlock extends StairBlock {
    public BaseStairsBlock(BlockState baseState, BlockBehaviour.Properties properties) {
        super(() -> baseState, properties);
    }
}
