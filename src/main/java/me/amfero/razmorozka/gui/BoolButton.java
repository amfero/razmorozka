package me.amfero.razmorozka.gui;



import java.awt.Color;

import me.amfero.razmorozka.Razmorozka;
import me.amfero.razmorozka.module.Module;
import me.amfero.razmorozka.setting.Setting;
import me.amfero.razmorozka.util.RenderUtil;
import me.amfero.razmorozka.util.font.FontUtil;
import net.minecraft.client.gui.Gui;

public class BoolButton extends SettingButton
{
	private final Setting setting;

	public BoolButton(Module module, Setting setting, int X, int Y, int W, int H)
	{
		super(module, X, Y, W, H);
		this.setting = setting;
	}

	@Override
	public void render(int mX, int mY)
	{
		Gui.drawRect(getX(), getY(), getX() + 105, getY() + 15, new Color(26,19,66,255).getRGB());
		if (setting.getBooleanValue())
		{
			Razmorozka.clickGUI.drawRect(getX() + 88, getY() + 2, getX() + 89, getY() + 15 - 2, new Color(124,11,75, 255).getRGB());
			Razmorozka.clickGUI.drawRect(getX() + 99, getY() + 2, getX() + 100, getY() + 15 - 2, new Color(230,5,89, 255).getRGB());
			RenderUtil.drawGradientSideways(getX() + 88, getY() + 1, getX() + 100, getY() + 2, new Color(124,11,75,255).getRGB(), new Color(194,8,85,255).getRGB());
			RenderUtil.drawGradientSideways(getX() + 88, getY() + 15 - 3, getX() + 100, getY() + 15 - 2, new Color(124,11,75,255).getRGB(), new Color(194,8,85,255).getRGB());
			RenderUtil.drawGradientSideways(getX() + 90, getY() + 3, getX() + 98, getY() + 15 - 4, new Color(124,11,75,255).getRGB(), new Color(194,8,85,255).getRGB());
		}
		else
		{
			Razmorozka.clickGUI.drawRect(getX() + 88, getY() + 2, getX() + 89, getY() + 15 - 2, new Color(55,48,89, 255).getRGB());
			Razmorozka.clickGUI.drawRect(getX() + 99, getY() + 2, getX() + 100, getY() + 15 - 2, new Color(54,47,87, 255).getRGB());
			RenderUtil.drawGradientSideways(getX() + 88, getY() + 1, getX() + 100, getY() + 2, new Color(55,48,89,255).getRGB(), new Color(54,47,87,255).getRGB());
			RenderUtil.drawGradientSideways(getX() + 88, getY() + 15 - 3, getX() + 100, getY() + 15 - 2, new Color(55,48,89,255).getRGB(), new Color(54,47,87,255).getRGB());
			RenderUtil.drawGradientSideways(getX() + 90, getY() + 3, getX() + 98, getY() + 15 - 4, new Color(25,17,62,255).getRGB(), new Color(25,17,62	,255).getRGB());
		}
		
		FontUtil.drawStringWithShadow(setting.getName(), (float) (getX() + 3), (float) (getY() + 4), new Color(175, 175, 175, 255).getRGB());
		
		Gui.drawRect(getX(), getY(), getX() + 1, getY() + 15, new Color(64,41,213, 255).getRGB());
		Gui.drawRect(getX() + 104, getY(), getX() + 105, getY() + 15, new Color(124,9,77, 255).getRGB());
	}

	@Override
	public void mouseDown(int mX, int mY, int mB)
	{
		if (isHover(getX(), getY(), getW(), getH() - 1, mX, mY) && (mB == 0 || mB == 1))
		{
			setting.setBooleanValue(!setting.getBooleanValue());
		}
	}
}
