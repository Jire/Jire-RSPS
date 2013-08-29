package rs317;

import jire.packet.InputPacketBuffer;
import jire.packet.Packet;
import jire.packet.RSOutputStream;
import jire.packet.translate.AbstractPacketTranslator;

public final class RS317Translator extends AbstractPacketTranslator {

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

	public static final int PACKET_SIZE_BYTE = -1;
	public static final int PACKET_SIZE_SHORT = -2;

	@Override
	public RSOutputStream encode(Packet packet) {
		RSOutputStream buffer = new RSOutputStream(packet.getLength() + 3);

		if (!packet.isHeadless()) {
			buffer.write(packet.getID());

			if (OUTGOING_PACKET_LENGTHS[packet.getID()] == PACKET_SIZE_BYTE) {
				buffer.write(packet.getLength());
			} else if (OUTGOING_PACKET_LENGTHS[packet.getID()] == PACKET_SIZE_SHORT) {
				buffer.writeShort(packet.getLength());
			}
		}

		buffer.writeBytes(packet.getData());

		return buffer;
	}

	@Override
	public Packet decode(InputPacketBuffer buffer) {
		if (buffer.limit() == 0)
			return null;

		int id = buffer.read() & 0xFF;

		int packetLength = getPacketLength(id, buffer);
		if (buffer.remaining() < packetLength) {
			return null;
		}
		byte[] data = new byte[packetLength];
		buffer.readBytes(data);

		return new Packet(id, data);
	}

	private int getPacketLength(int packetId, InputPacketBuffer buffer) {
		int possibleLength = INCOMING_PACKET_LENGTHS[packetId];
		if (possibleLength == PACKET_SIZE_BYTE) {
			return buffer.read() & 0xFF;
		} else if (possibleLength == PACKET_SIZE_SHORT) {
			return buffer.readShort();
		}
		return possibleLength;
	}

}