package jire.event;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public final class EventRegistry {

	private final Map<Integer, Map<Method, EventListener>> backing = new HashMap<Integer, Map<Method, EventListener>>();

	public synchronized void register(Class<? extends Event> event,
			EventPriority priority, boolean ignoreCancellation, Method method,
			EventListener listener) {
		final int key = createKey(event, priority, ignoreCancellation);

		Map<Method, EventListener> methodMap = backing.get(key);
		if (methodMap == null) {
			backing.put(key, methodMap = new HashMap<Method, EventListener>());
		}
		methodMap.put(method, listener);

		backing.put(key, methodMap);
	}

	public synchronized Map<Method, EventListener> getMethodMap(
			Class<? extends Event> event, EventPriority priority,
			boolean ignoreCancellation) {
		return backing.get(createKey(event, priority, ignoreCancellation));
	}

	private static int createKey(Class<? extends Event> event,
			EventPriority priority, boolean ignoreCancellation) {
		return (event.getName() + "-" + priority.name() + ":" + ignoreCancellation)
				.hashCode();
	}

}