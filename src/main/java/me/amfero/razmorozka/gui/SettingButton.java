package me.amfero.razmorozka.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import me.amfero.razmorozka.Razmorozka;
import me.amfero.razmorozka.module.Module;
import net.minecraft.client.Minecraft;

public class SettingButton
{	
	public final Minecraft mc = Minecraft.getMinecraft();
	private final int H;
	private Module module;
	private int X;
	private int Y;
	private int W;

	public SettingButton(Module module, int x, int y, int w, int h)
	{
		this.module = module;
		X = x;
		Y = y;
		W = w;
		H = h;
	}

	public void render(int mX, int mY)
	{
	}

	public void mouseDown(int mX, int mY, int mB)
	{
	}

	public void mouseUp(int mX, int mY)
	{
	}

	public void keyPress(int key)
	{
	}

	public void close()
	{
	}

	public void drawButton(int mX, int mY)
	{
		if (module.isEnabled()) {
			Razmorozka.clickGUI.drawGradient(X, Y, X + W, Y + H, new Color(150, 150, 250, 225).getRGB(), new Color(150, 150, 250, 225).getRGB());
			Razmorozka.clickGUI.drawGradient(X, Y, X + 2, Y + H, new Color(120, 120, 210, 225).getRGB(), new Color(120, 120, 210, 225).getRGB());
		} else {
			Razmorozka.clickGUI.drawGradient(X, Y, X + W, Y + H, new Color(70, 70, 70, 225).getRGB(), new Color(70, 70, 70, 225).getRGB());
			Razmorozka.clickGUI.drawGradient(X, Y, X + 2, Y + H, new Color(120, 120, 210, 225).getRGB(), new Color(120, 120, 210, 225).getRGB());
		}
	}

	public Module getModule()
	{
		return module;
	}

	public void setModule(Module module)
	{
		this.module = module;
	}

	public int getX()
	{
		return X;
	}

	public void setX(int x)
	{
		X = x;
	}

	public int getY()
	{
		return Y;
	}

	public void setY(int y)
	{
		Y = y;
	}

	public int getW()
	{
		return W;
	}

	public int getH()
	{
		return H;
	}


	public boolean isHover(int X, int Y, int W, int H, int mX, int mY)
	{
		return mX >= X && mX <= X + W && mY >= Y && mY <= Y + H;
	}
}
