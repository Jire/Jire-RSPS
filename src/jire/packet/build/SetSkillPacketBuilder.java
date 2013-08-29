package jire.packet.build;

import jire.packet.Packet;
import jire.packet.PacketBuilder;
import jire.packet.PacketRepresentation;
import jire.packet.RSOutputStream;
import jire.packet.reflective.BuildsPacket;
import jire.packet.represent.SetSkillPacket;

@BuildsPacket(SetSkillPacket.class)
public class SetSkillPacketBuilder implements PacketBuilder {

	@Override
	public Packet build(PacketRepresentation packetRep) {
		SetSkillPacket packet = (SetSkillPacket) packetRep;
		RSOutputStream output = new RSOutputStream();

		output.write(packet.getSkillID());
		output.writeInteger1(packet.getExperience());
		output.write(packet.getLevel());

		return new Packet(134, output.getData());
	}

}