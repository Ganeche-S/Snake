import java.util.Random;

//Classe pour l'�toile 

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
	
	// Fonction qui replace al�atoirement notre �toile � un nouvel emplacement
	public void setNewStarXY(Map map) {
		this.starX = random.nextInt((int)(map.getScreenWidth()/map.getUnitSize()))*map.getUnitSize();
		this.starY = random.nextInt((int)(map.getScreenHeight()/map.getUnitSize()))*map.getUnitSize();
	}
	
	// Fonction qui initialise/r�initialise le temps d'apparition de l'�toile et �galement le temps d'invincibilit� du joueur
	public void resetDelayStar() {
		this.delayStar = 25;
	}
	
	// Fonction qui d�cr�mente le temps d'apparition/invincibilit�
	public void downDelayStar() {
		this.delayStar--;
	}

}
