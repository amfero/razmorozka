package me.amfero.razmorozka.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.amfero.razmorozka.command.Command;
import me.amfero.razmorozka.util.SessionUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;

public class Login extends Command
{
	public Login(String name, String[] alias, String usage)
	{
		super(name, alias, usage);
	}

	@Override
	public void onTrigger(String arguments)
	{
		String[] split = arguments.split(" ");
		try
		{
			if (split[0].equals("") || split[1].equals(""))
			{
				printUsage();
				return;
			}
		}
		catch (Exception ignored)
		{
			printUsage();
			return;
		}

		if (SessionUtil.login(split[0], split[1]))
		{
			Minecraft.getMinecraft().player.sendMessage(new TextComponentString(ChatFormatting.DARK_GRAY + "[Razmorozka] " + ChatFormatting.GOLD +  "Logged in to " + SessionUtil.getSession().getUsername()));
		}
		else
		{
			Minecraft.getMinecraft().player.sendMessage(new TextComponentString(ChatFormatting.DARK_GRAY + "[Razmorozka] " + ChatFormatting.GOLD+  "Failed to login"));
		}
	}
}
