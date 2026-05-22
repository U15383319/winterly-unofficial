package winterly.config;

import net.minecraftforge.common.ForgeConfigSpec;

public final class WinterlyForgeConfig {
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.IntValue MAX_GIFT_BOX_CAPACITY;
    public static final ForgeConfigSpec.BooleanValue UPDATE_CHECKER_ENABLED;
    public static final ForgeConfigSpec.BooleanValue GENERATE_UNDERGROUND_ICICLES;
    public static final ForgeConfigSpec.BooleanValue GENERATE_CRYOMARBLE;
    public static final ForgeConfigSpec.BooleanValue GENERATE_FROZEN_GRASS;
    public static final ForgeConfigSpec.BooleanValue GENERATE_FROZEN_FLOWERS;
    public static final ForgeConfigSpec.BooleanValue MOB_DECORATIONS_ENABLED;
    public static final ForgeConfigSpec.BooleanValue MOB_DECORATIONS_ONLY_IN_WINTER;
    public static final ForgeConfigSpec.IntValue MOB_DECORATIONS_CHANCE;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        MAX_GIFT_BOX_CAPACITY = builder.defineInRange("maxGiftBoxCapacity", 3, 1, 64);
        UPDATE_CHECKER_ENABLED = builder.define("updateCheckerEnabled", true);
        GENERATE_UNDERGROUND_ICICLES = builder.define("generateUndergroundIcicles", true);
        GENERATE_CRYOMARBLE = builder.define("generateCryomarble", true);
        GENERATE_FROZEN_GRASS = builder.define("generateFrozenGrass", true);
        GENERATE_FROZEN_FLOWERS = builder.define("generateFrozenFlowers", true);

        builder.push("mobDecorations");
        MOB_DECORATIONS_ENABLED = builder.define("enabled", false);
        MOB_DECORATIONS_ONLY_IN_WINTER = builder.define("onlyInWinter", true);
        MOB_DECORATIONS_CHANCE = builder.defineInRange("chance", 15, 0, 100);
        builder.pop();

        SPEC = builder.build();
    }

    private WinterlyForgeConfig() {
    }
}
