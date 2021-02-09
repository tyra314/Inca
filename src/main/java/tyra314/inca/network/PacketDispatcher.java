package tyra314.inca.network;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import tyra314.inca.network.c2s.LlamaSpitAttackPacket;

public class PacketDispatcher
{
    public static void registerPackets()
    {
        ServerPlayNetworking.registerGlobalReceiver(LlamaSpitAttackPacket.ID, LlamaSpitAttackPacket::apply);
    }

    public static void registerClientPackets()
    {

    }
}
