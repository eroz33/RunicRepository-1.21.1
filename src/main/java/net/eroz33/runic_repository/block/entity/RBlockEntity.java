package net.eroz33.runic_repository.block.entity;

import net.eroz33.runic_repository.block.RBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static net.eroz33.runic_repository.core.RR.MOD_ID;

public class RBlockEntity {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, MOD_ID);

    public static final Supplier<BlockEntityType<TomeShelfBlockEntity>> TOME_SHELF_BE =
            BLOCK_ENTITIES.register("tome_shelf_be", () -> BlockEntityType.Builder.of(
                    TomeShelfBlockEntity::new, RBlocks.TOME_SHELF_BLOCK.get()).build(null));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
