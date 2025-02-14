package net.eroz33.runicrepository.storage;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StorageManager extends SavedData {
    private final Map<UUID, StorageTomeData> storageMap = new HashMap<>();

    // Loads the StorageManager from NBT data
    public static StorageManager load(CompoundTag tag){
        StorageManager manager = new StorageManager();
        // TODO: Deserialize storageMap from tag
        return manager;
    }

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider registries){
        // TODO: Serialize storageMap into tag
        return tag;
    }

    // Retrieve storage data by UUID
    public StorageTomeData get(UUID uuid){
        return storageMap.get(uuid);
    }

    // Add or update storage data for a given UUID
    public void put(UUID uuid, StorageTomeData data) {
        storageMap.put(uuid, data);
        setDirty();
    }

    // Create new storage entry and return its UUID
    public UUID createNewStorageTomeData(StorageTomeData data){
        UUID uuid = UUID.randomUUID();
        storageMap.put(uuid, data);
        setDirty();
        return uuid;
    }
}
