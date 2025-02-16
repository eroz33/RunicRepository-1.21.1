package net.eroz33.runicrepository.setup;

import net.eroz33.runicrepository.core.RCreativeModeTab;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

public final class CommonSetup {
    private CommonSetup(){ }

    @SubscribeEvent
    public static void onRegister(final RegisterEvent e) {
        e.register(Registries.CREATIVE_MODE_TAB, RCreativeModeTab::register);
    }

    // Non-specific Mod Setup
    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent e){

    }

    // Register necessary capabilities
    public static void onRegisterCapabilities(final RegisterCapabilitiesEvent e) {

    }
}
