package noobanidus.mods.mysticalmachinery.tiles;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.FurnaceContainer;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import noobanidus.mods.mysticalmachinery.container.KilnContainer;
import noobanidus.mods.mysticalmachinery.init.ModRecipes;
import noobanidus.mods.mysticalmachinery.init.ModTiles;

public class KilnTile extends AbstractFurnaceTileEntity {
  @SuppressWarnings("ConstantConditions")
  public KilnTile() {
    super(ModTiles.KILN.get(), ModRecipes.KILN_TYPE);
  }

  @Override
  protected ITextComponent getDefaultName() {
    return new TranslationTextComponent("mysticalmachinery.container.kiln");
  }

  @Override
  protected Container createMenu(int id, PlayerInventory player) {
    return new KilnContainer(id, player, this, this.furnaceData);
  }
}
