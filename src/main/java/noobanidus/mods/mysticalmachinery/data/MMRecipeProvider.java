package noobanidus.mods.mysticalmachinery.data;

import epicsquid.mysticallib.data.DeferredRecipeProvider;
import epicsquid.mysticalworld.MWTags;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.IForgeRegistryEntry;
import noobanidus.mods.mysticalmachinery.MysticalMachinery;
import noobanidus.mods.mysticalmachinery.blocks.MachineFrame;
import noobanidus.mods.mysticalmachinery.init.ModBlocks;
import noobanidus.mods.mysticalmachinery.init.ModItems;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class MMRecipeProvider extends DeferredRecipeProvider {
  public MMRecipeProvider(DataGenerator generatorIn) {
    super(generatorIn, MysticalMachinery.MODID);
  }

  @Override
  protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
    ShapedRecipeBuilder.shapedRecipe(ModBlocks.KILN.get(), 1)
        .patternLine(" X ")
        .patternLine("XFX")
        .patternLine(" X ")
        .key('X', epicsquid.mysticalworld.init.ModBlocks.TERRACOTTA_BRICK.get())
        .key('F', Blocks.FURNACE)
        .addCriterion("has_terracotta_bricks", this.hasItem(epicsquid.mysticalworld.init.ModBlocks.TERRACOTTA_BRICK.get()))
        .build(consumer);

    kilnRecipes(consumer);

    for (MachineFrame type : MachineFrame.values()) {
      ShapedRecipeBuilder.shapedRecipe(ModBlocks.MACHINE_FRAMES.get(type).get(), 1)
          .patternLine("IXI")
          .patternLine("XGX")
          .patternLine("IXI")
          .key('X', type.getTag())
          .key('G', Tags.Items.GLASS)
          .key('I', Tags.Items.INGOTS_IRON)
          .addCriterion("has_" + type.getTag().getId(), this.hasItem(type.getTag()))
          .build(consumer);
    }

    ShapedRecipeBuilder.shapedRecipe(ModBlocks.COOKIE_GENERATOR.get(), 1)
        .patternLine("TCT")
        .patternLine("CMC")
        .patternLine("TCT")
        .key('T', MWTags.Items.TIN_INGOT)
        .key('C', Items.COOKIE)
        .key('M', ModBlocks.MACHINE_FRAMES.get(MachineFrame.LAPIS).get())
        .addCriterion("has_cookie", this.hasItem(Items.COOKIE))
        .build(consumer);

    ShapedRecipeBuilder.shapedRecipe(ModItems.HEAT_CAPACITOR.get(), 1)
        .patternLine("CRC")
        .patternLine("CMC")
        .patternLine("CBC")
        .key('C', MWTags.Items.COPPER_INGOT)
        .key('M', ModBlocks.MACHINE_FRAMES.get(MachineFrame.COPPER).get())
        .key('R', Tags.Items.STORAGE_BLOCKS_REDSTONE)
        .key('B', Items.BLAST_FURNACE)
        .addCriterion("has_copper", this.hasItem(MWTags.Items.COPPER_INGOT))
        .build(consumer);

    ShapedRecipeBuilder.shapedRecipe(ModItems.SOLID_STATE_HEAT_CONVERTER.get(), 1)
        .patternLine("IRI")
        .patternLine("IMI")
        .patternLine("ILI")
        .key('L', MWTags.Items.LEAD_BLOCK)
        .key('R', Tags.Items.STORAGE_BLOCKS_REDSTONE)
        .key('M', ModBlocks.MACHINE_FRAMES.get(MachineFrame.LEAD).get())
        .key('I', MWTags.Items.LEAD_INGOT)
        .addCriterion("has_blast_furnace", this.hasItem(Items.BLAST_FURNACE))
        .build(consumer);

    ShapedRecipeBuilder.shapedRecipe(ModBlocks.STORED_HEAT_GENERATOR.get(), 1)
        .patternLine("ICI")
        .patternLine("IMI")
        .patternLine("ISI")
        .key('M', ModBlocks.MACHINE_FRAMES.get(MachineFrame.REDSTONE).get())
        .key('C', ModItems.HEAT_CAPACITOR.get())
        .key('S', ModItems.SOLID_STATE_HEAT_CONVERTER.get())
        .key('I', Tags.Items.INGOTS_IRON)
        .addCriterion("has_blast_furnace", this.hasItem(Items.BLAST_FURNACE))
        .build(consumer);

    ShapedRecipeBuilder.shapedRecipe(ModBlocks.END_STONE_FABRICATOR.get(), 1)
        .patternLine("ECE")
        .patternLine("EME")
        .patternLine("PPP")
        .key('M', ModBlocks.MACHINE_FRAMES.get(MachineFrame.CHORUS).get())
        .key('C', Items.CHORUS_FLOWER)
        .key('E', Tags.Items.END_STONES)
        .key('P', Items.PURPUR_PILLAR)
        .addCriterion("has_chorus_flower", this.hasItem(Items.CHORUS_FLOWER))
        .build(consumer);

    ShapedRecipeBuilder.shapedRecipe(ModBlocks.SAND_FABRICATOR.get(), 1)
        .patternLine("TST")
        .patternLine("TMT")
        .patternLine("CHC")
        .key('M', ModBlocks.MACHINE_FRAMES.get(MachineFrame.TERRACOTTA).get())
        .key('T', MWTags.Items.TIN_INGOT)
        .key('S', ItemTags.SAND)
        .key('C', Items.CUT_SANDSTONE)
        .key('H', Items.CHISELED_SANDSTONE)
        .addCriterion("has_sand", this.hasItem(Items.SAND))
        .build(consumer);

    ShapedRecipeBuilder.shapedRecipe(ModBlocks.RED_SAND_FABRICATOR.get(), 1)
        .patternLine("TST")
        .patternLine("TMT")
        .patternLine("CHC")
        .key('M', ModBlocks.MACHINE_FRAMES.get(MachineFrame.TERRACOTTA).get())
        .key('T', MWTags.Items.TIN_INGOT)
        .key('S', Tags.Items.STORAGE_BLOCKS_REDSTONE)
        .key('C', Items.CUT_SANDSTONE)
        .key('H', Items.CHISELED_SANDSTONE)
        .addCriterion("has_sand", this.hasItem(Items.SAND))
        .build(consumer);

    ShapedRecipeBuilder.shapedRecipe(ModBlocks.CLAY_FABRICATOR.get(), 1)
        .patternLine("BCB")
        .patternLine("BMB")
        .patternLine("YYY")
        .key('M', ModBlocks.MACHINE_FRAMES.get(MachineFrame.TERRACOTTA).get())
        .key('B', Tags.Items.INGOTS_BRICK)
        .key('C', Items.CLAY)
        .key('Y', Items.BRICKS)
        .addCriterion("has_clay", this.hasItem(Items.CLAY_BALL))
        .build(consumer);

    ShapedRecipeBuilder.shapedRecipe(ModBlocks.NETHERRACK_FABRICATOR.get(), 1)
        .patternLine("GNG")
        .patternLine("GMG")
        .patternLine("BBB")
        .key('M', ModBlocks.MACHINE_FRAMES.get(MachineFrame.NETHER).get())
        .key('G', Tags.Items.INGOTS_GOLD)
        .key('N', Tags.Items.NETHERRACK)
        .key('B', Items.NETHER_BRICKS)
        .addCriterion("has_netherrack", this.hasItem(Items.NETHERRACK))
        .build(consumer);

    ShapedRecipeBuilder.shapedRecipe(ModBlocks.SOUL_SAND_FABRICATOR.get(), 1)
        .patternLine("GNG")
        .patternLine("gMg")
        .patternLine("BBB")
        .key('M', ModBlocks.MACHINE_FRAMES.get(MachineFrame.RED_NETHER).get())
        .key('G', Tags.Items.INGOTS_GOLD)
        .key('g', Tags.Items.STORAGE_BLOCKS_GOLD)
        .key('N', Items.SOUL_SAND)
        .key('B', Items.RED_NETHER_BRICKS)
        .addCriterion("has_soul_sand", this.hasItem(Items.SOUL_SAND))
        .build(consumer);

    ShapedRecipeBuilder.shapedRecipe(ModBlocks.SLIME_FABRICATOR.get(), 1)
        .patternLine("SCS")
        .patternLine("SMS")
        .patternLine("GGG")
        .key('M', ModBlocks.MACHINE_FRAMES.get(MachineFrame.SLIME).get())
        .key('S', Items.SLIME_BALL)
        .key('C', MWTags.Items.COPPER_BLOCK)
        .key('G', Items.GREEN_GLAZED_TERRACOTTA)
        .addCriterion("has_slime", this.hasItem(Items.SLIME_BALL))
        .build(consumer);

    ShapedRecipeBuilder.shapedRecipe(ModBlocks.ICE_FABRICATOR.get(), 1)
        .patternLine("QWQ")
        .patternLine("QMQ")
        .patternLine("LLL")
        .key('M', ModBlocks.MACHINE_FRAMES.get(MachineFrame.QUICKSILVER).get())
        .key('Q', MWTags.Items.QUICKSILVER_INGOT)
        .key('W', Items.WATER_BUCKET)
        .key('L', Items.LIGHT_BLUE_WOOL)
        .addCriterion("has_quicksilver", this.hasItem(MWTags.Items.QUICKSILVER_INGOT))
        .build(consumer);

    ShapedRecipeBuilder.shapedRecipe(ModBlocks.SNOW_FABRICATOR.get(), 1)
        .patternLine("QWQ")
        .patternLine("QMQ")
        .patternLine("LLL")
        .key('M', ModBlocks.MACHINE_FRAMES.get(MachineFrame.QUICKSILVER).get())
        .key('Q', MWTags.Items.QUICKSILVER_INGOT)
        .key('W', Items.WATER_BUCKET)
        .key('L', Items.WHITE_WOOL)
        .addCriterion("has_quicksilver", this.hasItem(MWTags.Items.QUICKSILVER_INGOT))
        .build(consumer);

    ShapedRecipeBuilder.shapedRecipe(ModBlocks.DIRT_FABRICATOR.get(), 1)
        .patternLine("DDD")
        .patternLine("DMD")
        .patternLine("BBB")
        .key('M', ModBlocks.MACHINE_FRAMES.get(MachineFrame.STONE).get())
        .key('D', Items.DIRT)
        .key('B', ItemTags.STONE_BRICKS)
        .addCriterion("has_dirt", this.hasItem(Items.DIRT))
        .build(consumer);

    ShapedRecipeBuilder.shapedRecipe(ModBlocks.GRAVEL_FABRICATOR.get(), 1)
        .patternLine("DDD")
        .patternLine("DMD")
        .patternLine("BBB")
        .key('M', ModBlocks.MACHINE_FRAMES.get(MachineFrame.STONE).get())
        .key('D', Items.GRAVEL)
        .key('B', ItemTags.STONE_BRICKS)
        .addCriterion("has_gravel", this.hasItem(Items.GRAVEL))
        .build(consumer);
  }

  private void kilnRecipes(Consumer<IFinishedRecipe> consumer) {
    kiln(Items.CLAY, Items.TERRACOTTA, consumer);
    kiln(Items.CLAY_BALL, Items.BRICK, consumer);
    kiln(Items.NETHERRACK, Items.NETHER_BRICK, consumer);
    kiln(Items.COBBLESTONE, Items.STONE, consumer);
    kiln(Items.STONE, Items.SMOOTH_STONE, consumer);
    kiln(Items.STONE_BRICKS, Items.CRACKED_STONE_BRICKS, consumer);
    kiln(Items.COBBLESTONE_STAIRS, Items.STONE_STAIRS, consumer);
    kiln(Items.COBBLESTONE_SLAB, Items.STONE_SLAB, consumer);
    kiln(Items.STONE_SLAB, Items.SMOOTH_STONE_SLAB, consumer);
    kiln(Tags.Items.SAND, Items.GLASS, consumer);
    kiln(Items.SANDSTONE, Items.SMOOTH_SANDSTONE, consumer);
    kiln(Items.RED_SANDSTONE, Items.SMOOTH_RED_SANDSTONE, consumer);
    kiln(Items.SANDSTONE_SLAB, Items.SMOOTH_SANDSTONE_SLAB, consumer);
    kiln(Items.RED_SANDSTONE_SLAB, Items.SMOOTH_RED_SANDSTONE_SLAB, consumer);
    kiln(Items.SANDSTONE_STAIRS, Items.SMOOTH_SANDSTONE_STAIRS, consumer);
    kiln(Items.RED_SANDSTONE_STAIRS, Items.SMOOTH_RED_SANDSTONE_STAIRS, consumer);
    kiln(Items.WET_SPONGE, Items.SPONGE, consumer);
    kiln(epicsquid.mysticalworld.init.ModBlocks.WET_MUD_BLOCK, epicsquid.mysticalworld.init.ModBlocks.MUD_BLOCK, consumer);
    kiln(epicsquid.mysticalworld.init.ModBlocks.WET_MUD_BRICK, epicsquid.mysticalworld.init.ModBlocks.MUD_BRICK, consumer);

    kiln(Items.BLACK_TERRACOTTA, Items.BLACK_GLAZED_TERRACOTTA, consumer);
    kiln(Items.BLUE_TERRACOTTA, Items.BLUE_GLAZED_TERRACOTTA, consumer);
    kiln(Items.BROWN_TERRACOTTA, Items.BROWN_GLAZED_TERRACOTTA, consumer);
    kiln(Items.CYAN_TERRACOTTA, Items.CYAN_GLAZED_TERRACOTTA, consumer);
    kiln(Items.GRAY_TERRACOTTA, Items.GRAY_GLAZED_TERRACOTTA, consumer);
    kiln(Items.GREEN_TERRACOTTA, Items.GREEN_GLAZED_TERRACOTTA, consumer);
    kiln(Items.LIGHT_BLUE_TERRACOTTA, Items.LIGHT_BLUE_GLAZED_TERRACOTTA, consumer);
    kiln(Items.LIGHT_GRAY_TERRACOTTA, Items.LIGHT_GRAY_GLAZED_TERRACOTTA, consumer);
    kiln(Items.LIME_TERRACOTTA, Items.LIME_GLAZED_TERRACOTTA, consumer);
    kiln(Items.MAGENTA_TERRACOTTA, Items.MAGENTA_GLAZED_TERRACOTTA, consumer);
    kiln(Items.ORANGE_TERRACOTTA, Items.ORANGE_GLAZED_TERRACOTTA, consumer);
    kiln(Items.PINK_TERRACOTTA, Items.PINK_GLAZED_TERRACOTTA, consumer);
    kiln(Items.PURPLE_TERRACOTTA, Items.PURPLE_GLAZED_TERRACOTTA, consumer);
    kiln(Items.RED_TERRACOTTA, Items.RED_GLAZED_TERRACOTTA, consumer);
    kiln(Items.WHITE_TERRACOTTA, Items.WHITE_GLAZED_TERRACOTTA, consumer);
    kiln(Items.YELLOW_TERRACOTTA, Items.YELLOW_GLAZED_TERRACOTTA, consumer);

  }

  protected <T extends IItemProvider & IForgeRegistryEntry<?>> void kiln(Supplier<? extends T> source, Supplier<? extends T> result, Consumer<IFinishedRecipe> consumer) {
    KilnRecipeBuilder.kilnRecipe(Ingredient.fromItems(source.get()), result.get(), 0.35f, 100).addCriterion("has_" + safeName(source.get().getRegistryName()), this.hasItem(source.get())).build(consumer, safeId(result.get()) + "_from_kiln");
  }

  protected <T extends IItemProvider & IForgeRegistryEntry<?>> void kiln(T source, T result, Consumer<IFinishedRecipe> consumer) {
    KilnRecipeBuilder.kilnRecipe(Ingredient.fromItems(source), result, 0.35f, 100).addCriterion("has_" + safeName(source.getRegistryName()), this.hasItem(source)).build(consumer, safeId(result) + "_from_kiln");
  }

  protected <T extends IItemProvider & IForgeRegistryEntry<?>> void kiln(Tag<Item> source, T result, Consumer<IFinishedRecipe> consumer) {
    KilnRecipeBuilder.kilnRecipe(Ingredient.fromTag(source), result, 0.35f, 100).addCriterion("has_" + safeName(source.getId()), this.hasItem(source)).build(consumer, safeId(result) + "_from_kiln");
  }

  protected <T extends IItemProvider & IForgeRegistryEntry<?>> void kiln(Tag<Item> source, Supplier<? extends T> result, Consumer<IFinishedRecipe> consumer) {
    KilnRecipeBuilder.kilnRecipe(Ingredient.fromTag(source), result.get(), 0.35f, 100).addCriterion("has_" + safeName(source.getId()), this.hasItem(source)).build(consumer, safeId(result.get()) + "_from_kiln");
  }

  protected <T extends IItemProvider & IForgeRegistryEntry<?>> void kiln(Supplier<? extends T> source, T result, Consumer<IFinishedRecipe> consumer) {
    KilnRecipeBuilder.kilnRecipe(Ingredient.fromItems(source.get()), result, 0.35f, 100).addCriterion("has_" + safeName(source.get().getRegistryName()), this.hasItem(source.get())).build(consumer, safeId(result) + "_from_kiln");
  }

  protected <T extends IItemProvider & IForgeRegistryEntry<?>> void kiln(T source, Supplier<? extends T> result, Consumer<IFinishedRecipe> consumer) {
    KilnRecipeBuilder.kilnRecipe(Ingredient.fromItems(source), result.get(), 0.35f, 100).addCriterion("has_" + safeName(source.getRegistryName()), this.hasItem(source)).build(consumer, safeId(result.get()) + "_from_kiln");
  }
}
