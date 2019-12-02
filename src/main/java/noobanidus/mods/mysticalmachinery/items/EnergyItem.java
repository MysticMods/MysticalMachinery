package noobanidus.mods.mysticalmachinery.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import noobanidus.mods.mysticalmachinery.MysticalMachinery;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class EnergyItem extends Item {
  private final int max;
  private final int input;
  private final int output;

  public EnergyItem(Properties properties, int max, int input, int output) {
    super(properties);
    this.max = max;
    this.input = input;
    this.output = output;
    addPropertyOverride(new ResourceLocation(MysticalMachinery.MODID, "charge"), (stack, world, entity) -> charge(stack));
  }

  private float charge(ItemStack stack) {
    LazyOptional<IEnergyStorage> opt = stack.getCapability(CapabilityEnergy.ENERGY);
    if (!opt.isPresent()) {
      return 0;
    }

    IEnergyStorage cap = opt.orElseThrow(IllegalStateException::new);
    return (float) cap.getEnergyStored() / cap.getMaxEnergyStored();
  }

  @Nullable
  @Override
  public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
    return new ICapabilityProvider() {
      @Nonnull
      @Override
      public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == CapabilityEnergy.ENERGY ? LazyOptional.of(() -> new EnergyItemCapability(stack, max, input, output)).cast() : LazyOptional.empty();
      }
    };
  }

  @Override
  public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
    if (CapabilityEnergy.ENERGY == null) return;

    stack.getCapability(CapabilityEnergy.ENERGY).ifPresent((cap) -> {
      tooltip.add(new TranslationTextComponent("mysticalmachinery.energy_item.tooltip", String.format("%,d", cap.getEnergyStored()), String.format("%,d", cap.getMaxEnergyStored())));
    });
  }

  @Override
  public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
    if (this.isInGroup(group)) {
      items.add(new ItemStack(this));

      ItemStack full = new ItemStack(this);
      full.getOrCreateTag().putInt("Energy", max);
      items.add(full);
    }
  }

  @Override
  public boolean showDurabilityBar(ItemStack stack) {
    return true;
  }

  @Override
  public double getDurabilityForDisplay(ItemStack stack) {
    return 1 - charge(stack);
  }

  @Override
  public int getRGBDurabilityForDisplay(ItemStack stack) {
    return MathHelper.hsvToRGB((1 + charge(stack)) / 3.0f, 1.0f, 1.0f);
  }

  public static class EnergyItemCapability extends EnergyStorage {
    private final ItemStack stack;

    public EnergyItemCapability(ItemStack stack, int capacity, int maxReceive, int maxExtract) {
      super(capacity, maxReceive, maxExtract);
      this.stack = stack;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate)
    {
        if (!canReceive())
            return 0;

        int energy = getEnergyStored();
        int energyReceived = Math.min(capacity - energy, Math.min(this.maxReceive, maxReceive));
        if (!simulate)
          setEnergy(getEnergyStored() + energyReceived);
        return energyReceived;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate)
    {
        if (!canExtract())
            return 0;

        int energy = getEnergyStored();
        int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));
        if (!simulate)
            setEnergy(getEnergyStored() - energyExtracted);
        return energyExtracted;
    }

    @Override
    public int getEnergyStored() {
      return stack.getOrCreateTag().getInt("Energy");
    }

    private void setEnergy (int amount) {
      stack.getOrCreateTag().putInt("Energy", amount);
    }
  }
}