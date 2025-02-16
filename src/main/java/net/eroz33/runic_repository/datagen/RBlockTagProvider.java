package net.eroz33.runic_repository.datagen;

import net.eroz33.runic_repository.block.RBlocks;
import net.eroz33.runic_repository.util.RTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static net.eroz33.runic_repository.core.RR.MOD_ID;

public class RBlockTagProvider extends BlockTagsProvider {
    public RBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(RTags.Blocks.RUNIC_NETWORK)
                .add(RBlocks.GRIMOIRE_BLOCK.get())
                .add(RBlocks.TOME_SHELF_BLOCK.get());

        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(RBlocks.ARCANE_LOG.get())
                .add(RBlocks.ARCANE_WOOD.get())
                .add(RBlocks.STRIPPED_ARCANE_LOG.get())
                .add(RBlocks.STRIPPED_ARCANE_WOOD.get());
    }
}
