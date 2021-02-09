package tyra314.inca.handler;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import tyra314.inca.IncaMod;
import tyra314.inca.network.c2s.LlamaSpitAttackPacket;

@Environment(EnvType.CLIENT)
public class KeyBinds
{
    private static KeyBinding spitKeyBinding;


    @Environment(EnvType.CLIENT)
    public static void init()
    {
        IncaMod.LOG.info("Setting up key bindings...");

        spitKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.inca.spit",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_G,
                "category.inca.main"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (spitKeyBinding.wasPressed())
            {
                handleSpitKey();
            }
        });
    }

    @Environment(EnvType.CLIENT)
    private static void handleSpitKey()
    {
        new LlamaSpitAttackPacket().sendToServer();
    }
}
