package jire.packet;

import java.nio.ByteBuffer;

public final class InputPacketBuffer extends PacketBuffer {

	public InputPacketBuffer(ByteBuffer backing) {
		setBacking(backing);
	}

	public InputPacketBuffer(byte[] data) {
		this(ByteBuffer.wrap(data));
	}

	public InputPacketBuffer(Packet packet) {
		this(packet.getData());
	}

	@Override
	public final void switchAccessType(AccessType type) {
		if (type.equals(AccessType.BIT_ACCESS)) {
			throw new UnsupportedOperationException(
					"Reading bits is not implemented!");
		}
	}

	public int readByte(boolean signed, ValueType type) {
		int value = getBacking().get();
		switch (type) {
		case A:
			value = value - 0x80;
			break;
		case C:
			value = -value;
			break;
		case S:
			value = 0x80 - value;
			break;
		case STANDARD:
			break;
		default:
			throw new UnsupportedOperationException("Unexpected value type: "
					+ type);
		}
		return signed ? value : value & 0xFF;
	}

	public byte readByte() {
		return (byte) readByte(true, ValueType.STANDARD);
	}

	public int read() {
		return readByte();
	}

	public int readByte(boolean signed) {
		return readByte(signed, ValueType.STANDARD);
	}

	public int readByte(ValueType type) {
		return readByte(true, type);
	}

	public void readBytes(byte[] data) {
		getBacking().get(data);
	}

	public int readShort(boolean signed, ValueType type, ByteOrder order) {
		int value = 0;
		switch (order) {
		case BIG:
			value |= readByte(false) << 8;
			value |= readByte(false, type);
			break;
		case MIDDLE:
			throw new UnsupportedOperationException(
					"Middle-endian short is impossible!");
		case INVERSE_MIDDLE:
			throw new UnsupportedOperationException(
					"Inverse-middle-endian short is impossible!");
		case LITTLE:
			value |= readByte(false, type);
			value |= readByte(false) << 8;
			break;
		default:
			throw new UnsupportedOperationException(
					"Unexpected byte order type: " + type);
		}
		return signed ? value : value & 0xFFFF;
	}

	public int readShort() {
		return readShort(true, ValueType.STANDARD, ByteOrder.BIG);
	}

	public int readShort(boolean signed) {
		return readShort(signed, ValueType.STANDARD, ByteOrder.BIG);
	}

	public int readShort(ValueType type) {
		return readShort(true, type, ByteOrder.BIG);
	}

	public int readShort(boolean signed, ValueType type) {
		return readShort(signed, type, ByteOrder.BIG);
	}

	public int readShort(ByteOrder order) {
		return readShort(true, ValueType.STANDARD, order);
	}

	public int readShort(boolean signed, ByteOrder order) {
		return readShort(signed, ValueType.STANDARD, order);
	}

	public int readShort(ValueType type, ByteOrder order) {
		return readShort(true, type, order);
	}

	public long readInt(boolean signed, ValueType type, ByteOrder order) {
		long value = 0;
		switch (order) {
		case BIG:
			value |= readByte(false) << 24;
			value |= readByte(false) << 16;
			value |= readByte(false) << 8;
			value |= readByte(false, type);
			break;
		case MIDDLE:
			value |= readByte(false) << 8;
			value |= readByte(false, type);
			value |= readByte(false) << 24;
			value |= readByte(false) << 16;
			break;
		case INVERSE_MIDDLE:
			value |= readByte(false) << 16;
			value |= readByte(false) << 24;
			value |= readByte(false, type);
			value |= readByte(false) << 8;
			break;
		case LITTLE:
			value |= readByte(false, type);
			value |= readByte(false) << 8;
			value |= readByte(false) << 16;
			value |= readByte(false) << 24;
			break;
		}
		return signed ? value : value & 0xFFFFFFFFL;
	}

	public int readInt() {
		return (int) readInt(true, ValueType.STANDARD, ByteOrder.BIG);
	}

	public long readInt(boolean signed) {
		return readInt(signed, ValueType.STANDARD, ByteOrder.BIG);
	}

	public int readInt(ValueType type) {
		return (int) readInt(true, type, ByteOrder.BIG);
	}

	public long readInt(boolean signed, ValueType type) {
		return readInt(signed, type, ByteOrder.BIG);
	}

	public int readInt(ByteOrder order) {
		return (int) readInt(true, ValueType.STANDARD, order);
	}

	public long readInt(boolean signed, ByteOrder order) {
		return readInt(signed, ValueType.STANDARD, order);
	}

	public int readInt(ValueType type, ByteOrder order) {
		return (int) readInt(true, type, order);
	}

	public long readLong(ValueType type, ByteOrder order) {
		long value = 0;
		switch (order) {
		case BIG:
			value |= (long) readByte(false) << 56L;
			value |= (long) readByte(false) << 48L;
			value |= (long) readByte(false) << 40L;
			value |= (long) readByte(false) << 32L;
			value |= (long) readByte(false) << 24L;
			value |= (long) readByte(false) << 16L;
			value |= (long) readByte(false) << 8L;
			value |= readByte(false, type);
			break;
		case MIDDLE:
			throw new UnsupportedOperationException(
					"Middle-endian long is not implemented!");
		case INVERSE_MIDDLE:
			throw new UnsupportedOperationException(
					"Inverse-middle-endian long is not implemented!");
		case LITTLE:
			value |= readByte(false, type);
			value |= (long) readByte(false) << 8L;
			value |= (long) readByte(false) << 16L;
			value |= (long) readByte(false) << 24L;
			value |= (long) readByte(false) << 32L;
			value |= (long) readByte(false) << 40L;
			value |= (long) readByte(false) << 48L;
			value |= (long) readByte(false) << 56L;
			break;
		}
		return value;
	}

	public long readLong() {
		return readLong(ValueType.STANDARD, ByteOrder.BIG);
	}

	public long readLong(ValueType type) {
		return readLong(type, ByteOrder.BIG);
	}

	public long readLong(ByteOrder order) {
		return readLong(ValueType.STANDARD, order);
	}

	public String readString() {
		byte temp;
		StringBuilder b = new StringBuilder();
		while ((temp = (byte) read()) != '\n') {
			b.append((char) temp);
		}
		return b.toString();
	}

	public byte[] readBytes(int amount) {
		return readBytes(amount, ValueType.STANDARD);
	}

	public byte[] readBytes(int amount, ValueType type) {
		byte[] data = new byte[amount];
		for (int i = 0; i < amount; i++) {
			data[i] = (byte) readByte(type);
		}
		return data;
	}

	public byte[] readBytesReverse(int amount, ValueType type) {
		byte[] data = new byte[amount];
		int dataPosition = 0;
		for (int i = (getBacking().position() + amount) - 1; i >= getBacking()
				.position(); i--) {
			int value = getBacking().get(i);
			switch (type) {
			case A:
				value -= 0x80;
				break;
			case C:
				value = -value;
				break;
			case S:
				value = 0x80 - value;
				break;
			case STANDARD:
				break;
			default:
				throw new UnsupportedOperationException(
						"Unexpected value type: " + type);
			}
			data[dataPosition++] = (byte) value;
		}
		return data;
	}

	public int limit() {
		return getBacking().limit();
	}

}