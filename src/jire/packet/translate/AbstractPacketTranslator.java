package jire.packet.translate;

import net.burtleburtle.bob.rand.ISAACAlgorithm;

public abstract class AbstractPacketTranslator implements PacketTranslator {

	private ISAACAlgorithm encodeCipher = null;
	private ISAACAlgorithm decodeCipher = null;

	@Override
	public final ISAACAlgorithm getEncodeCipher() {
		return encodeCipher;
	}

	@Override
	public final void setEncodeCipher(ISAACAlgorithm cipher) {
		this.encodeCipher = cipher;
	}

	@Override
	public final ISAACAlgorithm getDecodeCipher() {
		return decodeCipher;
	}

	@Override
	public final void setDecodeCipher(ISAACAlgorithm cipher) {
		this.decodeCipher = cipher;
	}

}