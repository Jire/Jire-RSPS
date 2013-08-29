package jire.world;

public final class ChatMessage {

	private final String message;
	private final int effects, color;

	public ChatMessage(String message, int effects, int color) {
		this.message = message;
		this.effects = effects;
		this.color = color;
	}

	public String getMessage() {
		return message;
	}

	public int getEffects() {
		return effects;
	}

	public int getColor() {
		return color;
	}

}