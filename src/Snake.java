
public class Snake {
	
	private int x[];
	private int y[];
	private int bodyParts;
	private boolean invincible;

	public Snake(int x[], int y[]) {
		this.x = x;
		this.y = y;
		this.bodyParts = 6;
		this.invincible = false;
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
	
	
}
