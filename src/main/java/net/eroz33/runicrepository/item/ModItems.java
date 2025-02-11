package net.eroz33.runicrepository.item;

import net.eroz33.runicrepository.RunicRepository;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(RunicRepository.MOD_ID);

    public static final DeferredItem<Item> ARCANEASH = ITEMS.register("arcaneash",
            () -> new Item(new Item.Properties()));



    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
