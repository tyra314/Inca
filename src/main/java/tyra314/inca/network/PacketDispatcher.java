package tyra314.inca.network;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.fabricmc.fabric.api.network.PacketRegistry;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.fabricmc.fabric.api.server.PlayerStream;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import tyra314.inca.IncaMod;
import tyra314.inca.network.server.LlamaSpitAttackPacket;

import java.util.HashSet;
import java.util.Set;

public class PacketDispatcher {
    private static BiMap<Identifier, Class> PACKETS = HashBiMap.create();
    private static final Set<Identifier> CLIENT_PACKETS = new HashSet<>();
    private static final Set<Identifier> SERVER_PACKETS = new HashSet<>();

    private PacketDispatcher() {
    }

    public static void registerPackets() {
        registerPacket(LlamaSpitAttackPacket.ID, LlamaSpitAttackPacket.class);


        setupPacketConsumer(SERVER_PACKETS, ServerSidePacketRegistry.INSTANCE);
    }

    public static void registerClientPackets() {
        setupPacketConsumer(CLIENT_PACKETS, ClientSidePacketRegistry.INSTANCE);
    }

    private static void setupPacketConsumer(Set<Identifier> packets, PacketRegistry registry) {
        packets.forEach((id) -> {
            Class<?> c = PACKETS.get(id);
            registry.register(id, (context, buffer) -> {
                try {
                    AbstractPacket<?> message = (AbstractPacket<?>) c.getDeclaredConstructor().newInstance();

                    if (!message.isValidInEnvironment(context.getPacketEnvironment())) {
                        throw new RuntimeException("Packet is not valid on this side.");
                    }

                    message.read(buffer);

                    if (message.requiresMainThread()) {
                        context.getTaskQueue().execute(() -> message.process(context.getPlayer(), context.getPacketEnvironment()));
                    } else {
                        message.process(context.getPlayer(), context.getPacketEnvironment());
                    }
                } catch (Exception e) {
                    IncaMod.LOG.error("Couldn't parse incoming message: " + id.toString(), e);
                }
            });
        });
    }

    private static void registerPacket(Identifier id, Class cls) {
        PACKETS.put(id, cls);

        if (AbstractPacket.AbstractClientPacket.class.isAssignableFrom(cls)) {
            CLIENT_PACKETS.add(id);
        } else if (AbstractPacket.AbstractServerPacket.class.isAssignableFrom(cls)) {
            SERVER_PACKETS.add(id);
        } else {
            CLIENT_PACKETS.add(id);
            SERVER_PACKETS.add(id);
        }
    }

    /**
     * Send this message to everyone.
     */
    public static void sendToAll(MinecraftServer server, AbstractPacket<?> message) {
        PlayerStream.all(server).forEach(p -> ServerSidePacketRegistry.INSTANCE.sendToPlayer(p,
                PACKETS.inverse().get(message.getClass()),
                message.toByteBuf()
        ));
    }

    /**
     * Send this message to the specified player.
     */
    public static void sendTo(ServerPlayerEntity player, AbstractPacket<?> message) {
        ServerSidePacketRegistry.INSTANCE.sendToPlayer(player,
                PACKETS.inverse().get(message.getClass()),
                message.toByteBuf()
        );
    }

    /**
     * Send this message to the server.
     */
    @Environment(EnvType.CLIENT)
    public static void sendToServer(AbstractPacket<?> message) {
        ClientSidePacketRegistry.INSTANCE.sendToServer(
                PACKETS.inverse().get(message.getClass()),
                message.toByteBuf()
        );
    }
}
