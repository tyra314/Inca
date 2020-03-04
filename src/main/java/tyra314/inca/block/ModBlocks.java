package tyra314.inca.block;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import tyra314.inca.IncaMod;

public class ModBlocks {
    public static final Block NAUTILUS_ORE = new Block(FabricBlockSettings
            .of(Material.STONE)
            .hardness(10f)
            .breakByHand(false)
            .resistance(5f)
            .breakByTool(FabricToolTags.PICKAXES, 3)
            .build());

    public static void init() {
        Registry.register(Registry.BLOCK, new Identifier(IncaMod.MOD_ID, "nautilus_ore"), NAUTILUS_ORE);
        Registry.register(Registry.ITEM,
                new Identifier(IncaMod.MOD_ID, "nautilus_ore"),
                new BlockItem(NAUTILUS_ORE, new Item.Settings().group(IncaMod.ITEM_GROUP)));

    }
}
