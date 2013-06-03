package jire.packet.parse;

import jire.packet.Packet;
import jire.packet.PacketParser;
import jire.packet.PacketRepresentation;
import jire.packet.reflective.ParsesPacket;
import jire.packet.represent.PingPacket;

@ParsesPacket(0)
public final class PingPacketParser implements PacketParser {

	@Override
	public PacketRepresentation parse(Packet packet) {
		return PingPacket.get();
	}

}