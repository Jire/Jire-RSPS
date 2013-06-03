package jire.packet.represent;

import jire.player.Player;

public abstract class PlayerPacket {

	private final Player player;

	protected PlayerPacket(Player player) {
		this.player = player;
	}

	public final Player getPlayer() {
		return player;
	}

}