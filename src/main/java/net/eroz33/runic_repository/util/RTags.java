package net.eroz33.runic_repository.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import static net.eroz33.runic_repository.core.RR.MOD_ID;

public class RTags {
    public static class Blocks {
        public static final TagKey<Block> RUNIC_NETWORK = createTag("runic_network");

        private static TagKey<Block> createTag(String name){
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(MOD_ID, name));
        }
    }

    public static class Items {
        private static TagKey<Item> createTag(String name){
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(MOD_ID, name));
        }
    }
}
