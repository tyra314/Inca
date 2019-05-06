package tyra314.inca.mixin;

import net.minecraft.entity.passive.AbstractDonkeyEntity;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LlamaEntity.class)
public class LlamaMixin
{
    @Inject(at = @At("HEAD"), method = "canBeControlledByRider", cancellable = true)
    private void canBeControlledByRider(CallbackInfoReturnable<Boolean> info)
    {
        LlamaEntity llama = (LlamaEntity) ((Object) this);

        info.setReturnValue(llama.getPrimaryPassenger() instanceof PlayerEntity);
    }

    @Inject(at = @At("HEAD"), method = "canBeSaddled", cancellable = true)
    private void canBeSaddled(CallbackInfoReturnable<Boolean> info)
    {
        info.setReturnValue(true);
    }
}