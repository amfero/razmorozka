package me.amfero.razmorozka.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.opengl.GL11;

public class RenderUtil
{
	private static final Minecraft mc = Minecraft.getMinecraft();

	public static void drawBox(AxisAlignedBB box, float r, float g, float b, float a)
	{
		try
		{
			glSetup();
			RenderGlobal.renderFilledBox(box, r, g, b, a);
			RenderGlobal.drawSelectionBoundingBox(box, r, g, b, a * 1.5F);
			glCleanup();
		}
		catch (Exception ignored)
		{
		}
	}

	public static void drawBoxFromBlockpos(BlockPos blockPos, float r, float g, float b, float a)
	{
		AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos.getX() - mc.getRenderManager().viewerPosX, blockPos.getY() - mc.getRenderManager().viewerPosY, blockPos.getZ() - mc.getRenderManager().viewerPosZ, blockPos.getX() + 1 - mc.getRenderManager().viewerPosX, blockPos.getY() + 1 - mc.getRenderManager().viewerPosY, blockPos.getZ() + 1 - mc.getRenderManager().viewerPosZ);
		drawBox(axisAlignedBB, 170, 170, 110, 100);
	}


	public static void glSetup()
	{
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.disableDepth();
		GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
		GlStateManager.disableTexture2D();
		GlStateManager.depthMask(false);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glHint(GL11.GL_LINE_SMOOTH_HINT, GL11.GL_NICEST);
		GL11.glLineWidth(1.5f);
	}

	public static void glCleanup()
	{
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
		GlStateManager.depthMask(true);
		GlStateManager.enableDepth();
		GlStateManager.enableTexture2D();
		GlStateManager.disableBlend();
		GlStateManager.popMatrix();
	}
	
	public static void drawGradientSideways(final double left, final double top, final double right, final double bottom, final int col1, final int col2) {
        final float f = (col1 >> 24 & 0xFF) / 255.0f;
        final float f2 = (col1 >> 16 & 0xFF) / 255.0f;
        final float f3 = (col1 >> 8 & 0xFF) / 255.0f;
        final float f4 = (col1 & 0xFF) / 255.0f;
        final float f5 = (col2 >> 24 & 0xFF) / 255.0f;
        final float f6 = (col2 >> 16 & 0xFF) / 255.0f;
        final float f7 = (col2 >> 8 & 0xFF) / 255.0f;
        final float f8 = (col2 & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glShadeModel(7425);
        GL11.glPushMatrix();
        GL11.glBegin(7);
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glVertex2d(left, top);
        GL11.glVertex2d(left, bottom);
        GL11.glColor4f(f6, f7, f8, f5);
        GL11.glVertex2d(right, bottom);
        GL11.glVertex2d(right, top);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glShadeModel(7424);
    }

	public static void drawRect(float x, float y, float w, float h, int color)
	{
		float X = x;
		float Y = y;

		float f1 = X + w;
		float f2 = Y + h;
		float f3;
		if (X < f1)
		{
			f3 = X;
			X = f1;
			f1 = f3;
		}

		if (Y < f2)
		{
			f3 = Y;
			Y = f2;
			f2 = f3;
		}

		float f4 = (float) (color >> 24 & 255) / 255.0F;
		float f5 = (float) (color >> 16 & 255) / 255.0F;
		float f6 = (float) (color >> 8 & 255) / 255.0F;
		float f7 = (float) (color & 255) / 255.0F;
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferBuilder = tessellator.getBuffer();
		GlStateManager.enableBlend();
		GlStateManager.disableTexture2D();
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		GlStateManager.color(f5, f6, f7, f4);
		bufferBuilder.begin(7, DefaultVertexFormats.POSITION);
		bufferBuilder.pos(X, f2, 0.0D).endVertex();
		bufferBuilder.pos(f1, f2, 0.0D).endVertex();
		bufferBuilder.pos(f1, Y, 0.0D).endVertex();
		bufferBuilder.pos(X, Y, 0.0D).endVertex();
		tessellator.draw();
		GlStateManager.enableTexture2D();
		GlStateManager.disableBlend();
	}
}