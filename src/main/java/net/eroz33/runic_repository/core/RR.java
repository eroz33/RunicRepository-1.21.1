package net.eroz33.runic_repository.core;

import net.eroz33.runic_repository.block.RBlocks;
import net.eroz33.runic_repository.block.entity.RBlockEntity;
import net.eroz33.runic_repository.config.ClientConfig;
import net.eroz33.runic_repository.config.ServerConfig;
import net.eroz33.runic_repository.item.RItems;
import net.eroz33.runic_repository.setup.ClientSetup;
import net.eroz33.runic_repository.setup.CommonSetup;
import net.eroz33.runic_repository.setup.ServerSetup;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;


@Mod(RR.MOD_ID)
public final class RR
{
    public static final String MOD_ID = "runic_repository";
    public static final String NAME = "Runic Repository";

    public static final ServerConfig SERVER_CONFIG = new ServerConfig();
    public static final ClientConfig CLIENT_CONFIG = new ClientConfig();


    public RR(IEventBus eventBus, ModContainer container)
    {
        // If we are on the client
        if (FMLEnvironment.dist == Dist.CLIENT){
            eventBus.addListener(ClientSetup::onClientSetup);
            eventBus.addListener(ClientSetup::onRegisterKeymappings);
        }

        NeoForge.EVENT_BUS.register(new ServerSetup());

        container.registerConfig(ModConfig.Type.SERVER, SERVER_CONFIG.getSpec());
        container.registerConfig(ModConfig.Type.CLIENT, CLIENT_CONFIG.getSpec());

        RBlocks.register(eventBus);
        RItems.register(eventBus);
        RBlockEntity.register(eventBus);
        RCreativeModeTabs.register(eventBus);

        eventBus.addListener(CommonSetup::onCommonSetup);
        eventBus.addListener(CommonSetup::onRegister);
        eventBus.addListener(CommonSetup::onRegisterCapabilities);

    }
}
