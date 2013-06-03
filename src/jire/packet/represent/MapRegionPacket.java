package jire.packet.represent;

import jire.packet.PacketRepresentation;
import jire.world.Position;

public final class MapRegionPacket implements PacketRepresentation {

	private final Position position;

	private MapRegionPacket(Position position) {
		this.position = position;
	}

	public Position getPosition() {
		return position;
	}

	public static MapRegionPacket get(Position position) {
		return new MapRegionPacket(position);
	}

}