package noobanidus.mods.mysticalmachinery.init;

import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import noobanidus.mods.mysticalmachinery.MMTags;
import noobanidus.mods.mysticalmachinery.MysticalMachinery;
import noobanidus.mods.mysticalmachinery.blocks.CharcoalKilnBlock;
import noobanidus.mods.mysticalmachinery.blocks.KilnBlock;
import noobanidus.mods.mysticalmachinery.blocks.SawmillBlock;
import noobanidus.mods.mysticalmachinery.recipes.CharcoalKilnRecipeBuilder;

import java.util.function.ToIntFunction;

import static noobanidus.mods.mysticalmachinery.MysticalMachinery.REGISTRATE;

public class ModBlocks {
  private static ToIntFunction<BlockState> getLightValueLit(int lightValue) {
    return (state) -> {
      return state.get(BlockStateProperties.LIT) ? lightValue : 0;
    };
  }

  public static RegistryEntry<KilnBlock> KILN = REGISTRATE.block("kiln", Material.ROCK, KilnBlock::new)
      .properties((o) -> o.hardnessAndResistance(3.5F).variableOpacity().setLightLevel(getLightValueLit(13)))
      .item()
      .model((ctx, p) -> p.blockItem(ModBlocks.KILN))
      .build()
      .blockstate((ctx, p) -> {
      })
      .recipe((ctx, p) -> {
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.KILN.get(), 1)
            .patternLine(" X ")
            .patternLine("XFX")
            .patternLine(" X ")
            .key('X', epicsquid.mysticalworld.init.ModBlocks.TERRACOTTA_BRICK.get())
            .key('F', Blocks.FURNACE)
            .addCriterion("has_terracotta_bricks", p.hasItem(epicsquid.mysticalworld.init.ModBlocks.TERRACOTTA_BRICK.get()))
            .build(p);
        ModRecipes.kilnRecipes(p);
      })
      .register();

  public static RegistryEntry<CharcoalKilnBlock> CHARCOAL_KILN = REGISTRATE.block("charcoal_kiln", Material.IRON, CharcoalKilnBlock::new)
      .properties((o) -> o.hardnessAndResistance(3.5F).setLightLevel(getLightValueLit(13)).variableOpacity().notSolid())
      .blockstate((ctx, p) -> p.getVariantBuilder(ModBlocks.CHARCOAL_KILN.get()).forAllStates((state) -> {
        if (state.get(CharcoalKilnBlock.LIT)) {
          return ConfiguredModel.builder()
              .modelFile(p.models().getBuilder("charcoal_kiln_hot")
                  .parent(p.models().getExistingFile(new ResourceLocation(MysticalMachinery.MODID, "block/charcoal_kiln")))
                  .texture("kiln_face", new ResourceLocation(MysticalMachinery.MODID, "block/charcoal_kiln_face_hot"))
                  .texture("kiln_bottom", new ResourceLocation(MysticalMachinery.MODID, "block/charcoal_kiln_bottom_hot")))
              .rotationY(((int) state.get(BlockStateProperties.HORIZONTAL_FACING).getHorizontalAngle() + 180) % 360)
              .build();
        } else {
          return ConfiguredModel.builder()
              .modelFile(p.models().getBuilder("charcoal_kiln_cold")
                  .parent(p.models().getExistingFile(new ResourceLocation(MysticalMachinery.MODID, "block/charcoal_kiln")))
                  .texture("kiln_face", new ResourceLocation(MysticalMachinery.MODID, "block/charcoal_kiln_face"))
                  .texture("kiln_bottom", new ResourceLocation(MysticalMachinery.MODID, "block/charcoal_kiln_bottom")))
              .rotationY(((int) state.get(BlockStateProperties.HORIZONTAL_FACING).getHorizontalAngle() + 180) % 360)
              .build();
        }
      }))
      .item()
      .model((ctx, p) -> p.blockItem(ModBlocks.CHARCOAL_KILN))
      .build()
      .recipe((ctx, p) -> {
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.CHARCOAL_KILN.get(), 1)
            .patternLine(" X ")
            .patternLine("XFX")
            .patternLine(" X ")
            .key('F', ModBlocks.KILN.get())
            .key('X', epicsquid.mysticalworld.init.ModBlocks.IRON_BRICK.get())
            .addCriterion("has_iron_bricks", p.hasItem(epicsquid.mysticalworld.init.ModBlocks.IRON_BRICK.get()))
            .addCriterion("has_kiln", p.hasItem(ModBlocks.KILN.get()))
            .build(p);
        CharcoalKilnRecipeBuilder.charcoalKilnRecipe(new ItemStack(Items.CHARCOAL, 6), Ingredient.fromTag(ItemTags.LOGS), 4, 2, 3.5f, 550).addCriterion("has_logs", p.hasItem(ItemTags.LOGS)).build(p, "charcoal_from_charcoal_kiln");
      })
      .register();

  public static RegistryEntry<SawmillBlock> SAWMILL = REGISTRATE.block("sawmill", Material.ROCK, SawmillBlock::new)
      .properties(o -> o.hardnessAndResistance(3.5f).setLightLevel(getLightValueLit(13)).variableOpacity())
      .blockstate((ctx, p) ->
          p.getVariantBuilder(ctx.getEntry())
              .forAllStates(state -> ConfiguredModel.builder()
                  .modelFile(p.models().getExistingFile(new ResourceLocation(MysticalMachinery.MODID, state.get(SawmillBlock.LIT) ? "sawmill_on" : "sawmill_off")))
                  .rotationY(((int) state.get(BlockStateProperties.HORIZONTAL_FACING).getHorizontalAngle() + 180) % 360)
                  .build()
              )
      )
      .item()
      .model((ctx, p) -> p.withExistingParent("sawmill", new ResourceLocation(MysticalMachinery.MODID, "block/sawmill_off")))
      .build()
      .recipe((ctx, p) -> {
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.SAWMILL.get(), 1)
            .patternLine(" S ")
            .patternLine("LML")
            .patternLine(" F ")
            .key('M', Items.IRON_BARS)
            .key('L', ItemTags.LOGS)
            .key('S', Items.STONECUTTER)
            .key('F', Items.FURNACE)
            .addCriterion("has_logs", p.hasItem(ItemTags.LOGS))
            .build(p);

        ShapedRecipeBuilder.shapedRecipe(Items.PAPER, 4)
            .patternLine("SSS")
            .patternLine("S S")
            .patternLine("SSS")
            .key('S', MMTags.Items.SAWDUST)
            .addCriterion("has_sawdust", p.hasItem(MMTags.Items.SAWDUST))
            .build(p, new ResourceLocation(MysticalMachinery.MODID, "paper_from_sawdust"));

        ModRecipes.sawmillRecipes(p);
      })
      .register();

  public static void load() {
  }
}
