package jire.packet;

import jire.network.Client;
import jire.network.ClientEvent;

public abstract class PacketServiceEvent extends ClientEvent {

	private final PacketService service;
	private final Packet packet;
	private final PacketRepresentation packetRepresentation;

	protected PacketServiceEvent(Client client, PacketService service,
			Packet packet, PacketRepresentation packetRepresentation) {
		super(client);
		this.service = service;
		this.packet = packet;
		this.packetRepresentation = packetRepresentation;
	}

	public final PacketService getService() {
		return service;
	}

	public final Packet getPacket() {
		return packet;
	}

	public final PacketRepresentation getPacketRepresentation() {
		return packetRepresentation;
	}

}