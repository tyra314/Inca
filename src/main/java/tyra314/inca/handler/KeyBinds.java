package tyra314.inca.handler;

import com.raphydaphy.crochet.network.PacketHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keybinding.FabricKeyBinding;
import net.fabricmc.fabric.api.client.keybinding.KeyBindingRegistry;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.LlamaSpitEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.lwjgl.glfw.GLFW;
import tyra314.inca.IncaMod;
import tyra314.inca.network.SpitPacket;

public class KeyBinds
{
    private static FabricKeyBinding spitKeyBinding;


    @Environment(EnvType.CLIENT)
    public static void init()
    {
        IncaMod.LOG.info("Setting up keybinds...");

        spitKeyBinding = FabricKeyBinding.Builder.create(
                new Identifier(IncaMod.MODID, "spit"),
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_G,
                IncaMod.MODID
        ).build();

        KeyBindingRegistry.INSTANCE.addCategory(IncaMod.MODID);
        KeyBindingRegistry.INSTANCE.register(spitKeyBinding);

        ClientTickCallback.EVENT.register(e ->
        {
            if (spitKeyBinding.isPressed())
            {
                handleSpitKeybind();
            }
        });
    }

    /**
     * If this keybinding is pressed, we check if the player rides a llama and if he does, we let it spit in the direction the player is looking
     */
    private static void handleSpitKeybind()
    {
        PacketHandler.sendToServer(new SpitPacket());
    }
}
