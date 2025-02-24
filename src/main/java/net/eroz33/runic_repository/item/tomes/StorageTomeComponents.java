package net.eroz33.runic_repository.item.tomes;


import com.mojang.serialization.Codec;
import net.minecraft.core.UUIDUtil;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.RegisterEvent;


import java.util.Map;
import java.util.UUID;

import static net.eroz33.runic_repository.core.RR.MOD_ID;

public class StorageTomeComponents {
    public static final ResourceLocation STORAGE_UUID_ID = ResourceLocation.fromNamespaceAndPath(MOD_ID, "storage_uuid");
    public static final ResourceLocation STORAGE_DATA_ID = ResourceLocation.fromNamespaceAndPath(MOD_ID, "storage_data");

    public static final DataComponentType<UUID> STORAGE_UUID = DataComponentType.<UUID>builder()
            .persistent(UUIDUtil.CODEC)
            .build();

    public static final DataComponentType<Map<Item, Integer>> STORAGE_DATA = DataComponentType.<Map<Item, Integer>>builder()
            .persistent(Codec.unboundedMap(BuiltInRegistries.ITEM.byNameCodec(), Codec.INT))
            .build();

    public static void register(RegisterEvent event) {
        if (event.getRegistryKey().equals(Registries.DATA_COMPONENT_TYPE)) {
            event.register(Registries.DATA_COMPONENT_TYPE, STORAGE_UUID_ID, () -> STORAGE_UUID);
            event.register(Registries.DATA_COMPONENT_TYPE, STORAGE_DATA_ID, () -> STORAGE_DATA);
        }
    }

    public static void init(IEventBus modEventBus) {
        modEventBus.addListener(StorageTomeComponents::register);
    }
}
