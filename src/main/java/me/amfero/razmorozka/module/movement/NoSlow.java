package me.amfero.razmorozka.module.movement;

import me.amfero.razmorozka.module.Category;
import me.amfero.razmorozka.module.Module;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class NoSlow extends Module {

	public NoSlow(String name, String description, Category category) {
		super(name, description, category);
	}

	@SubscribeEvent
	public void onInput(InputUpdateEvent event) {
		if (mc.player.isHandActive() && !mc.player.isRiding()) {
            event.getMovementInput().moveStrafe *= 5;
            event.getMovementInput().moveForward *= 5;
        }
	}
	
}
