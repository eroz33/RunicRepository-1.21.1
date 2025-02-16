package net.eroz33.runic_repository.datagen;

import net.eroz33.runic_repository.worldgen.ModBiomeModifiers;
import net.eroz33.runic_repository.worldgen.ModConfiguredFeatures;
import net.eroz33.runic_repository.worldgen.ModPlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static net.eroz33.runic_repository.core.RR.MOD_ID;

public class RWorldGenProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder builder = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap);

    public RWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, builder, Set.of(MOD_ID));
    }
}
