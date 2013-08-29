package jire.packet.build;

import jire.packet.Packet;
import jire.packet.PacketBuilder;
import jire.packet.PacketRepresentation;
import jire.packet.RSOutputStream;
import jire.packet.reflective.BuildsPacket;
import jire.packet.represent.LoginResponsePacket;

@BuildsPacket(LoginResponsePacket.class)
public final class LoginResponseBuilder implements PacketBuilder {

	@Override
	public Packet build(PacketRepresentation packetRep) {
		LoginResponsePacket packet = (LoginResponsePacket) packetRep;

		RSOutputStream output = new RSOutputStream(1 + 1 + 1);
		output.write(packet.getResponseCode());
		if (packet.getResponseCode() == 2) {
			output.write(packet.getCrownCode());
			output.writeBit(packet.isFlagged());
		}

		return new Packet(output.getData());
	}

}