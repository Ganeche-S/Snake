import java.util.Random;

public class Apple {

	private int appleX;
	private int appleY;
	private int applesEaten;
	private Random random = new Random();
	
	public Apple(Map map) {
		this.appleX = random.nextInt((int)(map.getScreenWidth()/map.getUnitSize()))*map.getUnitSize();
		this.appleY = random.nextInt((int)(map.getScreenHeight()/map.getUnitSize()))*map.getUnitSize();
		this.applesEaten = 0;
	}


	public int getAppleX() {
		return appleX;
	}

	public int getAppleY() {
		return appleY;
	}

	public void upApplesEaten() {
		this.applesEaten++;
	}

	public int getApplesEaten() {
		return applesEaten;
	}
	
	public void setNewAppleXY(Map map) {
		this.appleX = random.nextInt((int)(map.getScreenWidth()/map.getUnitSize()))*map.getUnitSize();
		this.appleY = random.nextInt((int)(map.getScreenHeight()/map.getUnitSize()))*map.getUnitSize();
	}
}
