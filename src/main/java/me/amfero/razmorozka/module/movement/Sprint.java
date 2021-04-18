package me.amfero.razmorozka.module.movement;

import me.amfero.razmorozka.module.Category;
import me.amfero.razmorozka.module.Module;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class Sprint extends Module {
    public Sprint(String name, String description, Category category) {
        super(name, description, category);
    }

    @SubscribeEvent
    public void onUpdate(final TickEvent.ClientTickEvent event) {
    	if(mc.world == null) {
			return;
		}

        if (mc.player.movementInput.moveForward == 0f && mc.player.movementInput.moveStrafe == 0f) return;

        if (!mc.player.isSprinting()) {
            mc.player.setSprinting(true);
        }
    }
}