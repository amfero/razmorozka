package me.amfero.razmorozka.mixin.mixins.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import me.amfero.razmorozka.mixin.mixins.accessor.IRenderManager;
import net.minecraft.client.renderer.entity.RenderManager;

@Mixin(RenderManager.class)
public abstract class MixinRenderManager implements IRenderManager {

    @Accessor
    @Override
    public abstract double getRenderPosX();

    @Accessor
    @Override
    public abstract double getRenderPosY();

    @Accessor
    @Override
    public abstract double getRenderPosZ();
}
