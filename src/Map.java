
// Classe pour l'écran d'affichage

public class Map {
	
	// Variables
	private int screenWidth;
	private int screenHeight;
	private int unitSize;
	private int unitGame; 
	
	// Constructeur
	public Map() {
		this.screenHeight = 600;
		this.screenWidth = 600;
		this.unitSize = 25;
		this.unitGame = (this.screenWidth*this.screenHeight)/this.unitSize;
	}

	// Getters et Setters
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
