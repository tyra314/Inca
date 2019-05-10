package tyra314.inca.mixin;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.ProjectileDamageSource;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tyra314.inca.item.ModItems;

@Mixin(PlayerEntity.class)
public class PlayerMixin
{
    /**
     * Adds an mixin, which catches when a llama spits at the player.
     * <p>
     * Given he has an empty bottle to "catch" the spit in, he will get a bottle of llama spit
     */
    @Inject(at = @At("HEAD"), method = "damage", cancellable = true)
    private void damage(DamageSource source, float dmg, CallbackInfoReturnable<Boolean> info)
    {
        if (source instanceof ProjectileDamageSource)
        {
            ProjectileDamageSource projectile = (ProjectileDamageSource) source;

            if (projectile.getAttacker() instanceof LlamaEntity)
            {
                //noinspection ConstantConditions
                PlayerEntity player = (PlayerEntity) ((Object) this);

                for (Hand hand : Hand.values())
                {
                    ItemStack stack = player.getStackInHand(hand);
                    if (stack.getItem() == Items.GLASS_BOTTLE)
                    {
                        stack.subtractAmount(1);
                        player.inventory.insertStack(new ItemStack(ModItems.LLAMA_SPIT_BOTTLE));

                        player.swingHand(hand);

                        info.setReturnValue(false);
                    }
                }
            }
        }
    }
}