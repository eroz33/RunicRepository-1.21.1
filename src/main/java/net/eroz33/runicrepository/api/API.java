package net.eroz33.runicrepository.api;

import net.eroz33.runicrepository.api.tome.IStorageTomeProvider;
import net.eroz33.runicrepository.item.StorageTomeManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class API {
    private static final API INSTANCE = new API();

    public static void deliver() {

    }

    public static API instance() {
        return INSTANCE;
    }

    public StorageTomeManager getStorageTomeManager(ServerLevel level) {
        return StorageTomeManager.INSTANCE;
    }

    public Object createDefaultTome(ServerLevel level, int capacity, Player player){
        return new Object();
    }
}
