package net.eroz33.runic_repository.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class ArcaneQuillItem extends Item {
    public ArcaneQuillItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context){
        Level level = context.getLevel();

        if (!level.isClientSide()){
            if(level.getBlockState(context.getClickedPos()).is(BlockTags.LOGS)){
                level.destroyBlock(context.getClickedPos(), true, context.getPlayer());
            }
        }

        return InteractionResult.CONSUME;
    }
}
