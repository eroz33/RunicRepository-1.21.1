package net.eroz33.runicrepository.core;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.RegisterEvent;

import static net.eroz33.runicrepository.core.RunicRepository.MOD_ID;

public class RunicCreativeModeTabItems {
    private RunicCreativeModeTabItems(){ }

    public static void register(RegisterEvent.RegisterHelper<CreativeModeTab> helper){
        helper.register(ResourceLocation.fromNamespaceAndPath(MOD_ID, "general"), CreativeModeTab.builder()
            .title(Component.translatable("creativetab.runicrepository"))
            .icon(() -> new ItemStack(RunicItems.ARCANE_PRIMER))
            .displayItems((params, output) -> append(output))
            .build());
    }

    public static void append(CreativeModeTab.Output output){
        add(output, RunicItems.ARCANE_DUST);
        add(output, RunicItems.ARCANE_PRIMER);

        addBlock(output, RunicBlocks.GRIMOIRE_BLOCK);
        addBlock(output, RunicBlocks.TOME_SHELF_BLOCK);
    }

    private static void add(CreativeModeTab.Output output, DeferredHolder<Item, ? extends Item> i){
        output.accept(i.get());
    }

    private static void addBlock(CreativeModeTab.Output output, DeferredHolder<Block, ? extends Block> holder) {
        Block block = holder.get();
        output.accept(new ItemStack(block.asItem()));
    }
}
