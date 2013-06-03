package jire.packet.represent;

import jire.player.Player;

public final class PlayerUpdatePacket extends PlayerPacket {

	private PlayerUpdatePacket(Player player) {
		super(player);
	}

	public static PlayerUpdatePacket get(Player player) {
		return new PlayerUpdatePacket(player);
	}

}