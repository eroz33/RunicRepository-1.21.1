package net.eroz33.runicrepository;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.RegisterEvent;

import static net.eroz33.runicrepository.RunicRepository.MOD_ID;

public class RunicCreativeModeTabItems {
    private RunicCreativeModeTabItems(){ }

    public static void register(RegisterEvent.RegisterHelper<CreativeModeTab> helper){
        helper.register(ResourceLocation.fromNamespaceAndPath(MOD_ID, "general"), CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.runicrepository"))
            .icon(() -> new ItemStack(RunicItems.ARCANE_PRIMER))
            .displayItems((params, output) -> append(output))
            .build());
    }

    public static void append(CreativeModeTab.Output output){
        add(output, RunicItems.ARCANE_DUST);
        add(output, RunicItems.ARCANE_PRIMER);
    }

    private static void add(CreativeModeTab.Output output, DeferredHolder<Item, ? extends Item> i){
        output.accept(i.get());
    }
}
