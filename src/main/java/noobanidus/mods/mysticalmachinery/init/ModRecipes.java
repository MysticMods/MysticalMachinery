package noobanidus.mods.mysticalmachinery.init;

import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.IForgeRegistryEntry;
import noobanidus.mods.mysticalmachinery.MysticalMachinery;
import noobanidus.mods.mysticalmachinery.recipes.*;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static noobanidus.mods.mysticalmachinery.MysticalMachinery.REGISTRATE;

public class ModRecipes {
  public static final IRecipeType<KilnRecipe> KILN_TYPE = IRecipeType.register(new ResourceLocation(MysticalMachinery.MODID, "kiln").toString());
  public static final IRecipeType<SawmillRecipe> SAWMILL_TYPE = IRecipeType.register(new ResourceLocation(MysticalMachinery.MODID, "sawmill").toString());
  public static final IRecipeType<CharcoalKilnRecipe> CHARCOAL_KILN_TYPE = IRecipeType.register(new ResourceLocation(MysticalMachinery.MODID, "charcoal_kiln").toString());

  public static final RegistryEntry<KilnRecipe.Serializer> KILN_SERIALIZER = REGISTRATE.recipeSerializer("kiln", KilnRecipe.Serializer::new).register();
  public static final RegistryEntry<CharcoalKilnRecipeSerializer> CHARCOAL_KILN_SERIALIZER = REGISTRATE.recipeSerializer("charcoal_kiln", CharcoalKilnRecipeSerializer::new).register();
  public static final RegistryEntry<SawmillRecipe.Serializer> SAWMILL_SERIALIZER = REGISTRATE.recipeSerializer("sawmill", SawmillRecipe.Serializer::new).register();

  protected static String safeName(ResourceLocation nameSource) {
    return nameSource.getPath().replace('/', '_');
  }

  protected static InventoryChangeTrigger.Instance hasItem(IItemProvider itemIn) {
    return hasItem(ItemPredicate.Builder.create().item(itemIn).build());
  }

  protected static InventoryChangeTrigger.Instance hasItem(ITag.INamedTag<Item> tagIn) {
    return hasItem(ItemPredicate.Builder.create().tag(tagIn).build());
  }

  protected static InventoryChangeTrigger.Instance hasItem(ItemPredicate... predicate) {
    return new InventoryChangeTrigger.Instance(EntityPredicate.AndPredicate.ANY_AND, MinMaxBounds.IntBound.UNBOUNDED, MinMaxBounds.IntBound.UNBOUNDED, MinMaxBounds.IntBound.UNBOUNDED, predicate);
  }

  protected static ResourceLocation safeId(ResourceLocation id) {
    return new ResourceLocation(id.getNamespace(), safeName(id));
  }

  protected static ResourceLocation safeId(IForgeRegistryEntry<?> registryEntry) {
    return safeId(registryEntry.getRegistryName());
  }

  public static void sawmillRecipes(Consumer<IFinishedRecipe> consumer) {
    int stick = 2;
    sawmill(Tags.Items.RODS_WOODEN, ModItems.SAWDUST, stick, consumer);
    sawmill(ItemTags.PLANKS, Items.STICK, 3, consumer);
    sawmill(ItemTags.WOODEN_BUTTONS, ModItems.SAWDUST, stick * 3, consumer);
    sawmill(ItemTags.WOODEN_SLABS, ModItems.SAWDUST, stick, consumer);
    sawmill(ItemTags.WOODEN_DOORS, ModItems.SAWDUST, stick * 6, consumer);
    sawmill(ItemTags.WOODEN_FENCES, ModItems.SAWDUST, stick * 5, consumer);
    sawmill(ItemTags.WOODEN_STAIRS, ModItems.SAWDUST, stick * 5, consumer);
    sawmill(ItemTags.WOODEN_TRAPDOORS, ModItems.SAWDUST, stick * 9, consumer);
    sawmill(ItemTags.WOODEN_PRESSURE_PLATES, ModItems.SAWDUST, stick * 6, consumer);
    sawmill(Tags.Items.CHESTS_WOODEN, ModItems.SAWDUST, stick * 24, consumer);
    sawmill(Tags.Items.FENCES_WOODEN, ModItems.SAWDUST, stick * 5, consumer);
    sawmill(Tags.Items.FENCE_GATES_WOODEN, ModItems.SAWDUST, stick * 10, consumer);
  }

