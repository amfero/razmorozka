package me.amfero.razmorozka.command;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.amfero.razmorozka.Razmorozka;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;

public class Command
{
	private String name;
	private String[] alias;
	private String usage;

	public Command(String name, String[] alias, String usage)
	{
		setName(name);
		setAlias(alias);
		setUsage(usage);
	}

	public void onTrigger(String arguments) {}

	public void printUsage()
	{
		Minecraft.getMinecraft().player.sendMessage(new TextComponentString(ChatFormatting.GOLD +  "Usage: " + Razmorozka.commandManager.getPrefix() + usage));
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String[] getAlias()
	{
		return alias;
	}

	public void setAlias(String[] alias)
	{
		this.alias = alias;
	}

	public String getUsage()
	{
		return usage;
	}

	public void setUsage(String usage)
	{
		this.usage = usage;
	}
}
