package me.amfero.razmorozka.module.movement;

import me.amfero.razmorozka.module.Category;
import me.amfero.razmorozka.module.Module;
import me.amfero.razmorozka.setting.Setting;
import me.amfero.razmorozka.util.events.WalkEvent;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayer.Rotation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SlowWalk extends Module {

	Setting sneakOnly = new Setting("SneakOnly", this, true);
	
	public SlowWalk(String name, String description, Category category) {
		super(name, description, category);
		addSetting(sneakOnly);
	}
	
	@SubscribeEvent
    public void onUpdateWalkingPlayer(WalkEvent event) {
		if(mc.world == null) return; 
		if(sneakOnly.getBooleanValue()) {
			if(mc.player.isSneaking()) {
				mc.player.motionX = 0.001;
				mc.player.motionZ = 0.001;	
			}
			
		} else {
			mc.player.motionX = 0.001;
			mc.player.motionZ = 0.001;	
		}
	}
}

