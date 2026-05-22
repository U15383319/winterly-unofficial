package winterly.registry;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import winterly.Winterly;
import winterly.worldgen.CryomarbleFeature;
import winterly.worldgen.UndergroundIcicleFeature;

public final class WinterlyFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Winterly.MOD_ID);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> UNDERGROUND_ICICLE_FEATURE =
            FEATURES.register("underground_icicle", UndergroundIcicleFeature::new);
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> CRYOMARBLE_FEATURE =
            FEATURES.register("cryomarble", CryomarbleFeature::new);

    private WinterlyFeatures() {
    }
}
