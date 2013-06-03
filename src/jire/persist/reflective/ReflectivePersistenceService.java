package jire.persist.reflective;

import java.lang.reflect.Field;

import jire.persist.Persistable;
import jire.persist.PersistenceService;

public final class ReflectivePersistenceService implements PersistenceService {

	@Override
	public void persist(Persistable persistable) {
		for (Field field : persistable.getClass().getDeclaredFields()) {
			if (field.isAnnotationPresent(Persists.class)) {
				// TODO: Perform real persistence operation
			}
		}
	}

}