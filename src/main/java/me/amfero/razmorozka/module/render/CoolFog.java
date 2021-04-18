package me.amfero.razmorozka.module.render;

import java.awt.Color;

import me.amfero.razmorozka.module.Category;
import me.amfero.razmorozka.module.Module;
import me.amfero.razmorozka.setting.Setting;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CoolFog extends Module {

	private final Setting red = new Setting("Red", this, 120, 0, 255);
	private final Setting green = new Setting("Green", this, 120, 0, 255);
	private final Setting blue = new Setting("Blue", this, 210, 0, 255);
	private final Setting density = new Setting("Density", this, 5, 0, 10);
	Setting rainbow = new Setting("Rainbow", this, false);
	
	public CoolFog(String name, String description, Category category) {
		super(name, description, category);
			addSetting(red);
			addSetting(green);
			addSetting(blue);
			addSetting(rainbow);
			addSetting(density);
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
    public void onFogColorRender(final EntityViewRenderEvent.FogColors event) {
        final float[] hue = { System.currentTimeMillis() % 11520L / 11520.0f };
        final int rgb = Color.HSBtoRGB(hue[0], 1.0f, 1.0f);
        final int r = rgb >> 16 & 0xFF;
        final int g = rgb >> 8 & 0xFF;
        final int b = rgb & 0xFF;
        if (this.rainbow.getBooleanValue()) {
            event.setRed(r / 255.0f);
            event.setGreen(g / 255.0f);
            event.setBlue(b / 255.0f);
        }
        else {
            event.setRed(this.red.getIntegerValue() / 255.0f);
            event.setGreen(this.green.getIntegerValue() / 255.0f);
            event.setBlue(this.blue.getIntegerValue() / 255.0f);
        }
    }
	
	@SubscribeEvent
    public void fog(final EntityViewRenderEvent.FogDensity event) {
        event.setDensity(density.getIntegerValue() / 100f);
        event.setCanceled(true);
    }
	
}
