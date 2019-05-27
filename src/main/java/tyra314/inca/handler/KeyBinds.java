package tyra314.inca.handler;

import com.raphydaphy.crochet.network.PacketHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keybinding.FabricKeyBinding;
import net.fabricmc.fabric.api.client.keybinding.KeyBindingRegistry;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;
import tyra314.inca.IncaMod;
import tyra314.inca.network.SpitPacket;

@Environment(EnvType.CLIENT)
public class KeyBinds
{
    private static FabricKeyBinding spitKeyBinding;


    @Environment(EnvType.CLIENT)
    public static void init()
    {
        IncaMod.LOG.info("Setting up key bindings...");

        spitKeyBinding = FabricKeyBinding.Builder.create(
                new Identifier(IncaMod.MOD_ID, "spit"),
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_G,
                IncaMod.MOD_NAME
        ).build();

        KeyBindingRegistry.INSTANCE.addCategory(IncaMod.MOD_NAME);
        KeyBindingRegistry.INSTANCE.register(spitKeyBinding);

        ClientTickCallback.EVENT.register(e ->
        {
            if (spitKeyBinding.isPressed())
            {
                handleSpitKey();
            }
        });
    }

    /**
     * If this key binding is pressed, we check if the player rides a llama and if he does, we let it spit in the direction the player is looking
     */
    @Environment(EnvType.CLIENT)
    private static void handleSpitKey()
    {
        PacketHandler.sendToServer(new SpitPacket());
    }
}
