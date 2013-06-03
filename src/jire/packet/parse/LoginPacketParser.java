package jire.packet.parse;

import jire.packet.InputPacketBuffer;
import jire.packet.Packet;
import jire.packet.PacketParser;
import jire.packet.PacketRepresentation;
import jire.packet.reflective.ParsesPacket;
import jire.packet.represent.LoginPacket;

@ParsesPacket({ 16, 18 })
public final class LoginPacketParser implements PacketParser {

	@Override
	public PacketRepresentation parse(Packet packet) {
		InputPacketBuffer input = new InputPacketBuffer(packet);

		int magic = input.read() & 0xFF;
		int clientVersion = input.readShort();
		boolean highDetail = input.read() == 0;

		int[] fileChecksums = new int[9];
		for (int i = 0; i < fileChecksums.length; i++) {
			fileChecksums[i] = input.readInt();
		}

		int blockLength = input.read() & 0xFF;
		int blockOperationCode = input.read() & 0xFF;

		int[] sessionKeys = new int[4];
		for (int i = 0; i < sessionKeys.length; i++) {
			sessionKeys[i] = input.readInt();
		}

		int userId = input.readInt();

		String username = input.readString();
		String password = input.readString();

		return LoginPacket.get(magic, clientVersion, highDetail, fileChecksums,
				blockLength, blockOperationCode, sessionKeys, userId, username,
				password);
	}

}