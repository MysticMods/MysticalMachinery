package noobanidus.mods.mysticalmachinery;

import noobanidus.mods.mysticalmachinery.config.ConfigManager;
import noobanidus.mods.mysticalmachinery.init.*;
import noobanidus.mods.mysticalmachinery.setup.ClientSetup;
import noobanidus.mods.mysticalmachinery.setup.ModSetup;
import epicsquid.mysticallib.registry.ModRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("mysticalmachinery")
public class MysticalMachinery {
  public static final Logger LOG = LogManager.getLogger();
  public static final String MODID = "mysticalmachinery";

  public static final ItemGroup ITEM_GROUP = new ItemGroup("mysticalmachinery") {
    @Override
    public ItemStack createIcon() {
      return new ItemStack(ModBlocks.KILN.get());
    }
  };

  public static final ModRegistry REGISTRY = new ModRegistry(MODID);

  public static ModSetup setup = new ModSetup();

  public MysticalMachinery() {
    IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

    modBus.addListener(setup::init);
    modBus.addListener(setup::gatherData);
    DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> modBus.addListener(ClientSetup::init));

    ModItems.load();
    ModBlocks.load();
    ModEntities.load();
    ModTiles.load();
    ModRecipes.load();
    ModContainers.load();
    ModSounds.load();

    modBus.addGenericListener(EntityType.class, ModEntities::registerEntities);

    ConfigManager.loadConfig(ConfigManager.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MODID + "-common.toml"));
    REGISTRY.registerEventBus(modBus);
  }
}
