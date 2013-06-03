package jire.packet.parse;

import jire.packet.Packet;
import jire.packet.PacketParser;
import jire.packet.PacketRepresentation;
import jire.packet.reflective.ParsesPacket;
import jire.packet.represent.IdleLogoutPacket;

@ParsesPacket(202)
public final class IdleLogoutPacketParser implements PacketParser {

	@Override
	public PacketRepresentation parse(Packet packet) {
		return IdleLogoutPacket.get();
	}

}