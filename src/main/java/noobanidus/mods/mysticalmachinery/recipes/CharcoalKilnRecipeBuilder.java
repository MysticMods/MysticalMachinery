package noobanidus.mods.mysticalmachinery.recipes;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement.Builder;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.IRequirementsStrategy;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.RecipeUnlockedTrigger.Instance;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import noobanidus.mods.mysticalmachinery.init.ModRecipes;

import javax.annotation.Nullable;
import java.util.function.Consumer;

@SuppressWarnings("Duplicates")
public class CharcoalKilnRecipeBuilder {
  protected final ItemStack result;
  protected final Ingredient ingredient;
  protected final float experience;
  protected final int cookingTime;
  protected final int ingredientCount;
  protected final int maxAdditional;
  protected final Builder advancementBuilder = Builder.builder();
  protected String group;

  protected CharcoalKilnRecipeBuilder(ItemStack result, Ingredient ingredient, int ingredientCount, int maxAdditional, float xp, int cookTime) {
    this.result = result;
    this.ingredient = ingredient;
    this.experience = xp;
    this.cookingTime = cookTime;
    this.ingredientCount = ingredientCount;
    this.maxAdditional = maxAdditional;
  }

  public static CharcoalKilnRecipeBuilder charcoalKilnRecipe(ItemStack result, Ingredient ingredient, int ingredientCount, int maxAdditional, float xp, int cookTime) {
    return new CharcoalKilnRecipeBuilder(result, ingredient, ingredientCount, maxAdditional, xp, cookTime);
  }

  public CharcoalKilnRecipeBuilder addCriterion(String name, ICriterionInstance instance) {
    this.advancementBuilder.withCriterion(name, instance);
    return this;
  }

  public void build(Consumer<IFinishedRecipe> consumer) {
    this.build(consumer, this.result.getItem().getRegistryName());
  }

  public void build(Consumer<IFinishedRecipe> consumer, String name) {
    ResourceLocation resultName = this.result.getItem().getRegistryName();
    ResourceLocation recipeName = new ResourceLocation(name);
    if (recipeName.equals(resultName)) {
      throw new IllegalStateException("Recipe " + recipeName + " should remove its 'save' argument");
    } else {
      this.build(consumer, recipeName);
    }
  }

  public void build(Consumer<IFinishedRecipe> consumer, ResourceLocation resource) {
    this.validate(resource);
    this.advancementBuilder.withParentId(new ResourceLocation("recipes/root")).withCriterion("has_the_recipe", new Instance(EntityPredicate.AndPredicate.ANY_AND, resource)).withRewards(net.minecraft.advancements.AdvancementRewards.Builder.recipe(resource)).withRequirementsStrategy(IRequirementsStrategy.OR);
    ResourceLocation rl = this.result.getItem().getRegistryName();
    consumer.accept(new Result(resource, this.group == null ? "" : this.group, this.ingredient, this.ingredientCount, this.result, this.maxAdditional, this.experience, this.cookingTime, this.advancementBuilder, new ResourceLocation(resource.getNamespace(), "recipes/" + rl.getPath() + "/" + resource.getPath())));
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
    private final ItemStack result;
    private final float experience;
    private final int cookingTime;
    private final Builder advancementBuilder;
    private final ResourceLocation advancementId;
    private final int ingredientCount;
    private final int maxAdditional;

    public Result(ResourceLocation resource, String group, Ingredient ingredient, int ingredientCount, ItemStack output, int maxAdditional, float xp, int cookTime, Builder advBuilder, ResourceLocation advResource) {
      this.id = resource;
      this.group = group;
      this.ingredient = ingredient;
      this.result = output;
      this.experience = xp;
      this.cookingTime = cookTime;
      this.advancementBuilder = advBuilder;
      this.advancementId = advResource;
      this.ingredientCount = ingredientCount;
      this.maxAdditional = maxAdditional;
    }

    @Override
    public void serialize(JsonObject json) {
      if (!this.group.isEmpty()) {
        json.addProperty("group", this.group);
      }

      json.add("ingredient", this.ingredient.serialize());
      json.addProperty("ingredientCount", this.ingredientCount);
      json.addProperty("maxAdditional", this.maxAdditional);
      JsonObject result = new JsonObject();
      result.addProperty("item", this.result.getItem().getRegistryName().toString());
      result.addProperty("count", this.result.getCount());

      json.add("result", result);
      json.addProperty("experience", this.experience);
      json.addProperty("cookingtime", this.cookingTime);
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
      return ModRecipes.CHARCOAL_KILN_SERIALIZER.get();
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
}
