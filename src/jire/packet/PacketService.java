package jire.packet;

public interface PacketService extends PacketParser, PacketBuilder {

	static final int MAXIMUM_PACKETS = 256;

	void registerParser(PacketParser parser);

	void registerBuilder(PacketBuilder builder);

}