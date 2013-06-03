package jire.packet.represent;

import jire.packet.PacketRepresentation;

public final class IdleLogoutPacket implements PacketRepresentation {

	private IdleLogoutPacket() {
	}

	public static IdleLogoutPacket get() {
		return new IdleLogoutPacket();
	}

}