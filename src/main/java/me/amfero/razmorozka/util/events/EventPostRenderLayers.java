package me.amfero.razmorozka.util.events;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.model.ModelBase;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

@Cancelable
public class EventPostRenderLayers extends Event
{
    public float limbSwingAmount;
    public ModelBase modelBase;
    public RenderLivingBase renderer;
    public float headPitch;
    public float netHeadYaw;
    public float limbSwing;
    public float partialTicks;
    public EntityLivingBase entity;
    public float scaleIn;
    public float ageInTicks;
    
    public RenderLivingBase getRenderer() {
        return this.renderer;
    }
    
    public float getLimbSwingAmount() {
        return this.limbSwingAmount;
    }
    
    public float getHeadPitch() {
        return this.headPitch;
    }
    
    public float getNetHeadYaw() {
        return this.netHeadYaw;
    }
    
    public float getPartialTicks() {
        return this.partialTicks;
    }
    
    public ModelBase getModelBase() {
        return this.modelBase;
    }
    
    public float getAgeInTicks() {
        return this.ageInTicks;
    }
    
    public float getScaleIn() {
        return this.scaleIn;
    }
    
    public float getLimbSwing() {
        return this.limbSwing;
    }
    
    public EventPostRenderLayers(final RenderLivingBase var1, final ModelBase var2, final EntityLivingBase var3, final float var4, final float var5, final float var6, final float var7, final float var8, final float var9, final float var10) {
        this.renderer = var1;
        this.modelBase = var2;
        this.entity = var3;
        this.limbSwing = var4;
        this.limbSwingAmount = var5;
        this.partialTicks = var6;
        this.ageInTicks = var7;
        this.netHeadYaw = var8;
        this.headPitch = var9;
        this.scaleIn = var10;
    }
    
    public EntityLivingBase getEntitylivingbaseIn() {
        return this.entity;
    }
}