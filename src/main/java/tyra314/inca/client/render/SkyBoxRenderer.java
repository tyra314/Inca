package tyra314.inca.client.render;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.GuiLighting;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

import java.util.Random;

public class SkyBoxRenderer
{
    public static void draw(World world, float partialTicks)
    {
        if (!world.dimension.hasVisibleSky())
        {
            return;
        }

        GlStateManager.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
                GlStateManager.DestFactor.ONE,
                GlStateManager.SourceFactor.ONE,
                GlStateManager.DestFactor.ZERO);

        GlStateManager.enableBlend();
        GlStateManager.disableAlphaTest();
        GlStateManager.disableFog();
        GlStateManager.depthMask(false);
        GuiLighting.disable();

        GlStateManager.pushMatrix();
        GlStateManager.rotatef(-90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotatef(world.getSkyAngle(partialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);

        GlStateManager.rotatef(65.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotatef(-75.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotatef(37.0F, 0.0F, 0.0F, 1.0F);

        float brightness = 1.0F - world.getRainGradient(partialTicks);
        float star_brightness = world.getStarsBrightness(partialTicks) * brightness;

        if (star_brightness > 0.1F)
        {
            GlStateManager.color4f(star_brightness, star_brightness, star_brightness, star_brightness);
            GlStateManager.disableTexture();
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder buffer = tessellator.getBufferBuilder();

            buffer.begin(GL11.GL_QUADS, VertexFormats.POSITION);

            drawLlamaConstelation(buffer);

            tessellator.draw();
        }

        GlStateManager.enableTexture();

        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);

        GuiLighting.enable();
        GlStateManager.depthMask(true);
        GlStateManager.enableFog();
        GlStateManager.enableAlphaTest();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    private static void drawLlamaConstelation(BufferBuilder buffer)
    {
        drawStar(4, 10, buffer);

        drawStar(6, 14, buffer);
        drawStar(5, 12, buffer);
        drawStar(7, 4, buffer);
        drawStar(7, 6, buffer);
        drawStar(7, 10, buffer);
        drawStar(8, 1, buffer);

        drawStar(9, 8, buffer);
        drawStar(12, 15, buffer);

        drawStar(14, 9, buffer);

        drawStar(17, 16, buffer);
        drawStar(20, 10, buffer);

        drawStar(21, 7, buffer);
        drawStar(21, 19, buffer);
        drawStar(21, 24, buffer);
        drawStar(22, 3, buffer);
        drawStar(22, 27, buffer);
        drawStar(23, 3, buffer);
        drawStar(23, 6, buffer);
        drawStar(23, 30, buffer);
        drawStar(24, 9, buffer);
        drawStar(24, 21, buffer);
        drawStar(24, 28, buffer);
        drawStar(24, 31, buffer);

        drawStar(25, 18, buffer);
        drawStar(25, 25, buffer);
        drawStar(26, 13, buffer);
        drawStar(26, 16, buffer);
        drawStar(26, 28, buffer);
        drawStar(28, 26, buffer);
        drawStar(28, 27, buffer);
    }

    private final static Random random = new Random();

    private static void drawStar(float offsetX, float offsetZ, BufferBuilder buffer)
    {
        final float distance = 100f;
        final float offset = 16f;
        final float unit = 1f;
        float radius = 0.2f;

        if(random.nextFloat() < 0.002)
        {
            radius = 0.4f;

            if (MinecraftClient.getInstance().player.isInWater())
            {
                radius += 0.2f;
            }
        }




        Vec3d coord = new Vec3d(-offsetX * unit + offset, distance, -offsetZ * unit + offset);
        coord = coord.normalize().multiply(-distance);

        buffer.vertex(coord.x - radius, coord.y, coord.z + radius).next();
        buffer.vertex(coord.x + radius, coord.y, coord.z + radius).next();
        buffer.vertex(coord.x + radius, coord.y, coord.z - radius).next();
        buffer.vertex(coord.x - radius, coord.y, coord.z - radius).next();
    }
}
