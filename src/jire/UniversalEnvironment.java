package jire;

import jire.event.UniversalEventManager;
import jire.logging.UniversalLogger;
import jire.network.nio.NIOServer;
import jire.packet.reflective.ReflectivePacketService;
import jire.plugin.UniversalPluginManager;
import jire.task.CycledTaskService;

final class UniversalEnvironment extends AbstractEnvironment {

	UniversalEnvironment() {
		configureEventManager(new UniversalEventManager());
		configurePluginManager(new UniversalPluginManager());
		configureTaskService(new CycledTaskService(SUGGESTED_CYCLE_LENGTH));
		configureLogger(new UniversalLogger());
		configureServer(new NIOServer(SUGGESTED_PORT, SUGGESTED_CYCLE_LENGTH));
		getServer().configurePacketService(new ReflectivePacketService());
	}

}