import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener {

//	final int screenWidth = 600;
//	final int screenHeight = 600;
//	final int unitSize = 25;
//	final int unitGame = (screenWidth*screenHeight)/unitSize;
	Map map;
	static int delay = 150;
//	final int x[] = new int[unitGame];
//	final int y[] = new int[unitGame];
//	int bodyParts = 6;
	Snake snake;
//	int applesEaten;
//	int appleX;
//	int appleY;
	Apple apple;
	int starX;
	int starY;
	char direction = 'R';
	boolean running = false;
	boolean invincible = false;
	int delayStar;
	Timer timer;
	Random random;
    

	GamePanel(){
		random = new Random();
		map = new Map();
		this.setPreferredSize(new Dimension(map.getScreenWidth(),map.getScreenHeight()));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
	}
	public void startGame() {
		snake = new Snake(new int[map.getUnitGame()], new int[map.getUnitGame()]);
		apple = new Apple(map);
//		newApple();
		running = true;
		timer = new Timer (delay,this);
		timer.start();
	}
	

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	public void draw(Graphics g) {
	    if(running) {
		     g.setColor(Color.red);
		     g.fillOval(apple.getAppleX(), apple.getAppleY(), map.getUnitSize(), map.getUnitSize());
		     drawEvolve(g);
		     if(delayStar <= -50  && apple.getApplesEaten() > 8) {
			     g.setColor(Color.yellow);
			     g.fillOval(starX, starY, map.getUnitSize(), map.getUnitSize());
		     }
		    g.setColor(Color.red);
			g.setFont( new Font("Ink Free",Font.BOLD, 40));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score: "+apple.getApplesEaten(), 
					(map.getScreenWidth() - metrics.stringWidth("Score: "+apple.getApplesEaten()))/2,
					g.getFont().getSize());
	    }
	    else {
	    	gameOver(g);
	    }
	}

	public void drawEvolve(Graphics g) {
		if(invincible) {
			float R = random.nextFloat();
			float G = random.nextFloat();
			float B = random.nextFloat();
		     for(int i = 0; i< snake.getBodyParts();i++) {
			     if(i == 0) {
				      g.setColor(new Color(R, G, B));
				      g.fillRect(snake.getX()[i],  snake.getY()[i],  map.getUnitSize(), map.getUnitSize());
			     }
			     else {
				      g.setColor(new Color(R, G, B));
				      g.fillRect(snake.getX()[i],  snake.getY()[i],  map.getUnitSize(), map.getUnitSize());
			     }
		     }
		}
		else {
			if(apple.getApplesEaten() % 2 == 0) {
			     for(int i = 0; i< snake.getBodyParts();i++) {
				     if(i == 0) {
					      g.setColor(Color.green);
					      g.fillRect(snake.getX()[i],  snake.getY()[i],  map.getUnitSize(), map.getUnitSize());
				     }
				     else {
					      g.setColor(Color.magenta);
					      g.fillRect(snake.getX()[i],  snake.getY()[i],  map.getUnitSize(), map.getUnitSize());
				     }
			     }
			}
			else if(apple.getApplesEaten() % 5 == 0) {
			     for(int i = 0; i< snake.getBodyParts();i++) {
				     if(i == 0) {
					      g.setColor(Color.yellow);
					      g.fillRect(snake.getX()[i],  snake.getY()[i],  map.getUnitSize(), map.getUnitSize());
				     }
				     else {
					      g.setColor(Color.gray);
					      g.fillRect(snake.getX()[i],  snake.getY()[i],  map.getUnitSize(), map.getUnitSize());
				     }
			     }
			}
			else {
			     for(int i = 0; i< snake.getBodyParts();i++) {
				     if(i == 0) {
					      g.setColor(Color.cyan);
					      g.fillRect(snake.getX()[i],  snake.getY()[i],  map.getUnitSize(), map.getUnitSize());
				     }
				     else {
					      g.setColor(Color.red);
					      g.fillRect(snake.getX()[i],  snake.getY()[i],  map.getUnitSize(), map.getUnitSize());
				     }
			     }
			}
		}

	}
	
