package jire.packet.build;

import jire.packet.OutputPacketBuffer;
import jire.packet.Packet;
import jire.packet.PacketBuffer.ValueType;
import jire.packet.PacketBuilder;
import jire.packet.PacketRepresentation;
import jire.packet.reflective.BuildsPacket;
import jire.packet.represent.MapRegionPacket;

@BuildsPacket(MapRegionPacket.class)
public final class MapRegionBuilder implements PacketBuilder {

	@Override
	public Packet build(PacketRepresentation packetRep) {
		MapRegionPacket packet = (MapRegionPacket) packetRep;

		OutputPacketBuffer output = new OutputPacketBuffer(2 + 2);
		output.writeShort(packet.getPosition().getRegionX() + 6, ValueType.A);
		output.writeShort(packet.getPosition().getRegionY() + 6);

		return new Packet(73, output.array());
	}

}