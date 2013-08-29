package jire.world;

import java.util.EnumSet;

public final class UpdateFlagSet {

	public enum UpdateType {
		GRAPHICS, ANIMATION, FORCED_CHAT, CHAT, FACE_ENTITY, APPEARANCE, FACE_COORDINATE, HIT, HIT_2
	};

	private final EnumSet<UpdateType> flags = EnumSet.allOf(UpdateType.class);

	public EnumSet<UpdateType> getFlags() {
		return flags;
	}

	public boolean isUpdateRequired() {
		return !flags.isEmpty();
	}

}