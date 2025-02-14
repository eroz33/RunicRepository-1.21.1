package net.eroz33.runicrepository.blockentity;

import net.eroz33.runicrepository.RunicBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;

public class TomeShelfBlockEntity extends BaseBlockEntity {

    // Example field: maybe it holds a storage tome's unique identifier.
    private String storageTomeId = "";

    public TomeShelfBlockEntity(BlockPos pos, BlockState state) {
        super(RunicBlockEntities.TOME_SHELF_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    protected void readCustomData(CompoundTag tag) {
        if (tag.contains("StorageTomeId")) {
            storageTomeId = tag.getString("StorageTomeId");
        }
    }

    @Override
    protected void writeCustomData(CompoundTag tag) {
        tag.putString("StorageTomeId", storageTomeId);
    }

    // Additional methods to manage the storage tome data can be added here.
}
