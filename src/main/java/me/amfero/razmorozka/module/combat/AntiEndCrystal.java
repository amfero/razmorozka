package me.amfero.razmorozka.module.combat;

import org.lwjgl.input.Mouse;
import me.amfero.razmorozka.module.Category;
import me.amfero.razmorozka.module.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class AntiEndCrystal extends Module
{
	
	public AntiEndCrystal(String name, String description, Category category) {
        super(name, description, category);
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent.ClientTickEvent event) {
        if (this.mc.world == null) {
            return;
        }
        for (final Entity e : this.mc.world.loadedEntityList) {
            if (e instanceof EntityEnderCrystal && this.mc.player.getDistanceSq(e) <= 3.799999952316284 && !Mouse.isButtonDown(1) && e.isEntityAlive()) {
                this.mc.player.connection.getNetworkManager().closeChannel((ITextComponent)new TextComponentString("ENDER CRYSTAL IN RANGE"));
                disable();
                break;
            }
        }
    }
}