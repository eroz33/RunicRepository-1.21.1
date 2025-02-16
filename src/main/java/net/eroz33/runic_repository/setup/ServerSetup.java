package net.eroz33.runic_repository.setup;

import net.eroz33.runic_repository.core.RR;
import net.minecraft.commands.Commands;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

public class ServerSetup {
    // Register Server Commands When Needed
    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent e){
        // Use this to
        e.getDispatcher().register(Commands.literal(RR.MOD_ID));
    }

}
