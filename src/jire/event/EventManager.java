package jire.event;

public interface EventManager {

	void dispatchEvent(Event event);

	void registerListener(EventListener listener);

}