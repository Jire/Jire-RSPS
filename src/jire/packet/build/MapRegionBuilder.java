package jire.packet.build;

import jire.packet.Packet;
import jire.packet.PacketBuilder;
import jire.packet.PacketRepresentation;
import jire.packet.RSOutputStream;
import jire.packet.reflective.BuildsPacket;
import jire.packet.represent.MapRegionPacket;

@BuildsPacket(MapRegionPacket.class)
public final class MapRegionBuilder implements PacketBuilder {

	@Override
	public Packet build(PacketRepresentation packetRep) {
		MapRegionPacket packet = (MapRegionPacket) packetRep;

		RSOutputStream output = new RSOutputStream(2 + 2);
		output.writeShortA(packet.getPosition().getRegionX() + 6);
		output.writeShort(packet.getPosition().getRegionY() + 6);

		return new Packet(73, output.getData());
	}

}