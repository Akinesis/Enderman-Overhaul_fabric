package tech.alexnijjar.endermanoverhaul.common.network;

import com.teamresourceful.resourcefullib.common.network.Network;
import net.minecraft.resources.ResourceLocation;
import tech.alexnijjar.endermanoverhaul.EndermanOverhaul;
import tech.alexnijjar.endermanoverhaul.common.network.messages.ClientboundFlashScreenPacket;

public final class NetworkHandler {
    public static final Network CHANNEL = new Network(ResourceLocation.fromNamespaceAndPath(EndermanOverhaul.MOD_ID, "main"), 1);

    public static void init() {
        CHANNEL.register(ClientboundFlashScreenPacket.TYPE);
    }
}
