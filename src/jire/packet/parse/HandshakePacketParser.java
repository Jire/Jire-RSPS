package jire.packet.parse;

import jire.packet.InputPacketBuffer;
import jire.packet.Packet;
import jire.packet.PacketParser;
import jire.packet.PacketRepresentation;
import jire.packet.reflective.ParsesPacket;
import jire.packet.represent.HandshakePacket;

@ParsesPacket(14)
public final class HandshakePacketParser implements PacketParser {

	@Override
	public PacketRepresentation parse(Packet packet) {
		InputPacketBuffer input = new InputPacketBuffer(packet.getData());

		int nameHash = input.read();

		return HandshakePacket.get(nameHash);
	}

}