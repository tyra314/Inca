package tyra314.inca.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AbstractDonkeyEntity;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tyra314.inca.network.ISpitter;

@Mixin(LlamaEntity.class)
public class LlamaMixin extends AbstractDonkeyEntity implements ISpitter
{
    private int ticksTillSpit = 0;

    protected LlamaMixin(EntityType<? extends AbstractDonkeyEntity> entityType_1,
                         World world_1)
    {
        super(entityType_1, world_1);
    }

    @Inject(at = @At("HEAD"), method = "canBeControlledByRider", cancellable = true)
    private void canBeControlledByRider(CallbackInfoReturnable<Boolean> info)
    {
        //noinspection ConstantConditions
        LlamaEntity llama = (LlamaEntity) ((Object) this);

        info.setReturnValue(llama.getPrimaryPassenger() instanceof PlayerEntity);
    }

    @Inject(at = @At("HEAD"), method = "canBeSaddled", cancellable = true)
    private void canBeSaddled(CallbackInfoReturnable<Boolean> info)
    {
        info.setReturnValue(true);
    }

    @Override
    public void tick()
    {
        super.tick();

        if (ticksTillSpit > 0)
        {
            ticksTillSpit--;
        }
    }

    public void spit()
    {
        if (canSpit())
        {
            ticksTillSpit = world.random.nextInt(10) + 10;
        }
    }

    public boolean canSpit()
    {
        return ticksTillSpit == 0;
    }
}