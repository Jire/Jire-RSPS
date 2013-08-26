package jire.world;

public abstract class Entity {

	private Position position;

	public final void setPosition(Position position) {
		synchronized (this.position) {
			this.position = position;
		}
	}

	public final Position getPosition() {
		synchronized (position) {
			return position;
		}
	}

}