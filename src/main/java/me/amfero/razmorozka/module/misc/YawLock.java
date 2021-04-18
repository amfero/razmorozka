package me.amfero.razmorozka.module.misc;

import me.amfero.razmorozka.module.Category;
import me.amfero.razmorozka.module.Module;
import me.amfero.razmorozka.setting.Setting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class YawLock extends Module {

	private final Setting slice = new Setting("Slice", this, 8, 0, 8);
	
	public YawLock(String name, String description, Category category) {
		super(name, description, category);
		addSetting(slice);
	}
	
	@SubscribeEvent
	public void onTick(TickEvent.ClientTickEvent event) {
		if(mc.world == null) return;
		if(slice.getIntegerValue() == 0) return;
		int angle = 360 / slice.getIntegerValue();
        float yaw = mc.player.rotationYaw;
        mc.player.rotationYaw = yaw = (float)(Math.round(yaw / (float)angle) * angle);
        if (mc.player.isRiding()) {
            mc.player.getRidingEntity().rotationYaw = yaw;
        }
	}
}
