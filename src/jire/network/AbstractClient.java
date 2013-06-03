package jire.network;

import static jire.Environment.environment;

import java.util.UUID;

import jire.packet.Packet;
import jire.packet.PacketBuildEvent;
import jire.packet.PacketRepresentation;

public abstract class AbstractClient implements Client {

	private final Server server;
	private final UUID uuid;

	protected AbstractClient(Server server, UUID uuid) {
		this.server = server;
		this.uuid = uuid;
	}

	protected AbstractClient(Server server) {
		this(server, UUID.randomUUID());
	}

	@Override
	public final Server getServer() {
		return server;
	}

	@Override
	public final UUID getUUID() {
		return uuid;
	}

	@Override
	public final void write(PacketRepresentation packetRep) {
		Packet packet = getServer().getPacketService().build(packetRep);
		write(packet);

		environment.getEventManager().dispatchEvent(
				new PacketBuildEvent(this, getServer().getPacketService(),
						packet, packetRep));
	}

	@Override
	public final int hashCode() {
		return uuid.hashCode();
	}

	@Override
	public String toString() {
		return uuid.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Client))
			return false;
		return getUUID().equals(((Client) o).getUUID());
	}

}