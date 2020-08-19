package tyra314.inca.client.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gl.VertexBuffer;
import net.minecraft.client.render.*;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.client.world.ClientWorld;
import org.lwjgl.opengl.GL11;
import tyra314.inca.client.render.constellations.BaseConstellation;
import tyra314.inca.client.render.constellations.CondorConstellation;
import tyra314.inca.client.render.constellations.LlamaConstellation;

import java.util.ArrayList;
import java.util.List;

public
class SkyBoxRenderer
{
    private TextureManager textureManager;

    private VertexBuffer starsBuffer = new VertexBuffer(VertexFormats.POSITION);

    private List<BaseConstellation> constellations = new ArrayList<>();

    public
    SkyBoxRenderer(TextureManager textureManager)
    {
        this.textureManager = textureManager;
        constellations.add(new LlamaConstellation());
        constellations.add(new CondorConstellation());
    }

    public
    void draw(ClientWorld world, MatrixStack matrixStack, float partialTicks)
    {
        if (world.getSkyProperties().getSkyType() != SkyProperties.SkyType.NORMAL)
        {
            return;
        }

        matrixStack.push();
        RenderSystem.disableTexture();

        float star_brightness = world.method_23787(partialTicks) * (1.0F - world.getRainGradient(
                partialTicks));

        if (star_brightness > 0.1F)
        {

            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder buffer = tessellator.getBuffer();

            buffer.begin(GL11.GL_QUADS, VertexFormats.POSITION);

            RenderSystem.color4f(star_brightness, star_brightness, star_brightness,
                    star_brightness);
            RenderSystem.enableBlend();
            RenderSystem.blendFuncSeparate(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE, GlStateManager.SrcFactor.ONE, GlStateManager.DstFactor.ZERO);


            matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(-90.0F));
            matrixStack.multiply(Vector3f.POSITIVE_X
                    .getDegreesQuaternion(world.getSkyAngleRadians(partialTicks) * 360.0F));

            constellations.stream().forEach(
                    constellation -> constellation.draw(buffer, matrixStack));

            buffer.end();
            BufferRenderer.draw(buffer);

            RenderSystem.disableBlend();
        }
        matrixStack.pop();
    }
}
