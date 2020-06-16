package tyra314.inca.mixin.mixins;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tyra314.inca.client.render.SkyBoxRenderer;

@Environment(EnvType.CLIENT)
@Mixin(WorldRenderer.class)
public
class WorldRendererMixin
{
    @Shadow
    private ClientWorld world;
    @Shadow
    private TextureManager textureManager;

    private SkyBoxRenderer renderer = new SkyBoxRenderer(textureManager);

    @Inject(at = @At("TAIL"), method = "renderSky")
    private
    void renderSky(MatrixStack matrixStack, float partialTicks, CallbackInfo info)
    {
        renderer.draw(world, matrixStack, partialTicks);
    }
}