package net.eroz33.runicrepository.blockentity;

import net.eroz33.runicrepository.RunicBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;

public class TomeShelfBlockEntity extends BaseBlockEntity implements IItemHandler {
    // Internal invenetory
    private final ItemStackHandler inventory = new ItemStackHandler(6){
        @Override
        protected void onContentsChanged(int slot){
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot, ItemStack stack){
            return stack.getItem().getDescriptionId().contains("tome");
        }
    };

    // Example field: maybe it holds a storage tome's unique identifier.
    private String storageTomeId = "";

    public TomeShelfBlockEntity(BlockPos pos, BlockState state) {
        super(RunicBlockEntities.TOME_SHELF_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    protected void readCustomData(CompoundTag tag) {
        if (tag.contains("Inventory")) {
            inventory.deserializeNBT(tag.getCompound("Inventory"));
        }
    }

    @Override
    protected void writeCustomData(CompoundTag tag) {
        tag.put("Inventory", inventory.serializeNBT());
    }

    @Override
    public int getSlots() {
        return inventory.getSlots();
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return inventory.getStackInSlot(slot);
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        return inventory.insertItem(slot, stack, simulate);
    }

    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        return inventory.extractItem(slot, amount, simulate);
    }

    @Override
    public int getSlotLimit(int slot) {
        return inventory.getSlotLimit(slot);
    }

    @Override
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
