package noobanidus.mods.mysticalmachinery.setup;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import noobanidus.mods.mysticalmachinery.client.screen.KilnScreen;
import noobanidus.mods.mysticalmachinery.client.screen.SawmillScreen;
import noobanidus.mods.mysticalmachinery.init.ModContainers;

@OnlyIn(Dist.CLIENT)
public class ClientSetup {
  public static void init(FMLClientSetupEvent event) {
    ScreenManager.registerFactory(ModContainers.KILN_CONTAINER.get(), KilnScreen::new);
    ScreenManager.registerFactory(ModContainers.SAWMILL_CONTAINER.get(), SawmillScreen::new);
  }
}
