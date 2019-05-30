package tyra314.inca.block;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import tyra314.inca.IncaMod;

public class Blocks
{
    public static BaseBlock CARPET = new Carpet("foo_carpet");
    public static BaseBlock CARPET2 = new Carpet("inti_carpet");

    public static void init()
    {
        register(CARPET);
        register(CARPET2);
    }

    private static void register(BaseBlock block)
    {
        Registry.register(Registry.BLOCK, new Identifier(IncaMod.MOD_ID, block.getRegistryKey()), block);

        Registry.register(Registry.ITEM,
                new Identifier(IncaMod.MOD_ID, block.getRegistryKey()),
                new BlockItem(block, new Item.Settings().itemGroup(IncaMod.ITEM_GROUP)));
    }
}
