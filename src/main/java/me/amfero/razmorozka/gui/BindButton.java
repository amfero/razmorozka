package me.amfero.razmorozka.gui;

import java.awt.Color;

import org.lwjgl.input.Keyboard;

import me.amfero.razmorozka.Razmorozka;
import me.amfero.razmorozka.module.Module;
import me.amfero.razmorozka.util.RenderUtil;
import me.amfero.razmorozka.util.font.FontUtil;
import net.minecraft.client.gui.Gui;

public class BindButton extends SettingButton
{
	private final Module module;
	private boolean binding;
	
	public BindButton(Module module, int x, int y, int w, int h)
	{
		super(module, x, y, w, h);
		this.module = module;
	}
	
	public void render(int mX, int mY)
	{		
		Gui.drawRect(getX(), getY(), getX() + 105, getY() + 15, new Color(26,19,66,255).getRGB());
		FontUtil.drawStringWithShadow("Bind", (float) (getX() + 3), (float) (getY() + 4), new Color(255, 255, 255, 255).getRGB());
		if (binding)
		{
			FontUtil.drawStringWithShadow("...", (float) ((getX() + getW() - 6) - FontUtil.getStringWidth("...")), (float) (getY() + 4), new Color(255, 255, 255, 255).getRGB());
		}
		else
		{
			try
			{
				FontUtil.drawStringWithShadow(Keyboard.getKeyName(module.getBind()), (float) ((getX() + getW()) - FontUtil.getStringWidth(Keyboard.getKeyName(module.getBind()))), (float) (getY() + 4), new Color(255, 255, 255, 255).getRGB());
			}
			catch (Exception e)
			{
				FontUtil.drawStringWithShadow("NONE", (float) ((getX() + getW()) - FontUtil.getStringWidth("NONE")), (float) (getY() + 4), new Color(255, 255, 255, 255).getRGB());
			}
		}	
		Gui.drawRect(getX(), getY(), getX() + 1, getY() + 15, new Color(64,41,213, 255).getRGB());
		Gui.drawRect(getX() + 104, getY(), getX() + 105, getY() + 15, new Color(124,9,77, 255).getRGB());
		RenderUtil.drawGradientSideways(getX(), getY() + 15, getX() + 105, getY() + 16, new Color(64,41,213,255).getRGB(), new Color(124,9,77, 255).getRGB());
	}
	
	public void mouseDown(int mX, int mY, int mB)
	{
		if (isHover(getX(), getY(), getW(), getH() - 1, mX, mY))
		{
			binding = !binding;
		}
	}
	
	public void keyPress(int key)
	{	
		if (binding)
		{
			if (key == Keyboard.KEY_DELETE || key == Keyboard.KEY_ESCAPE || key == Keyboard.KEY_BACK || key == Keyboard.KEY_NONE)
			{
				getModule().setBind(Keyboard.KEYBOARD_SIZE);
			}
			else
			{
				getModule().setBind(key);
			}
			binding = false;
		}	
	}
}
