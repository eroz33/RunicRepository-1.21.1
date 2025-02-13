package net.eroz33.runicrepository.setup;

import net.eroz33.runicrepository.RunicCreativeModeTabItems;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

public final class CommonSetup {
    private CommonSetup(){ }

    @SubscribeEvent
    public static void onRegister(final RegisterEvent e) {
        e.register(Registries.CREATIVE_MODE_TAB, RunicCreativeModeTabItems::register);
    }

    // Non-specific Mod Setup
    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent e){

    }
}
