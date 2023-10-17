package skywars;

public abstract class Ship {
	protected int xCoordinate;
	protected int yCoordinate;
	protected String type ="didn't work";

	//ship constructor with coordinates are parameters
	public Ship(int x, int y) {
		this.xCoordinate = x;
		this.yCoordinate = y;
	}

	//input where to move and add that to existing coordinates
	public void move(int xInput, int yInput, int gridSize) {
		int newXCoordinate = xCoordinate + xInput;
		int newYCoordinate = yCoordinate + yInput;

		// check if the new coordinates are within the grid
		if (newXCoordinate >= 0 && newXCoordinate < gridSize && newYCoordinate >= 0 && newYCoordinate < gridSize) {
			xCoordinate = newXCoordinate;
			yCoordinate = newYCoordinate;
		}
	}
	//check is one ship is in the same as another ship 
	public boolean isColliding(Ship other) {
		return xCoordinate == other.xCoordinate && yCoordinate == other.yCoordinate;
	}
	
	public String getType(){
		return type;
	}
}

