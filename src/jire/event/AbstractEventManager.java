package jire.event;

import static jire.Environment.environment;

import java.lang.reflect.Method;

public abstract class AbstractEventManager implements EventManager {

	private final EventRegistry registry;

	public AbstractEventManager(EventRegistry registry) {
		this.registry = registry;
	}

	public AbstractEventManager() {
		this(new EventRegistry());
	}

	protected final EventRegistry getRegistry() {
		return registry;
	}

	@Override
	@SuppressWarnings("unchecked")
	public final void registerListener(EventListener listener) {
		for (Method method : listener.getClass().getMethods()) {
			if (method.isAnnotationPresent(EventHandler.class)
					&& method.getParameterTypes().length == 1
					&& Event.class
							.isAssignableFrom(method.getParameterTypes()[0])) {
				EventHandler eventHandler = method
						.getAnnotation(EventHandler.class);
				/*
				 * Unchecked cast suppressed because it is better to throw an
				 * exception, as it clearly signals something is wrong with the
				 * specified event handler.
				 */
				getRegistry().register(
						(Class<? extends Event>) method.getParameterTypes()[0],
						eventHandler.priority(),
						eventHandler.ignoreCancelled(), method, listener);
			}
		}
		environment.getEventManager().dispatchEvent(
				new EventListenerRegisteredEvent(this, listener));
	}

}