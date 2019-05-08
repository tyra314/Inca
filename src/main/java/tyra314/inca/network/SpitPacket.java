package tyra314.inca.network;

import com.raphydaphy.crochet.network.IPacket;
import com.raphydaphy.crochet.network.MessageHandler;
import net.fabricmc.fabric.api.network.PacketContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.LlamaSpitEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import tyra314.inca.IncaMod;

public class SpitPacket implements IPacket
{
    public static final Identifier ID = new Identifier(IncaMod.MOD_ID, "spit");

    public SpitPacket()
    {
    }

    @Override
    public void read(PacketByteBuf packetByteBuf)
    {

    }

    @Override
    public void write(PacketByteBuf packetByteBuf)
    {

    }

    @Override
    public Identifier getID()
    {
        return ID;
    }


    public static class Handler extends MessageHandler<SpitPacket>
    {
        @Override
        protected SpitPacket create()
        {
            return new SpitPacket();
        }

        @Override
        public void handle(PacketContext ctx, SpitPacket message)
        {
            PlayerEntity player = ctx.getPlayer();

            ctx.getTaskQueue().execute(() -> {


                        Entity vehicle = player.getVehicle();

                        if (vehicle instanceof LlamaEntity)
                        {
                            LlamaEntity llama = (LlamaEntity) vehicle;

                            if (!llama.isTame() || !((ISpitter) llama).canSpit())
                            {
                                return;
                            }

                            World world = player.world;

                            LlamaSpitEntity spit = new LlamaSpitEntity(world, llama);

                            Vec3d rot = player.getRotationVector();

                            float strength = MathHelper.sqrt(rot.x * rot.x + rot.z * rot.z) * 0.2F;
                            spit.setVelocity(rot.x, rot.y + (double) strength, rot.z, 1.5F, 10.0F);
                            world.playSound(null,
                                    llama.x,
                                    llama.y,
                                    llama.z,
                                    SoundEvents.ENTITY_LLAMA_SPIT,
                                    llama.getSoundCategory(),
                                    1.0F,
                                    1.0F + (world.random.nextFloat() - world.random.nextFloat()) * 0.2F);
                            world.spawnEntity(spit);
                            ((ISpitter) llama).spit();
                        }
                    }
            );
        }
    }
}
