package me.amfero.razmorozka.gui;

import java.awt.Color;
import java.text.DecimalFormat;

import me.amfero.razmorozka.Razmorozka;
import me.amfero.razmorozka.module.Module;
import me.amfero.razmorozka.setting.Setting;
import me.amfero.razmorozka.util.RenderUtil;
import me.amfero.razmorozka.util.font.FontUtil;
import net.minecraft.client.gui.Gui;

public class SliderButton extends SettingButton
{
	private final Setting setting;
	protected boolean dragging;
	protected int sliderWidth;

	SliderButton(Module module, Setting setting, int X, int Y, int W, int H)
	{
		super(module, X, Y, W, H);
		this.dragging = false;
		this.sliderWidth = 0;
		this.setting = setting;
	}

	protected void updateSlider(int mouseX)
	{
	}
	
	@Override
	public void render(int mX, int mY)
	{
		updateSlider(mX);		
		Razmorozka.clickGUI.drawRect(getX(), getY(), getX() + getW(), getY() + getH(), new Color(26,19,66,255).getRGB());
		RenderUtil.drawGradientSideways(getX() + 2, getY(), getX() + (sliderWidth) + 4, getY() + getH(), new Color(124,11,75, 255).getRGB(), new Color(194,8,85, 255).getRGB());
		FontUtil.drawStringWithShadow(setting.getName(), (float) (getX() + 3), (float) (getY() + 4), new Color(255, 255, 255, 255).getRGB());
		FontUtil.drawStringWithShadow(String.valueOf(setting.getIntegerValue()), (float) ((getX() + getW()) - FontUtil.getStringWidth(String.valueOf(setting.getIntegerValue()))) - 2, (float) (getY() + 4), new Color(255, 255, 255, 255).getRGB());
		Gui.drawRect(getX(), getY(), getX() + 1, getY() + 15, new Color(64,41,213, 255).getRGB());
		Gui.drawRect(getX() + 104, getY(), getX() + 105, getY() + 15, new Color(124,9,77, 255).getRGB());
	}

	@Override
	public void mouseDown(int mX, int mY, int mB)
	{
		if (isHover(getX(), getY(), getW(), getH() - 1, mX, mY))
		{
			dragging = true;
		}
	}

	@Override
	public void mouseUp(int mouseX, int mouseY)
	{
		dragging = false;
	}

	@Override
	public void close()
	{
		dragging = false;
	}

	public static class IntSlider extends SliderButton
	{
		private final Setting intSetting;

		public IntSlider(Module module, Setting setting, int X, int Y, int W, int H)
		{
			super(module, setting, X, Y, W, H);
			intSetting = setting;
		}

		@Override
		protected void updateSlider(final int mouseX)
		{
			final double diff = Math.min(getW(), Math.max(0, mouseX - getX()));
			final double min = intSetting.getMinIntegerValue();
			final double max = intSetting.getMaxIntegerValue();
			sliderWidth = (int) ((getW() - 6) * (intSetting.getIntegerValue() - min) / (max - min));
			if (dragging)
			{
				if (diff == 0.0)
				{
					intSetting.setIntegerValue(intSetting.getMinIntegerValue());
				}
				else
				{
					final DecimalFormat format = new DecimalFormat("##");
					final String newValue = format.format(diff / getW() * (max - min) + min);
					intSetting.setIntegerValue(Integer.parseInt(newValue));
				}
			}
		}
	}
}