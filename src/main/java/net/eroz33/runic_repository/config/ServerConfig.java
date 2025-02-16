package net.eroz33.runic_repository.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ServerConfig {
    private final ModConfigSpec spec;

    public ServerConfig(){
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

        spec = builder.build();
    }

    public ModConfigSpec getSpec() {
        return spec;
    }
}
