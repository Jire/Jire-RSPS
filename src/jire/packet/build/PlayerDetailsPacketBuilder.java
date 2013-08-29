package jire.packet.build;

import jire.packet.Packet;
import jire.packet.PacketBuilder;
import jire.packet.PacketRepresentation;
import jire.packet.RSOutputStream;
import jire.packet.reflective.BuildsPacket;
import jire.packet.represent.PlayerDetailsPacket;

@BuildsPacket(PlayerDetailsPacket.class)
public final class PlayerDetailsPacketBuilder implements PacketBuilder {

	@Override
	public Packet build(PacketRepresentation packetRep) {
		PlayerDetailsPacket packet = (PlayerDetailsPacket) packetRep;

		RSOutputStream output = new RSOutputStream(1 + 2);
		output.writeByteA(packet.isMember() ? 1 : 0);
		output.writeLEShortA(packet.getIndex());

		return new Packet(249, output.getData());
	}

}