package me.amfero.razmorozka.module.render;

import me.amfero.razmorozka.module.Category;
import me.amfero.razmorozka.module.Module;
import me.amfero.razmorozka.setting.Setting;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ItemViewmodle extends Module {

	private final Setting fov = new Setting("Fov", this, 120, 90, 160);
	private final Setting armPitch = new Setting("Arm Pitch", this, 90, 0, 360);
	private final Setting armYaw = new Setting("Arm Yaw", this, 220, 0, 360);
	
	public ItemViewmodle(String name, String description, Category category) {
		super(name, description, category);
		addSetting(fov);
		addSetting(armPitch);
		//addSetting(armYaw);
	}

	@SubscribeEvent
    public void onTick(final TickEvent.ClientTickEvent event) {
		if(mc.world == null) {
			return;
		}
		mc.player.renderArmPitch = (float) armPitch.getIntegerValue();
        //mc.player.renderArmYaw = (float) armYaw.getIntegerValue();
	}
	
	@SubscribeEvent
    public void FOVEvent(EntityViewRenderEvent.FOVModifier event) {
        event.setFOV((float) fov.getIntegerValue());
    }
}