import java.util.Random;

public class Star {

	private int starX;
	private int starY;
	private int delayStar;
	private Random random = new Random();
	
	public Star(Map map) {
		this.starX = random.nextInt((int)(map.getScreenWidth()/map.getUnitSize()))*map.getUnitSize();
		this.starY = random.nextInt((int)(map.getScreenHeight()/map.getUnitSize()))*map.getUnitSize();
	}

	public void resetDelayStar() {
		this.delayStar = 25;
	}
	
	public int getStarX() {
		return starX;
	}

	public int getStarY() {
		return starY;
	}

	public int getDelayStar() {
		return delayStar;
	}
	
	public void downDelayStar() {
		this.delayStar--;
	}
	
	public void setNewStarXY(Map map) {
		this.starX = random.nextInt((int)(map.getScreenWidth()/map.getUnitSize()))*map.getUnitSize();
		this.starY = random.nextInt((int)(map.getScreenHeight()/map.getUnitSize()))*map.getUnitSize();
	}

}
