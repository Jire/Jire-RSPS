package jire.packet.represent;

import jire.packet.PacketRepresentation;

public final class WalkPacket implements PacketRepresentation {

	private final int steps;
	private final boolean runSteps;
	private final int[][] path;
	private final int firstX, firstY;

	private WalkPacket(int steps, boolean runSteps, int[][] path, int firstX,
			int firstY) {
		this.steps = steps;
		this.runSteps = runSteps;
		this.path = path;
		this.firstX = firstX;
		this.firstY = firstY;
	}

	public int getSteps() {
		return steps;
	}

	public boolean isRunSteps() {
		return runSteps;
	}

	public int[][] getPath() {
		return path;
	}

	public int getFirstX() {
		return firstX;
	}

	public int getFirstY() {
		return firstY;
	}

	public static WalkPacket get(int steps, boolean runSteps, int[][] path,
			int firstX, int firstY) {
		return new WalkPacket(steps, runSteps, path, firstX, firstY);
	}

}