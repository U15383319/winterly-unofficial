package winterly.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import winterly.Winterly;

public final class WinterlyModelLayers {
    public static final ModelLayerLocation SANTA_HAT_LAYER = new ModelLayerLocation(Winterly.id("santa_hat"), "main");
    public static final ModelLayerLocation SCARF_LAYER = new ModelLayerLocation(Winterly.id("scarf"), "main");

    private WinterlyModelLayers() {
    }
}
