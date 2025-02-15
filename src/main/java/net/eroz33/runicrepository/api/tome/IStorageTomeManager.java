package net.eroz33.runicrepository.api.tome;

import java.util.UUID;

public interface IStorageTomeManager {
    void set(UUID id, Object tomeData);
    void markForSaving();
}
