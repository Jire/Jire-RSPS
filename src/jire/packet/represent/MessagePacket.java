package jire.packet.represent;

import jire.packet.PacketRepresentation;

public final class MessagePacket implements PacketRepresentation {

	private final String content;

	private MessagePacket(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public static MessagePacket get(String content) {
		return new MessagePacket(content);
	}

}