//	public void newApple() {
//		appleX = random.nextInt((int)(screenWidth/unitSize))*unitSize;
//		appleY = random.nextInt((int)(screenHeight/unitSize))*unitSize;
//	}
	
	public void newStar() {
	 	starX = random.nextInt((int)(map.getScreenWidth()/map.getUnitSize()))*map.getUnitSize();
		starY = random.nextInt((int)(map.getScreenHeight()/map.getUnitSize()))*map.getUnitSize();
	}
	
	public void move() {
		for(int i = snake.getBodyParts();i>0;i--) {
			snake.getX()[i] = snake.getX()[i-1];
			snake.getY()[i] = snake.getY()[i-1];
		}
		
		switch(direction) {
		case 'U':
			snake.getY()[0] = snake.getY()[0] - map.getUnitSize();
			break;
		case 'D':
			snake.getY()[0] = snake.getY()[0] + map.getUnitSize();
			break;
		case 'L':
			snake.getX()[0] = snake.getX()[0] - map.getUnitSize();
			break;
		case 'R':
			snake.getX()[0] = snake.getX()[0] + map.getUnitSize();
			break;	
		}
	}
	public void checkApple() {
		if((snake.getX()[0] == apple.getAppleX()) && (snake.getY()[0] == apple.getAppleY())) {
			snake.upBodyParts();
			apple.upApplesEaten();
			apple.setNewAppleXY(map);
			speedUp();
		}
	}
	
	public void checkStar() {
		if((snake.getX()[0] == starX) && (snake.getY()[0] == starY)) {
			delayStar = 25;
			invincible = true;
		}
		else {
			delayStar--;
			if(delayStar == 0) {
				invincible = false;
			}
			else if(delayStar == -30 && apple.getApplesEaten() >= 8) {
				newStar();
			}
		}
	}
	
	public void speedUp() {
		if(delay > 50) {
			timer.stop();
			delay = delay - 5;
			timer = new Timer (delay,this);
			timer.restart();
		}
	}
	
	public void checkCollisions() {
		//checks if head collides with body
		if(invincible) {
			for(int i = snake.getBodyParts();i>0;i--) {
				if((snake.getX()[0] == snake.getX()[i]&& (snake.getY()[0] == snake.getY()[i]))){
					running = true;
				}
			}
		}
		else if(!invincible) {
			for(int i = snake.getBodyParts();i>0;i--) {
				if((snake.getX()[0] == snake.getX()[i]&& (snake.getY()[0] == snake.getY()[i]))){
					running = false;
				}
			}
		}
			//check if head touches left boreder
			if(snake.getX()[0] < 0) {
				running = false;
			}
			//check if head touches right border
			if(snake.getX()[0] > map.getScreenWidth()) {
				running = false;
			}	
			//check if head touches top border
			if(snake.getY()[0] < 0) {
				running = false;
			}
			//check if head touches bottom border
			if(snake.getY()[0] > map.getScreenHeight()) {
				running = false;
			}

		if(!running) {
			timer.stop();
		}
	}
	public void gameOver(Graphics g) {
		//Score
		g.setColor(Color.green);
		g.setFont( new Font("Ink Free",Font.BOLD, 40));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Score: "+apple.getApplesEaten(), (map.getScreenWidth() - metrics1.stringWidth("Score: "+ apple.getApplesEaten()))/2, g.getFont().getSize());
		//Game Over text
		g.setColor(Color.blue);
		g.setFont( new Font("Ink Free",Font.BOLD, 75));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Game Over", (map.getScreenWidth() - metrics2.stringWidth("Game Over"))/2, map.getScreenHeight()/2);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(running) {
			move();
			checkApple();
			checkStar();
			checkCollisions();
		}
		repaint();
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(direction != 'R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(direction != 'L') {
					direction = 'R';
				}
				break;
			case KeyEvent.VK_UP:
				if(direction != 'D') {
					direction = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if(direction != 'U') {
					direction = 'D';
				}
				break;	
			}
		}
	}

}
