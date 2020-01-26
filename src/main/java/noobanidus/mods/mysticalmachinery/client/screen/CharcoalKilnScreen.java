package noobanidus.mods.mysticalmachinery.client.screen;

import com.mojang.blaze3d.platform.GlStateManager;
import epicsquid.mysticallib.client.CycleTimer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
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

  public void render(int p1, int p2, float p3) {
    this.timer.onDraw();
    this.renderBackground();
    this.drawGuiContainerBackgroundLayer(p3, p1, p2);
    super.render(p1, p2, p3);
    this.renderHoveredToolTip(p1, p2);
  }

  protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
    String s = this.title.getFormattedText();
    this.font.drawString(s, (float) (this.xSize / 2 - this.font.getStringWidth(s) / 2), 6.0F, 4210752);
    this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0F, (float) (this.ySize - 96 + 2), 4210752);
  }

  protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
    GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
    Objects.requireNonNull(this.minecraft).getTextureManager().bindTexture(FURNACE_GUI_TEXTURES);
    int i = this.guiLeft;
    int j = this.guiTop;
    this.blit(i, j, 0, 0, this.xSize, this.ySize);
    int l = this.container.getCookProgressionScaled();
    this.blit(i + 79, j + 34, 176, 14, l + 1, 16);
    if (this.container.isBurning()) {
      this.blit(i + 56, j + 12 + 36 + 12 - 200, 176, 12 - 200, 14, 200 + 1);
    } else {
      this.blit(i + 56, j + 46, 176, 31, 15, 15);
      Item item = timer.getCycledItem(MMTags.Items.FIRELIGHTERS.getAllElements());
      if (item != null) {
        ItemRenderer render = this.minecraft.getItemRenderer();
        RenderHelper.enableGUIStandardItemLighting();
        render.renderItemIntoGUI(new ItemStack(item), i + 38, j + 46);
        RenderHelper.disableStandardItemLighting();
      }
    }
  }

  public void removed() {
    super.removed();
  }
}
