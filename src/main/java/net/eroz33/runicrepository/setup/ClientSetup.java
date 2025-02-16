package net.eroz33.runicrepository.setup;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;

public final class ClientSetup {

    private ClientSetup(){ }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent e){
        // Preform Client only setup

    }

    @SubscribeEvent
    public static void onRegisterKeymappings(RegisterKeyMappingsEvent e) {

    }
}
