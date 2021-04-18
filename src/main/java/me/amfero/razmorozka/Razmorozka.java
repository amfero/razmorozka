package me.amfero.razmorozka;

import java.awt.Font;

import org.lwjgl.opengl.Display;

import me.amfero.razmorozka.command.CommandManager;
import me.amfero.razmorozka.gui.ClickGUI;
import me.amfero.razmorozka.module.Module;
import me.amfero.razmorozka.module.ModuleManager;
import me.amfero.razmorozka.setting.SettingManager;
import me.amfero.razmorozka.util.Config;
import me.amfero.razmorozka.util.EventHandler;
import me.amfero.razmorozka.util.font.CustomFont;
import me.amfero.razmorozka.util.font.CustomFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = Razmorozka.MODID, name = Razmorozka.NAME, version = Razmorozka.VERSION)
public class Razmorozka {

	public static final String MODID = "razmorozka";
    public static final String NAME = "razmorozka";
    public static final String VERSION = "1.2.6";
    public static SettingManager settingManager;
    public static ModuleManager moduleManager;
    public static ClickGUI clickGUI;
    public static CustomFontRenderer customFontRenderer;
    public static CommandManager commandManager;

    @Mod.EventHandler
    public void initialize(FMLInitializationEvent event)
    {
    	settingManager = new SettingManager();
    	moduleManager = new ModuleManager();
    	commandManager = new CommandManager();
    	clickGUI = new ClickGUI();
    	customFontRenderer = new CustomFontRenderer(new Font("Arial", Font.PLAIN, 19), true, false);
    	Display.setTitle(NAME + " " + VERSION);
    	Config.loadConfig();
		Runtime.getRuntime().addShutdownHook(new Config());
        MinecraftForge.EVENT_BUS.register(new EventHandler());
    }
}