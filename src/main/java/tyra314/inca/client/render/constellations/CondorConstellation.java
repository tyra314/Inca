package tyra314.inca.client.render.constellations;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.math.Matrix4f;

public
class CondorConstellation
        extends BaseConstellation
{

    @Override
    public
    void draw(BufferBuilder buffer, MatrixStack matrixStack)
    {
        matrixStack.push();

        matrixStack.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(140f));
        matrixStack.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(-65F));
        matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(85F));
        matrixStack.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(-37F));

        Matrix4f matrix4f2 = matrixStack.peek().getModel();

        drawStar(0, 22, buffer, matrix4f2);
        drawStar(0, 24, buffer, matrix4f2);
        drawStar(1, 1, buffer, matrix4f2);
        drawStar(1, 4, buffer, matrix4f2);

        drawStar(4, 0, buffer, matrix4f2);
        drawStar(5, 4, buffer, matrix4f2);
        drawStar(6, 21, buffer, matrix4f2);
        drawStar(7, 8, buffer, matrix4f2);

        drawStar(8, 18, buffer, matrix4f2);
        drawStar(9, 12, buffer, matrix4f2);
        drawStar(11, 9, buffer, matrix4f2);

        drawStar(12, 12, buffer, matrix4f2);
        drawStar(13, 16, buffer, matrix4f2);
        drawStar(15, 19, buffer, matrix4f2);

        drawStar(17, 13, buffer, matrix4f2);
        drawStar(17, 21, buffer, matrix4f2);

        drawStar(20, 21, buffer, matrix4f2);
        drawStar(21, 17, buffer, matrix4f2);
        drawStar(22, 16, buffer, matrix4f2);
        drawStar(21, 16, buffer, matrix4f2);
        drawStar(22, 9, buffer, matrix4f2);
        drawStar(23, 6, buffer, matrix4f2);

        drawStar(26, 1, buffer, matrix4f2);
        drawStar(27, 4, buffer, matrix4f2);


        matrixStack.pop();
    }

}
