package jire.packet.translate;

import jire.packet.Packet;
import jire.packet.RSOutputStream;

public interface PacketEncoder {

	RSOutputStream encode(Packet packet);

}