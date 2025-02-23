package net.eroz33.runic_repository.block.entity;

import net.eroz33.runic_repository.block.runic_network.TomeShelfBlock;
import net.eroz33.runic_repository.item.RItems;

import net.eroz33.runic_repository.menu.GrimoireMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrimoireBlockEntity extends BlockEntity implements MenuProvider {
    private static final int SEARCH_RADIUS = 5;

    public GrimoireBlockEntity(BlockPos pos, BlockState state) {
        super(RBlockEntity.GRIMOIRE_BE.get(), pos, state);
    }

    public List<BlockPos> findTomeShelves() {
        List<BlockPos> shelves = new ArrayList<>();
        if (level != null) {
            BlockPos.betweenClosedStream(
                    worldPosition.offset(-SEARCH_RADIUS, -SEARCH_RADIUS, -SEARCH_RADIUS),
                    worldPosition.offset(SEARCH_RADIUS, SEARCH_RADIUS, SEARCH_RADIUS)
            ).forEach(pos -> {
                if (level.getBlockState(pos).getBlock() instanceof TomeShelfBlock) {
                    shelves.add(pos.immutable());
                }
            });
        }
        return shelves;
    }

    public Component getDisplayName(){
        return Component.translatable("gui.runic_repository.grimoire");
    }

    @Nullable
    public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
        return new GrimoireMenu(id, playerInventory, this);
    }
}
