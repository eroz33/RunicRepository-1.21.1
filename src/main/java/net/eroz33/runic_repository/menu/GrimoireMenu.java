package net.eroz33.runic_repository.menu;

import net.eroz33.runic_repository.block.RBlocks;
import net.eroz33.runic_repository.block.entity.GrimoireBlockEntity;
import net.eroz33.runic_repository.block.entity.TomeShelfBlockEntity;
import net.eroz33.runic_repository.item.tomes.RTome;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;

import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.Map;

public class GrimoireMenu extends AbstractContainerMenu {
    private final GrimoireBlockEntity grimoire;
    private final Level level;
    private final SimpleContainer virtualInventory;
    private final Slot inputSlot;
    private static final int INVENTORY_SIZE = 27;

    public GrimoireMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
        this(id, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()));
    }

    public GrimoireMenu(int id, Inventory inv, BlockEntity blockEntity) {
        super(RMenus.GRIMOIRE_MENU.get(), id);
        this.grimoire = ((GrimoireBlockEntity) blockEntity);
        this.level = inv.player.level();
        this.virtualInventory = new SimpleContainer(INVENTORY_SIZE);


        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        grimoire.updateLinkedShelves();
        loadStoredItems();

        // **Input slot for inserting items**
        this.inputSlot = addSlot(new Slot(new SimpleContainer(1), 0, 152, 36) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return true; // Accept any item
            }

            @Override
            public void setChanged() {
                super.setChanged();
                ItemStack stack = this.getItem();
                if (!stack.isEmpty()) {
                    insertIntoTome(stack);
                }
            }

            @Override
            public void onQuickCraft(ItemStack oldStack, ItemStack newStack) {
                super.onQuickCraft(oldStack, newStack);
                if (!newStack.isEmpty()) {
                    insertIntoTome(newStack);
                }
            }
        });

        // **Create 27 slots for displaying stored items**
        for (int i = 0; i < INVENTORY_SIZE; i++) {
            final int index = i;
            addSlot(new Slot(virtualInventory, i, 8 + (i % 9) * 18, 18 + (i / 9) * 18) {
                @Override
                public boolean mayPlace(ItemStack stack) {
                    return true; // Prevent players from manually placing items
                }

                @Override
                public void onTake(Player player, ItemStack stack) {
                    super.onTake(player, stack);
                    removeFromTome(stack, index);
                }
            });
        }
    }

    private void insertIntoTome(ItemStack stack) {
        for (TomeShelfBlockEntity shelf : grimoire.getLinkedShelves()) {
            for (int i = 0; i < shelf.inventory.getSlots(); i++) {
                ItemStack tomeStack = shelf.inventory.getStackInSlot(i);
                if (tomeStack.getItem() instanceof RTome tomeItem) {
                    Minecraft.getInstance().player.sendSystemMessage(Component.literal("insertIntoTome: " + tomeItem));
                    int inserted = tomeItem.storeItem(tomeStack, stack);
                    if (inserted > 0) {
                        stack.shrink(inserted);
                        if (stack.isEmpty()) inputSlot.set(ItemStack.EMPTY);
                        loadStoredItems(); // Refresh inventory
                        return;
                    }
                }
            }
        }
    }

    private void loadStoredItems() {
        virtualInventory.clearContent(); // Clear the virtual inventory before repopulating

        // Ensure shelves are connected
        if (grimoire.getLinkedShelves().isEmpty()) {
            return;
        }

        int slotIndex = 0;
        for (TomeShelfBlockEntity shelf : grimoire.getLinkedShelves()) {
            for (int i = 0; i < shelf.inventory.getSlots(); i++) {
                ItemStack tomeStack = shelf.inventory.getStackInSlot(i);
                if (tomeStack.isEmpty() || !(tomeStack.getItem() instanceof RTome tomeItem)) {
                    continue; // Skip empty or invalid tomes
                }

                // 🔥 Load stored items from the tome
                Map<Item, Integer> storedData = tomeItem.getStoredItems(tomeStack);
                if (storedData == null || storedData.isEmpty()) {
                    continue; // Skip if empty
                }

                for (Map.Entry<Item, Integer> entry : storedData.entrySet()) {
                    if (slotIndex >= virtualInventory.getContainerSize()) break; // Prevent exceeding the inventory size
                    Item item = entry.getKey();
                    int count = entry.getValue();
                    virtualInventory.setItem(slotIndex++, new ItemStack(item, count));
                }
            }
        }
    }

    private void removeFromTome(ItemStack stack, int slotIndex) {
        for (TomeShelfBlockEntity shelf : grimoire.getLinkedShelves()) {
            for (int i = 0; i < shelf.inventory.getSlots(); i++) {
                ItemStack tomeStack = shelf.inventory.getStackInSlot(i);
                if (tomeStack.getItem() instanceof RTome tomeItem) {
                    if (tomeItem.removeItem(tomeStack, stack)) {
                        loadStoredItems(); // Refresh after removal
                        return;
                    }
                }
            }
        }
    }

    public ItemStack getStoredItem(int index) {
        if (index >= 0 && index < virtualInventory.getContainerSize()) {
            return virtualInventory.getItem(index);
        }
        return ItemStack.EMPTY;
    }

    public int getStoredItemCount() {
        return virtualInventory.getContainerSize();
    }

    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
    private static final int TE_INVENTORY_SLOT_COUNT = 1;

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  // No item to move
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the clicked slot is a player inventory slot
        if (index < VANILLA_SLOT_COUNT) {
            // Attempt to insert into the tome
            int originalCount = sourceStack.getCount();
            insertIntoTome(sourceStack);

            // If items were inserted, update the slot
            if (sourceStack.getCount() < originalCount) {
                sourceSlot.setChanged();
                if (sourceStack.getCount() == 0) {
                    sourceSlot.set(ItemStack.EMPTY);
                }
                sourceSlot.onTake(player, sourceStack);
                return copyOfSourceStack;
            }
        }
        // Moving from Tome inventory back to player
        else if (index >= TE_INVENTORY_FIRST_SLOT_INDEX) {
            if (!moveItemStackTo(sourceStack, 0, VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + index);
            return ItemStack.EMPTY;
        }

        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, grimoire.getBlockPos()),
                player, RBlocks.GRIMOIRE_BLOCK.get());
    }

    private void addPlayerInventory(Inventory playerInventory){
        for (int i = 0; i< 3; i++){
            for (int l = 0; l < 9; l++){
                this.addSlot(new Slot(playerInventory,l+i*9+9, 8+l*18,86+i*18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory){
        for(int i = 0; i < 9; i++){
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 144));
        }
    }
}
