package net.eroz33.runicrepository.block;

import net.eroz33.runicrepository.RunicBlockEntities;
import net.eroz33.runicrepository.blockentity.GrimoireBlockEntity;
import net.eroz33.runicrepository.blockentity.TomeShelfBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class TomeShelfBlock extends Block implements EntityBlock {
    public TomeShelfBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state){
        return new TomeShelfBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (!level.isClientSide && type == RunicBlockEntities.GRIMOIRE_BLOCK_ENTITY.get()) {
            return (lvl, pos, blockState, blockEntity) -> {
                if (blockEntity instanceof GrimoireBlockEntity grimoire) {
                    GrimoireBlockEntity.tick(lvl, pos, blockState, grimoire);
                }
            };
        }
        return null;
    }


}
