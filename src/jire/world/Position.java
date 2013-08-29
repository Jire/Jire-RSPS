package jire.world;

public class Position {

	private final int x, y, z;

	protected Position(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public int getRegionX() {
		return (x >> 3) - 6;
	}

	public int getRegionY() {
		return (y >> 3) - 6;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ", " + z + ")";
	}

	public Position transform(int diffX, int diffY, int diffZ) {
		return Position.create(x + diffX, y + diffY, z + diffZ);
	}

	public int getLocalX(Position position) {
		return x - 8 * position.getRegionX();
	}

	public int getLocalY(Position position) {
		return y - 8 * position.getRegionY();
	}

	public int getLocalX() {
		return getLocalX(this);
	}

	public int getLocalY() {
		return getLocalY(this);
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Position)) {
			return false;
		}
		Position pos = (Position) o;
		return getX() == pos.getX() && getY() == pos.getY()
				&& getZ() == pos.getZ();
	}

	@Override
	public int hashCode() {
		return ((getZ() & 3) << 30) | ((getY() & 0x7FFF) << 15)
				| (getX() & 0x7FFF);
	}

	public static Position create(int x, int y, int z) {
		return new Position(x, y, z);
	}

	public static Position create(int x, int y) {
		return create(x, y, 0);
	}

}