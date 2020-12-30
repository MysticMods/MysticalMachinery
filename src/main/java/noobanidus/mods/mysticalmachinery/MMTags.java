package noobanidus.mods.mysticalmachinery;

import epicsquid.mysticalworld.MysticalWorld;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeTagHandler;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Function;

public class MMTags {
  public static class Items extends MMTags {
    public static ITag.INamedTag<Item> SAWDUST = compatTag("dusts/wooden");
    public static ITag.INamedTag<Item> FIRELIGHTERS = modTag("firelighters");

    static ITag.INamedTag<Item> modTag(String name) {
      return ForgeTagHandler.makeWrapperTag(ForgeRegistries.ITEMS, new ResourceLocation(MysticalMachinery.MODID, name));
    }

    static ITag.INamedTag<Item> compatTag(String name) {
      return ForgeTagHandler.makeWrapperTag(ForgeRegistries.ITEMS, new ResourceLocation("forge", name));
    }
  }

  static <T extends ITag.INamedTag<?>> T tag(Function<ResourceLocation, T> creator, String modid, String name) {
    return creator.apply(new ResourceLocation(modid, name));
  }

  static <T extends ITag.INamedTag<?>> T modTag(Function<ResourceLocation, T> creator, String name) {
    return tag(creator, MysticalWorld.MODID, name);
  }

  static <T extends ITag.INamedTag<?>> T compatTag(Function<ResourceLocation, T> creator, String name) {
    return tag(creator, "forge", name);
  }
}
