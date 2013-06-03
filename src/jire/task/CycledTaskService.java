package jire.task;

import java.util.Iterator;

import jire.util.Stopwatch;

public final class CycledTaskService extends AbstractTaskService {

	private final int cycleLength;

	public CycledTaskService(int cycleLength) {
		this.cycleLength = cycleLength;
	}

	public final int getCycleLength() {
		return cycleLength;
	}

	@Override
	public void run() {
		while (isRunning()) {
			Stopwatch stopwatch = Stopwatch.start();

			if (getTasks().size() > 0) {
				synchronized (mutex) {
					Iterator<Task> it = getTasks().iterator();
					while (it.hasNext()) {
						if (!it.next().run())
							it.remove();
					}
				}
			}

			long elapsed = stopwatch.elapsed();
			if (elapsed < getCycleLength()) {
				try {
					Thread.sleep(getCycleLength() - elapsed);
				} catch (InterruptedException e) {
					// Return immediately
					return;
				}
			}
		}
	}

}