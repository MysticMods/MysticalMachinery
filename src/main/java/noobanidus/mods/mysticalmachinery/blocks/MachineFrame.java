package noobanidus.mods.mysticalmachinery.blocks;

import epicsquid.mysticalworld.MWTags;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import noobanidus.mods.mysticalmachinery.MysticalMachinery;

import javax.annotation.Nullable;

public enum MachineFrame {
  AMETHYST("amethyst", MWTags.Items.AMETHYST_GEM),
  CHORUS("chorus", MWTags.Items.PURPUR),
  COPPER("copper", MWTags.Items.COPPER_INGOT),
  DIAMOND("diamond", Tags.Items.GEMS_DIAMOND),
  EMERALD("emerald", Tags.Items.GEMS_EMERALD),
  GOLD("gold", Tags.Items.INGOTS_GOLD),
  IRON("iron", Tags.Items.INGOTS_IRON),
  LEAD("lead", MWTags.Items.LEAD_INGOT),
  NETHER("nether", MWTags.Items.NETHER_BRICKS),
  PRISMARINE("prismarine", Tags.Items.GEMS_PRISMARINE),
  QUICKSILVER("quicksilver", MWTags.Items.QUICKSILVER_INGOT),
  RED_NETHER("red_nether", MWTags.Items.RED_NETHER_BRICKS),
  SILVER("silver", MWTags.Items.SILVER_INGOT),
  TERRACOTTA("terracotta", MWTags.Items.TERRACOTTA),
  TIN("tin", MWTags.Items.TIN_INGOT),
  WOOD("wood", ItemTags.PLANKS),
  LAPIS("lapis", Tags.Items.GEMS_LAPIS),
  REDSTONE("redstone", Tags.Items.DUSTS_REDSTONE),
  STONE("stone", Tags.Items.STONE),
  REINFORCED("reinforced", Tags.Items.STORAGE_BLOCKS_IRON),
  SLIME("slime", MWTags.Items.SLIME)
  ;

  private String name;
  private Tag<Item> tag;

  MachineFrame(String name, Tag<Item> tag) {
    this.name = name;
    this.tag = tag;
  }

  public Tag<Item> getTag() {
    return tag;
  }

  public String getName() {
    return name;
  }

  public ResourceLocation model () {
    return new ResourceLocation(MysticalMachinery.MODID, "block/" + name + "_machine_frame");
  }

  @Nullable
  public static MachineFrame getByName (String name) {
    for (MachineFrame t : values()) {
      if (t.getName().equals(name)) {
        return t;
      }
    }

    return null;
  }

  @Nullable
  public static MachineFrame getByOrdinal (int ord) {
    int i = 0;
    for (MachineFrame t : values()) {
      if (i == ord) {
        return t;
      }
      i++;
    }

    return null;
  }
}
