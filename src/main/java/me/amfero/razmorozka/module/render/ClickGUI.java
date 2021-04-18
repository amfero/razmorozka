package me.amfero.razmorozka.module.render;


import org.lwjgl.input.Keyboard;
import me.amfero.razmorozka.Razmorozka;
import me.amfero.razmorozka.module.Category;
import me.amfero.razmorozka.module.Module;
import me.amfero.razmorozka.setting.Setting;
import net.minecraft.client.Minecraft;

public class ClickGUI extends Module
{
	
	public ClickGUI(String name, String description, Category category)
	{
		super(name, description, category);
		setBind(Keyboard.KEY_RSHIFT);
	}

	@Override
	public void onEnable()
	{	
		mc.displayGuiScreen(Razmorozka.clickGUI);
	}
}