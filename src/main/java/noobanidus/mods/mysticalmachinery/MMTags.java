package noobanidus.mods.mysticalmachinery;

import epicsquid.mysticalworld.MysticalWorld;
import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

import java.util.function.Function;

public class MMTags {
  public static class Items extends MMTags {
    public static Tag<Item> COOKIES = compatTag("cookie");
    public static Tag<Item> GENERATOR_COOKIES = modTag("generator_cookies");
    public static Tag<Item> BASE_POWERCELL = modTag("base_powercells");
    public static Tag<Item> SAWDUST = compatTag("dusts/wooden");
    public static Tag<Item> FIRELIGHTERS = modTag("firelighters");

    static Tag<Item> tag(String modid, String name) {
      return tag(ItemTags.Wrapper::new, modid, name);
    }

    static Tag<Item> modTag(String name) {
      return tag(MysticalWorld.MODID, name);
    }

    static Tag<Item> compatTag(String name) {
      return tag("forge", name);
    }
  }

  static <T extends Tag<?>> T tag(Function<ResourceLocation, T> creator, String modid, String name) {
    return creator.apply(new ResourceLocation(modid, name));
  }

  static <T extends Tag<?>> T modTag(Function<ResourceLocation, T> creator, String name) {
    return tag(creator, MysticalWorld.MODID, name);
  }

  static <T extends Tag<?>> T compatTag(Function<ResourceLocation, T> creator, String name) {
    return tag(creator, "forge", name);
  }
}
