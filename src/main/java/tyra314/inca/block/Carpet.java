package tyra314.inca.block;

import net.fabricmc.fabric.api.block.BlockSettingsExtensions;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.entity.EntityContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.ViewableWorld;

public class Carpet extends BaseBlock
{
    protected static final VoxelShape SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);

    public Carpet(String key)
    {
        super(key, get_settings());
    }

    private static Settings get_settings()
    {
        Settings settings = Block.Settings.of(Material.CARPET, MaterialColor.RED).strength(0.1f, 0.1f);

        BlockSettingsExtensions.sounds(settings, BlockSoundGroup.WOOL);

        return settings;
    }

    public VoxelShape getOutlineShape(BlockState blockState_1,
                                      BlockView blockView_1,
                                      BlockPos blockPos_1,
                                      EntityContext entityContext_1)
    {
        return SHAPE;
    }

    public BlockState getStateForNeighborUpdate(BlockState blockState_1, Direction direction_1, BlockState blockState_2, IWorld iWorld_1, BlockPos blockPos_1, BlockPos blockPos_2) {
        return !blockState_1.canPlaceAt(iWorld_1, blockPos_1) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(blockState_1, direction_1, blockState_2, iWorld_1, blockPos_1, blockPos_2);
    }

    public boolean canPlaceAt(BlockState blockState_1, ViewableWorld viewableWorld_1, BlockPos blockPos_1) {
        return !viewableWorld_1.isAir(blockPos_1.down());
    }
}
