package net.eroz33.runicrepository;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

// Create our keybindings
public final class RunicKeybindings {
    public static final KeyMapping FOCUS_SEARCH_BAR = new KeyMapping(
            "key.runicrepository.focusSearchBar",
            KeyConflictContext.GUI,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_TAB,
            RunicRepository.NAME
    );
}
