package noobanidus.mods.mysticalmachinery.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class DustItem extends Item {
  public DustItem(Properties properties) {
    super(properties);
  }

  @Override
  public int getBurnTime(ItemStack itemStack) {
    return 34;
  }
}
