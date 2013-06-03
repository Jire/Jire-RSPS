package jire.packet.represent;

import jire.packet.PacketRepresentation;

public final class PingPacket implements PacketRepresentation {

	private PingPacket() {
	}

	public static PingPacket get() {
		return new PingPacket();
	}

}