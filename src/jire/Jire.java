package jire;

import static jire.Environment.environment;
import jire.event.EventHandler;
import jire.event.EventListener;
import jire.event.EventPriority;
import jire.logging.Logger;
import jire.packet.PacketBuildEvent;
import jire.packet.PacketBuilder;
import jire.packet.PacketParseEvent;
import jire.packet.PacketParser;
import jire.packet.build.HandshakeResponseBuilder;
import jire.packet.build.LoginResponseBuilder;
import jire.packet.build.MapRegionBuilder;
import jire.packet.build.MessagePacketBuilder;
import jire.packet.build.PlayerDetailsPacketBuilder;
import jire.packet.build.PlayerUpdatePacketBuilder;
import jire.packet.parse.HandshakePacketParser;
import jire.packet.parse.IdleLogoutPacketParser;
import jire.packet.parse.LoginPacketParser;
import jire.packet.parse.PingPacketParser;
import jire.packet.parse.WalkPacketParser;
import jire.packet.represent.LoginResponsePacket;
import jire.packet.represent.MapRegionPacket;
import jire.packet.represent.MessagePacket;
import jire.packet.represent.PlayerDetailsPacket;
import jire.packet.represent.PlayerUpdatePacket;
import jire.player.Player;
import jire.world.Position;

public final class Jire implements EventListener {

	private static final PacketBuilder[] builders = {
			new HandshakeResponseBuilder(), new LoginResponseBuilder(),
			new PlayerDetailsPacketBuilder(), new MapRegionBuilder(),
			new MessagePacketBuilder(), new PlayerUpdatePacketBuilder() };

	private static final PacketParser[] parsers = {
			new HandshakePacketParser(), new LoginPacketParser(),
			new PingPacketParser(), new IdleLogoutPacketParser(),
			new WalkPacketParser() };

	public static void main(String[] args) {
		for (PacketBuilder builder : builders)
			environment.getServer().getPacketService().registerBuilder(builder);
		for (PacketParser parser : parsers)
			environment.getServer().getPacketService().registerParser(parser);

		environment.getPluginManager().loadPlugins();
		environment.getPluginManager().enablePlugins();

		environment.getEventManager().registerListener(new Jire());

		environment.getServer().start();

		environment.getLogger().configure(Logger.Type.DEBUG, true);
		environment.getLogger().message("Jire is ready!");
	}

	@EventHandler
	public void onPacketParse(PacketParseEvent event) {
		environment.getLogger().debug(
				"Parsed packet: " + event.getPacket().getID());
		if (event.getPacket().getID() == 0) {
			Player player = new Player(event.getClient().getUUID(), 1, "Jire",
					"jire", 2);
			player.setPosition(Position.create(3222, 3222));
			event.getClient().write(PlayerUpdatePacket.get(player));
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onPacketBuild(final PacketBuildEvent event) {
		environment.getLogger().debug(
				"Built packet: " + event.getPacket().getID() + "/"
						+ event.getPacketRepresentation().getClass().getName());
		if (event.getPacketRepresentation() instanceof LoginResponsePacket) {
			event.getClient().write(PlayerDetailsPacket.get(0, true));
			event.getClient().write(
					MapRegionPacket.get(Position.create(3222, 3222)));
			event.getClient().write(MessagePacket.get("Welcome to Jire."));
		}
	}

}