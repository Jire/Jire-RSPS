package jire.event;

public class EventListenerRegisteredEvent extends EventListenerEvent {

	public EventListenerRegisteredEvent(EventManager manager,
			EventListener listener) {
		super(manager, listener);
	}

}