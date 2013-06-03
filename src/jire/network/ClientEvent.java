package jire.network;

import jire.event.Event;

public abstract class ClientEvent implements Event {

	private final Client client;

	protected ClientEvent(Client client) {
		this.client = client;
	}

	public final Client getClient() {
		return client;
	}

}