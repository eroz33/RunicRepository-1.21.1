package net.eroz33.runicrepository.api.tome;

import net.minecraft.world.item.ItemStack;

import java.util.UUID;

/**
 * Maps tome items to an id
 * Implement this on a tome item
 */
public interface IStorageTomeProvider {
    UUID getId(ItemStack tome);

    void setId(ItemStack tome, UUID id);

    boolean isValid(ItemStack tome);

    int getCapacity(ItemStack tome);

    void markForSaving();
}
