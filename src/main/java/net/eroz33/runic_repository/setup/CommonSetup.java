package net.eroz33.runic_repository.setup;

import net.eroz33.runic_repository.core.RCreativeModeTabs;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

public final class CommonSetup {
    private CommonSetup(){ }

    @SubscribeEvent
    public static void onRegister(final RegisterEvent e) {
        
    }

    // Non-specific Mod Setup
    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent e){

    }

    // Register necessary capabilities
    public static void onRegisterCapabilities(final RegisterCapabilitiesEvent e) {

    }
}
