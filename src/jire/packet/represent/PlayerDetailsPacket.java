package jire.packet.represent;

import jire.packet.PacketRepresentation;

public final class PlayerDetailsPacket implements PacketRepresentation {

	private final int index;
	private final boolean member;

	private PlayerDetailsPacket(int index, boolean member) {
		this.index = index;
		this.member = member;
	}

	public int getIndex() {
		return index;
	}

	public boolean isMember() {
		return member;
	}

	public static PlayerDetailsPacket get(int index, boolean member) {
		return new PlayerDetailsPacket(index, member);
	}

}