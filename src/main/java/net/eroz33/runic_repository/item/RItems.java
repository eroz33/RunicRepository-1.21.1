package net.eroz33.runic_repository.item;

import net.eroz33.runic_repository.item.tomes.RTome;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static net.eroz33.runic_repository.core.RR.MOD_ID;

public class RItems {
    // Setup our Deferred Register
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MOD_ID);

    // Create Items
    public static final DeferredItem<Item> ARCANE_DUST = ITEMS.registerSimpleItem("arcane_dust");
    public static final DeferredItem<Item> RUNIC_CORE_G = ITEMS.registerSimpleItem("runic_core_g");
    public static final DeferredItem<Item> RUNIC_CORE_R = ITEMS.registerSimpleItem("runic_core_r");
    public static final DeferredItem<Item> RUNIC_CORE_P = ITEMS.registerSimpleItem("runic_core_p");
    public static final DeferredItem<Item> RUNIC_CORE_B = ITEMS.registerSimpleItem("runic_core_b");


    public static final DeferredItem<Item> ARCANE_PRIMER =
            ITEMS.registerItem("arcane_primer", Item::new, new Item.Properties());
    public static final DeferredItem<Item> ARCANE_QUILL =
            ITEMS.registerItem("arcane_quill", ArcaneQuillItem::new, new Item.Properties());

    public static final DeferredItem<Item> ARCANE_TOME_GREEN =
            ITEMS.registerItem("arcane_tome", properties -> new RTome(properties, 1024), new Item.Properties());
    public static final DeferredItem<Item> ARCANE_TOME_RED =
            ITEMS.registerItem("arcane_tome_tier2", properties -> new RTome(properties, 4096), new Item.Properties());
    public static final DeferredItem<Item> ARCANE_TOME_PURPLE =
            ITEMS.registerItem("arcane_tome_tier3", properties -> new RTome(properties, 16384), new Item.Properties());
    public static final DeferredItem<Item> ARCANE_TOME_BLUE =
            ITEMS.registerItem("arcane_tome_tier4", properties -> new RTome(properties, 65536), new Item.Properties());

    private RItems() { }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
