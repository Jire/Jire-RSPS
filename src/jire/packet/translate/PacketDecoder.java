package jire.packet.translate;

import jire.packet.InputPacketBuffer;
import jire.packet.Packet;

public interface PacketDecoder {

	Packet decode(InputPacketBuffer buffer);

}