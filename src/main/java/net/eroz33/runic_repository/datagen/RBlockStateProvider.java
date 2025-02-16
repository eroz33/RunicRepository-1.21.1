package net.eroz33.runic_repository.datagen;

import net.eroz33.runic_repository.block.RBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
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
        // Arcane Tree
        logBlock((RotatedPillarBlock) RBlocks.ARCANE_LOG.get());
        axisBlock((RotatedPillarBlock) RBlocks.ARCANE_WOOD.get(), blockTexture(RBlocks.ARCANE_LOG.get()), blockTexture(RBlocks.ARCANE_LOG.get()));
        logBlock((RotatedPillarBlock) RBlocks.STRIPPED_ARCANE_LOG.get());
        axisBlock((RotatedPillarBlock) RBlocks.STRIPPED_ARCANE_WOOD.get(), blockTexture(RBlocks.STRIPPED_ARCANE_LOG.get()), blockTexture(RBlocks.STRIPPED_ARCANE_LOG.get()));

        blockItem(RBlocks.ARCANE_LOG);
        blockItem(RBlocks.ARCANE_WOOD);
        blockItem(RBlocks.STRIPPED_ARCANE_LOG);
        blockItem(RBlocks.STRIPPED_ARCANE_WOOD);

        blockWithItem(RBlocks.ARCANE_PLANKS);
        leavesBlock(RBlocks.ARCANE_LEAVES);
        saplingBlock(RBlocks.ARCANE_SAPLING);


        // Runic Network
        blockWithItem(RBlocks.GRIMOIRE_BLOCK);
        horizontalBlock(RBlocks.TOME_SHELF_BLOCK.get(), modLoc("block/tome_shelf_outer_block"), modLoc("block/tome_shelf_block"), modLoc("block/tome_shelf_outer_block"));
    }

    private void leavesBlock(DeferredBlock<Block> deferredBlock){
        simpleBlockWithItem(deferredBlock.get(),
                models().singleTexture(BuiltInRegistries.BLOCK.getKey(deferredBlock.get()).getPath(), ResourceLocation.parse("minecraft:block/leaves"),
                        "all", blockTexture(deferredBlock.get())).renderType("cutout"));
    }

    private void saplingBlock(DeferredBlock<Block> deferredBlock){
        simpleBlock(deferredBlock.get(), models().cross(BuiltInRegistries.BLOCK.getKey(deferredBlock.get()).getPath(), blockTexture(deferredBlock.get())).renderType("cutout"));
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock){
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<Block> deferredBlock){
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("runic_repository:block/" + deferredBlock.getId().getPath()));
    }
}
