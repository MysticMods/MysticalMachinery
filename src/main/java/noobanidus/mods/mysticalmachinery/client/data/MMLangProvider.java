package noobanidus.mods.mysticalmachinery.client.data;

import epicsquid.mysticallib.client.data.DeferredLanguageProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.fml.RegistryObject;
import noobanidus.mods.mysticalmachinery.MysticalMachinery;
import noobanidus.mods.mysticalmachinery.blocks.MachineFrameBlock;
import noobanidus.mods.mysticalmachinery.init.ModBlocks;
import noobanidus.mods.mysticalmachinery.init.ModItems;

public class MMLangProvider extends DeferredLanguageProvider {
  public MMLangProvider(DataGenerator gen) {
    super(gen, MysticalMachinery.MODID);
  }

  @Override
  protected void addTranslations() {
    addItemGroup(MysticalMachinery.ITEM_GROUP, "Mystical Machinery");

    addBlock(ModBlocks.KILN);

    for (RegistryObject<MachineFrameBlock> type : ModBlocks.MACHINE_FRAMES.values()) {
      addBlock(type);
    }

    addBlock(ModBlocks.COOKIE_GENERATOR);

    addItem(ModItems.HEAT_CAPACITOR);
    addItem(ModItems.SOLID_STATE_HEAT_CONVERTER);
    addBlock(ModBlocks.STORED_HEAT_GENERATOR);
    addBlock(ModBlocks.END_STONE_FABRICATOR);

    addBlock(ModBlocks.SAND_FABRICATOR);
    addBlock(ModBlocks.RED_SAND_FABRICATOR);
    addBlock(ModBlocks.CLAY_FABRICATOR);
    addBlock(ModBlocks.NETHERRACK_FABRICATOR);
    addBlock(ModBlocks.SOUL_SAND_FABRICATOR);
    addBlock(ModBlocks.SLIME_FABRICATOR);
    addBlock(ModBlocks.ICE_FABRICATOR);
    addBlock(ModBlocks.SNOW_FABRICATOR);
    addBlock(ModBlocks.DIRT_FABRICATOR);
    addBlock(ModBlocks.GRAVEL_FABRICATOR);

    add("mysticalmachinery.container.kiln", "Kiln");
    add("mysticalmachinery.jei.kiln", "Kiln Baking");
    add("mysticalmachinery.tile.cookie_generator.contains", "Contains %s/%sFCE (Forge Cookie Energy)");
    add("mysticalmachinery.tile.end_stone_generator.contains", "Contains %s End Stone (%s/%s FE)");
    add("mysticalmachinery.tile.block_generator.contains", "Contains %s %s (%s/%s FE)");
    add("mysticalmachinery.subtitles.block.kiln.fire_crackle", "Kiln crackles");
    add("mysticalmachinery.subtitles.block.cookie_generator.munch", "Generator munches");
    add("mysticalmachinery.subtitles.block.end_stone_generator.generate", "End Stone Generator operates");
    // TODO: Containers
  }
}
