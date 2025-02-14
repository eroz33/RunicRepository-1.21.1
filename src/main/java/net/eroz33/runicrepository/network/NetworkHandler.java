package net.eroz33.runicrepository.network;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;

public class NetworkHandler {
    public void sendTo(ServerPlayer player, CustomPacketPayload message){
        if (!(player.isFakePlayer())){
            PacketDistributor.sendToPlayer(player,message);
        }
    }

    public void sendToServer(CustomPacketPayload message){
        PacketDistributor.sendToServer(message);
    }


}
