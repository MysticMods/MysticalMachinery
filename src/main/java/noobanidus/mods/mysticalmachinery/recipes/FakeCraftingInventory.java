package noobanidus.mods.mysticalmachinery.recipes;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.RecipeItemHelper;

public class FakeCraftingInventory extends CraftingInventory {
  private ItemStack stack;

  public FakeCraftingInventory() {
    super(null, 1, 1);
  }

  public int getSizeInventory() {
    return 1;
  }

  public boolean isEmpty() {
    return stack.isEmpty();
  }

  public ItemStack getStackInSlot(int index) {
    return stack;
  }

  public ItemStack removeStackFromSlot(int index) {
    ItemStack orig = this.stack;
    this.stack = ItemStack.EMPTY;
    return orig;
  }

  public ItemStack decrStackSize(int index, int count) {
    return removeStackFromSlot(0);
  }

  public void setInventorySlotContents(int index, ItemStack stack) {
    this.stack = stack;
  }

  public void markDirty() {
  }

  public boolean isUsableByPlayer(PlayerEntity player) {
    return true;
  }

  public void clear() {
    this.stack = ItemStack.EMPTY;
  }

  public int getHeight() {
    return 1;
  }

  public int getWidth() {
    return 1;
  }

  public void fillStackedContents(RecipeItemHelper helper) {
    helper.accountPlainStack(stack);
  }
}
