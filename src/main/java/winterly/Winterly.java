package winterly;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import winterly.config.WinterlyForgeConfig;
import winterly.registry.WinterlyBlockEntities;
import winterly.registry.WinterlyBlocks;
import winterly.registry.WinterlyCreativeTabs;
import winterly.registry.WinterlyFeatures;
import winterly.registry.WinterlyItems;

@Mod(Winterly.MOD_ID)
public class Winterly {
    public static final String MOD_ID = "winterly";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Winterly() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        WinterlyBlocks.BLOCKS.register(modBus);
        WinterlyBlocks.BLOCK_ITEMS.register(modBus);
        WinterlyItems.ITEMS.register(modBus);
        WinterlyBlockEntities.BLOCK_ENTITIES.register(modBus);
        WinterlyFeatures.FEATURES.register(modBus);
        WinterlyCreativeTabs.CREATIVE_MODE_TABS.register(modBus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, WinterlyForgeConfig.SPEC);
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    @SubscribeEvent
    public void onFinalizeSpawn(MobSpawnEvent.FinalizeSpawn event) {
        // Original Fabric mob-decoration data used mixins. The Forge port keeps the config
        // surface but leaves hostile mob decoration disabled unless a Forge data sync layer is added.
    }
}
