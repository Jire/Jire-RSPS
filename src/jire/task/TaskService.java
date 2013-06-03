package jire.task;

public interface TaskService {

	void start();

	void shutdown();

	void submit(Task task);

}