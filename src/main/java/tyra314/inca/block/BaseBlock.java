package tyra314.inca.block;

import net.minecraft.block.Block;

public class BaseBlock extends Block
{
    private final String registry_key;

    public BaseBlock(String registry_key, Block.Settings settings)
    {
        super(settings);

        this.registry_key = registry_key;
    }


    @SuppressWarnings("WeakerAccess")
    public String getRegistryKey()
    {
        return registry_key;
    }
}
