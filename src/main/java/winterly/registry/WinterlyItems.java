package winterly.registry;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import winterly.Winterly;
import winterly.item.SantaHatItem;
import winterly.item.ScarfItem;
import winterly.item.tool.CryomarbleAxeItem;
import winterly.item.tool.CryomarbleHoeItem;
import winterly.item.tool.CryomarblePickaxeItem;
import winterly.item.tool.CryomarbleShovelItem;
import winterly.item.tool.CryomarbleSwordItem;

public final class WinterlyItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Winterly.MOD_ID);

    public static final RegistryObject<Item> RED_CANDY_CANE = add("red_candy_cane", new Item.Properties().food(candyCane()));
    public static final RegistryObject<Item> GREEN_CANDY_CANE = add("green_candy_cane", new Item.Properties().food(candyCane()));
    public static final RegistryObject<Item> BLUE_CANDY_CANE = add("blue_candy_cane", new Item.Properties().food(candyCane()));
    public static final RegistryObject<Item> CRYOMARBLE_SHARD = add("cryomarble_shard", new Item.Properties());
    public static final RegistryObject<Item> CRYOMARBLE = add("cryomarble", new Item.Properties());

    public static final RegistryObject<Item> CRYOMARBLE_SWORD = ITEMS.register("cryomarble_sword", () -> new CryomarbleSwordItem(Tiers.DIAMOND, 3, -2.4F, new Item.Properties()));
    public static final RegistryObject<Item> CRYOMARBLE_SHOVEL = ITEMS.register("cryomarble_shovel", () -> new CryomarbleShovelItem(Tiers.DIAMOND, 1.5F, -3.0F, new Item.Properties()));
    public static final RegistryObject<Item> CRYOMARBLE_PICKAXE = ITEMS.register("cryomarble_pickaxe", () -> new CryomarblePickaxeItem(Tiers.DIAMOND, 1, -2.8F, new Item.Properties()));
    public static final RegistryObject<Item> CRYOMARBLE_AXE = ITEMS.register("cryomarble_axe", () -> new CryomarbleAxeItem(Tiers.DIAMOND, 5.0F, -3.0F, new Item.Properties()));
    public static final RegistryObject<Item> CRYOMARBLE_HOE = ITEMS.register("cryomarble_hoe", () -> new CryomarbleHoeItem(Tiers.DIAMOND, -3, 0.0F, new Item.Properties()));

    public static final RegistryObject<Item> RED_SANTA_HAT = ITEMS.register("red_santa_hat", () -> new SantaHatItem(new Item.Properties(), "red"));
    public static final RegistryObject<Item> BLUE_SANTA_HAT = ITEMS.register("blue_santa_hat", () -> new SantaHatItem(new Item.Properties(), "blue"));
    public static final RegistryObject<Item> WHITE_SCARF = ITEMS.register("white_scarf", () -> new ScarfItem(new Item.Properties(), "white"));
    public static final RegistryObject<Item> RED_SCARF = ITEMS.register("red_scarf", () -> new ScarfItem(new Item.Properties(), "red"));
    public static final RegistryObject<Item> GREEN_SCARF = ITEMS.register("green_scarf", () -> new ScarfItem(new Item.Properties(), "green"));
    public static final RegistryObject<Item> BLUE_SCARF = ITEMS.register("blue_scarf", () -> new ScarfItem(new Item.Properties(), "blue"));
    public static final RegistryObject<Item> RAINBOW_SCARF = ITEMS.register("rainbow_scarf", () -> new ScarfItem(new Item.Properties(), "rainbow"));

    private static RegistryObject<Item> add(String name, Item.Properties properties) {
        return ITEMS.register(name, () -> new Item(properties));
    }

    private static FoodProperties candyCane() {
        return new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).fast().build();
    }

    private WinterlyItems() {
    }
}
