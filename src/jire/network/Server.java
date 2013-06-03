package jire.network;

import java.util.Set;

import jire.packet.PacketService;
import jire.packet.translate.PacketTranslator;

public interface Server {

	int getPort();

	Set<Client> getClients();

	boolean start();

	boolean stop();

	PacketService getPacketService();

	PacketTranslator getPacketTranslator();

	Server configurePacketService(PacketService packetService);

	Server configurePacketTranslator(PacketTranslator packetTranslator);

}