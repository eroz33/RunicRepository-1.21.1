package net.eroz33.runic_repository.datagen;

import net.eroz33.runic_repository.block.RBlocks;
import net.eroz33.runic_repository.item.RItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static net.eroz33.runic_repository.core.RR.MOD_ID;

public class RRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public RRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput){
        List<ItemLike> ARCANE_DUST_SMELTABLES = List.of(RBlocks.ARCANE_LOG,
                RBlocks.ARCANE_WOOD, RBlocks.STRIPPED_ARCANE_LOG, RBlocks.STRIPPED_ARCANE_WOOD);
        
        
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

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, RBlocks.ARCANE_PLANKS.get(), 4)
                .requires(RBlocks.ARCANE_LOG.get())
                .unlockedBy("has_arcane_log", has(RBlocks.ARCANE_LOG.get())).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, RBlocks.ARCANE_PLANKS.get(), 4)
                .requires(RBlocks.STRIPPED_ARCANE_LOG.get())
                .unlockedBy("has_stripped_arcane_log", has(RBlocks.ARCANE_LOG.get())).save(recipeOutput, "runic_repository:arcane_planks_2");


        //oreSmelting(recipeOutput, ARCANE_DUST_SMELTABLES, RecipeCategory.MISC, RItems.ARCANE_DUST.get(), 0.25f, 200, "arcane_dust");
        oreBlasting(recipeOutput, ARCANE_DUST_SMELTABLES, RecipeCategory.MISC, new ItemStack(RItems.ARCANE_DUST.get(), 4).getItem(), 0.25f, 100, "arcane_dust");

    }

    protected static void oreSmelting(RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput pRecipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pRecipeOutput, MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }

}
