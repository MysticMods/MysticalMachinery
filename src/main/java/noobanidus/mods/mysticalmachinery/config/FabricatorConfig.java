package noobanidus.mods.mysticalmachinery.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class FabricatorConfig {

  private String name;
  private int maxFE;
  private int maxTransfer;
  private int operationCost;
  private int frequency;

  private ForgeConfigSpec.IntValue configMaxFE;
  private ForgeConfigSpec.IntValue configMaxTransfer;
  private ForgeConfigSpec.IntValue configOperationCost;
  private ForgeConfigSpec.IntValue configFrequency;

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
    configMaxFE = builder.comment("Maximum amount of FE that can be stored in the block").defineInRange("maxFE", 0, Integer.MAX_VALUE, maxFE);
    configMaxTransfer = builder.comment("Maximum amount of FE that can be transferred into the block per tick").defineInRange("maxTransfer", 0, Integer.MAX_VALUE, maxTransfer);
    configOperationCost = builder.comment("Cost to create one " + name + " per operation").defineInRange("operationCost", 0, Integer.MAX_VALUE, operationCost);
    configFrequency = builder.comment("Frequency (in ticks) that " + name + " is produced").defineInRange("frequency", 0, Integer.MAX_VALUE, frequency);
    builder.pop();
  }
}
