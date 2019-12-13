package noobanidus.mods.mysticalmachinery.setup;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import noobanidus.mods.mysticalmachinery.client.screen.KilnScreen;
import noobanidus.mods.mysticalmachinery.client.screen.SawmillScreen;
import noobanidus.mods.mysticalmachinery.init.ModBlocks;
import noobanidus.mods.mysticalmachinery.init.ModContainers;

@OnlyIn(Dist.CLIENT)
public class ClientSetup {
  public static void init(FMLClientSetupEvent event) {
    ScreenManager.registerFactory(ModContainers.KILN_CONTAINER.get(), KilnScreen::new);
    ScreenManager.registerFactory(ModContainers.SAWMILL_CONTAINER.get(), SawmillScreen::new);

    Minecraft.getInstance().getBlockColors().register((state, world, pos, index) -> Fluids.WATER.getAttributes().getColor(world, pos), ModBlocks.WATER_FABRICATOR.get());
    Minecraft.getInstance().getItemColors().register((p_getColor_1_, index) -> Fluids.WATER.getAttributes().getColor(), ModBlocks.WATER_FABRICATOR.get());
  }
}
