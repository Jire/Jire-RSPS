package jire.task;

public abstract class CycledTask extends AbstractTask {

	private final int cycles;
	private int countdown;

	public CycledTask(int cycles) {
		this.cycles = countdown = cycles;
	}

	@Override
	public final boolean run() {
		if (--countdown < 1) {
			countdown = cycles;
			return super.run();
		}
		return isRunning();
	}

}