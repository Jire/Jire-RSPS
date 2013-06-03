package jire.packet.translate;

import jire.packet.OutputPacketBuffer;
import jire.packet.Packet;

public interface PacketEncoder {

	OutputPacketBuffer encode(Packet packet);

}