package noobanidus.mods.mysticalmachinery.recipes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import epicsquid.mysticallib.recipe.AbstractCookingRecipeSerializer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

@SuppressWarnings("Duplicates")
public class CharcoalKilnRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<CharcoalKilnRecipe> {
  protected final int defaultCookTime;

  public CharcoalKilnRecipeSerializer() {
    this.defaultCookTime = 2000;
  }

  @Override
  public CharcoalKilnRecipe read(ResourceLocation recipeId, JsonObject json) {
    String s = JSONUtils.getString(json, "group", "");
    JsonElement jsonelement = (JSONUtils.isJsonArray(json, "ingredient") ? JSONUtils.getJsonArray(json, "ingredient") : JSONUtils.getJsonObject(json, "ingredient"));
    Ingredient ingredient = Ingredient.deserialize(jsonelement);
    int ingredientCount = JSONUtils.getInt(json, "ingredientCount", 1);
    if (!json.has("result"))
      throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
    ItemStack itemstack;
    if (json.get("result").isJsonObject())
      itemstack = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
    else {
      String s1 = JSONUtils.getString(json, "result");
      ResourceLocation resourcelocation = new ResourceLocation(s1);
      Item item = ForgeRegistries.ITEMS.getValue(resourcelocation);
      if (item == null) {
        throw new IllegalStateException("Item: " + s1 + " does not exist");
      }
      itemstack = new ItemStack(item);
    }
    int maxAdditional = JSONUtils.getInt(json, "maxAdditional", 0);
    float f = JSONUtils.getFloat(json, "experience", 0.0F);
    int i = JSONUtils.getInt(json, "cookingtime", this.defaultCookTime);
    return new CharcoalKilnRecipe(recipeId, s, ingredient, ingredientCount, itemstack, maxAdditional, f, i);
  }

  @Override
  public CharcoalKilnRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
    String s = buffer.readString(32767);
    Ingredient ingredient = Ingredient.read(buffer);
    int ingredientCount = buffer.readInt();
    ItemStack itemstack = buffer.readItemStack();
    int maxAdditional = buffer.readInt();
    float f = buffer.readFloat();
    int i = buffer.readVarInt();
    return new CharcoalKilnRecipe(recipeId, s, ingredient, ingredientCount, itemstack, maxAdditional, f, i);
  }

  @Override
  public void write(PacketBuffer buffer, CharcoalKilnRecipe recipe) {
    buffer.writeString(recipe.getGroup());
    recipe.getIngredients().forEach(o -> o.write(buffer));
    buffer.writeVarInt(recipe.getIngredientCount());
    buffer.writeItemStack(recipe.getRecipeOutput());
    buffer.writeVarInt(recipe.getMaxAdditional());
    buffer.writeFloat(recipe.getExperience());
    buffer.writeVarInt(recipe.getCookTime());
  }
}
