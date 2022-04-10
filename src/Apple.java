import java.util.Random;

public class Apple {

	private int appleX;
	private int appleY;
	private Random random = new Random();
	
	public Apple(Map map) {
		this.appleX = random.nextInt((int)(map.getScreenWidth()/map.getUnitSize()))*map.getUnitSize();
		this.appleY = random.nextInt((int)(map.getScreenHeight()/map.getUnitSize()))*map.getUnitSize();
	}


	public int getAppleX() {
		return appleX;
	}

	public int getAppleY() {
		return appleY;
	}
	
//	public void setNewAppleXY(Map map) {
//		this.appleX = random.nextInt((int)(map.getScreenWidth()/map.getUnitSize()))*map.getUnitSize();
//		this.appleY = random.nextInt((int)(map.getScreenHeight()/map.getUnitSize()))*map.getUnitSize();
//	}
}
