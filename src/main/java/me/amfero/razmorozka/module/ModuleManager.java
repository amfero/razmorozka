package me.amfero.razmorozka.module;

import java.util.ArrayList;
import java.util.stream.Collectors;

import me.amfero.razmorozka.module.combat.AntiEndCrystal;
import me.amfero.razmorozka.module.combat.AutoArmor;
import me.amfero.razmorozka.module.combat.AutoTotem;
import me.amfero.razmorozka.module.combat.Criticals;
import me.amfero.razmorozka.module.combat.FootXp;
import me.amfero.razmorozka.module.combat.KillAura;
import me.amfero.razmorozka.module.exploit.EchestBP;
import me.amfero.razmorozka.module.exploit.HitBox;
import me.amfero.razmorozka.module.exploit.InstantBurrow;
import me.amfero.razmorozka.module.exploit.RockezFly;
import me.amfero.razmorozka.module.misc.BigModule;
import me.amfero.razmorozka.module.misc.DiscordRPC;
import me.amfero.razmorozka.module.misc.FakePlayer;
import me.amfero.razmorozka.module.misc.FastUse;
import me.amfero.razmorozka.module.misc.MiddleClickPearl;
import me.amfero.razmorozka.module.misc.PacketMine;
import me.amfero.razmorozka.module.misc.QueueSkip;
import me.amfero.razmorozka.module.misc.Refill;
import me.amfero.razmorozka.module.misc.VisualRange;
import me.amfero.razmorozka.module.misc.YawLock;
import me.amfero.razmorozka.module.movement.Anchor;
import me.amfero.razmorozka.module.movement.AutoWalk;
import me.amfero.razmorozka.module.movement.NoSlow;
import me.amfero.razmorozka.module.movement.ReverseStep;
import me.amfero.razmorozka.module.movement.SlowWalk;
import me.amfero.razmorozka.module.movement.Sprint;
import me.amfero.razmorozka.module.movement.Step;
import me.amfero.razmorozka.module.movement.Velocity;
import me.amfero.razmorozka.module.movement.godNoFall;
import me.amfero.razmorozka.module.render.Chams;
import me.amfero.razmorozka.module.render.ClickGUI;
import me.amfero.razmorozka.module.render.CoolFog;
import me.amfero.razmorozka.module.render.ExtraTab;
import me.amfero.razmorozka.module.render.FullBright;
import me.amfero.razmorozka.module.render.Hud;
import me.amfero.razmorozka.module.render.ItemViewmodle;
import me.amfero.razmorozka.module.render.NightMode;
import me.amfero.razmorozka.module.render.PlayerList;
import me.amfero.razmorozka.module.render.ShulkerPreview;

public class ModuleManager
{
	private final ArrayList<Module> modules = new ArrayList<>();

	public ModuleManager()
	{
		// Combat
		modules.add(new AntiEndCrystal("AntiEndCrystal", "", Category.COMBAT));
		modules.add(new AutoArmor("AutoArmor", "", Category.COMBAT));
		modules.add(new AutoTotem("AutoTotem", "", Category.COMBAT));
		modules.add(new Criticals("Criticals", "", Category.COMBAT));
		modules.add(new FootXp("FootXp", "", Category.COMBAT));
		modules.add(new KillAura("KillAura", "", Category.COMBAT));
		// Exploit
		modules.add(new InstantBurrow("Burrow", "", Category.EXPLOIT));
		modules.add(new HitBox("HitBox", "", Category.EXPLOIT));
		modules.add(new EchestBP("EchestBP", "", Category.EXPLOIT));
		modules.add(new RockezFly("RockezFly", "", Category.EXPLOIT));
		// Misc
		modules.add(new BigModule("BigModule", "", Category.MISC));
		modules.add(new DiscordRPC("DiscordRPC", "", Category.MISC));
		modules.add(new FakePlayer("FakePlayer", "", Category.MISC));
		modules.add(new FastUse("FastUse", "", Category.MISC));
		modules.add(new MiddleClickPearl("MiddleClickPearl", "", Category.MISC));
		modules.add(new YawLock("YawLock", "", Category.MISC));
		modules.add(new PacketMine("PacketMine", "", Category.MISC));
		modules.add(new QueueSkip("QueueSkip", "", Category.MISC));
		modules.add(new Refill("Refill", "", Category.MISC));
		modules.add(new VisualRange("VisualRange", "", Category.MISC));
		// Movement
		modules.add(new Anchor("Anchor", "", Category.MOVEMENT));
		modules.add(new AutoWalk("AutoWalk", "", Category.MOVEMENT));
		modules.add(new godNoFall("GodNoFall", "", Category.MOVEMENT));
		modules.add(new SlowWalk("SlowWalk", "", Category.MOVEMENT));
		modules.add(new NoSlow("NoSlow", "", Category.MOVEMENT));
		modules.add(new ReverseStep("ReverseStep", "", Category.MOVEMENT));
		modules.add(new Sprint("Sprint", "", Category.MOVEMENT));
		modules.add(new Step("Step", "", Category.MOVEMENT));
		modules.add(new Velocity("Velocity", "", Category.MOVEMENT));
		// Render
		modules.add(new Chams("Chams", "", Category.RENDER));
		modules.add(new ClickGUI("ClickGUI", "", Category.RENDER));
		modules.add(new CoolFog("Cool Sky", "", Category.RENDER));
		modules.add(new ExtraTab("ExtraTab", "", Category.RENDER));
		modules.add(new FullBright("FullBright", "", Category.RENDER));
		modules.add(new Hud("Hud", "", Category.RENDER));
		modules.add(new ItemViewmodle("ItemViewmodle", "", Category.RENDER));
		modules.add(new NightMode("NightMode", "", Category.RENDER));
		modules.add(new PlayerList("Player list", "", Category.RENDER));
		modules.add(new ShulkerPreview("ShulkerPreview", "", Category.RENDER));	
	}

	public ArrayList<Module> getModules()
	{
		return modules;
	}

	public Module getModule(String name)
	{
		for (Module module : modules)
		{
			if (module.getName().equalsIgnoreCase(name)) return module;
		}

		return null;
	}

	public ArrayList<Module> getModules(Category category)
	{
		ArrayList<Module> mods = new ArrayList<>();

		for (Module module : modules)
		{
			if (module.getCategory().equals(category)) mods.add(module);
		}

		return mods;
	}

	public ArrayList<Module> getEnabledModules()
	{
		return modules.stream().filter(Module::isEnabled).collect(Collectors.toCollection(ArrayList::new));
	}
}
