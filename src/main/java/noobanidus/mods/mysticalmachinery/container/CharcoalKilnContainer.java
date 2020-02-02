package noobanidus.mods.mysticalmachinery.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.FurnaceResultSlot;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import noobanidus.mods.mysticalmachinery.init.ModContainers;
import noobanidus.mods.mysticalmachinery.init.ModRecipes;

@SuppressWarnings("NullableProblems")
public class CharcoalKilnContainer extends Container {
  private final IInventory inventory;
  private final IIntArray data;
  protected final World world;

  public CharcoalKilnContainer(int id, PlayerInventory playerInventoryIn) {
    this(id, playerInventoryIn, new Inventory(2), new IntArray(4));
  }

  public CharcoalKilnContainer(int id, PlayerInventory playerInventoryIn, IInventory inventory, IIntArray data) {
    super(ModContainers.CHARCOAL_KILN_CONTAINER.get(), id);
    assertInventorySize(inventory, 2);
    assertIntArraySize(data, 4);
    this.inventory = inventory;
    this.data = data;
    this.world = playerInventoryIn.player.world;
    this.addSlot(new Slot(inventory, 0, 56, 27));
    this.addSlot(new FurnaceResultSlot(playerInventoryIn.player, inventory, 1, 116, 35));

    for (int i = 0; i < 3; ++i) {
      for (int j = 0; j < 9; ++j) {
        this.addSlot(new Slot(playerInventoryIn, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
      }
    }

    for (int k = 0; k < 9; ++k) {
      this.addSlot(new Slot(playerInventoryIn, k, 8 + k * 18, 142));
    }

    this.trackIntArray(data);
  }

  public boolean canInteractWith(PlayerEntity playerIn) {
    return this.inventory.isUsableByPlayer(playerIn);
  }

  public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
    ItemStack itemstack = ItemStack.EMPTY;
    Slot slot = this.inventorySlots.get(index);
    if (slot != null && slot.getHasStack()) {
      ItemStack itemstack1 = slot.getStack();
      itemstack = itemstack1.copy();
      if (index == 1) {
        if (!this.mergeItemStack(itemstack1, 3, 38, true)) {
          return ItemStack.EMPTY;
        }

        slot.onSlotChange(itemstack1, itemstack);
      } else if (index != 0) {
        if (this.recipeMatches(itemstack1)) {
          if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
            return ItemStack.EMPTY;
          }
        } else if (index >= 3 && index < 30) {
          if (!this.mergeItemStack(itemstack1, 30, 38, false)) {
            return ItemStack.EMPTY;
          }
        } else if (index >= 30 && index < 38 && !this.mergeItemStack(itemstack1, 3, 30, false)) {
          return ItemStack.EMPTY;
        }
      } else if (!this.mergeItemStack(itemstack1, 3, 38, false)) {
        return ItemStack.EMPTY;
      }

      if (itemstack1.isEmpty()) {
        slot.putStack(ItemStack.EMPTY);
      } else {
        slot.onSlotChanged();
      }

      if (itemstack1.getCount() == itemstack.getCount()) {
        return ItemStack.EMPTY;
      }

      slot.onTake(playerIn, itemstack1);
    }

    return itemstack;
  }

  private boolean recipeMatches(ItemStack itemStack) {
    return this.world.getRecipeManager().getRecipe(ModRecipes.CHARCOAL_KILN_TYPE, new Inventory(itemStack), this.world).isPresent();
  }

  @OnlyIn(Dist.CLIENT)
  public int getCookProgressionScaled() {
    int i = this.data.get(1);
    int j = this.data.get(2);
    return j != 0 && i != 0 ? i * 24 / j : 0;
  }

  @OnlyIn(Dist.CLIENT)
  public boolean isBurning() {
    return this.data.get(0) == 1;
  }

  @OnlyIn(Dist.CLIENT)
  public boolean isBlocked () {
    return this.data.get(3) == 1;
  }
}

