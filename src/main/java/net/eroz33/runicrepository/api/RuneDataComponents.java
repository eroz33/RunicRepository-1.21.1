package net.eroz33.runicrepository.api;

import net.eroz33.runicrepository.api.tome.TomeUUID;
import net.eroz33.runicrepository.api.tome.TomeUUIDCodec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static net.eroz33.runicrepository.RunicRepository.MOD_ID;

public final class RuneDataComponents {
    // Create the Deferred Register for data components
    public static final DeferredRegister.DataComponents REGISTRAR =
            DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, MOD_ID);

    // Register our TOME_UUID data component type:
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<TomeUUID>> TOME_UUID_TYPE =
            REGISTRAR.registerComponentType(
                    "tome_uuid",
                    builder -> builder.persistent(TomeUUIDCodec.CODEC) // persistent so it's saved to disk
            );

    private RuneDataComponents(){}
}
