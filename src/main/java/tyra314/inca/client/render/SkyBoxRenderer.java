package tyra314.inca.client.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.sun.javafx.geom.Vec3f;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.VertexBuffer;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.BufferRenderer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.client.util.math.Matrix4f;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.client.world.ClientWorld;
import org.lwjgl.opengl.GL11;

import java.util.Random;

public
class SkyBoxRenderer
{
    private TextureManager textureManager;
    private Random random = new Random();

    private VertexBuffer starsBuffer = new VertexBuffer(VertexFormats.POSITION);

    public
    SkyBoxRenderer(TextureManager textureManager)
    {
        this.textureManager = textureManager;
    }

    private
    void drawLlamaConstelation(BufferBuilder buffer, Matrix4f matrix4f2)
    {
        drawStar(4, 10, buffer, matrix4f2);

        drawStar(6, 14, buffer, matrix4f2);
        drawStar(5, 12, buffer, matrix4f2);
        drawStar(7, 4, buffer, matrix4f2);
        drawStar(7, 6, buffer, matrix4f2);
        drawStar(7, 10, buffer, matrix4f2);
        drawStar(8, 1, buffer, matrix4f2);

        drawStar(9, 8, buffer, matrix4f2);
        drawStar(12, 15, buffer, matrix4f2);

        drawStar(14, 9, buffer, matrix4f2);

        drawStar(17, 16, buffer, matrix4f2);
        drawStar(20, 10, buffer, matrix4f2);

        drawStar(21, 7, buffer, matrix4f2);
        drawStar(21, 19, buffer, matrix4f2);
        drawStar(21, 24, buffer, matrix4f2);
        drawStar(22, 3, buffer, matrix4f2);
        drawStar(22, 27, buffer, matrix4f2);
        drawStar(23, 3, buffer, matrix4f2);
        drawStar(23, 6, buffer, matrix4f2);
        drawStar(23, 30, buffer, matrix4f2);
        drawStar(24, 9, buffer, matrix4f2);
        drawStar(24, 21, buffer, matrix4f2);
        drawStar(24, 28, buffer, matrix4f2);
        drawStar(24, 31, buffer, matrix4f2);

        drawStar(25, 18, buffer, matrix4f2);
        drawStar(25, 25, buffer, matrix4f2);
        drawStar(26, 13, buffer, matrix4f2);
        drawStar(26, 16, buffer, matrix4f2);
        drawStar(26, 28, buffer, matrix4f2);
        drawStar(28, 26, buffer, matrix4f2);
        drawStar(28, 27, buffer, matrix4f2);
    }

    private
    void drawStar(float offsetX, float offsetZ, BufferBuilder buffer, Matrix4f matrix4f2)
    {
        final float distance = 100f;
        final float offset = 16f;
        final float unit = 1f;
        float radius = 0.2f;

        if (random.nextFloat() < 0.002)
        {
            radius = 0.4f;

            if (MinecraftClient.getInstance().player.isTouchingWater())
            {
                radius += 0.2f;
            }
        }

        Vec3f coord = new Vec3f(-offsetX * unit + offset, distance, -offsetZ * unit + offset);
        coord.normalize();
        coord.mul(-distance);

        buffer.vertex(matrix4f2,coord.x - radius, coord.y, coord.z + radius).next();
        buffer.vertex(matrix4f2,coord.x + radius, coord.y, coord.z + radius).next();
        buffer.vertex(matrix4f2,coord.x + radius, coord.y, coord.z - radius).next();
        buffer.vertex(matrix4f2,coord.x - radius, coord.y, coord.z - radius).next();
    }

    public
    void draw(ClientWorld world, MatrixStack matrixStack, float partialTicks)
    {
        if (!world.dimension.hasVisibleSky())
        {
            return;
        }

        matrixStack.push();
        RenderSystem.disableTexture();

        float star_brightness = world.method_23787(partialTicks) * (1.0F - world.getRainGradient(partialTicks));

        if (star_brightness > 0.1F)
        {

            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder buffer = tessellator.getBuffer();

            buffer.begin(GL11.GL_QUADS, VertexFormats.POSITION);

            RenderSystem.color4f(star_brightness, star_brightness, star_brightness, star_brightness);

            matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(-90.0F));
            matrixStack.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(world.getSkyAngle(partialTicks) * 360.0F));

            matrixStack.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(65F));
            matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(-75F));
            matrixStack.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(37F));

            Matrix4f matrix4f2 = matrixStack.peek().getModel();

            drawLlamaConstelation(buffer, matrix4f2);

            buffer.end();
            BufferRenderer.draw(buffer);
        }
        matrixStack.pop();
    }
}
