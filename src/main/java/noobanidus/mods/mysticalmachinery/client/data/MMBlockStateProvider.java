package noobanidus.mods.mysticalmachinery.client.data;

import epicsquid.mysticallib.client.data.DeferredBlockStateProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import noobanidus.mods.mysticalmachinery.MysticalMachinery;

public class MMBlockStateProvider extends DeferredBlockStateProvider {
  public MMBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
    super("Mystical Machinery Block State & Model Generator", gen, MysticalMachinery.MODID, exFileHelper);
  }

  @Override
  protected void registerStatesAndModels() {
  }
}
