package net.eroz33.runicrepository;

import net.eroz33.runicrepository.item.ArcaneDustItem;
import net.eroz33.runicrepository.item.ArcanePrimerItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static net.eroz33.runicrepository.RunicRepository.MOD_ID;

public class RunicItems {
    // Setup our Deferred Register
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, MOD_ID);

    // Create Items
    public static final DeferredHolder<Item, ArcaneDustItem> ARCANE_DUST = ITEMS.register("arcane_dust", ArcaneDustItem::new);
    public static final DeferredHolder<Item, ArcanePrimerItem> ARCANE_PRIMER = ITEMS.register("arcane_primer", ArcanePrimerItem::new);

    private RunicItems() { }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
