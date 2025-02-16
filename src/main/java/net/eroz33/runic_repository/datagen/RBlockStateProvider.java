package net.eroz33.runic_repository.datagen;

import net.eroz33.runic_repository.block.RBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import static net.eroz33.runic_repository.core.RR.MOD_ID;

public class RBlockStateProvider extends BlockStateProvider {
    public RBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(RBlocks.GRIMOIRE_BLOCK);

        horizontalBlock(RBlocks.TOME_SHELF_BLOCK.get(), modLoc("block/tome_shelf_outer_block"), modLoc("block/tome_shelf_block"), modLoc("block/tome_shelf_outer_block"));
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock){
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
