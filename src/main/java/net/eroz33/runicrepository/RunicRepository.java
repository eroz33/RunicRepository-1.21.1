package net.eroz33.runicrepository;

import net.eroz33.runicrepository.api.API;
import net.eroz33.runicrepository.config.ClientConfig;
import net.eroz33.runicrepository.config.ServerConfig;
import net.eroz33.runicrepository.network.NetworkHandler;
import net.eroz33.runicrepository.setup.ClientSetup;
import net.eroz33.runicrepository.setup.CommonSetup;
import net.eroz33.runicrepository.setup.ServerSetup;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;


@Mod(RunicRepository.MOD_ID)
public final class RunicRepository
{
    public static final String MOD_ID = "runicrepository";
    public static final String NAME = "Runic Repository";

    public static final NetworkHandler NETWORK_HANDLER = new NetworkHandler();
    public static final ServerConfig SERVER_CONFIG = new ServerConfig();
    public static final ClientConfig CLIENT_CONFIG = new ClientConfig();


    public RunicRepository(IEventBus eventBus, ModContainer container)
    {
        // If we are on the client
        if (FMLEnvironment.dist == Dist.CLIENT){
            eventBus.addListener(ClientSetup::onClientSetup);
        }

        NeoForge.EVENT_BUS.register(new ServerSetup());

        container.registerConfig(ModConfig.Type.SERVER, SERVER_CONFIG.getSpec());
        container.registerConfig(ModConfig.Type.CLIENT, CLIENT_CONFIG.getSpec());

        RunicBlocks.register(eventBus);
        RunicItems.register(eventBus);

        eventBus.addListener(CommonSetup::onCommonSetup);
        eventBus.addListener(CommonSetup::onRegister);
        eventBus.addListener(CommonSetup::onRegisterCapabilities);

        API.deliver();
    }
}
