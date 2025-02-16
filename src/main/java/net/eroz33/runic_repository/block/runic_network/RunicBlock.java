package net.eroz33.runic_repository.block.runic_network;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public abstract class RunicBlock extends Block {
    public static final List<BlockPos> BOOKSHELF_OFFSETS = BlockPos.betweenClosedStream(-3, -1, -3, 3, 3, 3)
            .filter(p_341357_ -> Math.abs(p_341357_.getX()) == 2 || Math.abs(p_341357_.getZ()) == 2)
            .map(BlockPos::immutable)
            .toList();

    public RunicBlock(Properties properties) {
        super(properties);
    }

    public static boolean isValidBookShelf(Level level, BlockPos enchantingTablePos, BlockPos bookshelfPos) {
        return level.getBlockState(enchantingTablePos.offset(bookshelfPos)).getEnchantPowerBonus(level, enchantingTablePos.offset(bookshelfPos)) != 0
                && level.getBlockState(enchantingTablePos.offset(bookshelfPos.getX() / 2, bookshelfPos.getY(), bookshelfPos.getZ() / 2))
                .is(BlockTags.ENCHANTMENT_POWER_TRANSMITTER);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        // This Method is CLIENT ONLY
        super.animateTick(state, level, pos, random);

        for (BlockPos blockpos : BOOKSHELF_OFFSETS) {
            if (random.nextInt(16) == 0 && isValidBookShelf(level, pos, blockpos)) {
                level.addParticle(
                        ParticleTypes.ENCHANT,
                        (double) pos.getX() + 0.5,
                        (double) pos.getY() + 1.5,
                        (double) pos.getZ() + 0.5,
                        (double) ((float) blockpos.getX() + random.nextFloat()) - 0.5,
                        (double) ((float) blockpos.getY() - random.nextFloat() - 1.0F),
                        (double) ((float) blockpos.getZ() + random.nextFloat()) - 0.5
                );
            }
        }
    }
}
