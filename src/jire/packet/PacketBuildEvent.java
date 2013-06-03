package jire.packet;

import jire.network.Client;

public class PacketBuildEvent extends PacketServiceEvent {

	public PacketBuildEvent(Client client, PacketService service,
			Packet packet, PacketRepresentation packetRepresentation) {
		super(client, service, packet, packetRepresentation);
	}

}