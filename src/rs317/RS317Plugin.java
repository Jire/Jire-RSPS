package rs317;

import jire.event.EventHandler;
import jire.event.EventListener;
import jire.event.EventPriority;
import jire.packet.PacketParseEvent;
import jire.packet.PacketRepresentation;
import jire.packet.represent.HandshakeResponsePacket;
import jire.packet.represent.LoginResponsePacket;
import jire.plugin.AbstractPlugin;
import jire.plugin.PluginManifest;

@PluginManifest(name = "RS317", version = 1.3, description = "Configures the environment to use RS317 packet translation", authors = {
		"Thomas G. P. Nappo", "Ryley M. C. M. Kimmel" })
public final class RS317Plugin extends AbstractPlugin implements EventListener {

	@Override
	public void onEnable() {
		getServer().configurePacketTranslator(new RS317Translator());
		getEventManager().registerListener(this);

		getLogger().debug("Successfully configured RS317 packet translation.");
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPacketParse(PacketParseEvent event) {
		PacketRepresentation rep = null;

		switch (event.getPacket().getID()) {
		case 14:
			rep = HandshakeResponsePacket.get(0, 0);
			break;
		case 16:
			rep = LoginResponsePacket.get(2, 0, false);
			break;
		default:
			return;
		}

		event.getClient().write(rep);
	}

}