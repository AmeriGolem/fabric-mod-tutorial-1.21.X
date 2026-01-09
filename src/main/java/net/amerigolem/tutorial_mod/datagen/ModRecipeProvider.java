package net.amerigolem.tutorial_mod.datagen;

import net.amerigolem.tutorial_mod.TutorialMod;
import net.amerigolem.tutorial_mod.block.ModBlocks;
import net.amerigolem.tutorial_mod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
        List<ItemConvertible> PINK_GARNET_SMELTABLES = List.of(ModItems.RAW_PINK_GARNET, ModBlocks.PINK_GARNET_ORE,
                ModBlocks.PINK_GARNET_DEEPSLATE_ORE);

        offerSmelting(recipeExporter, PINK_GARNET_SMELTABLES, RecipeCategory.MISC, ModItems.PINK_GARNET, 0.25f, 200, "pink_garnet");
        offerBlasting(recipeExporter, PINK_GARNET_SMELTABLES, RecipeCategory.MISC, ModItems.PINK_GARNET, 0.25f, 100, "pink_garnet");

        offerReversibleCompactingRecipes(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModItems.PINK_GARNET, RecipeCategory.DECORATIONS, ModBlocks.PINK_GARNET_BLOCK);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.RAW_PINK_GARNET_BLOCK)
                .pattern("RRR")
                .pattern("RRR")
                .pattern("RRR")
                .input('R', ModItems.RAW_PINK_GARNET)
                .criterion(hasItem(ModItems.RAW_PINK_GARNET), conditionsFromItem(ModItems.RAW_PINK_GARNET))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.DASH_WAND)
                .pattern("P")
                .pattern("R")
                .input('P', Items.ENDER_PEARL)
                .input('R', Items.BLAZE_ROD)
                .criterion("has_Ender_Pearl_or_Blaze_Rod",
                        InventoryChangedCriterion.Conditions.items(
                                Items.ENDER_PEARL, Items.BLAZE_ROD
                        )
                )
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CYAN_BRICK_BLOCK)
                .pattern(" b ")
                .pattern("bdb")
                .pattern(" b ")
                .input('b', Items.BRICK)
                .input('d', Items.CYAN_DYE)
                .criterion("has_brick_or_dye",
                        InventoryChangedCriterion.Conditions.items(
                                Items.BRICK, Items.CYAN_DYE
                        ))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.PINK_GARNET_WALL,
                        6
                )
                .pattern("###")
                .pattern("###")
                .input('#', ModBlocks.PINK_GARNET_BLOCK)
                .criterion(
                        hasItem(ModBlocks.PINK_GARNET_BLOCK),
                        conditionsFromItem(ModBlocks.PINK_GARNET_BLOCK)
                )
                .offerTo(recipeExporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RAW_PINK_GARNET, 9)
                .input(ModBlocks.RAW_PINK_GARNET_BLOCK)
                .criterion(hasItem(ModBlocks.RAW_PINK_GARNET_BLOCK), conditionsFromItem(ModBlocks.RAW_PINK_GARNET_BLOCK))
                .offerTo(recipeExporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RAW_PINK_GARNET, 32)
                .input(ModBlocks.MAGIC_BLOCK)
                .criterion(hasItem(ModBlocks.RAW_PINK_GARNET_BLOCK), conditionsFromItem(ModBlocks.RAW_PINK_GARNET_BLOCK))
                .offerTo(recipeExporter, Identifier.of(TutorialMod.MOD_ID,"raw_pink_garnet_from_magic_block"));

        createDoorRecipe(ModBlocks.PINK_GARNET_DOOR, Ingredient.ofItems(ModBlocks.PINK_GARNET_BLOCK))
                .criterion(hasItem(ModBlocks.PINK_GARNET_BLOCK), conditionsFromItem(ModBlocks.PINK_GARNET_BLOCK))
                .offerTo(recipeExporter);
        createFenceRecipe(ModBlocks.PINK_GARNET_FENCE, Ingredient.ofItems(ModBlocks.PINK_GARNET_BLOCK))
                .criterion(hasItem(ModBlocks.PINK_GARNET_BLOCK), conditionsFromItem(ModBlocks.PINK_GARNET_BLOCK))
                .offerTo(recipeExporter);
        createFenceGateRecipe(ModBlocks.PINK_GARNET_FENCE_GATE, Ingredient.ofItems(ModBlocks.PINK_GARNET_BLOCK))
                .criterion(hasItem(ModBlocks.PINK_GARNET_BLOCK), conditionsFromItem(ModBlocks.PINK_GARNET_BLOCK))
                .offerTo(recipeExporter);
        createTrapdoorRecipe(ModBlocks.PINK_GARNET_TRAPDOOR, Ingredient.ofItems(ModBlocks.PINK_GARNET_BLOCK))
                .criterion(hasItem(ModBlocks.PINK_GARNET_BLOCK), conditionsFromItem(ModBlocks.PINK_GARNET_BLOCK))
                .offerTo(recipeExporter);
        createPressurePlateRecipe(RecipeCategory.REDSTONE, ModBlocks.PINK_GARNET_PRESSURE_PLATE, Ingredient.ofItems(ModBlocks.PINK_GARNET_BLOCK))
                .criterion(hasItem(ModBlocks.PINK_GARNET_BLOCK), conditionsFromItem(ModBlocks.PINK_GARNET_BLOCK))
                .offerTo(recipeExporter);

        createStairsRecipe(ModBlocks.PINK_GARNET_STAIRS, Ingredient.ofItems(ModBlocks.PINK_GARNET_BLOCK))
                .criterion(hasItem(ModBlocks.PINK_GARNET_BLOCK), conditionsFromItem(ModBlocks.PINK_GARNET_BLOCK))
                .offerTo(recipeExporter);
        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PINK_GARNET_SLAB, Ingredient.ofItems(ModBlocks.PINK_GARNET_BLOCK))
                .criterion(hasItem(ModBlocks.PINK_GARNET_BLOCK), conditionsFromItem(ModBlocks.PINK_GARNET_BLOCK))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(
                        RecipeCategory.COMBAT,
                        ModItems.PINK_GARNET_SWORD
                )
                .pattern("g")
                .pattern("g")
                .pattern("s")
                .input('g', ModItems.PINK_GARNET)
                .input('s', Items.STICK)
                .criterion(
                        hasItem(ModItems.PINK_GARNET),
                        conditionsFromItem(ModItems.PINK_GARNET)
                )
                .offerTo(recipeExporter);
        ShapedRecipeJsonBuilder.create(
                        RecipeCategory.TOOLS,
                        ModItems.PINK_GARNET_PICKAXE
                )
                .pattern("ggg")
                .pattern(" s ")
                .pattern(" s ")
                .input('g', ModItems.PINK_GARNET)
                .input('s', Items.STICK)
                .criterion(
                        hasItem(ModItems.PINK_GARNET),
                        conditionsFromItem(ModItems.PINK_GARNET)
                )
                .offerTo(recipeExporter);
        ShapedRecipeJsonBuilder.create(
                        RecipeCategory.TOOLS,
                        ModItems.PINK_GARNET_AXE
                )
                .pattern("gg")
                .pattern("sg")
                .pattern("s ")
                .input('g', ModItems.PINK_GARNET)
                .input('s', Items.STICK)
                .criterion(
                        hasItem(ModItems.PINK_GARNET),
                        conditionsFromItem(ModItems.PINK_GARNET)
                )
                .offerTo(recipeExporter, Identifier.of("right_pink_garnet_axe"));
        ShapedRecipeJsonBuilder.create(
                        RecipeCategory.TOOLS,
                        ModItems.PINK_GARNET_AXE
                )
                .pattern("gg")
                .pattern("gs")
                .pattern(" s")
                .input('g', ModItems.PINK_GARNET)
                .input('s', Items.STICK)
                .criterion(
                        hasItem(ModItems.PINK_GARNET),
                        conditionsFromItem(ModItems.PINK_GARNET)
                )
                .offerTo(recipeExporter, Identifier.of("left_pink_garnet_axe"));
        ShapedRecipeJsonBuilder.create(
                        RecipeCategory.TOOLS,
                        ModItems.PINK_GARNET_SHOVEL
                )
                .pattern("g")
                .pattern("s")
                .pattern("s")
                .input('g', ModItems.PINK_GARNET)
                .input('s', Items.STICK)
                .criterion(
                        hasItem(ModItems.PINK_GARNET),
                        conditionsFromItem(ModItems.PINK_GARNET)
                )
                .offerTo(recipeExporter);
        ShapedRecipeJsonBuilder.create(
                        RecipeCategory.TOOLS,
                        ModItems.PINK_GARNET_HOE
                )
                .pattern("gg")
                .pattern("s ")
                .pattern("s ")
                .input('g', ModItems.PINK_GARNET)
                .input('s', Items.STICK)
                .criterion(
                        hasItem(ModItems.PINK_GARNET),
                        conditionsFromItem(ModItems.PINK_GARNET)
                )
                .offerTo(recipeExporter, Identifier.of("right_pink_garnet_hoe"));
        ShapedRecipeJsonBuilder.create(
                        RecipeCategory.TOOLS,
                        ModItems.PINK_GARNET_HOE
                )
                .pattern("gg")
                .pattern(" s")
                .pattern(" s")
                .input('g', ModItems.PINK_GARNET)
                .input('s', Items.STICK)
                .criterion(
                        hasItem(ModItems.PINK_GARNET),
                        conditionsFromItem(ModItems.PINK_GARNET)
                )
                .offerTo(recipeExporter, Identifier.of("left_pink_garnet_hoe"));

        ShapedRecipeJsonBuilder.create(
                        RecipeCategory.TOOLS,
                        ModItems.PINK_GARNET_HAMMER
                )
                .pattern("ggg")
                .pattern("gsg")
                .pattern(" s ")
                .input('g', ModItems.PINK_GARNET)
                .input('s', Items.STICK)
                .criterion(
                        hasItem(ModItems.PINK_GARNET),
                        conditionsFromItem(ModItems.PINK_GARNET)
                )
                .offerTo(recipeExporter);


        ShapedRecipeJsonBuilder.create(
                        RecipeCategory.COMBAT,
                        ModItems.PINK_GARNET_HELMET
                )
                .pattern("ggg")
                .pattern("g g")
                .input('g', ModItems.PINK_GARNET)
                .criterion(
                        hasItem(ModItems.PINK_GARNET),
                        conditionsFromItem(ModItems.PINK_GARNET)
                )
                .offerTo(recipeExporter);
        ShapedRecipeJsonBuilder.create(
                        RecipeCategory.COMBAT,
                        ModItems.PINK_GARNET_CHESTPLATE
                )
                .pattern("g g")
                .pattern("ggg")
                .pattern("ggg")
                .input('g', ModItems.PINK_GARNET)
                .criterion(
                        hasItem(ModItems.PINK_GARNET),
                        conditionsFromItem(ModItems.PINK_GARNET)
                )
                .offerTo(recipeExporter);
        ShapedRecipeJsonBuilder.create(
                        RecipeCategory.COMBAT,
                        ModItems.PINK_GARNET_LEGGINGS
                )
                .pattern("ggg")
                .pattern("g g")
                .pattern("g g")
                .input('g', ModItems.PINK_GARNET)
                .criterion(
                        hasItem(ModItems.PINK_GARNET),
                        conditionsFromItem(ModItems.PINK_GARNET)
                )
                .offerTo(recipeExporter);
        ShapedRecipeJsonBuilder.create(
                        RecipeCategory.COMBAT,
                        ModItems.PINK_GARNET_BOOTS
                )
                .pattern("g g")
                .pattern("g g")
                .input('g', ModItems.PINK_GARNET)
                .criterion(
                        hasItem(ModItems.PINK_GARNET),
                        conditionsFromItem(ModItems.PINK_GARNET)
                )
                .offerTo(recipeExporter);
    }
}
