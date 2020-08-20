package noobanidus.mods.mysticalmachinery;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import noobanidus.libs.noobutil.registrate.CustomRegistrate;
import noobanidus.mods.mysticalmachinery.init.*;
import noobanidus.mods.mysticalmachinery.setup.ClientInit;
import noobanidus.mods.mysticalmachinery.setup.ModSetup;
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
    DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientInit::init);

    REGISTRATE = CustomRegistrate.create(MODID);
    REGISTRATE.itemGroup(() -> ITEM_GROUP);

    ModItems.load();
    ModBlocks.load();
    ModTiles.load();
    ModRecipes.load();
    ModContainers.load();
    ModSounds.load();
    ModLang.load();
    ModTags.load();

    /*    ConfigManager.loadConfig(ConfigManager.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MODID + "-common.toml"));*/
  }
}
