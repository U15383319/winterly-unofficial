package winterly.data;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;

public final class CachedFlowers {
    public static Map<ResourceKey<Level>, Map<BlockPos, Block>> data = new LinkedHashMap<>();

    @Nullable
    public static Block getFlower(ResourceKey<Level> level, BlockPos pos) {
        Map<BlockPos, Block> worldData = data.get(level);
        return worldData == null ? null : worldData.get(pos);
    }

    public static void cacheFlower(ResourceKey<Level> level, BlockPos pos, Block flower) {
        data.computeIfAbsent(level, ignored -> new LinkedHashMap<>()).put(pos, flower);
    }

    private CachedFlowers() {
    }
}
