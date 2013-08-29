package jire.packet.parse;

import jire.packet.Packet;
import jire.packet.PacketParser;
import jire.packet.PacketRepresentation;
import jire.packet.RSInputStream;
import jire.packet.reflective.ParsesPacket;
import jire.packet.represent.WalkPacket;

@ParsesPacket({ 98, 164, 248 })
public final class WalkPacketParser implements PacketParser {

	@Override
	public PacketRepresentation parse(Packet packet) {
		RSInputStream input = new RSInputStream(packet.getData());

		int size = packet.getLength();
		if (packet.getID() == 248) {
			size -= 14;
		}

		int steps = (size - 5) / 2;
		if (steps < 0)
			steps = 0;
		int[][] path = new int[steps][2];

		int firstX = input.readLEShortA();
		for (int i = 0; i < steps; i++) {
			path[i][0] = (byte) input.read();
			path[i][1] = (byte) input.read();
		}
		int firstY = input.readLEShort();
		boolean runSteps = input.readByteC() == 1;

		return WalkPacket.get(steps, runSteps, path, firstX, firstY);
	}

}