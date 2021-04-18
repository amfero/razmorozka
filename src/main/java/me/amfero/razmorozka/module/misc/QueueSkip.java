package me.amfero.razmorozka.module.misc;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.amfero.razmorozka.module.Category;
import me.amfero.razmorozka.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.util.text.TextComponentString;

public class QueueSkip extends Module {

	public QueueSkip(String name, String description, Category category) {
		super(name, description, category);
	}
	
	public void onEnable() {
		
		if (mc.getCurrentServerData() == null || (mc.getCurrentServerData() != null && !mc.getCurrentServerData().serverIP.equals("2b2t.org")))  {
			Minecraft.getMinecraft().player.sendMessage(new TextComponentString(ChatFormatting.RED +  "Error"));
			disable();
			return;
		}
		Minecraft.getMinecraft().player.sendMessage(new TextComponentString(ChatFormatting.GOLD +  "Connecting to the server..."));
		Minecraft.getMinecraft().displayGuiScreen(new GuiConnecting(null, mc, "play.oldfag.org", 25565));
		disable();
	}
}

//Minecraft.getMinecraft().player.sendMessage(new TextComponentString(ChatFormatting.GOLD +  "Position in queue: " + ChatFormatting.BOLD + "411"));
//Minecraft.getMinecraft().player.sendMessage(new TextComponentString(ChatFormatting.GOLD +  "Position in queue: " + ChatFormatting.BOLD + "266"));
//Minecraft.getMinecraft().player.sendMessage(new TextComponentString(ChatFormatting.GOLD +  "Position in queue: " + ChatFormatting.BOLD + "194"));
//Minecraft.getMinecraft().player.sendMessage(new TextComponentString(ChatFormatting.GOLD +  "Position in queue: " + ChatFormatting.BOLD + "90"));
//Minecraft.getMinecraft().player.sendMessage(new TextComponentString(ChatFormatting.GOLD +  "Position in queue: " + ChatFormatting.BOLD + "45"));
//Minecraft.getMinecraft().player.sendMessage(new TextComponentString(ChatFormatting.GOLD +  "Position in queue: " + ChatFormatting.BOLD + "22"));
//Minecraft.getMinecraft().player.sendMessage(new TextComponentString(ChatFormatting.GOLD +  "Position in queue: " + ChatFormatting.BOLD + "14"));
//Minecraft.getMinecraft().player.sendMessage(new TextComponentString(ChatFormatting.GOLD +  "Position in queue: " + ChatFormatting.BOLD + "1"));