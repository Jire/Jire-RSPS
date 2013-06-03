package jire.packet;

import jire.network.Client;

public class PacketParseEvent extends PacketServiceEvent {

	public PacketParseEvent(Client client, PacketService service,
			Packet packet, PacketRepresentation packetRepresentation) {
		super(client, service, packet, packetRepresentation);
	}

}