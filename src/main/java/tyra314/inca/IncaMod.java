package tyra314.inca;

import io.github.cottonmc.cotton.logging.ModLogger;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import tyra314.inca.block.Blocks;
import tyra314.inca.item.ModItems;
import tyra314.inca.network.SpitPacket;

public class IncaMod implements ModInitializer
{
    public static final String SHARED_NAMESPACE = "c";
    public static final String MOD_ID = "inca";
    public static final String MOD_NAME = "Inca";

    public static final ModLogger LOG = new ModLogger(IncaMod.MOD_ID, IncaMod.MOD_NAME);


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
        Blocks.init();

        ServerSidePacketRegistry.INSTANCE.register(SpitPacket.ID, new SpitPacket.Handler());
    }
}
