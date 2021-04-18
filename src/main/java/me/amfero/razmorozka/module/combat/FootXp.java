package me.amfero.razmorozka.module.combat;

import me.amfero.razmorozka.mixin.mixins.accessor.ICPacketPlayer;
import me.amfero.razmorozka.module.Category;
import me.amfero.razmorozka.module.Module;
import me.amfero.razmorozka.util.events.PacketSendEvent;
import net.minecraft.item.ItemExpBottle;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FootXp extends Module {

	public FootXp(String name, String description, Category category) {
		super(name, description, category);
	}

	@SubscribeEvent
	public void onPacketSend(final PacketSendEvent event) {
		if(mc.world == null) return;
		if (event.getPacket() instanceof CPacketPlayer && mc.player.getHeldItemMainhand().getItem() instanceof ItemExpBottle) {
			CPacketPlayer packet = (CPacketPlayer) event.getPacket();
			((ICPacketPlayer) packet).setPitch(90.0F);
		}
	}
	
}
