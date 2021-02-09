package tyra314.inca.network;

import io.netty.buffer.Unpooled;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import tyra314.inca.IncaMod;
import tyra314.inca.network.c2s.LlamaSpitAttackPacket;

import java.io.IOException;

/**
 * Inherit from this class for packets that gets send in both directions
 */
public abstract class AbstractPacket<T extends AbstractPacket> {
    public abstract Identifier getId();

    public abstract void write(PacketByteBuf packetByteBuf) throws IOException;

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
    public static abstract class C2SPacket<T extends AbstractPacket<T>> extends AbstractPacket<T> {
        public void sendToServer() {
            ClientPlayNetworking.send(getId(), toByteBuf());
        }
    }

    /**
     * Inherit from this class for packets that gets send from the server to the client
     */
    public static abstract class S2CPacket<T extends AbstractPacket<T>> extends AbstractPacket<T> {
        public void sendTo(ServerPlayerEntity player) {
            ServerPlayNetworking.send(player, getId(), toByteBuf());
        }
    }
}
