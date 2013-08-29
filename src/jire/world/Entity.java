package jire.world;

public abstract class Entity {

	private final transient WalkingQueue walkingQueue = new WalkingQueue(this);
	private final transient Sprites sprites = new Sprites();

	private transient Position lastKnownRegion = getPosition();

	private transient boolean mapRegionChanging = false;
	private transient boolean teleporting = false;
	private transient Position teleportTarget = null;

	public WalkingQueue getWalkingQueue() {
		return walkingQueue;
	}

	public Sprites getSprites() {
		return sprites;
	}

	public Position getLastKnownRegion() {
		return lastKnownRegion;
	}

	public void setLastKnownRegion(Position lastKnownRegion) {
		this.lastKnownRegion = lastKnownRegion;
	}

	public boolean isMapRegionChanging() {
		return mapRegionChanging;
	}

	public void setMapRegionChanging(boolean mapRegionChanging) {
		this.mapRegionChanging = mapRegionChanging;
	}

	public boolean isTeleporting() {
		return teleporting;
	}

	public void setTeleporting(boolean teleporting) {
		this.teleporting = teleporting;
	}

	public Position getTeleportTarget() {
		return teleportTarget;
	}

	public void setTeleportTarget(Position teleportTarget) {
		this.teleportTarget = teleportTarget;
	}

	private Position position = Position.create(3222, 3222);

	public final void setPosition(Position position) {
		synchronized (this.position) {
			this.position = position;
		}
	}

	public final Position getPosition() {
		return position;
	}

}