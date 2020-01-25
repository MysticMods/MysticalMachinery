package noobanidus.mods.mysticalmachinery.setup;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IEnviromentBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import noobanidus.mods.mysticalmachinery.blocks.CharcoalKilnBlock;
import noobanidus.mods.mysticalmachinery.client.screen.CharcoalKilnScreen;
import noobanidus.mods.mysticalmachinery.client.screen.KilnScreen;
import noobanidus.mods.mysticalmachinery.client.screen.SawmillScreen;
import noobanidus.mods.mysticalmachinery.init.ModBlocks;
import noobanidus.mods.mysticalmachinery.init.ModContainers;

import javax.annotation.Nullable;
import java.awt.*;

@OnlyIn(Dist.CLIENT)
public class ClientSetup {
  public static void init(FMLClientSetupEvent event) {
    ScreenManager.registerFactory(ModContainers.KILN_CONTAINER.get(), KilnScreen::new);
    ScreenManager.registerFactory(ModContainers.SAWMILL_CONTAINER.get(), SawmillScreen::new);
    ScreenManager.registerFactory(ModContainers.CHARCOAL_KILN_CONTAINER.get(), CharcoalKilnScreen::new);

    Minecraft mc = Minecraft.getInstance();

    mc.getBlockColors().register((state, world, pos, index) -> Fluids.WATER.getAttributes().getColor(world, pos), ModBlocks.WATER_FABRICATOR.get());
    mc.getItemColors().register((p1, index) -> Fluids.WATER.getAttributes().getColor(), ModBlocks.WATER_FABRICATOR.get());
  }
}
