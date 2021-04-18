package me.amfero.razmorozka.module.misc;

import me.amfero.razmorozka.Discord;
import me.amfero.razmorozka.module.Category;
import me.amfero.razmorozka.module.Module;

public class DiscordRPC extends Module {

	public DiscordRPC(String name, String description, Category category) {
		super(name, description, category);
	}
	
	public void onEnable() {
		Discord.startRPC();
	}
	
	public void onDisable() {
		Discord.stopRPC();
	}

}
