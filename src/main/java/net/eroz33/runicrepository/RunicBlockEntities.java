package net.eroz33.runicrepository;


import net.eroz33.runicrepository.blockentity.GrimoireBlockEntity;
import net.eroz33.runicrepository.blockentity.TomeShelfBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static net.eroz33.runicrepository.RunicRepository.MOD_ID;

public class RunicBlockEntities {

        // Setup our Deferred Register for Block Entity Types
        public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
                DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, MOD_ID);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<GrimoireBlockEntity>> GRIMOIRE_BLOCK_ENTITY =
                register("grimoire", () ->
                        BlockEntityType.Builder.of(GrimoireBlockEntity::new, RunicBlocks.GRIMOIRE_BLOCK.get()).build(null));

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TomeShelfBlockEntity>> TOME_SHELF_BLOCK_ENTITY =
                register("tomeshelf", () ->
                        BlockEntityType.Builder.of(TomeShelfBlockEntity::new, RunicBlocks.TOME_SHELF_BLOCK.get()).build(null));


    private RunicBlockEntities() { }

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }

    // Helper method to simplify registration of block entities.
    private static <T extends BlockEntity> DeferredHolder<BlockEntityType<?>, BlockEntityType<T>> register(
            String name, java.util.function.Supplier<BlockEntityType<T>> supplier) {
        return BLOCK_ENTITIES.register(name, supplier);
    }
}
