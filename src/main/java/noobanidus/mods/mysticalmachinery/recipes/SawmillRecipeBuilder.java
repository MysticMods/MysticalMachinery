package noobanidus.mods.mysticalmachinery.recipes;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement.Builder;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.IRequirementsStrategy;
import net.minecraft.advancements.criterion.RecipeUnlockedTrigger.Instance;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import noobanidus.mods.mysticalmachinery.init.ModRecipes;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class SawmillRecipeBuilder extends RecipeBuilder<SawmillRecipe> {
  private final int count;

  protected SawmillRecipeBuilder(IItemProvider result, Ingredient ingredient, int count, float xp, int cookTime) {
    super(result.asItem(), ingredient, xp, cookTime);
    this.count = count;
  }

  @Override
  public SawmillRecipeBuilder addCriterion(String name, ICriterionInstance instance) {
    this.advancementBuilder.withCriterion(name, instance);
    return this;
  }

  @Override
  public IRecipeSerializer<?> getSerializer() {
    return ModRecipes.SAWMILL_SERIALIZER.get();
  }

  @Override
  public void build(Consumer<IFinishedRecipe> consumer) {
    this.build(consumer, ForgeRegistries.ITEMS.getKey(this.result));
  }

  @Override
  public void build(Consumer<IFinishedRecipe> consumer, String name) {
    ResourceLocation resultName = ForgeRegistries.ITEMS.getKey(this.result);
    ResourceLocation recipeName = new ResourceLocation(name);
    if (recipeName.equals(resultName)) {
      throw new IllegalStateException("Recipe " + recipeName + " should remove its 'save' argument");
    } else {
      this.build(consumer, recipeName);
    }
  }

  @Override
  public void build(Consumer<IFinishedRecipe> consumer, ResourceLocation resource) {
    this.validate(resource);
    this.advancementBuilder.withParentId(new ResourceLocation("recipes/root")).withCriterion("has_the_recipe", new Instance(resource)).withRewards(net.minecraft.advancements.AdvancementRewards.Builder.recipe(resource)).withRequirementsStrategy(IRequirementsStrategy.OR);
    consumer.accept(new Result(resource, this.group == null ? "" : this.group, this.ingredient, this.result, this.count, this.experience, this.cookingTime, this.advancementBuilder, new ResourceLocation(resource.getNamespace(), "recipes/" + this.result.getGroup().getPath() + "/" + resource.getPath()), getSerializer()));
  }

  private void validate(ResourceLocation resource) {
    if (this.advancementBuilder.getCriteria().isEmpty()) {
      throw new IllegalStateException("No way of obtaining recipe " + resource);
    }
  }

  public static class Result implements IFinishedRecipe {
    private final ResourceLocation id;
    private final String group;
    private final Ingredient ingredient;
    private final Item result;
    private final float experience;
    private final int cookingTime;
    private final Builder advancementBuilder;
    private final ResourceLocation advancementId;
    private final IRecipeSerializer<?> serializer;
    private final int count;

    public Result(ResourceLocation resource, String group, Ingredient ingredient, Item output, int count, float xp, int cookTime, Builder advBuilder, ResourceLocation advResource, IRecipeSerializer<?> serializer) {
      this.id = resource;
      this.group = group;
      this.ingredient = ingredient;
      this.result = output;
      this.count = count;
      this.experience = xp;
      this.cookingTime = cookTime;
      this.advancementBuilder = advBuilder;
      this.advancementId = advResource;
      this.serializer = serializer;
    }

    @Override
    public void serialize(JsonObject json) {
      if (!this.group.isEmpty()) {
        json.addProperty("group", this.group);
      }

      json.add("ingredient", this.ingredient.serialize());
      json.addProperty("result", ForgeRegistries.ITEMS.getKey(this.result).toString());
      json.addProperty("experience", this.experience);
      json.addProperty("cookingtime", this.cookingTime);
      json.addProperty("itemcount", this.count);
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
      return serializer;
    }

    @Override
    public ResourceLocation getID() {
      return this.id;
    }

    @Override
    @Nullable
    public JsonObject getAdvancementJson() {
      return this.advancementBuilder.serialize();
    }

    @Override
    @Nullable
    public ResourceLocation getAdvancementID() {
      return this.advancementId;
    }
  }

  public static SawmillRecipeBuilder sawmillRecipe(Ingredient input, IItemProvider result, float xp, int cookTime) {
    return new SawmillRecipeBuilder(result, input, 1, xp, cookTime);
  }

  public static SawmillRecipeBuilder sawmillRecipe(Ingredient input, IItemProvider result, int count, float xp, int cookTime) {
    return new SawmillRecipeBuilder(result, input, count, xp, cookTime);
  }
}
