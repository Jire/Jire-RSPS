package jire.packet.represent;

import jire.packet.PacketRepresentation;

public final class ChatPacket implements PacketRepresentation {

	private final String content;
	private final int effects, color;

	private ChatPacket(String content, int effects, int color) {
		this.content = content;
		this.effects = effects;
		this.color = color;
	}

	private ChatPacket(String content) {
		this(content, 0, 0);
	}

	public String getContent() {
		return content;
	}

	public int getEffects() {
		return effects;
	}

	public int getColor() {
		return color;
	}

	public static ChatPacket get(String content, int effects, int color) {
		return new ChatPacket(content, effects, color);
	}

	public static ChatPacket get(String content) {
		return new ChatPacket(content);
	}

}