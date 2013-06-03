package jire.network;

import java.util.UUID;

import jire.packet.Packet;
import jire.packet.PacketRepresentation;

public interface Client {

	static final int SUGGESTED_CONCURRENT_LIMIT = 2000;

	Server getServer();

	UUID getUUID();

	void write(Packet packet);

	void write(PacketRepresentation packetRep);

	boolean disconnect();

}