package net.eroz33.runicrepository.item;

import net.eroz33.runicrepository.api.API;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.UUID;

public class ArcaneTomeItem extends Item implements IStorageTomeProvider {
    public static final String NBT_ID = "ID";
    private final ItemStorageType type;

    public ArcaneTomeItem(ItemStorageType type) {
        super(new Item.Properties().stacksTo(1));
        this.type = type;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected){
        super.inventoryTick(stack, level, entity, slot, selected);

        if (!level.isClientSide && !stack.hasTag() && entity instanceof Player){
            UUID id = UUID.randomUUID();

            API.instance().getStorageTomeManager((ServerLevel) level).set(id, API.instance().createDefaultTome((ServerLevel) level, getCapacity(stack), (Player) entity));
            API.instance().getStorageTomeManager((ServerLevel) level).markForSaving();

            setId(stack, id);
        }
    }

    @Override
    public int getEntityLifespan(ItemStack stack, Level level) {
        return Integer.MAX_VALUE;
    }

    @Override
    public UUID getId(ItemStack tome) {
        return tome.getTag().getUUID(NBT_ID);
    }

    @Override
    public void setId(ItemStack tome, UUID id) {
        tome.setTag(new CompoundTag());
        tome.getTag().putUUID(NBT_ID, id);
    }

    @Override
    public boolean isValid(ItemStack tome) {
        return tome.hasTag() && tome.getTag().hasUUID(NBT_ID);
    }

    @Override
    public int getCapacity(ItemStack tome) {
        return type.getCapacity();
    }

    @Override
    public StorageType getType() {
        return StorageType.ITEM;
    }
}
