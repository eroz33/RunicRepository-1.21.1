package net.eroz33.runicrepository.setup;

import net.eroz33.runicrepository.RunicBlocks;
import net.eroz33.runicrepository.RunicKeybindings;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.event.AddReloadListenerEvent;

public final class ClientSetup {

    private ClientSetup(){ }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent e){
        // Preform Client only setup
        ItemBlockRenderTypes.setRenderLayer(RunicBlocks.TOME_SHELF_BLOCK.get(), RenderType.translucent());
    }

    @SubscribeEvent
    public static void onRegisterKeymappings(RegisterKeyMappingsEvent e) {
        e.register(RunicKeybindings.FOCUS_SEARCH_BAR);
    }
}
