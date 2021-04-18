package me.amfero.razmorozka.module.misc;

import java.util.Objects;

import me.amfero.razmorozka.module.Category;
import me.amfero.razmorozka.module.Module;
import me.amfero.razmorozka.setting.Setting;
import me.amfero.razmorozka.util.RenderUtil;
import net.minecraft.init.Blocks;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class PacketMine extends Module {
	
	Setting render = new Setting("Render", this, true);
	
	private int timer;
    private BlockPos renderBlock;

	public PacketMine(String name, String description, Category category) {
		super(name, description, category);
		this.timer = -1;
		addSetting(render);
	}
	
	@SubscribeEvent
    public void onBlockClick(final PlayerInteractEvent.LeftClickBlock event) {
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        if (this.mc.world.getBlockState(event.getPos()).getBlock().getBlockHardness(this.mc.world.getBlockState(event.getPos()), (World)this.mc.world, event.getPos()) != -1.0f) {
            this.mc.player.swingArm(EnumHand.MAIN_HAND);
            this.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, event.getPos(), (EnumFacing)Objects.requireNonNull(event.getFace())));
            this.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, event.getPos(), event.getFace()));
            if (this.renderBlock == null && this.render.getBooleanValue()) {
                this.renderBlock = event.getPos();
            }
        }
    }
    
    @SubscribeEvent
    public void onWorldRender(final RenderWorldLastEvent event) {
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        if (this.renderBlock != null && this.timer > 0) {
            RenderUtil.drawBoxFromBlockpos(this.renderBlock, 0.3f, 0.3f, 0.3f, 0.5f);
        }
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent.ClientTickEvent event) {
        if (mc.world == null) {
            return;
        }
        if (this.renderBlock != null && this.mc.world.getBlockState(this.renderBlock).getBlock() == Blocks.AIR) {
            this.renderBlock = null;
        }
        if (this.renderBlock != null && this.timer == -1) {
            this.timer = 130;
        }
        if (this.timer > 0) {
            --this.timer;
        }
        if (this.timer == 0) {
            this.timer = -1;
            this.renderBlock = null;
        }
    }

}
