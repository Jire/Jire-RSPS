package jire.task;

public abstract class AbstractTask implements Task {

	private boolean running = true;

	public void stop() {
		if (!isRunning()) {
			throw new IllegalStateException("Task is already stopped!");
		}
		running = false;
	}

	public final boolean isRunning() {
		return running;
	}

	protected abstract void execute();

	@Override
	public boolean run() {
		execute();
		return isRunning();
	}

}