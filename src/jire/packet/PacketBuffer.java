package jire.packet;

import java.nio.ByteBuffer;

public abstract class PacketBuffer {

	public enum ByteOrder {
		LITTLE, BIG, MIDDLE, INVERSE_MIDDLE
	}

	public enum ValueType {
		STANDARD, A, C, S
	}

	public enum AccessType {
		BYTE_ACCESS, BIT_ACCESS
	}

	protected static final int[] BIT_MASK = { 0, 0x1, 0x3, 0x7, 0xf, 0x1f,
			0x3f, 0x7f, 0xff, 0x1ff, 0x3ff, 0x7ff, 0xfff, 0x1fff, 0x3fff,
			0x7fff, 0xffff, 0x1ffff, 0x3ffff, 0x7ffff, 0xfffff, 0x1fffff,
			0x3fffff, 0x7fffff, 0xffffff, 0x1ffffff, 0x3ffffff, 0x7ffffff,
			0xfffffff, 0x1fffffff, 0x3fffffff, 0x7fffffff, -1 };

	private int bitPosition;

	private ByteBuffer backing;

	private AccessType accessType = AccessType.BYTE_ACCESS;

	public final int remaining() {
		return backing.remaining();
	}

	public final void flip() {
		getBacking().flip();
	}

	public final byte[] array() {
		return getBacking().array();
	}

	public final boolean hasRemaining() {
		return getBacking().hasRemaining();
	}

	public final void clear() {
		getBacking().clear();
	}

	protected abstract void switchAccessType(AccessType type);

	protected final AccessType getAccessType() {
		return accessType;
	}

	protected final int getBitPosition() {
		return bitPosition;
	}

	public final synchronized ByteBuffer getBacking() {
		return backing;
	}

	protected final void setBitPosition(int bitPosition) {
		this.bitPosition = bitPosition;
	}

	protected final synchronized void setBacking(ByteBuffer backing) {
		this.backing = backing;
	}

	protected final void setAccessType(AccessType accessType) {
		this.accessType = accessType;
		switchAccessType(accessType);
	}

}