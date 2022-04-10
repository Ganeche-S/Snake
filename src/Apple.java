import java.util.Random;

//Classe pour la pomme

public class Apple {

	// Variables
	private int appleX;
	private int appleY;
	private int applesEaten;
	private Random random = new Random();
	
	// Constructeur
	public Apple(Map map) {
		this.appleX = random.nextInt((int)(map.getScreenWidth()/map.getUnitSize()))*map.getUnitSize();
		this.appleY = random.nextInt((int)(map.getScreenHeight()/map.getUnitSize()))*map.getUnitSize();
		this.applesEaten = 0;
	}

	// Getters et Setters
	public int getAppleX() {
		return appleX;
	}

	public int getAppleY() {
		return appleY;
	}

	public int getApplesEaten() {
		return applesEaten;
	}
	
	// Fonction qui incremente le score affiché
	public void upApplesEaten() {
		this.applesEaten++;
	}
	
	// Fonction qui replace aléatoirement notre pomme à un nouvel emplacement
	public void setNewAppleXY(Map map) {
		this.appleX = random.nextInt((int)(map.getScreenWidth()/map.getUnitSize()))*map.getUnitSize();
		this.appleY = random.nextInt((int)(map.getScreenHeight()/map.getUnitSize()))*map.getUnitSize();
	}
}
