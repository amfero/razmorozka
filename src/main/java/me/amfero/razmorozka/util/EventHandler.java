package me.amfero.razmorozka.util;

import org.lwjgl.input.Keyboard;

import me.amfero.razmorozka.Razmorozka;
import me.amfero.razmorozka.module.Module;
import me.amfero.razmorozka.util.events.Render2DEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class EventHandler
{
	@SubscribeEvent
	public void onKeyInput(InputEvent.KeyInputEvent event)
	{
		if (!Keyboard.getEventKeyState()) return;

		for (Module module : Razmorozka.moduleManager.getModules())
		{
			if (module.getBind() == Keyboard.getEventKey())
			{
				module.toggle();
			}
		}
	}
	
	@SubscribeEvent
	public void onChatSend(ClientChatEvent event)
	{
		if (Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().world == null) return;

		if (event.getMessage().startsWith(Razmorozka.commandManager.getPrefix()))
		{
			event.setCanceled(true);
			Minecraft.getMinecraft().ingameGUI.getChatGUI().addToSentMessages(event.getMessage());
			Razmorozka.commandManager.runCommand(event.getMessage());
		}
	}
	
}

