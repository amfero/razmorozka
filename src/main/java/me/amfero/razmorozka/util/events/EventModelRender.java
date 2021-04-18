package me.amfero.razmorozka.util.events;

import net.minecraft.entity.Entity;
import net.minecraft.client.model.ModelBase;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

@Cancelable
public class EventModelRender extends Event
{
    public ModelBase modelBase;
    public float ageInTicks;
    public float limbSwing;
    public float scaleFactor;
    public float headPitch;
    public float netHeadYaw;
    public float limbSwingAmount;
    public Entity entity;
    public int type;
    
    public EventModelRender(final ModelBase var1, final Entity var2, final float var3, final float var4, final float var5, final float var6, final float var7, final float var8, final int type) {
        this.type = 0;
        this.modelBase = var1;
        this.entity = var2;
        this.limbSwing = var3;
        this.limbSwingAmount = var4;
        this.ageInTicks = var5;
        this.netHeadYaw = var6;
        this.headPitch = var7;
        this.scaleFactor = var8;
        this.type = type;
    }
}