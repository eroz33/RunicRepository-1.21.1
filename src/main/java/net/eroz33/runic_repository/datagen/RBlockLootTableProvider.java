package net.eroz33.runic_repository.datagen;

import net.eroz33.runic_repository.block.RBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class RBlockLootTableProvider extends BlockLootSubProvider {
    protected RBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        // Arcane Trees
        dropSelf(RBlocks.ARCANE_LOG.get());
        dropSelf(RBlocks.ARCANE_WOOD.get());
        dropSelf(RBlocks.STRIPPED_ARCANE_LOG.get());
        dropSelf(RBlocks.STRIPPED_ARCANE_WOOD.get());
        dropSelf(RBlocks.ARCANE_PLANKS.get());
        dropSelf(RBlocks.ARCANE_SAPLING.get());
        this.add(RBlocks.ARCANE_LEAVES.get(), block ->
                createLeavesDrops(block, RBlocks.ARCANE_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        // Runic Network
        dropSelf(RBlocks.GRIMOIRE_BLOCK.get());
        dropSelf(RBlocks.TOME_SHELF_BLOCK.get());

//        add(RBlocks.ORE_BLOCK.get(),
//                block -> createOreDrop(RBlocks.ORE_BLOCK.get(), RItems.ARCANE_DUST.get()));

    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks(){
        return RBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
