package me.amfero.razmorozka.module.movement;

import me.amfero.razmorozka.module.Category;
import me.amfero.razmorozka.module.Module;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ReverseStep extends Module {

	public ReverseStep(String name, String description, Category category) {
		super(name, description, category);
	}

	@SubscribeEvent
	public void onUpdate(final TickEvent.ClientTickEvent event) {
		if(mc.world == null || mc.player.isInWater() || mc.player.isInLava()) return;
		if (mc.player.onGround) {
            mc.player.motionY -= 1.0;
        }
	}
	
}
