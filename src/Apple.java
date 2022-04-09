import java.util.Random;

public class Apple {

	int applesEaten;
	int appleX;
	int appleY;
	Random random = new Random();
	
	public Apple(Map map) {
		this.appleX = random.nextInt((int)(map.getScreenWidth()/map.getUnitSize()))*map.getUnitSize();
		this.appleY = random.nextInt((int)(map.getScreenHeight()/map.getUnitSize()))*map.getUnitSize();
		this.applesEaten = 0;
	}
	
	public void upApplesEaten() {
		this.applesEaten++;
	}

	public int getApplesEaten() {
		return applesEaten;
	}

	public void setApplesEaten(int applesEaten) {
		this.applesEaten = applesEaten;
	}

	public int getAppleX() {
		return appleX;
	}

	public void setAppleX(int appleX) {
		this.appleX = appleX;
	}

	public int getAppleY() {
		return appleY;
	}

	public void setAppleY(int appleY) {
		this.appleY = appleY;
	}
	
	public void setNewAppleXY(Map map) {
		this.appleX = random.nextInt((int)(map.getScreenWidth()/map.getUnitSize()))*map.getUnitSize();
		this.appleY = random.nextInt((int)(map.getScreenHeight()/map.getUnitSize()))*map.getUnitSize();
	}
}
