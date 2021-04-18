package me.amfero.razmorozka.module.render;

import me.amfero.razmorozka.module.Category;
import me.amfero.razmorozka.module.Module;
import me.amfero.razmorozka.setting.Setting;
import me.amfero.razmorozka.util.events.PacketReceiveEvent;
import net.minecraft.network.play.server.SPacketTimeUpdate;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class NightMode extends Module {
	
	private final Setting Time = new Setting("Time", this, 18000, 0, 23000);
	
	public NightMode(String name, String description, Category category)
	{
		super(name, description, category);
		addSetting(Time);
	}
	
	@SubscribeEvent
    public void onTick(final TickEvent.ClientTickEvent event) {
		if(mc.world == null) {
			return;
		}
        mc.world.setWorldTime(Time.getIntegerValue());
	}
	
	@SubscribeEvent
    public void listener(final PacketReceiveEvent event) {
		if(event.getPacket() instanceof SPacketTimeUpdate) {
			event.setCanceled(true);
		}
	}
}
