package me.amfero.razmorozka.module.render;

import me.amfero.razmorozka.module.Category;
import me.amfero.razmorozka.module.Module;

public class FullBright extends Module {
	
	float old;
	
	public FullBright(String name, String description, Category category)
	{
		super(name, description, category);
	}
	
	@Override
	public void onEnable() {
        old = mc.gameSettings.gammaSetting;
        mc.gameSettings.gammaSetting = 666f;
    }
	
    public void onDisable() {
        mc.gameSettings.gammaSetting = old;
    }

}
