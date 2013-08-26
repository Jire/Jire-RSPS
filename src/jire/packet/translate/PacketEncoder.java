package jire.packet.translate;

import jire.packet.OutputPacketBuffer;
import jire.packet.Packet;
import net.burtleburtle.bob.rand.ISAACAlgorithm;

public interface PacketEncoder {

	OutputPacketBuffer encode(Packet packet);

	ISAACAlgorithm getEncodeCipher();

	void setEncodeCipher(ISAACAlgorithm cipher);

}