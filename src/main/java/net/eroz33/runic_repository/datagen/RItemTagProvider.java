package net.eroz33.runic_repository.datagen;

import net.eroz33.runic_repository.block.RBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static net.eroz33.runic_repository.core.RR.MOD_ID;

public class RItemTagProvider extends ItemTagsProvider {
    public RItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                            CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ItemTags.LOGS_THAT_BURN)
                .add(RBlocks.ARCANE_LOG.get().asItem())
                .add(RBlocks.ARCANE_WOOD.get().asItem())
                .add(RBlocks.STRIPPED_ARCANE_LOG.get().asItem())
                .add(RBlocks.STRIPPED_ARCANE_WOOD.get().asItem());

        tag(ItemTags.PLANKS)
                .add(RBlocks.ARCANE_PLANKS.get().asItem());

    }
}
