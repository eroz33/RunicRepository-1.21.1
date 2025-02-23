package net.eroz33.runic_repository.setup;

import net.eroz33.runic_repository.block.RBlocks;
import net.eroz33.runic_repository.menu.RMenus;
import net.eroz33.runic_repository.menu.GrimoireScreen;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

public final class ClientSetup {

    private ClientSetup(){ }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent e){

    }

    @SubscribeEvent
    public static void onRegisterKeymappings(RegisterKeyMappingsEvent e) {

    }

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(RMenus.GRIMOIRE_MENU.get(), GrimoireScreen::new);
    }
}
