
// Classe pour le serpent

public class Snake {
	
	// Variables
	private int x[];
	private int y[];
	private int bodyParts;
	private boolean invincible;

	// Constructeur
	public Snake(int x[], int y[]) {
		this.x = x;
		this.y = y;
		this.bodyParts = 6;
		this.invincible = false;
	}

	// Getters et Setters
	public int getBodyParts() {
		return bodyParts;
	}

	public int[] getX() {
		return this.x;
	}

	public int[] getY() {
		return this.y;
	}
	
	public boolean isInvincible() {
		return this.invincible;
	}

	public void setInvincible(boolean invincible) {
		this.invincible = invincible;
	}
	
	// Fonction qui incremente la taille du serpent
	public void upBodyParts() {
		this.bodyParts++;
	}
	
}
