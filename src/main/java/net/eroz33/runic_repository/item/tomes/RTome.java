package net.eroz33.runic_repository.item.tomes;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import java.util.Map;
import java.util.UUID;
import java.util.HashMap;

public class RTome extends Item {
    private final int maxCapacity; // Maximum items this tome can store

    public RTome(Properties properties, int maxCapacity) {
        super(properties);
        this.maxCapacity = maxCapacity;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
        // Initialize only on the server side to avoid duplicate work on clients
        if (!world.isClientSide()) {
            if (!stack.has(StorageTomeComponents.STORAGE_UUID)) {
                stack.set(StorageTomeComponents.STORAGE_UUID, UUID.randomUUID());
            }
            if (!stack.has(StorageTomeComponents.STORAGE_DATA)) {
                // Initialize with an empty HashMap
                stack.set(StorageTomeComponents.STORAGE_DATA, new HashMap<Item, Integer>());
            }
        }
    }

    public UUID getStorageUUID(ItemStack stack) {
        return stack.get(StorageTomeComponents.STORAGE_UUID);
    }

    public Map<Item, Integer> getStoredItems(ItemStack tomeStack) {
        Map<Item, Integer> storedItems = tomeStack.get(StorageTomeComponents.STORAGE_DATA);
        return storedItems != null ? storedItems : new HashMap<>();
    }

    public boolean removeItem(ItemStack tomeStack, ItemStack itemStack) {
        Map<Item, Integer> storedData = tomeStack.get(StorageTomeComponents.STORAGE_DATA);
        if (storedData == null || !storedData.containsKey(itemStack.getItem())) {
            return false;
        }
        int currentCount = storedData.get(itemStack.getItem());
        if (currentCount < itemStack.getCount()) {
            return false;
        }
        // Reduce stored count and remove key if count goes to zero
        storedData.put(itemStack.getItem(), currentCount - itemStack.getCount());
        if (storedData.get(itemStack.getItem()) <= 0) {
            storedData.remove(itemStack.getItem());
        }
        tomeStack.set(StorageTomeComponents.STORAGE_DATA, storedData);
        return true;
    }

    public boolean isFull(ItemStack tomeStack) {
        if (!tomeStack.has(StorageTomeComponents.STORAGE_DATA)) return false;
        Map<Item, Integer> storedItems = tomeStack.get(StorageTomeComponents.STORAGE_DATA);
        int totalCount = storedItems.values().stream().mapToInt(Integer::intValue).sum();
        return totalCount >= this.maxCapacity;
    }

    public boolean isEmpty(ItemStack tomeStack) {
        if (!tomeStack.has(StorageTomeComponents.STORAGE_DATA)) return true;
        return tomeStack.get(StorageTomeComponents.STORAGE_DATA).isEmpty();

    }

    /**
     * Attempts to store items from itemStack into the tome.
     * Only items up to the available capacity are stored.
     * Returns the number of items actually stored.
     */
    public int storeItem(ItemStack tomeStack, ItemStack itemStack) {
        if (tomeStack.isEmpty() || itemStack.isEmpty()) return 0;

        // Retrieve stored items and ensure it's mutable
        Map<Item, Integer> storedItems = new HashMap<>(tomeStack.get(StorageTomeComponents.STORAGE_DATA));

        Item item = itemStack.getItem();
        int count = itemStack.getCount();

        // Ensure storage capacity is not exceeded
        int currentTotal = storedItems.values().stream().mapToInt(Integer::intValue).sum();
        int spaceLeft = this.maxCapacity - currentTotal;
        if (spaceLeft <= 0) return 0; // Tome is already full

        int itemsToStore = Math.min(spaceLeft, count);
        storedItems.put(item, storedItems.getOrDefault(item, 0) + itemsToStore);

        // Set updated mutable map back into the tome
        tomeStack.set(StorageTomeComponents.STORAGE_DATA, storedItems);
        Minecraft.getInstance().player.sendSystemMessage(Component.literal("STORAGE_DATA:" + StorageTomeComponents.STORAGE_DATA));
        return itemsToStore;
    }

}

