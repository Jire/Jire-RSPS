package jire.packet;

import java.nio.ByteBuffer;

public final class OutputPacketBuffer extends PacketBuffer {

	public OutputPacketBuffer(ByteBuffer backing) {
		setBacking(backing);
	}

	public OutputPacketBuffer(int capacity) {
		this(ByteBuffer.allocate(capacity));
	}

	public OutputPacketBuffer(Packet packet) {
		this(packet.getLength());
	}

	@Override
	public final void switchAccessType(AccessType type) {
		switch (type) {
		case BIT_ACCESS:
			setBitPosition(getBacking().position() * 8);
			break;
		case BYTE_ACCESS:
			getBacking().position((getBitPosition() + 7) / 8);
			break;
		default:
			throw new UnsupportedOperationException("Unexpected access type: " + type);
		}
	}

	public void finishVariablePacketHeader() {
		getBacking().put(0, (byte) (getBacking().position() - 1));
	}

	public void finishVariableShortPacketHeader() {
		getBacking().putShort(0, (short) (getBacking().position() - 2));
	}

	public void writeBytes(ByteBuffer from) {
		for (int i = 0; i < from.position(); i++) {
			writeByte(from.get(i));
		}
	}

	public void writeBytesReverse(byte[] data) {
		for (int i = data.length - 1; i >= 0; i--) {
			writeByte(data[i]);
		}
	}

	public void writeBits(int amount, int value) {
		if (!getAccessType().equals(AccessType.BIT_ACCESS)) {
			throw new IllegalStateException("Illegal access type.");
		}
		if ((amount < 0) || (amount > 32)) {
			throw new IllegalArgumentException(
					"Number of bits must be between 1 and 32 inclusive.");
		}

		int currentPosition = getBitPosition() >> 3;
		int bitOffset = 8 - (getBitPosition() & 7);
		setBitPosition(getBitPosition() + amount);

		int requiredBytes = (currentPosition - getBacking().position()) + 1;
		requiredBytes += (amount + 7) / 8;
		if (getBacking().remaining() < requiredBytes) {
			ByteBuffer previous = getBacking();
			setBacking(ByteBuffer.allocate(previous.capacity() + requiredBytes));
			previous.flip();
			getBacking().put(previous);
		}

		for (; amount > bitOffset; bitOffset = 8) {
			byte previous = getBacking().get(currentPosition);
			previous &= ~BIT_MASK[bitOffset];
			previous |= (value >> (amount - bitOffset)) & BIT_MASK[bitOffset];
			getBacking().put(currentPosition++, previous);
			amount -= bitOffset;
		}
		if (amount == bitOffset) {
			byte previous = getBacking().get(currentPosition);
			previous &= ~BIT_MASK[bitOffset];
			previous |= value & BIT_MASK[bitOffset];
			getBacking().put(currentPosition, previous);
		} else {
			byte previous = getBacking().get(currentPosition);
			previous &= ~(BIT_MASK[amount] << (bitOffset - amount));
			previous |= (value & BIT_MASK[amount]) << (bitOffset - amount);
			getBacking().put(currentPosition, previous);
		}
	}

	public void writeBit(boolean value) {
		writeBits(1, value ? 1 : 0);
	}

	public void writeByte(int value, ValueType type) {
		if (!getAccessType().equals(AccessType.BYTE_ACCESS)) {
			throw new IllegalStateException("Illegal access type.");
		}
		switch (type) {
		case A:
			value += 0x80;
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
		getBacking().put((byte) value);
	}

	public void writeBoolean(boolean value) {
		write(value ? 1 : 0);
	}

	public void write(int value) {
		writeByte((byte) (value & 0xFF));
	}

	public void writeByte(int value) {
		writeByte(value, ValueType.STANDARD);
	}

	public void writeBytes(byte[] values) {
		getBacking().put(values);
	}

	public void writeShort(int value, ValueType type, ByteOrder order) {
		switch (order) {
		case BIG:
			write(value >> 8);
			writeByte(value, type);
			break;
		case MIDDLE:
			throw new IllegalArgumentException(
					"Middle-endian short is impossible!");
		case INVERSE_MIDDLE:
			throw new IllegalArgumentException(
					"Inverse-middle-endian short is impossible!");
		case LITTLE:
			writeByte(value, type);
			write(value >> 8);
			break;
		}
	}

	public void writeShort(int value) {
		writeShort(value, ValueType.STANDARD, ByteOrder.BIG);
	}

	public void writeShort(int value, ValueType type) {
		writeShort(value, type, ByteOrder.BIG);
	}

	public void writeShort(int value, ByteOrder order) {
		writeShort(value, ValueType.STANDARD, order);
	}

	/*
	 * public void writeShortA(int value) { write(value >> 8); write(value +
	 * 128); }
	 * 
	 * public void writeLEShortA(int value) { write(value + 128); write(value >>
	 * 8); }
	 */

	public void writeInt(int value, ValueType type, ByteOrder order) {
		switch (order) {
		case BIG:
			write(value >> 24);
			write(value >> 16);
			write(value >> 8);
			writeByte(value, type);
			break;
		case MIDDLE:
			write(value >> 8);
			writeByte(value, type);
			write(value >> 24);
			write(value >> 16);
			break;
		case INVERSE_MIDDLE:
			write(value >> 16);
			write(value >> 24);
			writeByte(value, type);
			write(value >> 8);
			break;
		case LITTLE:
			writeByte(value, type);
			write(value >> 8);
			write(value >> 16);
			write(value >> 24);
			break;
		}
	}

	public void writeInt(int value) {
		writeInt(value, ValueType.STANDARD, ByteOrder.BIG);
	}

	public void writeInt(int value, ValueType type) {
		writeInt(value, type, ByteOrder.BIG);
	}

	public void writeInt(int value, ByteOrder order) {
		writeInt(value, ValueType.STANDARD, order);
	}

	public void writeLong(long value, ValueType type, ByteOrder order) {
		switch (order) {
		case BIG:
			write((int) (value >> 56));
			write((int) (value >> 48));
			write((int) (value >> 40));
			write((int) (value >> 32));
			write((int) (value >> 24));
			write((int) (value >> 16));
			write((int) (value >> 8));
			writeByte((int) value, type);
			break;
		case MIDDLE:
			throw new UnsupportedOperationException(
					"Middle-endian long is not implemented!");
		case INVERSE_MIDDLE:
			throw new UnsupportedOperationException(
					"Inverse-middle-endian long is not implemented!");
		case LITTLE:
			writeByte((int) value, type);
			write((int) (value >> 8));
			write((int) (value >> 16));
			write((int) (value >> 24));
			write((int) (value >> 32));
			write((int) (value >> 40));
			write((int) (value >> 48));
			write((int) (value >> 56));
			break;
		}
	}

	public void writeLong(long value) {
		writeLong(value, ValueType.STANDARD, ByteOrder.BIG);
	}

	public void writeLong(long value, ValueType type) {
		writeLong(value, type, ByteOrder.BIG);
	}

	public void writeLong(long value, ByteOrder order) {
		writeLong(value, ValueType.STANDARD, order);
	}

	public void writeString(String string) {
		for (byte value : string.getBytes()) {
			writeByte(value);
		}
		write('\n');
	}

}
