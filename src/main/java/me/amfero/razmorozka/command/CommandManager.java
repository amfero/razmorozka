package me.amfero.razmorozka.command;

import java.util.ArrayList;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.amfero.razmorozka.command.commands.Login;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;

public class CommandManager
{
	private final ArrayList<Command> commands = new ArrayList<>();
	private String prefix = "%";

	public CommandManager()
	{
		commands.add(new Login("Login", new String[]{"login"}, "login <email> <password>"));
	}

	public void runCommand(String args)
	{
		boolean found = false;
		String[] split = args.split(" ");
		String startCommand = split[0];
		String arguments = args.substring(startCommand.length()).trim();

		for (Command command : getCommands())
		{
			for (String alias : command.getAlias())
			{
				if (startCommand.equals(getPrefix() + alias))
				{
					command.onTrigger(arguments);
					found = true;
				}
			}
		}

		if (!found)
		{
			Minecraft.getMinecraft().player.sendMessage(new TextComponentString(ChatFormatting.GOLD +  "Unknown command"));
		}
	}

	public ArrayList<Command> getCommands()
	{
		return commands;
	}

	public String getPrefix()
	{
		return prefix;
	}

	public void setPrefix(String prefix)
	{
		this.prefix = prefix;
	}
}
