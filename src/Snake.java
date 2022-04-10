
public class Snake {
	
	private int x[];
	private int y[];
	private int bodyParts;
	private boolean invincible;
	private int applesEaten;

	public Snake(int x[], int y[]) {
		this.x = x;
		this.y = y;
		this.bodyParts = 6;
		this.invincible = false;
		this.applesEaten = 0;
	}

	public int getBodyParts() {
		return bodyParts;
	}
	
	public void upBodyParts() {
		this.bodyParts++;
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
	
	public void upApplesEaten() {
		this.applesEaten++;
	}

	public int getApplesEaten() {
		return applesEaten;
	}
	
	
}
