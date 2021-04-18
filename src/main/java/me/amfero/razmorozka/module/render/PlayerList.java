package me.amfero.razmorozka.module.render;

import java.awt.Color;
import java.util.Objects;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.amfero.razmorozka.Razmorozka;
import me.amfero.razmorozka.module.Category;
import me.amfero.razmorozka.module.Module;
import me.amfero.razmorozka.util.EntityUtil;
import me.amfero.razmorozka.util.MathUtil;
import me.amfero.razmorozka.util.SessionUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PlayerList extends Module
{
	
	public PlayerList(String name, String description, Category category)
	{
		super(name, description, category);
	}

	@SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post event) {
		
		if(mc.world == null) {
			return;
		}
		
		if (event.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
			int count = 0;
			for (EntityPlayer player : mc.world.playerEntities) {
				if(player.getName() != mc.player.getName()) {
					
					String healthS = " ";
			            final float health = EntityUtil.totalHealth(player);
			            healthS += (MathHelper.ceil(player.getHealth() + player.getAbsorptionAmount()) + "hp");
					
					String pingS = getPing(player) + "ms";
					Razmorozka.customFontRenderer.drawStringWithShadow(player.getName().toString() + " - " + ChatFormatting.WHITE + pingS + " - " + ChatFormatting.GREEN + healthS, 490, 275 + (count*10), new Color(120, 120, 210, 225).getRGB());
					count++;
				}
			}
		}
	}
	
	public int getPing(final EntityPlayer player) {
        int ping = 0;
        try {
            ping = (int) MathUtil.clamp((float) Objects.requireNonNull(mc.getConnection()).getPlayerInfo(player.getUniqueID()).getResponseTime(), 1, 300.0f);
        }
        catch (NullPointerException ignored) {
        }
        return ping;
    }
	
}