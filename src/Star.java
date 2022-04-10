import java.util.Random;

//Classe pour l'étoile 

public class Star {

	// Variables
	private int starX;
	private int starY;
	private int delayStar;
	private Random random = new Random();
	
	// Contructeur
	public Star(Map map) {
		this.starX = -1;
		this.starY = -1;
	}

	// Getters et Setters
	public int getStarX() {
		return starX;
	}

	public int getStarY() {
		return starY;
	}

	public int getDelayStar() {
		return delayStar;
	}
	
	// Fonction qui replace aléatoirement notre étoile à un nouvel emplacement
	public void setNewStarXY(Map map) {
		this.starX = random.nextInt((int)(map.getScreenWidth()/map.getUnitSize()))*map.getUnitSize();
		this.starY = random.nextInt((int)(map.getScreenHeight()/map.getUnitSize()))*map.getUnitSize();
	}
	
	// Fonction qui initialise/réinitialise le temps d'apparition de l'étoile et également le temps d'invincibilité du joueur
	public void resetDelayStar() {
		this.delayStar = 25;
	}
	
	// Fonction qui décrémente le temps d'apparition/invincibilité
	public void downDelayStar() {
		this.delayStar--;
	}

}
