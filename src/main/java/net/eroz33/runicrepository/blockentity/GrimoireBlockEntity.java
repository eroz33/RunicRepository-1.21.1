package net.eroz33.runicrepository.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import static net.eroz33.runicrepository.core.RunicBlockEntities.GRIMOIRE_BLOCK_ENTITY;

public class GrimoireBlockEntity extends BlockEntity {
    public GrimoireBlockEntity(BlockPos pos, BlockState state){
        super (GRIMOIRE_BLOCK_ENTITY.get(), pos, state);
    }
}