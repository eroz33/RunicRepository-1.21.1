package net.eroz33.runic_repository.worldgen.tree;

import net.eroz33.runic_repository.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

import static net.eroz33.runic_repository.core.RR.MOD_ID;

public class RTreeGrowers {
    public static final TreeGrower ARCANE = new TreeGrower(MOD_ID + ":arcane",
            Optional.empty(), Optional.of(ModConfiguredFeatures.ARCANE_KEY), Optional.empty());

}
