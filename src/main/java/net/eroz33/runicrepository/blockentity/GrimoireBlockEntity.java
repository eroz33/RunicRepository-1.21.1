package net.eroz33.runicrepository.blockentity;

import net.eroz33.runicrepository.RunicBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class GrimoireBlockEntity extends BaseBlockEntity {

    // List of connected TomeShelf block positions.
    private final List<BlockPos> connectedTomeShelves = new ArrayList<>();

    public GrimoireBlockEntity(BlockPos pos, BlockState state) {
        super(RunicBlockEntities.GRIMOIRE_BLOCK_ENTITY.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, GrimoireBlockEntity blockEntity) {
        // Perform periodic tasks, e.g. updating network connections or syncing data.
    }

    /**
     * Registers a TomeShelf by its position.
     */
    public void registerTomeShelf(BlockPos shelfPos) {
        if (!connectedTomeShelves.contains(shelfPos)) {
            connectedTomeShelves.add(shelfPos);
            markDirtyAndSync();
        }
    }

    /**
     * Returns the list of connected TomeShelf positions.
     */
    public List<BlockPos> getConnectedTomeShelves() {
        return connectedTomeShelves;
    }

    @Override
    protected void readCustomData(CompoundTag tag) {
        // Clear current list.
        connectedTomeShelves.clear();
        // Check if the tag contains a list under "TomeShelves"
        if (tag.contains("TomeShelves", Tag.TAG_LIST)) {
            ListTag listTag = tag.getList("TomeShelves", Tag.TAG_COMPOUND);
            for (int i = 0; i < listTag.size(); i++) {
                CompoundTag posTag = listTag.getCompound(i);
                int x = posTag.getInt("x");
                int y = posTag.getInt("y");
                int z = posTag.getInt("z");
                BlockPos pos = new BlockPos(x, y, z);
                connectedTomeShelves.add(pos);
            }
        }
    }

    @Override
    protected void writeCustomData(CompoundTag tag) {
        // Inverse of readCustomData: write the connected TomeShelf positions.
        ListTag listTag = new ListTag();
        for (BlockPos pos : connectedTomeShelves) {
            CompoundTag posTag = new CompoundTag();
            posTag.putInt("x", pos.getX());
            posTag.putInt("y", pos.getY());
            posTag.putInt("z", pos.getZ());
            listTag.add(posTag);
        }
        tag.put("TomeShelves", listTag);
    }
}