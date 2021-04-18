package me.amfero.razmorozka.mixin.mixins.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderEnderCrystal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.amfero.razmorozka.Razmorozka;

import java.awt.*;

@Mixin(RenderEnderCrystal.class)
public abstract class MixinRenderEnderCrystal {
    @Shadow public abstract void doRender(EntityEnderCrystal entity, double x, double y, double z, float entityYaw, float partialTicks);
    @Shadow public ModelBase modelEnderCrystal;
    @Shadow public ModelBase modelEnderCrystalNoBase;


    @Final
    @Shadow private static ResourceLocation ENDER_CRYSTAL_TEXTURES;
    //Razmorozka.moduleManager.getModule("Chams").isEnabled() && Razmorozka.settingManager.getSettingEasy("Chams", 7).getBooleanValue()

    @Inject(method = "doRender(Lnet/minecraft/entity/item/EntityEnderCrystal;DDDFF)V", at = @At("RETURN"), cancellable = true)
    public void IdoRender(EntityEnderCrystal entity, double x, double y, double z, float entityYaw, float partialTicks, CallbackInfo callback) {
        if (Razmorozka.moduleManager.getModule("Chams").isEnabled() && Razmorozka.settingManager.getSettingEasy("Chams", 6).getBooleanValue()) {
            GL11.glPushMatrix();
            float f = (float) entity.innerRotation + partialTicks;
            GlStateManager.translate(x, y, z);
            Minecraft.getMinecraft().getRenderManager().renderEngine.bindTexture(ENDER_CRYSTAL_TEXTURES);
            float f1 = MathHelper.sin(f * 0.2F) / 2.0F + 0.5F;
            f1 = f1 * f1 + f1;
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            GL11.glColor4f(Razmorozka.settingManager.getSettingEasy("Chams", 0).getIntegerValue() / 255f, Razmorozka.settingManager.getSettingEasy("Chams", 1).getIntegerValue() / 255f, Razmorozka.settingManager.getSettingEasy("Chams", 2).getIntegerValue() / 255f, 0.5f);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            if (entity.shouldShowBottom()) {
                this.modelEnderCrystal.render(entity, 0.0F, f * 3.0F, f1 * 0.2F, 0.0F, 0.0F, 0.0625F);
            } else {
                this.modelEnderCrystalNoBase.render(entity, 0.0F, f * 3.0F, f1 * 0.2F, 0.0F, 0.0F, 0.0625F);
            }
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glColor4f(Razmorozka.settingManager.getSettingEasy("Chams", 0).getIntegerValue() / 255f, Razmorozka.settingManager.getSettingEasy("Chams", 1).getIntegerValue() / 255f, Razmorozka.settingManager.getSettingEasy("Chams", 2).getIntegerValue() / 255f, 0.5f);
            if (entity.shouldShowBottom()) {
                this.modelEnderCrystal.render(entity, 0.0F, f * 3.0F, f1 * 0.2F, 0.0F, 0.0F, 0.0625F);
            } else {
                this.modelEnderCrystalNoBase.render(entity, 0.0F, f * 3.0F, f1 * 0.2F, 0.0F, 0.0F, 0.0625F);
            }
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glColor4f(1f, 1f, 1f, 1f);
            GlStateManager.popMatrix();
        }
    }
}