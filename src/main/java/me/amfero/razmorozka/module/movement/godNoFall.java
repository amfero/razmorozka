package me.amfero.razmorozka.module.movement;

import java.util.Arrays;

import me.amfero.razmorozka.module.Category;

import me.amfero.razmorozka.module.Module;
import me.amfero.razmorozka.setting.Setting;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
public class godNoFall extends Module {
	
	private final Setting fallDist = new Setting("FallDistance", this, 4, 3, 20);
	private final Setting fallDist2 = new Setting("FallDistance2", this, 15, 10, 40); 
	private final Setting mode = new Setting("Mode", this, Arrays.asList(
			"Predict",
			"Old"
    ));
	
	public godNoFall(String name, String description, Category category) {
		super(name, description, category);
		addSetting(fallDist);
		addSetting(fallDist2);
		addSetting(mode);
	}
	
	@SubscribeEvent
	public void onUpdate(final TickEvent.ClientTickEvent event) {
		if(mc.world == null) return;
		if(mode.getEnumValue().equals("Predict")) {
			Vec3d vec = new Vec3d(mc.player.lastTickPosX + (mc.player.posX - mc.player.lastTickPosX) * mc.getRenderPartialTicks(), mc.player.lastTickPosY + (mc.player.posY - mc.player.lastTickPosY) * mc.getRenderPartialTicks(), mc.player.lastTickPosZ + (mc.player.posZ - mc.player.lastTickPosZ) * mc.getRenderPartialTicks());
			BlockPos pos = new BlockPos(vec.x, vec.y - 2, vec.z);
			BlockPos[] posList = { pos.north(), pos.south(), pos.east(), pos.west(), pos.down(), pos.down() };
			for (BlockPos blockPos : posList)
			{
				Block block = mc.world.getBlockState(blockPos).getBlock();
				if (mc.player.dimension == 1) {
					if(mc.player.fallDistance > fallDist2.getIntegerValue()) {
						mc.player.connection.sendPacket(new CPacketPlayer.Position(0, 64, 0, false));
						mc.player.fallDistance = fallDist.getIntegerValue() + 1;
					}
					if (mc.player.fallDistance > fallDist.getIntegerValue() && block != Blocks.AIR) {
						mc.player.connection.sendPacket(new CPacketPlayer.Position(0, 64, 0, false));
						mc.player.fallDistance = 0;
					}
				} else {
					if(mc.player.fallDistance > fallDist2.getIntegerValue()) {
						mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, 0, mc.player.posZ, false));
						mc.player.fallDistance = fallDist.getIntegerValue() + 1;
					}
					if (mc.player.fallDistance > fallDist.getIntegerValue() && block != Blocks.AIR) {
						mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, 0, mc.player.posZ, false));
						mc.player.fallDistance = 0;
					}
				}
			}
		}
		if(mode.getEnumValue().equals("Old")) {
			if(mc.player.fallDistance > fallDist.getIntegerValue()) {
				mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, 0, mc.player.posZ, false));
				mc.player.fallDistance = 0;
			}
		}
	}
}