  public static void kilnRecipes(Consumer<IFinishedRecipe> consumer) {
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

  protected static <T extends IItemProvider & IForgeRegistryEntry<?>> void kiln(Supplier<? extends T> source, Supplier<? extends T> result, Consumer<IFinishedRecipe> consumer) {
    KilnRecipeBuilder.kilnRecipe(Ingredient.fromItems(source.get()), result.get(), 0.35f, 80).addCriterion("has_" + safeName(source.get().getRegistryName()), hasItem(source.get())).build(consumer, safeId(result.get()) + "_from_kiln");
  }

  protected static <T extends IItemProvider & IForgeRegistryEntry<?>> void kiln(T source, T result, Consumer<IFinishedRecipe> consumer) {
    KilnRecipeBuilder.kilnRecipe(Ingredient.fromItems(source), result, 0.35f, 80).addCriterion("has_" + safeName(source.getRegistryName()), hasItem(source)).build(consumer, safeId(result) + "_from_kiln");
  }

  protected static <T extends IItemProvider & IForgeRegistryEntry<?>> void kiln(ITag.INamedTag<Item> source, T result, Consumer<IFinishedRecipe> consumer) {
    KilnRecipeBuilder.kilnRecipe(Ingredient.fromTag(source), result, 0.35f, 80).addCriterion("has_" + safeName(source.getName()), hasItem(source)).build(consumer, safeId(result) + "_from_kiln");
  }

  protected static <T extends IItemProvider & IForgeRegistryEntry<?>> void kiln(ITag.INamedTag<Item> source, Supplier<? extends T> result, Consumer<IFinishedRecipe> consumer) {
    KilnRecipeBuilder.kilnRecipe(Ingredient.fromTag(source), result.get(), 0.35f, 80).addCriterion("has_" + safeName(source.getName()), hasItem(source)).build(consumer, safeId(result.get()) + "_from_kiln");
  }

  protected static <T extends IItemProvider & IForgeRegistryEntry<?>> void kiln(Supplier<? extends T> source, T result, Consumer<IFinishedRecipe> consumer) {
    KilnRecipeBuilder.kilnRecipe(Ingredient.fromItems(source.get()), result, 0.35f, 80).addCriterion("has_" + safeName(source.get().getRegistryName()), hasItem(source.get())).build(consumer, safeId(result) + "_from_kiln");
  }

  protected static <T extends IItemProvider & IForgeRegistryEntry<?>> void kiln(T source, Supplier<? extends T> result, Consumer<IFinishedRecipe> consumer) {
    KilnRecipeBuilder.kilnRecipe(Ingredient.fromItems(source), result.get(), 0.35f, 80).addCriterion("has_" + safeName(source.getRegistryName()), hasItem(source)).build(consumer, safeId(result.get()) + "_from_kiln");
  }

  protected static <T extends IItemProvider & IForgeRegistryEntry<?>> void sawmill(ITag.INamedTag<Item> source, T result, int count, Consumer<IFinishedRecipe> consumer) {
    SawmillRecipeBuilder.sawmillRecipe(Ingredient.fromTag(source), result, count, 0.35f, 30).addCriterion("has_" + safeName(source.getName()), hasItem(source)).build(consumer, safeId(result) + "_from_sawmill_" + safeName(source.getName()));
  }

  protected static <T extends IItemProvider & IForgeRegistryEntry<?>> void sawmill(ITag.INamedTag<Item> source, Supplier<? extends T> result, int count, Consumer<IFinishedRecipe> consumer) {
    SawmillRecipeBuilder.sawmillRecipe(Ingredient.fromTag(source), result.get(), count, 0.35f, 30).addCriterion("has_" + safeName(source.getName()), hasItem(source)).build(consumer, safeId(result.get()) + "_from_sawmill_" + safeName(source.getName()));
  }

  public static void load() {

  }
}
