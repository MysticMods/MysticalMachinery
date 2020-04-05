package noobanidus.mods.mysticalmachinery;

import epicsquid.mysticallib.registrate.CustomRegistrate;
import epicsquid.mysticalworld.init.ModEntities;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
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

  public static CustomRegistrate REGISTRATE;

  public static ModSetup setup = new ModSetup();

  public MysticalMachinery() {
/*    ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigManager.COMMON_CONFIG);*/
    IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

    modBus.addListener(setup::init);
    DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> modBus.addListener(ClientSetup::init));

    REGISTRATE = CustomRegistrate.create(MODID);
    REGISTRATE.itemGroup(() -> ITEM_GROUP);

    ModItems.load();
    ModBlocks.load();
    ModEntities.load();
    ModTiles.load();
    ModRecipes.load();
    ModContainers.load();
    ModSounds.load();
    ModLang.load();
    ModTags.load();

/*    ConfigManager.loadConfig(ConfigManager.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MODID + "-common.toml"));*/
  }
}
