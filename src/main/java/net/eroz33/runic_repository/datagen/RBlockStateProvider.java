package net.eroz33.runic_repository.datagen;

import net.eroz33.runic_repository.block.RBlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
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
        blockWithItem(RBlocks.TOME_SHELF_BLOCK);
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock){
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
