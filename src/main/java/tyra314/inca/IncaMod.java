package tyra314.inca;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tyra314.inca.item.ModItems;
import tyra314.inca.network.PacketDispatcher;

public class IncaMod implements ModInitializer
{
    public static final String SHARED_NAMESPACE = "c";
    public static final String MOD_ID = "inca";
    public static final String MOD_NAME = "Inca";

    public static final Logger LOG = LogManager.getLogger(MOD_ID);


    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier(MOD_ID, "general"),
            () -> new ItemStack(ModItems.LLAMA_SPIT_BOTTLE));

    @Override
    public void onInitialize()
    {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        LOG.info("Initializing Inca Mod...");

        ModItems.init();

        PacketDispatcher.registerPackets();
    }
}
