package net.eroz33.runicrepository.item;

import net.eroz33.runicrepository.api.API;

import net.eroz33.runicrepository.api.RuneDataComponents;
import net.eroz33.runicrepository.api.tome.IStorageTomeProvider;
import net.eroz33.runicrepository.api.tome.ItemStorageType;
import net.eroz33.runicrepository.api.tome.TomeUUID;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.UUID;

public class ArcaneTomeItem extends Item implements IStorageTomeProvider {
    private final ItemStorageType type;

    public ArcaneTomeItem(ItemStorageType type) {
        super(new Item.Properties().stacksTo(1));
        this.type = type;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected){
        super.inventoryTick(stack, level, entity, slot, selected);

        if (!level.isClientSide && !isValid(stack) && entity instanceof Player){
            UUID new_id = UUID.randomUUID();
            stack.set(RuneDataComponents.TOME_UUID_TYPE.value(), new TomeUUID(new_id));

            API.instance().getStorageTomeManager((ServerLevel) level).set(
                    new_id,
                    API.instance().createDefaultTome((ServerLevel) level, getCapacity(stack), (Player) entity));
            API.instance().getStorageTomeManager((ServerLevel) level).markForSaving();
        }
    }

    @Override
    public int getEntityLifespan(ItemStack stack, Level level) {
        return Integer.MAX_VALUE;
    }

    @Override
    public UUID getId(ItemStack tome) {
        // retrieve the record form the data component
        TomeUUID rec = tome.get(RuneDataComponents.TOME_UUID_TYPE.value());
        return rec != null ? rec.id() : null;
    }

    @Override
    public void setId(ItemStack tome, UUID id) {
        if (id == null) {
            tome.remove(RuneDataComponents.TOME_UUID_TYPE.value());
        } else {
            tome.set(RuneDataComponents.TOME_UUID_TYPE.value(), new TomeUUID(id));
        }
    }

    @Override
    public boolean isValid(ItemStack tome) {
        return tome.get(RuneDataComponents.TOME_UUID_TYPE.value()) != null;
    }

    @Override
    public int getCapacity(ItemStack tome) {
        return type.getCapacity();
    }

    @Override
    public void markForSaving() {

    }
}
