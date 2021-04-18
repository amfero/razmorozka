package me.amfero.razmorozka.gui;

import java.awt.Color;

import me.amfero.razmorozka.Razmorozka;
import me.amfero.razmorozka.module.Module;
import me.amfero.razmorozka.setting.Setting;
import me.amfero.razmorozka.util.font.FontUtil;
import net.minecraft.client.gui.Gui;

public class EnumButton extends SettingButton
{
	private final Setting setting;

	public EnumButton(Module module, Setting setting, int X, int Y, int W, int H)
	{
		super(module, X, Y, W, H);
		this.setting = setting;
	}


	@Override
	public void render(int mX, int mY)
	{
		Razmorozka.clickGUI.drawGradient(getX(), getY(), getX() + 105, getY() + 15, new Color(26,19,66,255).getRGB(), new Color(26,19,66, 255).getRGB());
		FontUtil.drawStringWithShadow(setting.getName(), (float) (getX() + 3), (float) (getY() + 4), new Color(255, 255, 255, 255).getRGB());
		FontUtil.drawStringWithShadow(setting.getEnumValue(), (float) ((getX() + getW()) - FontUtil.getStringWidth(setting.getEnumValue())), (float) (getY() + 4), new Color(255, 255, 255, 255).getRGB());
		Gui.drawRect(getX(), getY(), getX() + 1, getY() + 15, new Color(64,41,213, 255).getRGB());
		Gui.drawRect(getX() + 104, getY(), getX() + 105, getY() + 15, new Color(124,9,77, 255).getRGB());
	}

	@Override
	public void mouseDown(int mX, int mY, int mB)
	{
		if (isHover(getX(), getY(), getW(), getH() - 1, mX, mY))
		{
			if (mB == 0)
			{
				int i = 0;
				int enumIndex = 0;
				for (String enumName : setting.getEnumValues())
				{
					if (enumName.equals(setting.getEnumValue())) enumIndex = i;
					i++;
				}
				if (enumIndex == setting.getEnumValues().size() - 1)
				{
					setting.setEnumValue(setting.getEnumValues().get(0));
				}
				else
				{
					enumIndex++;
					i = 0;
					for (String enumName : setting.getEnumValues())
					{
						if (i == enumIndex) setting.setEnumValue(enumName);
						i++;
					}
				}
			}
			else if (mB == 1)
			{
				int i = 0;
				int enumIndex = 0;
				for (String enumName : setting.getEnumValues())
				{
					if (enumName.equals(setting.getEnumValue())) enumIndex = i;
					i++;
				}
				if (enumIndex == 0)
				{
					setting.setEnumValue(setting.getEnumValues().get(setting.getEnumValues().size() - 1));
				}
				else
				{
					enumIndex--;
					i = 0;
					for (String enumName : setting.getEnumValues())
					{
						if (i == enumIndex) setting.setEnumValue(enumName);
						i++;
					}
				}
			}
		}
	}
}
