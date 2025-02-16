package net.eroz33.runicrepository.blockentity;


import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;

import static net.eroz33.runicrepository.core.RBlockEntities.TOME_SHELF_BLOCK_ENTITY;


public class TomeShelfBlockEntity extends BlockEntity {
    private final int SIZE = 6;

    private final ItemStackHandler inventory = new ItemStackHandler(SIZE){
        @Override
        protected void onContentsChanged(int slot){
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot, ItemStack stack){
            return stack.getItem().getDescriptionId().contains("tome");
        }
    };

    public TomeShelfBlockEntity(BlockPos pos, BlockState blockState) {
        super(TOME_SHELF_BLOCK_ENTITY.get(), pos, blockState);
    }

    public int getSlots() {
        return inventory.getSlots();
    }

    public ItemStack getStackInSlot(int slot) {
        return inventory.getStackInSlot(slot);
    }

    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        return inventory.insertItem(slot, stack, simulate);
    }

    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        return inventory.extractItem(slot, amount, simulate);
    }

    public int getSlotLimit(int slot) {
        return inventory.getSlotLimit(slot);
    }

    public boolean isItemValid(int slot, ItemStack stack) {
        return inventory.isItemValid(slot, stack);
    }

    public boolean addTome(ItemStack tome){
        for (int i=0; i< inventory.getSlots(); i++){
            if (inventory.insertItem(i, tome, true).isEmpty()){
                inventory.insertItem(i, tome, false);
                return true;
            }
        }
        return false; // inventory is full
    }

    public ItemStack retrieveTome(int slot){
        return inventory.extractItem(slot, inventory.getStackInSlot(slot).getCount(), false);
    }
}
