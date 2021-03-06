package tyra314.inca.network.c2s;

import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.LlamaSpitEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import tyra314.inca.IncaMod;
import tyra314.inca.mixin.interfaces.ISpitter;
import tyra314.inca.network.AbstractPacket;

public class LlamaSpitAttackPacket extends AbstractPacket.C2SPacket<LlamaSpitAttackPacket>
{
    public static final Identifier ID = new Identifier(IncaMod.MOD_ID, "llama_spit_attack");

    public LlamaSpitAttackPacket() {
    }

    @Override
    public Identifier getId() {
        return ID;
    }

    @Override
    public void write(PacketByteBuf packetByteBuf) {
    }

    public static void apply(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        server.execute(() -> {
            Entity vehicle = player.getVehicle();

            if (vehicle instanceof LlamaEntity) {
                LlamaEntity llama = (LlamaEntity) vehicle;

                if (!llama.isTame() || !((ISpitter) llama).canSpit()) {
                    return;
                }

                World world = player.world;

                LlamaSpitEntity spit = new LlamaSpitEntity(world, llama);
                spit.setOwner(player);

                Vec3d rot = player.getRotationVector();

                float strength = MathHelper.sqrt(rot.x * rot.x + rot.z * rot.z) * 0.2F;
                spit.setVelocity(rot.x, rot.y + (double) strength, rot.z, 1.5F, 10.0F);
                world.playSound(null,
                        llama.getX(),
                        llama.getY(),
                        llama.getZ(),
                        SoundEvents.ENTITY_LLAMA_SPIT,
                        llama.getSoundCategory(),
                        1.0F,
                        1.0F + (world.random.nextFloat() - world.random.nextFloat()) * 0.2F);
                world.spawnEntity(spit);
                ((ISpitter) llama).spit();
            }
        });
    }
}
