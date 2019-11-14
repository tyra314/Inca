package tyra314.inca.network;

import io.netty.buffer.Unpooled;
import net.fabricmc.api.EnvType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.PacketByteBuf;
import tyra314.inca.IncaMod;

import java.io.IOException;

/**
 * Inherit from this class for packets that gets send in both directions
 */
public abstract class AbstractPacket<T extends AbstractPacket> {

    boolean requiresMainThread() {
        return true;
    }

    public boolean isValidInEnvironment(EnvType env) {
        return true;
    }

    public abstract void read(PacketByteBuf packetByteBuf) throws IOException;

    public abstract void write(PacketByteBuf packetByteBuf) throws IOException;

    public abstract void process(PlayerEntity player, EnvType side);


    PacketByteBuf toByteBuf() {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        try {
            write(buf);
        } catch (IOException e) {
            IncaMod.LOG.warn("Error sending packet", e);
        }
        return buf;
    }

    /**
     * Inherit from this class for packets that gets send from the client to the server
     */
    public static abstract class AbstractClientPacket<T extends AbstractPacket<T>> extends AbstractPacket<T> {
        @Override
        public boolean isValidInEnvironment(EnvType env) {
            return env == EnvType.CLIENT;
        }
    }

    /**
     * Inherit from this class for packets that gets send from the client to the server
     */
    public static abstract class AbstractServerPacket<T extends AbstractPacket<T>> extends AbstractPacket<T> {
        @Override
        public boolean isValidInEnvironment(EnvType env) {
            return env == EnvType.SERVER;
        }
    }
}
