
public class Map {
	
	final int screenWidth;
	final int screenHeight;
	final int unitSize;
	final int unitGame; 
	
	public Map() {
		this.screenHeight = 600;
		this.screenWidth = 600;
		this.unitSize = 25;
		this.unitGame = (this.screenWidth*this.screenHeight)/this.unitSize;
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public int getUnitSize() {
		return unitSize;
	}

	public int getUnitGame() {
		return unitGame;
	}
}