package net.eroz33.runicrepository.core;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static net.eroz33.runicrepository.core.RR.MOD_ID;

public final class RBlocks {
    // Setup our Deferred Register for Blocks.
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MOD_ID);

    public static final DeferredBlock<Block> GRIMOIRE_BLOCK = registerBlock("grimoire",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f)
            ));

    public static final DeferredBlock<Block> TOME_SHELF_BLOCK = registerBlock("tome_shelf",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f)
            ));

    private RBlocks() { }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block){
        RItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name,block);
        registerBlockItem(name,toReturn);
        return toReturn;
    }
}
