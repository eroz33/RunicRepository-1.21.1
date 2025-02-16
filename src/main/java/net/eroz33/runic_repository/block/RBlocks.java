package net.eroz33.runic_repository.block;

import net.eroz33.runic_repository.block.runic_network.GrimoireBlock;
import net.eroz33.runic_repository.block.runic_network.TomeShelfBlock;
import net.eroz33.runic_repository.item.RItems;
import net.eroz33.runic_repository.worldgen.tree.RTreeGrowers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static net.eroz33.runic_repository.core.RR.MOD_ID;

public final class RBlocks {
    // Setup our Deferred Register for Blocks.
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(MOD_ID);

    // Arcane Tree/Woods
    public static final DeferredBlock<Block> ARCANE_LOG = registerBlock("arcane_log",
            () -> new RArcaneTreeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final DeferredBlock<Block> ARCANE_WOOD = registerBlock("arcane_wood",
            () -> new RArcaneTreeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)));
    public static final DeferredBlock<Block> STRIPPED_ARCANE_LOG = registerBlock("stripped_arcane_log",
            () -> new RArcaneTreeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));
    public static final DeferredBlock<Block> STRIPPED_ARCANE_WOOD = registerBlock("stripped_arcane_wood",
            () -> new RArcaneTreeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)));
    public static final DeferredBlock<Block> ARCANE_PLANKS = registerBlock("arcane_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });
    public static final DeferredBlock<Block> ARCANE_LEAVES = registerBlock("arcane_leaves",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });
    public static final DeferredBlock<Block> ARCANE_SAPLING = registerBlock("arcane_sapling",
            () -> new SaplingBlock(RTreeGrowers.ARCANE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));


    // Runic Network Blocks
    public static final DeferredBlock<Block> GRIMOIRE_BLOCK = registerBlock("grimoire_block",
            () -> new GrimoireBlock(BlockBehaviour.Properties.of().strength(2f)));
    public static final DeferredBlock<Block> TOME_SHELF_BLOCK = registerBlock("tome_shelf_block",
            () -> new TomeShelfBlock(BlockBehaviour.Properties.of().strength(2f)));




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
