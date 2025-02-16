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
    }

}
