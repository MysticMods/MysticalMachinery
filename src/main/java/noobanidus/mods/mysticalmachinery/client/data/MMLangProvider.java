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
    addBlock(ModBlocks.SAWMILL);

    for (RegistryObject<MachineFrameBlock> type : ModBlocks.MACHINE_FRAMES.values()) {
      addBlock(type);
    }

    addBlock(ModBlocks.COOKIE_GENERATOR);

    addItem(ModItems.HEAT_CAPACITOR);
    addItem(ModItems.SOLID_STATE_HEAT_CONVERTER);
    addItem(ModItems.ENCHANTMENT_DUST);
    addItem(ModItems.POWERCELL_LEAD);
    addItem(ModItems.POWERCELL_COPPER);
    addItem(ModItems.POWERCELL_SILVER);
    addItem(ModItems.POWERCELL_TIN);
    addItem(ModItems.POWERCELL_QUICKSILVER);

    addBlock(ModBlocks.DRAGONFIRE_FORGE);
    addBlock(ModBlocks.STORED_HEAT_GENERATOR);

    ModBlocks.BLOCKS_WITH_MODELS.forEach(this::addBlock);

    add("mysticalmachinery.container.kiln", "Kiln");
    add("mysticalmachinery.jei.kiln", "Kiln Baking");
    add("mysticalmachinery.tile.cookie_generator.contains", "Contains %s/%sFCE (Forge Cookie Energy)");
    add("mysticalmachinery.tile.end_stone_generator.contains", "Contains %s End Stone (%s/%s FE)");
    add("mysticalmachinery.tile.block_generator.contains", "Contains %s %s (%s/%s FE)");
    add("mysticalmachinery.subtitles.block.kiln.fire_crackle", "Kiln crackles");
    add("mysticalmachinery.subtitles.block.cookie_generator.munch", "Generator munches");
    add("mysticalmachinery.subtitles.block.end_stone_generator.generate", "End Stone Generator operates");
    add("mysticalmachinery.energy_item.tooltip", "Energy: %s/%s FE");
    // TODO: Containers
  }
}
