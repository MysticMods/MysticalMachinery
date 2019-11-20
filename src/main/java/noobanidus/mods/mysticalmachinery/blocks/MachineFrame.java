package noobanidus.mods.mysticalmachinery.blocks;

import epicsquid.mysticalworld.MWTags;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import noobanidus.mods.mysticalmachinery.MysticalMachinery;

import javax.annotation.Nullable;

public class MachineFrame extends Block {
  private Type type;

  public MachineFrame(Properties properties, MachineFrame.Type type) {
    super(properties);
  }

  public Type getType() {
    return type;
  }

  public enum Type {
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
    REDSTONE("redstone", Tags.Items.DUSTS_REDSTONE)
    ;

    private String name;
    private Tag<Item> tag;

    Type (String name, Tag<Item> tag) {
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
    public static Type getByName (String name) {
      for (Type t : values()) {
        if (t.getName().equals(name)) {
          return t;
        }
      }

      return null;
    }

    @Nullable
    public static Type getByOrdinal (int ord) {
      int i = 0;
      for (Type t : values()) {
        if (i == ord) {
          return t;
        }
        i++;
      }

      return null;
    }
  }
}
