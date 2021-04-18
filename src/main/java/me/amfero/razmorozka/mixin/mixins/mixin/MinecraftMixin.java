package me.amfero.razmorozka.mixin.mixins.mixin;


import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;

import me.amfero.razmorozka.mixin.mixins.accessor.IMinecraft;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Timer;

@Mixin(value = Minecraft.class, priority =  634756347)
public abstract class MinecraftMixin implements IMinecraft
{
	@Final @Shadow private Timer timer;

	@Override
	public Timer getTimer()
	{
		return timer;
	}
	
	@Accessor
    @Override
    public abstract void setRightClickDelayTimer(int delay);
}
