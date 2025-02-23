package net.eroz33.runic_repository.menu;

import net.eroz33.runic_repository.block.entity.GrimoireBlockEntity;
import net.eroz33.runic_repository.core.RR;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Objects;

public class RMenus {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, RR.MOD_ID);

    public static final DeferredHolder<MenuType<?>, MenuType<GrimoireMenu>> GRIMOIRE_MENU =
            MENUS.register("grimoire_menu",
                    () -> IMenuTypeExtension.create(new IContainerFactory<>() {
                        @Override
                        public GrimoireMenu create(int id, Inventory inventory, RegistryFriendlyByteBuf extraData) {
                            return new GrimoireMenu(id, inventory, (GrimoireBlockEntity) Objects.requireNonNull(inventory.player.level().getBlockEntity(extraData.readBlockPos())));
                        }
                    }));

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
