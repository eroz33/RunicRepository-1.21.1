package net.eroz33.runic_repository.item.tomes;

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
        if (!stack.has(StorageTomeComponents.STORAGE_UUID)) {
            stack.set(StorageTomeComponents.STORAGE_UUID, UUID.randomUUID());
        }
        if (!stack.has(StorageTomeComponents.STORAGE_DATA)) {
            stack.set(StorageTomeComponents.STORAGE_DATA, new HashMap<>());
        }
    }

    public UUID getStorageUUID(ItemStack stack) {
        return stack.get(StorageTomeComponents.STORAGE_UUID);
    }

    public Map<Item, Integer> getStoredItems(ItemStack stack) {
        return stack.get(StorageTomeComponents.STORAGE_DATA);
    }

    public boolean addItem(ItemStack tome, ItemStack itemStack) {
        if (!tome.has(StorageTomeComponents.STORAGE_DATA)) return false;

        Map<Item, Integer> storedItems = tome.get(StorageTomeComponents.STORAGE_DATA);
        int totalCount = storedItems.values().stream().mapToInt(Integer::intValue).sum();
        int itemCount = itemStack.getCount();

        // Check if there's room in the tome
        if (totalCount + itemCount > maxCapacity) return false;

        // Add item to tome
        storedItems.put(itemStack.getItem(), storedItems.getOrDefault(itemStack.getItem(), 0) + itemCount);
        tome.set(StorageTomeComponents.STORAGE_DATA, storedItems);
        return true;
    }

    public boolean removeItem(ItemStack tome, Item item, int count) {
        if (!tome.has(StorageTomeComponents.STORAGE_DATA)) return false;

        Map<Item, Integer> storedItems = tome.get(StorageTomeComponents.STORAGE_DATA);
        if (!storedItems.containsKey(item)) return false;

        int currentCount = storedItems.get(item);
        if (currentCount < count) return false; // Not enough items

        // Remove items
        storedItems.put(item, currentCount - count);
        if (storedItems.get(item) <= 0) {
            storedItems.remove(item);
        }
        tome.set(StorageTomeComponents.STORAGE_DATA, storedItems);
        return true;
    }

    public boolean isFull(ItemStack tome) {
        if (!tome.has(StorageTomeComponents.STORAGE_DATA)) return false;
        Map<Item, Integer> storedItems = tome.get(StorageTomeComponents.STORAGE_DATA);
        int totalCount = storedItems.values().stream().mapToInt(Integer::intValue).sum();
        return totalCount >= maxCapacity;
    }

    public boolean isEmpty(ItemStack tome) {
        if (!tome.has(StorageTomeComponents.STORAGE_DATA)) return true;
        return tome.get(StorageTomeComponents.STORAGE_DATA).isEmpty();
    }
}
