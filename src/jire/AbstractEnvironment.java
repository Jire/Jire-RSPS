package jire;

import jire.event.EventManager;
import jire.logging.Logger;
import jire.network.Server;
import jire.persist.PersistenceService;
import jire.plugin.PluginManager;
import jire.task.TaskService;

public abstract class AbstractEnvironment implements Environment {

	private EventManager eventManager;
	private PluginManager pluginManager;
	private PersistenceService persistenceService = null;
	private Server server;
	private TaskService taskService;
	private Logger logger;

	@Override
	public final synchronized Environment configureEventManager(
			EventManager eventManager) {
		this.eventManager = eventManager;
		return this;
	}

	@Override
	public final synchronized Environment configurePluginManager(
			PluginManager pluginManager) {
		this.pluginManager = pluginManager;
		return this;
	}

	@Override
	public final synchronized Environment configurePersistenceService(
			PersistenceService persistenceService) {
		this.persistenceService = persistenceService;
		return this;
	}

	@Override
	public final synchronized Environment configureServer(Server server) {
		this.server = server;
		return this;
	}

	@Override
	public final synchronized Environment configureTaskService(
			TaskService taskService) {
		this.taskService = taskService;
		return this;
	}

	@Override
	public final synchronized Environment configureLogger(Logger logger) {
		this.logger = logger;
		return this;
	}

	@Override
	public final synchronized TaskService getTaskService() {
		return taskService;
	}

	@Override
	public final synchronized Server getServer() {
		return server;
	}

	@Override
	public final synchronized EventManager getEventManager() {
		return eventManager;
	}

	@Override
	public final synchronized PluginManager getPluginManager() {
		return pluginManager;
	}

	@Override
	public final synchronized PersistenceService getPersistenceService() {
		return persistenceService;
	}

	@Override
	public final synchronized Logger getLogger() {
		return logger;
	}

}