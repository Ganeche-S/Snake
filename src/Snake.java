
public class Snake {
	
	final int x[];
	final int y[];
	int bodyParts;
	
	public Snake(int x[], int y[]) {
		this.x = x;
		this.y = y;
		this.bodyParts = 6;
	}

	public int getBodyParts() {
		return bodyParts;
	}
	
	public void upBodyParts() {
		this.bodyParts++;
	}

	public int[] getX() {
		return x;
	}

	public int[] getY() {
		return y;
	}
	
	
}
