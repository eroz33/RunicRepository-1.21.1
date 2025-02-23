package net.eroz33.runic_repository.block.entity;

import net.eroz33.runic_repository.block.runic_network.TomeShelfBlock;
import net.eroz33.runic_repository.item.RItems;

import net.eroz33.runic_repository.menu.GrimoireMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class GrimoireBlockEntity extends BlockEntity implements MenuProvider {
    private static final int SEARCH_RADIUS = 5;

    public GrimoireBlockEntity(BlockPos pos, BlockState state) {
        super(RBlockEntity.GRIMOIRE_BE.get(), pos, state);
    }

    public Component getDisplayName(){
        return Component.translatable("gui.runic_repository.grimoire");
    }

    @Nullable
    public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
        return new GrimoireMenu(id, playerInventory, this);
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }
}
