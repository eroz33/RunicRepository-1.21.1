package net.eroz33.runicrepository.core;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.RegisterEvent;

import static net.eroz33.runicrepository.core.RR.MOD_ID;

public class RCreativeModeTab {
    private RCreativeModeTab(){ }

    public static void register(RegisterEvent.RegisterHelper<CreativeModeTab> helper){
        helper.register(ResourceLocation.fromNamespaceAndPath(MOD_ID, "general"), CreativeModeTab.builder()
            .title(Component.translatable("creativetab.runicrepository"))
            .icon(() -> new ItemStack(RItems.ARCANE_PRIMER))
            .displayItems((params, output) -> append(output))
            .build());
    }

    public static void append(CreativeModeTab.Output output){
        add(output, RItems.ARCANE_DUST);
        add(output, RItems.ARCANE_PRIMER);

        addBlock(output, RBlocks.GRIMOIRE_BLOCK);
        addBlock(output, RBlocks.TOME_SHELF_BLOCK);
    }

    private static void add(CreativeModeTab.Output output, DeferredHolder<Item, ? extends Item> i){
        output.accept(i.get());
    }

    private static void addBlock(CreativeModeTab.Output output, DeferredHolder<Block, ? extends Block> holder) {
        Block block = holder.get();
        output.accept(new ItemStack(block.asItem()));
    }
}
