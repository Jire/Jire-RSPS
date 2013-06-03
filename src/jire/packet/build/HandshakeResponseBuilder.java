package jire.packet.build;

import jire.packet.OutputPacketBuffer;
import jire.packet.Packet;
import jire.packet.PacketBuilder;
import jire.packet.PacketRepresentation;
import jire.packet.reflective.BuildsPacket;
import jire.packet.represent.HandshakeResponsePacket;

@BuildsPacket(HandshakeResponsePacket.class)
public class HandshakeResponseBuilder implements PacketBuilder {

	private static final byte[] INITIAL_RESPONSE = { 0, 0, 0, 0, 0, 0, 0, 0 };

	@Override
	public Packet build(PacketRepresentation packetRep) {
		HandshakeResponsePacket packet = (HandshakeResponsePacket) packetRep;

		OutputPacketBuffer output = new OutputPacketBuffer(
				INITIAL_RESPONSE.length + 1 + 8);
		output.writeBytes(INITIAL_RESPONSE);
		output.write(packet.getResponseCode());
		output.writeLong(packet.getSessionKey());

		return new Packet(output.array());
	}

}