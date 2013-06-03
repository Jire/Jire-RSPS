package jire.packet.represent;

import jire.packet.PacketRepresentation;

public final class LogoutPacket implements PacketRepresentation {

	private LogoutPacket() {
	}

	public static LogoutPacket get() {
		return new LogoutPacket();
	}

}