package noobanidus.mods.mysticalmachinery.setup;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import noobanidus.mods.mysticalmachinery.client.data.MMBlockStateProvider;
import noobanidus.mods.mysticalmachinery.client.data.MMItemModelProvider;
import noobanidus.mods.mysticalmachinery.client.data.MMLangProvider;
import noobanidus.mods.mysticalmachinery.data.MMLootTableProvider;
import noobanidus.mods.mysticalmachinery.data.MMRecipeProvider;

public class ModSetup {
  public ModSetup() {
  }

  public void init(FMLCommonSetupEvent event) {
  }

  public void gatherData (GatherDataEvent event) {
    DataGenerator gen = event.getGenerator();
    if (event.includeClient()) {
      gen.addProvider(new MMBlockStateProvider(gen, event.getExistingFileHelper()));
      gen.addProvider(new MMItemModelProvider(gen, event.getExistingFileHelper()));
      gen.addProvider(new MMLangProvider(gen));
    }
    if (event.includeServer()) {
      gen.addProvider(new MMLootTableProvider(gen));
      gen.addProvider(new MMRecipeProvider(gen));
    }
  }
}
