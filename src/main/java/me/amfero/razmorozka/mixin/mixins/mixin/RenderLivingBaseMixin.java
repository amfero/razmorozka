package me.amfero.razmorozka.mixin.mixins.mixin;

import me.amfero.razmorozka.util.events.EventModelRender;
import me.amfero.razmorozka.util.events.EventPostRenderLayers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;

@Mixin(value = { RenderLivingBase.class }, priority = 2147483645)
public abstract class RenderLivingBaseMixin
{
	
    @Shadow
    protected ModelBase mainModel;
    
    @Redirect(method = { "renderModel" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelBase;render(Lnet/minecraft/entity/Entity;FFFFFF)V"))
    private void renderModelWrapper(final ModelBase modelBase, final Entity entity, final float limbSwing, final float limbSwingAmount, final float ageInTicks, final float netHeadYaw, final float headPitch, final float scaleFactor) {
        final EventModelRender modelrenderpre = new EventModelRender(modelBase, entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, 0);
        MinecraftForge.EVENT_BUS.post((Event)modelrenderpre);
        if (modelrenderpre.isCanceled()) {
            return;
        }
        modelBase.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
        final EventModelRender modelrenderpost = new EventModelRender(modelBase, entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, 1);
        MinecraftForge.EVENT_BUS.post((Event)modelrenderpost);
    }
    
    @Inject(method = { "renderLayers" }, at = { @At("RETURN") })
    public void renderLayers(final EntityLivingBase entitylivingbaseIn, final float limbSwing, final float limbSwingAmount, final float partialTicks, final float ageInTicks, final float netHeadYaw, final float headPitch, final float scaleIn, final CallbackInfo ci) {
        final EventPostRenderLayers eventPostRenderLayers = new EventPostRenderLayers(RenderLivingBase.class.cast(this), this.mainModel, entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scaleIn);
        MinecraftForge.EVENT_BUS.post((Event)eventPostRenderLayers);
    }
}