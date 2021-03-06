package jire.packet;

import java.io.ByteArrayInputStream;

public final class RSInputStream extends ByteArrayInputStream {

	public RSInputStream(byte[] data) {
		super(data);
	}
	
	public RSInputStream(int size) {
		this(new byte[size]);
	}

	public int readByteA() {
		return read() - 128;
	}

	public int readByteC() {
		return -read();
	}

	public int readUnsignedByte() {
		return read() & 0xFF;
	}

	public int readShort() {
		return (read() << 8) + read();
	}

	public int readShortA() {
		int i = (readUnsignedByte() << 8) + (read() - 128 & 0xFF);
		if (i > 32767)
			i -= 0x10000;
		return i;
	}

	public int readLEShort() {
		int i = readUnsignedByte() + (readUnsignedByte() << 8);
		if (i > 32767)
			i -= 0x10000;
		return i;
	}

	public int readLEShortA() {
		int i = (read() - 128 & 0xFF) + (readUnsignedByte() << 8);
		if (i > 32767)
			i -= 0x10000;
		return i;
	}

	public int readUnsignedShort() {
		return (readUnsignedByte() << 8) + readUnsignedByte();
	}

	public int readInteger() {
		return (readShort() << 16) + readShort();
	}

	public long readUnsignedInteger() {
		return (readUnsignedShort() << 16) + readUnsignedShort();
	}

	public long readLong() {
		return (readInteger() << 24) + readInteger();
	}

	public String readString() {
		StringBuilder buffer = new StringBuilder();
		int read;
		while ((read = readUnsignedByte()) != '\n' && super.available() != 0) {
			buffer.append((char) read);
		}
		return buffer.toString();
	}

}