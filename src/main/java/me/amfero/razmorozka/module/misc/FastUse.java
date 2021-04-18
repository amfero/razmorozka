package me.amfero.razmorozka.module.misc;

import me.amfero.razmorozka.mixin.mixins.accessor.IMinecraft;
import me.amfero.razmorozka.module.Category;
import me.amfero.razmorozka.module.Module;
import me.amfero.razmorozka.setting.Setting;
import net.minecraft.init.Items;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;


public class FastUse extends Module {

	Setting all = new Setting("All", this, false);
	Setting bow = new Setting("Bow", this, true);
	Setting exp = new Setting("Exp", this, true);
	Setting endCrystals = new Setting("End Crystals", this, true);
	Setting fireworks = new Setting("Fireworks", this, true);
	
	public FastUse(String name, String description, Category category) {
		super(name, description, category);
		addSetting(all);
		addSetting(exp);
		addSetting(endCrystals);
		addSetting(fireworks);
	}

	@SubscribeEvent
	public void onTick(final TickEvent.ClientTickEvent event) {
		if(mc.world == null) return;
		if(all.getBooleanValue()) {
			((IMinecraft) mc).setRightClickDelayTimer(0);
		}
		if(exp.getBooleanValue() && mc.player.getHeldItemMainhand().getItem() == Items.EXPERIENCE_BOTTLE || exp.getBooleanValue() && mc.player.getHeldItemOffhand().getItem() == Items.EXPERIENCE_BOTTLE) {
			((IMinecraft) mc).setRightClickDelayTimer(0);
		}
		if(endCrystals.getBooleanValue() && mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL || endCrystals.getBooleanValue() && mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) {
			((IMinecraft) mc).setRightClickDelayTimer(0);
		}
		if(fireworks.getBooleanValue() && mc.player.getHeldItemMainhand().getItem() == Items.FIREWORKS || fireworks.getBooleanValue() && mc.player.getHeldItemOffhand().getItem() == Items.FIREWORKS) {
			((IMinecraft) mc).setRightClickDelayTimer(0);
		}
	}
}
