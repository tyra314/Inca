package tyra314.inca.mixin;


import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.projectile.LlamaSpitEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BoundingBox;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LlamaSpitEntity.class)
public class LlamaSpitMixin
{
    private static void putOutFire(BoundingBox box, World world)
    {
        int int_1 = MathHelper.floor(box.minX);
        int int_2 = MathHelper.ceil(box.maxX);
        int int_3 = MathHelper.floor(box.minY);
        int int_4 = MathHelper.ceil(box.maxY);
        int int_5 = MathHelper.floor(box.minZ);
        int int_6 = MathHelper.ceil(box.maxZ);

        BlockPos.stream(int_1, int_3, int_5, int_2 - 1, int_4 - 1, int_6 - 1).forEach((pos) -> {
            BlockState state = world.getBlockState(pos);
            if(state.getBlock() == Blocks.FIRE)
            {
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
                world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.AMBIENT, 1.0F, 1.0F + (world.random.nextFloat() - world.random.nextFloat()) * 0.2F);
            }
        });
    }

    /**
     * This adds some code to check, if the spit hits fire and if it does, we'll put the fire out.
     * Our llama is a true fire fighter
     */
    @Inject(at = @At("TAIL"), method = "tick")
    private void tick(CallbackInfo info)
    {
        //noinspection ConstantConditions
        LlamaSpitEntity spit = (LlamaSpitEntity) ((Object) this);

        if (spit.world.doesAreaContainFireSource(spit.getBoundingBox()))
        {
            putOutFire(spit.getBoundingBox(), spit.world);
        }
    }
}