package jire.event;

public abstract class EventListenerEvent extends EventManagerEvent {

	private final EventListener listener;

	protected EventListenerEvent(EventManager manager, EventListener listener) {
		super(manager);
		this.listener = listener;
	}

	public final EventListener getListener() {
		return listener;
	}

}