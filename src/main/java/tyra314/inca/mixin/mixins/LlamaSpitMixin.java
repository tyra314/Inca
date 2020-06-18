package tyra314.inca.mixin.mixins;


import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.entity.projectile.LlamaSpitEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LlamaSpitEntity.class)
public class LlamaSpitMixin {
    private static void putOutFire(Box box, World world) {
        int int_1 = MathHelper.floor(box.getMin(Direction.Axis.X));
        int int_2 = MathHelper.ceil(box.getMax(Direction.Axis.X));
        int int_3 = MathHelper.floor(box.getMin(Direction.Axis.Y));
        int int_4 = MathHelper.ceil(box.getMax(Direction.Axis.Y));
        int int_5 = MathHelper.floor(box.getMin(Direction.Axis.Z));
        int int_6 = MathHelper.ceil(box.getMax(Direction.Axis.Z));

        BlockPos.stream(int_1, int_3, int_5, int_2 - 1, int_4 - 1, int_6 - 1).forEach((pos) -> {
            BlockState state = world.getBlockState(pos);
            if (state.getBlock() == Blocks.FIRE) {
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

        boolean hasHitFire = spit.world.method_29546(spit.getBoundingBox()).anyMatch(state -> {
           return state.getMaterial() == Material.FIRE;
        });

        if (hasHitFire)
        {
            putOutFire(spit.getBoundingBox(), spit.world);
        }
    }
}