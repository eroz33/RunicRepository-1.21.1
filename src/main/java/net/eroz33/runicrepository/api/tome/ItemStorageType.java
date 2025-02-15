package net.eroz33.runicrepository.api.tome;

public enum ItemStorageType {
    RUNIC(1024),
    ARCANE(2048),
    WIZENED(4096),
    MYSTIC(8192);

    private final int capacity;

    ItemStorageType(int capacity){
        this.capacity = capacity;
    }

    public int getCapacity(){
        return capacity;
    }
}
