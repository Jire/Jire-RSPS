package jire.packet.build;

import jire.packet.OutputPacketBuffer;
import jire.packet.Packet;
import jire.packet.PacketBuilder;
import jire.packet.PacketRepresentation;
import jire.packet.reflective.BuildsPacket;
import jire.packet.represent.LoginResponsePacket;

@BuildsPacket(LoginResponsePacket.class)
public final class LoginResponseBuilder implements PacketBuilder {

	@Override
	public Packet build(PacketRepresentation packetRep) {
		LoginResponsePacket packet = (LoginResponsePacket) packetRep;

		OutputPacketBuffer output = new OutputPacketBuffer(1 + 1 + 1);
		output.write(packet.getResponseCode());
		if (packet.getResponseCode() == 2) {
			output.writeByte(packet.getCrownCode());
			output.writeBoolean(packet.isFlagged());
		}

		return new Packet(output.array());
	}

}