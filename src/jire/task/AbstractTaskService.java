package jire.task;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTaskService extends Thread implements TaskService {

	private final List<Task> tasks;

	protected final Object mutex = new Object();

	private boolean running;

	protected AbstractTaskService(List<Task> tasks) {
		this.tasks = tasks;
	}

	protected AbstractTaskService() {
		this(new ArrayList<Task>());
	}

	protected final List<Task> getTasks() {
		return tasks;
	}

	public final boolean isRunning() {
		return running;
	}

	@Override
	public final void submit(Task task) {
		synchronized (mutex) {
			getTasks().add(task);
		}
	}

	@Override
	public final void start() {
		if (isRunning()) {
			throw new IllegalStateException(
					"Task service has already been started!");
		}
		running = true;
		super.start();
	}

	@Override
	public final void shutdown() {
		if (!isRunning()) {
			throw new IllegalStateException(
					"Task service has not yet been started!");
		}
		running = false;
	}

}