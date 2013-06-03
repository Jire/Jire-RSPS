package rs317;

import jire.packet.InputPacketBuffer;
import jire.packet.OutputPacketBuffer;
import jire.packet.Packet;
import jire.packet.translate.PacketTranslator;

public final class RS317Translator implements PacketTranslator {

	public static final int[] INCOMING_PACKET_LENGTHS = { 0, 0, 0, 1, -1, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 1, 0, -1, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 4, 5, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, -1,
			0, 0, 0, 6, 0 };

	public static final int[] OUTGOING_PACKET_LENGTHS = { 0, 0, 0, 0, 6, 0, 0,
			0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0,
			0, 0, 0, 0, 0, -2, 4, 3, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 6, 0, 0, 9,
			0, 0, -2, 0, 0, 0, 0, 0, 0, -2, 1, 0, 0, 2, -2, 0, 0, 0, 0, 6, 3,
			2, 4, 2, 4, 0, 0, 0, 4, 0, -2, 0, 0, 7, 2, 0, 6, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 2, 0, 1, 0, 2, 0, 0, -1, 4, 1, 0, 0, 0, 1, 0, 0, 0, 2, 0,
			0, 15, 0, 0, 0, 4, 4, 0, 0, 0, -2, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0,
			0, 0, 0, 0, 2, 0, 0, 0, 0, 14, 0, 0, 0, 4, 0, 0, 0, 0, 3, 0, 0, 0,
			4, 0, 0, 0, 2, 0, 6, 0, 0, 0, 0, 3, 0, 0, 5, 0, 10, 6, 0, 0, 0, 0,
			0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 4, 0, 0, 0,
			0, 0, 3, 0, 2, 0, 0, 0, 0, 0, -2, 7, 0, 0, 2, 0, 0, 1, 0, 0, 0, 0,
			0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, -2, 0, 0, 0, 0, 6, 0,
			4, 3, 0, 0, 0, -1, 6, 0, 0 };

	@Override
	public OutputPacketBuffer encode(Packet packet) {
		int length = packet.getLength();
		if (packet.getID() >= 0
				&& packet.getID() < OUTGOING_PACKET_LENGTHS.length) {
			length = OUTGOING_PACKET_LENGTHS[packet.getID()];
			if (length < 0)
				length = packet.getLength();
		}

		OutputPacketBuffer buffer = new OutputPacketBuffer(length
				+ (packet.isHeadless() ? 0 : 1));

		if (!packet.isHeadless()) {
			System.out.println("Outgoing length: "
					+ OUTGOING_PACKET_LENGTHS[packet.getID()]
					+ ", actual length=" + buffer.array().length);
			buffer.write(packet.getID());
		}
		buffer.writeBytes(packet.getData());

		return buffer;
	}

	@Override
	public Packet decode(InputPacketBuffer buffer) {
		if (buffer.hasRemaining()) {
			int id = buffer.read();

			if (id >= 0 && id < INCOMING_PACKET_LENGTHS.length) {
				int length = INCOMING_PACKET_LENGTHS[id];
				if (length < 0 && buffer.hasRemaining())
					length = buffer.read();

				if (length > 0 && buffer.hasRemaining()) {
					byte[] data = new byte[length];
					buffer.readBytes(data);

					return new Packet(id, data);
				}
			}
		}

		return null;
	}

}