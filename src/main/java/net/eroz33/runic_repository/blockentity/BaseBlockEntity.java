package net.eroz33.runic_repository.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class BaseBlockEntity extends BlockEntity {

    public BaseBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    /**
     * Called when loading the block entity from NBT.
     */
    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        // Call your custom data reader here.
        readCustomData(tag);
    }

    /**
     * Called when saving the block entity to NBT.
     * Writes any custom data to the provided tag.
     */
    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        writeCustomData(tag);
    }

    /**
     * Reads custom data for the block entity.
     *
     * @param tag The CompoundTag containing the saved data.
     */
    protected abstract void readCustomData(CompoundTag tag);

    /**
     * Writes custom data for the block entity.
     *
     * @param tag The CompoundTag to write data into.
     */
    protected abstract void writeCustomData(CompoundTag tag);

    /**
     * Marks this block entity as changed and, if desired, sends a network update.
     */
    public void markDirtyAndSync() {
        setChanged();
        if (level != null) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }
}