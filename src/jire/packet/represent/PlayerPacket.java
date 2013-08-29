package jire.packet.represent;

import jire.packet.PacketRepresentation;
import jire.player.Player;

public abstract class PlayerPacket implements PacketRepresentation {

	private final Player player;

	protected PlayerPacket(Player player) {
		this.player = player;
	}

	public final Player getPlayer() {
		return player;
	}

}