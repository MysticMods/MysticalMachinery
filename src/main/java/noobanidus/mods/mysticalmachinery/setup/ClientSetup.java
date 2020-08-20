package noobanidus.mods.mysticalmachinery.setup;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import noobanidus.libs.noobutil.setup.ShadedClientSetup;
import noobanidus.mods.mysticalmachinery.client.screen.CharcoalKilnScreen;
import noobanidus.mods.mysticalmachinery.client.screen.KilnScreen;
import noobanidus.mods.mysticalmachinery.client.screen.SawmillScreen;
import noobanidus.mods.mysticalmachinery.init.ModBlocks;
import noobanidus.mods.mysticalmachinery.init.ModContainers;

@SuppressWarnings("deprecation")
@OnlyIn(Dist.CLIENT)
public class ClientSetup {
  public static void init(FMLClientSetupEvent event) {
    DeferredWorkQueue.runLater(() -> {
      ScreenManager.registerFactory(ModContainers.KILN_CONTAINER.get(), KilnScreen::new);
      ScreenManager.registerFactory(ModContainers.SAWMILL_CONTAINER.get(), SawmillScreen::new);
      ScreenManager.registerFactory(ModContainers.CHARCOAL_KILN_CONTAINER.get(), CharcoalKilnScreen::new);

      RenderType cutout = RenderType.getCutoutMipped();
      RenderTypeLookup.setRenderLayer(ModBlocks.SAWMILL.get(), cutout);

      ShadedClientSetup.init(event);
    });
  }
}
