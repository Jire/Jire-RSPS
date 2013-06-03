package jire.event;

public abstract class EventManagerEvent implements Event {

	private final EventManager manager;

	protected EventManagerEvent(EventManager manager) {
		this.manager = manager;
	}

	public final EventManager getManager() {
		return manager;
	}

}