package net.eroz33.runic_repository.worldgen;

import net.eroz33.runic_repository.block.RBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

import static net.eroz33.runic_repository.core.RR.MOD_ID;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> ARCANE_PLACED_KEY = registerKey("arcane_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature>context){
        HolderGetter<ConfiguredFeature<?,?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, ARCANE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ARCANE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        RBlocks.ARCANE_SAPLING.get()));
    }

    public static ResourceKey<PlacedFeature> registerKey(String name){
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?,?>> config,
                                 List<PlacementModifier> modifiers){
        context.register(key, new PlacedFeature(config, List.copyOf(modifiers)));
    }
}
