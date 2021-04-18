package me.amfero.razmorozka.module.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.mojang.authlib.GameProfile;

import me.amfero.razmorozka.module.Category;
import me.amfero.razmorozka.module.Module;
import net.minecraft.client.entity.EntityOtherPlayerMP;

public class FakePlayer extends Module {
	
	private final List<Integer> fakePlayerIdList = new ArrayList<>();
	
	public FakePlayer(String name, String description, Category category) {
        super(name, description, category);
	}
     
	public void onEnable() {
		
		if(mc.world == null || mc.player == null) {
			disable();
		}
		
		GameProfile profile = new GameProfile(UUID.fromString("b4e468dc-f67f-494c-8214-23e248cf1706"), "AmferohackMP4");
		EntityOtherPlayerMP fakePlayer = new EntityOtherPlayerMP(mc.world, profile);

		fakePlayer.copyLocationAndAnglesFrom(mc.player);
		fakePlayer.setHealth(mc.player.getHealth() + mc.player.getAbsorptionAmount());

		mc.world.addEntityToWorld(-69, fakePlayer);

		fakePlayerIdList.add(-69);
	}
	
	public void onDisable() {
		for (int id : fakePlayerIdList)
		{
			mc.world.removeEntityFromWorld(id);
		}
	}
}
