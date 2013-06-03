package jire.network;

import static jire.Environment.environment;
import static jire.network.Client.SUGGESTED_CONCURRENT_LIMIT;

import java.util.HashSet;
import java.util.Set;

import jire.packet.PacketService;
import jire.packet.translate.PacketTranslator;

public abstract class AbstractServer implements Server, Runnable {

	private final int port;
	private PacketService packetService;
	private PacketTranslator packetTranslator;
	private final Set<Client> clients;

	protected AbstractServer(int port, Set<Client> clients) {
		this.port = port;
		this.clients = clients;
	}

	protected AbstractServer(int port) {
		this(port, new HashSet<Client>(SUGGESTED_CONCURRENT_LIMIT));
	}

	@Override
	public boolean start() {
		new Thread(this).start();
		environment.getEventManager().dispatchEvent(new ServerStartEvent(this));
		return true;
	}

	@Override
	public boolean stop() {
		environment.getEventManager().dispatchEvent(new ServerStopEvent(this));
		return true;
	}

	@Override
	public final int getPort() {
		return port;
	}

	@Override
	public final synchronized Set<Client> getClients() {
		return clients;
	}

	@Override
	public final synchronized PacketService getPacketService() {
		return packetService;
	}

	@Override
	public final synchronized PacketTranslator getPacketTranslator() {
		return packetTranslator;
	}

	@Override
	public final synchronized Server configurePacketService(
			PacketService packetService) {
		this.packetService = packetService;
		return this;
	}

	@Override
	public final synchronized Server configurePacketTranslator(
			PacketTranslator packetTranslator) {
		this.packetTranslator = packetTranslator;
		return this;
	}

}