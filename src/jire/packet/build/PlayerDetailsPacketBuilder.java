package jire.packet.build;

import jire.packet.OutputPacketBuffer;
import jire.packet.Packet;
import jire.packet.PacketBuffer.ByteOrder;
import jire.packet.PacketBuffer.ValueType;
import jire.packet.PacketBuilder;
import jire.packet.PacketRepresentation;
import jire.packet.reflective.BuildsPacket;
import jire.packet.represent.PlayerDetailsPacket;

@BuildsPacket(PlayerDetailsPacket.class)
public final class PlayerDetailsPacketBuilder implements PacketBuilder {

	@Override
	public Packet build(PacketRepresentation packetRep) {
		PlayerDetailsPacket packet = (PlayerDetailsPacket) packetRep;

		OutputPacketBuffer output = new OutputPacketBuffer(1 + 2);
		output.writeBoolean(packet.isMember());
		output.writeShort(packet.getIndex(), ValueType.A, ByteOrder.LITTLE);

		return new Packet(249, output.array());
	}

}