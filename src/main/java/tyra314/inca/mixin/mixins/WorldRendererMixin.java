package tyra314.inca.mixin.mixins;

import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tyra314.inca.client.render.SkyBoxRenderer;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin
{
    @Shadow private ClientWorld world;

    @Inject(at = @At("TAIL"), method = "renderSky")
    private void renderSky(float partialTicks, CallbackInfo info)
    {
        SkyBoxRenderer.draw(world, partialTicks);
    }
}