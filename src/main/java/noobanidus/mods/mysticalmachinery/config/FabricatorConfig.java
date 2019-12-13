package noobanidus.mods.mysticalmachinery.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class FabricatorConfig {

  private String name;
  private int maxFE;
  private int maxTransfer;
  private int operationCost;
  private int frequency;

  private ForgeConfigSpec.ConfigValue<Integer> configMaxFE;
  private ForgeConfigSpec.ConfigValue<Integer> configMaxTransfer;
  private ForgeConfigSpec.ConfigValue<Integer> configOperationCost;
  private ForgeConfigSpec.ConfigValue<Integer> configFrequency;

  public FabricatorConfig(String name, int maxFE, int maxTransfer, int operationCost, int frequency) {
    this.name = name;
    this.maxFE = maxFE;
    this.maxTransfer = maxTransfer;
    this.operationCost = operationCost;
    this.frequency = frequency;
  }

  public String getName() {
    return name;
  }

  public int getMaxFE() {
    return configMaxFE.get();
  }

  public int getMaxTransfer() {
    return configMaxTransfer.get();
  }

  public int getOperationCost() {
    return configOperationCost.get();
  }

  public int getFrequency() {
    return configFrequency.get();
  }

  public int[] values () {
    return new int[]{getMaxFE(), getMaxTransfer(), getOperationCost(), getFrequency()};
  }

  public void apply(ForgeConfigSpec.Builder builder) {
    builder.comment(name + " fabricator").push(name + "_fabricator");
    configMaxFE = builder.comment("Maximum amount of FE that can be stored in the block").define("maxFE", maxFE);
    configMaxTransfer = builder.comment("Maximum amount of FE that can be transferred into the block per tick").define("maxTransfer", maxTransfer);
    configOperationCost = builder.comment("Cost to create one " + name + " per operation").define("operationCost", operationCost);
    configFrequency = builder.comment("Frequency (in ticks) that " + name + " is produced").define("frequency", frequency);
    builder.pop();
  }
}
