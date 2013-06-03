package jire;

import jire.event.EventManager;
import jire.logging.Logger;
import jire.network.Server;
import jire.persist.PersistenceService;
import jire.plugin.PluginManager;
import jire.task.TaskService;

public abstract class EnvironmentForwarder implements Environment {

	private final Environment environment;

	protected EnvironmentForwarder(Environment environment) {
		this.environment = environment;
	}

	@Override
	public TaskService getTaskService() {
		return environment.getTaskService();
	}

	@Override
	public Server getServer() {
		return environment.getServer();
	}

	@Override
	public EventManager getEventManager() {
		return environment.getEventManager();
	}

	@Override
	public PluginManager getPluginManager() {
		return environment.getPluginManager();
	}

	@Override
	public PersistenceService getPersistenceService() {
		return environment.getPersistenceService();
	}

	@Override
	public Logger getLogger() {
		return environment.getLogger();
	}

	@Override
	public Environment configureTaskService(TaskService taskService) {
		return environment.configureTaskService(taskService);
	}

	@Override
	public Environment configureServer(Server server) {
		return environment.configureServer(server);
	}

	@Override
	public Environment configureEventManager(EventManager eventManager) {
		return environment.configureEventManager(eventManager);
	}

	@Override
	public Environment configurePluginManager(PluginManager pluginManager) {
		return environment.configurePluginManager(pluginManager);
	}

	@Override
	public Environment configurePersistenceService(
			PersistenceService persistenceService) {
		return environment.configurePersistenceService(persistenceService);
	}

	@Override
	public Environment configureLogger(Logger logger) {
		return environment.configureLogger(logger);
	}

}