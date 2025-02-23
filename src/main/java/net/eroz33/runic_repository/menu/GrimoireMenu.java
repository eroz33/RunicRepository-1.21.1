package net.eroz33.runic_repository.menu;

import net.eroz33.runic_repository.block.entity.GrimoireBlockEntity;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Map;

public class GrimoireMenu extends AbstractContainerMenu {
    private final GrimoireBlockEntity grimoire;
    private final Container inventory;

    public GrimoireMenu(int id, Inventory playerInventory, GrimoireBlockEntity grimoire) {
        super(RMenus.GRIMOIRE_MENU.get(), id);
        this.grimoire = grimoire;
        this.inventory = new SimpleContainer(27);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }
}
