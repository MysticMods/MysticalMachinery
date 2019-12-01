package noobanidus.mods.mysticalmachinery.tiles;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import noobanidus.mods.mysticalmachinery.container.KilnContainer;
import noobanidus.mods.mysticalmachinery.container.SawmillContainer;
import noobanidus.mods.mysticalmachinery.init.ModRecipes;
import noobanidus.mods.mysticalmachinery.init.ModTiles;

public class SawmillTile extends AbstractFurnaceTileEntity {
  @SuppressWarnings("ConstantConditions")
  public SawmillTile() {
    super(ModTiles.SAWMILL.get(), ModRecipes.SAWMILL_TYPE);
  }

  @Override
  protected ITextComponent getDefaultName() {
    return new TranslationTextComponent("mysticalmachinery.container.sawmill");
  }

  @Override
  protected Container createMenu(int id, PlayerInventory player) {
    return new SawmillContainer(id, player, this, this.furnaceData);
  }
}
