package noobanidus.mods.mysticalmachinery.client.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import noobanidus.libs.noobutil.client.CycleTimer;
import noobanidus.mods.mysticalmachinery.MMTags;
import noobanidus.mods.mysticalmachinery.MysticalMachinery;
import noobanidus.mods.mysticalmachinery.container.CharcoalKilnContainer;

import java.util.Objects;

@SuppressWarnings("Duplicates")
@OnlyIn(Dist.CLIENT)
public class CharcoalKilnScreen extends ContainerScreen<CharcoalKilnContainer> {
  private static final ResourceLocation FURNACE_GUI_TEXTURES = new ResourceLocation(MysticalMachinery.MODID, "textures/gui/charcoal_kiln.png");

  private CycleTimer timer;

  public CharcoalKilnScreen(CharcoalKilnContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
    super(screenContainer, inv, titleIn);
    this.timer = new CycleTimer(-1);
  }

  @Override
  public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
    this.timer.onDraw();
    this.renderBackground(matrixStack);
    super.render(matrixStack, mouseX, mouseY, partialTicks);
    this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
  }

  @Override
  protected void drawGuiContainerBackgroundLayer(MatrixStack m, float partialTicks, int mouseX, int mouseY) {
    RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
    Objects.requireNonNull(this.minecraft).getTextureManager().bindTexture(FURNACE_GUI_TEXTURES);
    int i = this.guiLeft;
    int j = this.guiTop;
    this.blit(m, i, j, 0, 0, this.xSize, this.ySize);
    int l = this.container.getCookProgressionScaled();
    this.blit(m, i + 79, j + 34, 176, 14, l + 1, 16);
    if (this.container.isBlocked()) {
      this.blit(m, i + 77, j + 32, 176, 46, 28, 21);
    }
    if (this.container.isBurning()) {
      this.blit(m, i + 56, j + 12 + 36 + 12 - 200, 176, 12 - 200, 14, 200 + 1);
    } else {
      this.blit(m, i + 56, j + 46, 176, 31, 15, 15);
      Item item = timer.getCycledItem(MMTags.Items.FIRELIGHTERS.getAllElements());
      if (item != null) {
        ItemRenderer render = this.minecraft.getItemRenderer();
        //RenderHelper.enableGUIStandardItemLighting();
        render.renderItemIntoGUI(new ItemStack(item), i + 38, j + 46);
        //RenderHelper.disableStandardItemLighting();
      }
    }
  }
}
