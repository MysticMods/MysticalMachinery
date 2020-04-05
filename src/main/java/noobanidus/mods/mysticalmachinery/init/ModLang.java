package noobanidus.mods.mysticalmachinery.init;

import static noobanidus.mods.mysticalmachinery.MysticalMachinery.REGISTRATE;

public class ModLang {
  static {
    REGISTRATE.addRawLang("mysticalmachinery.container.kiln", "Kiln");
    REGISTRATE.addRawLang("mysticalmachinery.container.sawmill", "Sawmill");
    REGISTRATE.addRawLang("mysticalmachinery.container.charcoal_kiln", "Charcoal Kiln");
    REGISTRATE.addRawLang("mysticalmachinery.jei.kiln", "Kiln Baking");
    REGISTRATE.addRawLang("mysticalmachinery.jei.sawmill", "Sawing");
    REGISTRATE.addRawLang("mysticalmachinery.jei.charcoal_kiln", "Charcoal Kiln Baking");
    REGISTRATE.addRawLang("mysticalmachinery.jei.charcoal_kiln.max_additional", "Extra: ~%s");
    REGISTRATE.addRawLang("mysticalmachinery.subtitles.block.charcoal_kiln.fire_crackle", "Charcoal Kiln crackles");
    REGISTRATE.addRawLang("mysticalmachinery.subtitles.block.kiln.fire_crackle", "Kiln crackles");
  }

  public static void load() {
  }
}
