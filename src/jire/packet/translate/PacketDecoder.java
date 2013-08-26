package jire.packet.translate;

import net.burtleburtle.bob.rand.ISAACAlgorithm;
import jire.packet.InputPacketBuffer;
import jire.packet.Packet;

public interface PacketDecoder {

	Packet decode(InputPacketBuffer buffer);

	ISAACAlgorithm getDecodeCipher();

	void setDecodeCipher(ISAACAlgorithm cipher);

}