package jire.packet.represent;

import jire.packet.PacketRepresentation;

public final class SetSkillPacket implements PacketRepresentation {

	private final int skillID;
	private final int experience, level;

	private SetSkillPacket(int skillID, int experience, int level) {
		this.skillID = skillID;
		this.experience = experience;
		this.level = level;
	}

	public int getSkillID() {
		return skillID;
	}

	public int getExperience() {
		return experience;
	}

	public int getLevel() {
		return level;
	}

	public static SetSkillPacket get(int skillID, int experience, int level) {
		return new SetSkillPacket(skillID, experience, level);
	}

}