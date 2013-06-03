package jire.packet.represent;

import jire.packet.PacketRepresentation;

public final class LoginResponsePacket implements PacketRepresentation {

	private final int responseCode;
	private final int crownCode;
	private final boolean flagged;

	private LoginResponsePacket(int responseCode, int crownCode, boolean flagged) {
		this.responseCode = responseCode;
		this.crownCode = crownCode;
		this.flagged = flagged;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public int getCrownCode() {
		return crownCode;
	}

	public boolean isFlagged() {
		return flagged;
	}

	public static LoginResponsePacket get(int responseCode, int crownCode,
			boolean flagged) {
		return new LoginResponsePacket(responseCode, crownCode, flagged);
	}

}