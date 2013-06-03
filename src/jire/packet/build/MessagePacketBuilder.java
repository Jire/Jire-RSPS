package jire.packet.build;

import jire.packet.OutputPacketBuffer;
import jire.packet.Packet;
import jire.packet.PacketBuilder;
import jire.packet.PacketRepresentation;
import jire.packet.reflective.BuildsPacket;
import jire.packet.represent.MessagePacket;

@BuildsPacket(MessagePacket.class)
public final class MessagePacketBuilder implements PacketBuilder {

	@Override
	public Packet build(PacketRepresentation packetRep) {
		MessagePacket packet = (MessagePacket) packetRep;

		OutputPacketBuffer output = new OutputPacketBuffer(packet.getContent()
				.getBytes().length + 1);
		output.writeString(packet.getContent());

		return new Packet(253, output.array());
	}

}