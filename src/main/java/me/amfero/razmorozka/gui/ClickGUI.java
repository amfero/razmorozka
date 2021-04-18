package me.amfero.razmorozka.gui;

import java.awt.Color;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import me.amfero.razmorozka.Razmorozka;
import me.amfero.razmorozka.module.Category;
import me.amfero.razmorozka.module.Module;
import me.amfero.razmorozka.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class ClickGUI extends GuiScreen
{
	private final ArrayList<Window> windows = new ArrayList<>();
	
	public ClickGUI()
	{
		int xOffset = 3;
		
		for (Category category : Category.values())
		{
			Window window = new Window(category, xOffset, 3, 105, 15);
			windows.add(window);
			xOffset += 110;
		}
	}
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{	
		
		doScroll();

		for (Window window : windows)
		{
			window.render(mouseX, mouseY);
		}
		if (OpenGlHelper.shadersSupported && mc.getRenderViewEntity() instanceof EntityPlayer) {
			RenderUtil.drawGradientSideways(0, 0, 1000, 1000, new Color(64,41,213,35).getRGB(), new Color(124,9,77,35).getRGB());
	           if (mc.entityRenderer.getShaderGroup() != null) {
	               mc.entityRenderer.getShaderGroup().deleteShaderGroup();
	           }
	           mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/blur.json"));
	       }
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton)
	{
		for (Window window : windows)
		{
			window.mouseDown(mouseX, mouseY, mouseButton);
		}
	}

	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state)
	{
		for (Window window : windows)
		{
			window.mouseUp(mouseX, mouseY);
		}
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode)
	{
		for (Window window : windows)
		{
			window.keyPress(keyCode);
		}

		if (keyCode == Keyboard.KEY_ESCAPE)
		{
			mc.displayGuiScreen(null);

			if (mc.currentScreen == null)
			{
				mc.setIngameFocus();
			}
		}
	}

	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}

	public void drawGradient(int left, int top, int right, int bottom, int startColor, int endColor)
	{
		drawGradientRect(left, top, right, bottom, startColor, endColor);
	}

	@Override
	public void onGuiClosed()
	{
		for (Window window : windows)
		{
			window.close();
		}

		Razmorozka.moduleManager.getModule("ClickGUI").disable();
		
		if (mc.entityRenderer.getShaderGroup() != null) {
	           mc.entityRenderer.getShaderGroup().deleteShaderGroup();
		}
	}

	private void doScroll()
	{
		int w = Mouse.getDWheel();
		if (w < 0)
		{
			for (Window window : windows)
			{
				window.setY(window.getY() - 8);
			}
		}
		else if (w > 0)
		{
			for (Window window : windows)
			{
				window.setY(window.getY() + 8);
			}
		}
	}
}