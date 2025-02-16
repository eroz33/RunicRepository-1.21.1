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
