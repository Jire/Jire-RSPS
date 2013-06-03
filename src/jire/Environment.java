package jire;

import jire.event.EventManager;
import jire.logging.Logger;
import jire.network.Server;
import jire.persist.PersistenceService;
import jire.plugin.PluginManager;
import jire.task.TaskService;

public interface Environment {

	static final int SUGGESTED_CYCLE_LENGTH = 600;
	static final int SUGGESTED_PORT = 43594;

	static final Environment environment = new UniversalEnvironment();

	TaskService getTaskService();

	Server getServer();

	EventManager getEventManager();

	PluginManager getPluginManager();

	PersistenceService getPersistenceService();

	Logger getLogger();

	Environment configureTaskService(TaskService taskService);

	Environment configureServer(Server server);

	Environment configureEventManager(EventManager eventManager);

	Environment configurePluginManager(PluginManager pluginManager);

	Environment configurePersistenceService(
			PersistenceService persistenceService);

	Environment configureLogger(Logger logger);

}