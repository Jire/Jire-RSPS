package jire.packet.represent;

import jire.packet.PacketRepresentation;

public final class HandshakePacket implements PacketRepresentation {

	private final int nameHash;

	private HandshakePacket(int nameHash) {
		this.nameHash = nameHash;
	}

	public int getNameHash() {
		return nameHash;
	}

	public static HandshakePacket get(int nameHash) {
		return new HandshakePacket(nameHash);
	}

}