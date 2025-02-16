package net.eroz33.runic_repository.core;

import net.eroz33.runic_repository.block.RBlocks;
import net.eroz33.runic_repository.item.RItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static net.eroz33.runic_repository.core.RR.MOD_ID;

public class RCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    public static final Supplier<CreativeModeTab> RUNIC_TAB =
            CREATIVE_MODE_TAB.register("runic_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("creativetab.runic_repository"))
                    .icon(() -> new ItemStack(RItems.ARCANE_DUST.get()))
                    .displayItems((pParams, pOutput) -> {
                        pOutput.accept(RItems.ARCANE_DUST);
                        pOutput.accept(RItems.ARCANE_PRIMER);
                        pOutput.accept(RItems.ARCANE_QUILL);

                        pOutput.accept(RBlocks.GRIMOIRE_BLOCK);
                        pOutput.accept(RBlocks.TOME_SHELF_BLOCK);
                    })
                    .build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
