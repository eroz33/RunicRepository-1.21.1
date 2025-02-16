package net.eroz33.runic_repository.item;

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
    public static final DeferredItem<Item> ARCANE_PRIMER =
            ITEMS.registerItem("arcane_primer", Item::new, new Item.Properties());
    public static final DeferredItem<Item> ARCANE_QUILL =
            ITEMS.registerItem("arcane_quill", ArcaneQuillItem::new, new Item.Properties());

    private RItems() { }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
