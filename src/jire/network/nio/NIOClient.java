package jire.network.nio;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

import jire.network.AbstractClient;
import jire.network.Server;
import jire.packet.InputPacketBuffer;
import jire.packet.OutputPacketBuffer;
import jire.packet.Packet;

final class NIOClient extends AbstractClient {

	static final int DEFAULT_BUFFER_ALLOCATION = 512;

	private final SelectionKey key;
	private final SocketChannel channel;

	private final InputPacketBuffer inputBuffer;

	NIOClient(Server server, SelectionKey key, SocketChannel channel,
			InputPacketBuffer inputBuffer) {
		super(server);
		this.key = key;
		this.channel = channel;
		this.inputBuffer = inputBuffer;
	}

	NIOClient(Server server, SelectionKey key, SocketChannel channel) {
		this(server, key, channel, new InputPacketBuffer(new byte[DEFAULT_BUFFER_ALLOCATION]));
	}

	NIOClient(Server server, SelectionKey key) {
		this(server, key, (SocketChannel) key.channel());
	}

	synchronized SelectionKey getKey() {
		return key;
	}

	synchronized SocketChannel getChannel() {
		return channel;
	}

	synchronized InputPacketBuffer getInputBuffer() {
		return inputBuffer;
	}

	@Override
	public void write(Packet packet) {
		OutputPacketBuffer buffer = getServer().getPacketTranslator().encode(packet);
		buffer.flip();

		try {
			channel.write(buffer.getBacking());
		} catch (IOException e) {
			disconnect();
		}
	}

	@Override
	public boolean disconnect() {
		try {
			getChannel().close();
			getKey().cancel();
		} catch (IOException e) {
			return false;
		}

		return true;
	}

}