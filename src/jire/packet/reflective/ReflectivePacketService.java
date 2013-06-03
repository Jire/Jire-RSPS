package jire.packet.reflective;

import java.util.HashMap;
import java.util.Map;

import jire.packet.Packet;
import jire.packet.PacketBuilder;
import jire.packet.PacketParser;
import jire.packet.PacketRepresentation;
import jire.packet.PacketService;

public final class ReflectivePacketService implements PacketService {

	private final Map<Integer, PacketParser> parsers;
	private final Map<Class<? extends PacketRepresentation>, PacketBuilder> builders;

	private ReflectivePacketService(Map<Integer, PacketParser> parsers,
			Map<Class<? extends PacketRepresentation>, PacketBuilder> builders) {
		this.parsers = parsers;
		this.builders = builders;
	}

	public ReflectivePacketService() {
		this(
				new HashMap<Integer, PacketParser>(MAXIMUM_PACKETS),
				new HashMap<Class<? extends PacketRepresentation>, PacketBuilder>(
						MAXIMUM_PACKETS));
	}

	@Override
	public PacketRepresentation parse(Packet packet) {
		PacketParser parser = parsers.get(packet.getID());
		if (parser != null)
			return parser.parse(packet);
		return null;
	}

	@Override
	public Packet build(PacketRepresentation packetRep) {
		PacketBuilder builder = builders.get(packetRep.getClass());
		if (builder != null)
			return builder.build(packetRep);
		return null;
	}

	@Override
	public void registerParser(PacketParser parser) {
		ParsesPacket annotation = parser.getClass().getAnnotation(
				ParsesPacket.class);
		if (annotation == null)
			throw new IllegalArgumentException(
					"Packet parsers must be annotated with @ParsesPacket");
		for (int packetId : annotation.value())
			parsers.put(packetId, parser);
	}

	@Override
	public void registerBuilder(PacketBuilder builder) {
		BuildsPacket annotation = builder.getClass().getAnnotation(
				BuildsPacket.class);
		if (annotation == null)
			throw new IllegalArgumentException(
					"Packet builders must be annotated with @BuildsPacket");
		builders.put(annotation.value(), builder);
	}

}