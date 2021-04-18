package me.amfero.razmorozka.module.movement;

import me.amfero.razmorozka.Razmorozka;
import me.amfero.razmorozka.module.Category;
import me.amfero.razmorozka.module.Module;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class AutoWalk extends Module
{
	public AutoWalk(String name, String description, Category category) {
        super(name, description, category);
    }

    @SubscribeEvent
    public void onUpdate(final TickEvent.ClientTickEvent event) {
    	if(mc.world == null) {
    		Razmorozka.moduleManager.getModule("AutoWalk").disable();
    		return;
    	}
    	
        KeyBinding.setKeyBindState(mc.gameSettings.keyBindForward.getKeyCode(), true);
    }
    
    public void onDisable() {
    	KeyBinding.setKeyBindState(mc.gameSettings.keyBindForward.getKeyCode(), false);
    }
}
