package net.eroz33.runic_repository.menu;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

import java.util.List;

import static net.eroz33.runic_repository.core.RR.MOD_ID;

public class GrimoireScreen extends AbstractContainerScreen<GrimoireMenu> {
    public static final ResourceLocation GUI_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "textures/gui/grimoire_gui.png");
    private int scrollOffset = 0;
    private static final int ITEMS_PER_PAGE = 3*9;


    public GrimoireScreen(GrimoireMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected void init() {
        super.init();

        // Get rid of labels
        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);
        guiGraphics.blit(GUI_TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight);

        int startX = leftPos + 8;
        int startY = topPos + 18;
        int totalItems = menu.getStoredItemCount();

        if (totalItems > 0) {
            for (int i = 0; i < ITEMS_PER_PAGE; i++) {
                int index = i + scrollOffset * ITEMS_PER_PAGE;
                if (index >= totalItems) break;

                ItemStack stack = menu.getStoredItem(index);
                if (!stack.isEmpty()){
                    guiGraphics.renderItem(stack, startX + (i % 9) * 18, startY + (i / 9) * 18);
                }
            }
        }

    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        int maxOffset = Math.max(0, (menu.getStoredItemCount() -1) / ITEMS_PER_PAGE);
        scrollOffset = (int) Mth.clamp(scrollOffset - scrollY, 0, maxOffset);
        return true;
    }
}
