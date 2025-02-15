package net.eroz33.runicrepository.item;

import net.eroz33.runicrepository.api.tome.IStorageTomeManager;
import net.eroz33.runicrepository.api.tome.IStorageTomeProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StorageTomeManager implements IStorageTomeManager {
    public static final StorageTomeManager INSTANCE = new StorageTomeManager();
    private final Map<UUID, Object> tomeDataMap = new HashMap<>();

    @Override
    public void set(UUID id, Object data){
        tomeDataMap.put(id,data);
    }

    @Override
    public void markForSaving(){

    }
}
