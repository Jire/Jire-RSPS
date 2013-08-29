package jire.player;

public final class LocalPlayerListEntry {

	public enum LocalPlayerStatus {
		BEING_ADDED, NO_CHANGE, BEING_REMOVED
	};

	private final Player player;
	private LocalPlayerStatus status;

	public LocalPlayerListEntry(Player player, LocalPlayerStatus status) {
		this.player = player;
		this.status = status;
	}

	public Player getPlayer() {
		return player;
	}

	public LocalPlayerStatus getStatus() {
		return status;
	}

	public void setStatus(LocalPlayerStatus status) {
		this.status = status;
	}

}