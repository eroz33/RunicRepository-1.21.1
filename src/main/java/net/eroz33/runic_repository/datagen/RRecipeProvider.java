package net.eroz33.runic_repository.datagen;

import net.eroz33.runic_repository.item.RItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class RRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public RRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput){
        // Arcane Primer Recipe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RItems.ARCANE_PRIMER.get())
                .pattern("DDD")
                .pattern("DBD")
                .pattern("DDD")
                .define('D', RItems.ARCANE_DUST)
                .define('B', Items.BOOK)
                .unlockedBy("has_arcane_dust", has(RItems.ARCANE_DUST.get())).save(recipeOutput);
        // Arcane Core tier 1 Recipe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RItems.RUNIC_CORE_G.get())
                .pattern("DRD")
                .pattern("RGR")
                .pattern("DRD")
                .define('D', RItems.ARCANE_DUST)
                .define('G', Items.GOLD_INGOT)
                .define('R', Items.REDSTONE)
                .unlockedBy("has_arcane_dust", has(RItems.ARCANE_DUST.get())).save(recipeOutput);
        // Arcane Core tier 2 Recipe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RItems.RUNIC_CORE_R.get())
                .pattern("NDN")
                .pattern("RNR")
                .pattern("DRD")
                .define('D', RItems.ARCANE_DUST)
                .define('N', Items.NETHER_WART)
                .define('R', RItems.RUNIC_CORE_G)
                .unlockedBy("has_runic_core_g", has(RItems.RUNIC_CORE_G.get())).save(recipeOutput);
        // Arcane Core tier 3 Recipe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RItems.RUNIC_CORE_P.get())
                .pattern("NDN")
                .pattern("RNR")
                .pattern("DRD")
                .define('D', RItems.ARCANE_DUST)
                .define('N', Items.END_STONE)
                .define('R', RItems.RUNIC_CORE_R)
                .unlockedBy("has_runic_core_r", has(RItems.RUNIC_CORE_R.get())).save(recipeOutput);
        // Arcane Core tier 4 Recipe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RItems.RUNIC_CORE_B.get())
                .pattern("NDN")
                .pattern("RNR")
                .pattern("DRD")
                .define('D', RItems.ARCANE_DUST)
                .define('N', Items.ECHO_SHARD)
                .define('R', RItems.RUNIC_CORE_P)
                .unlockedBy("has_runic_core_p", has(RItems.RUNIC_CORE_P.get())).save(recipeOutput);
    }

}
