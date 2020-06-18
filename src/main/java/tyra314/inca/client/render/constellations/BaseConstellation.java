package tyra314.inca.client.render.constellations;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Vec3d;

import java.util.Random;

public abstract
class BaseConstellation
{
    private Random random = new Random();

    protected
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

        Vec3d coord = new Vec3d(-offsetX * unit + offset, distance, -offsetZ * unit + offset);
        coord = coord.normalize().multiply(-distance);

        float x = (float) coord.x;
        float y = (float) coord.y;
        float z = (float) coord.z;

        buffer.vertex(matrix4f2, x - radius, y, z + radius).next();
        buffer.vertex(matrix4f2, x + radius, y, z + radius).next();
        buffer.vertex(matrix4f2, x + radius, y, z - radius).next();
        buffer.vertex(matrix4f2, x - radius, y, z - radius).next();
    }

    public abstract
    void draw(BufferBuilder buffer, MatrixStack matrixStack);
}
