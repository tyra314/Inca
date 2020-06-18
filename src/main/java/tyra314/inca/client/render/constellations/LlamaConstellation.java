package tyra314.inca.client.render.constellations;

import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.math.Matrix4f;

public
class LlamaConstellation
        extends BaseConstellation
{

    @Override
    public
    void draw(BufferBuilder buffer, MatrixStack matrixStack)
    {
        matrixStack.push();

        matrixStack.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(65F));
        matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(-75F));
        matrixStack.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(37F));

        Matrix4f matrix4f2 = matrixStack.peek().getModel();

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

        matrixStack.pop();
    }

}
