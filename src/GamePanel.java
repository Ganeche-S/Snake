import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener {

	static final int screenWidth = 600;
	static final int screenHeight = 600;
	static final int unitSize = 25;
	static final int unitGame = (screenWidth*screenHeight)/unitSize;
	static int delay = 150;
	final int x[] = new int[unitGame];
	final int y[] = new int[unitGame];
	int bodyParts = 6;
	int applesEaten;
	int appleX;
	int appleY;
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
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
	}
	public void startGame() {
		newApple();
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
		     g.fillOval(appleX, appleY, unitSize, unitSize);
		     drawEvolve(g);
		     if(delayStar <= -50  && applesEaten > 8) {
			     g.setColor(Color.yellow);
			     g.fillOval(starX, starY, unitSize, unitSize);
		     }
		     g.setColor(Color.red);
				g.setFont( new Font("Ink Free",Font.BOLD, 40));
				FontMetrics metrics = getFontMetrics(g.getFont());
				g.drawString("Score: "+applesEaten, (screenWidth - metrics.stringWidth("Score: "+applesEaten))/2, g.getFont().getSize());
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
		     for(int i = 0; i< bodyParts;i++) {
			     if(i == 0) {
				      g.setColor(new Color(R, G, B));
				      g.fillRect(x[i],  y[i],  unitSize, unitSize);
			     }
			     else {
				      g.setColor(new Color(R, G, B));
				      g.fillRect(x[i],  y[i],  unitSize, unitSize);
			     }
		     }
		}
		else {
			if(applesEaten % 2 == 0) {
			     for(int i = 0; i< bodyParts;i++) {
				     if(i == 0) {
					      g.setColor(Color.green);
					      g.fillRect(x[i],  y[i],  unitSize, unitSize);
				     }
				     else {
					      g.setColor(Color.magenta);
					      g.fillRect(x[i],  y[i],  unitSize, unitSize);
				     }
			     }
			}
			else if(applesEaten % 5 == 0) {
			     for(int i = 0; i< bodyParts;i++) {
				     if(i == 0) {
					      g.setColor(Color.yellow);
					      g.fillRect(x[i],  y[i],  unitSize, unitSize);
				     }
				     else {
					      g.setColor(Color.gray);
					      g.fillRect(x[i],  y[i],  unitSize, unitSize);
				     }
			     }
			}
			else {
			     for(int i = 0; i< bodyParts;i++) {
				     if(i == 0) {
					      g.setColor(Color.cyan);
					      g.fillRect(x[i],  y[i],  unitSize, unitSize);
				     }
				     else {
					      g.setColor(Color.red);
					      g.fillRect(x[i],  y[i],  unitSize, unitSize);
				     }
			     }
			}
		}

	}
	
	public void newApple() {
		appleX = random.nextInt((int)(screenWidth/unitSize))*unitSize;
		appleY = random.nextInt((int)(screenHeight/unitSize))*unitSize;
	}
	
	public void newStar() {
	 	starX = random.nextInt((int)(screenWidth/unitSize))*unitSize;
		starY = random.nextInt((int)(screenHeight/unitSize))*unitSize;
	}
	
	public void move() {
		for(int i = bodyParts;i>0;i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		
		switch(direction) {
		case 'U':
			y[0] = y[0] - unitSize;
			break;
		case 'D':
			y[0] = y[0] + unitSize;
			break;
		case 'L':
			x[0] = x[0] - unitSize;
			break;
		case 'R':
			x[0] = x[0] + unitSize;
			break;	
		}
	}
	public void checkApple() {
		if((x[0] == appleX) && (y[0] == appleY)) {
			bodyParts++;
			applesEaten++;
			newApple();
			speedUp();
		}
	}
	
	public void checkStar() {
		if((x[0] == starX) && (y[0] == starY)) {
			delayStar = 25;
			invincible = true;
		}
		else {
			delayStar--;
			if(delayStar == 0) {
				invincible = false;
			}
			else if(delayStar == -30 && applesEaten == 8) {
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
			for(int i = bodyParts;i>0;i--) {
				if((x[0] == x[i]&& (y[0] == y[i]))){
					running = true;
				}
			}
		}
		else if(!invincible) {
			for(int i = bodyParts;i>0;i--) {
				if((x[0] == x[i]&& (y[0] == y[i]))){
					running = false;
				}
			}
		}
			//check if head touches left boreder
			if(x[0] < 0) {
				running = false;
			}
			//check if head touches right border
			if(x[0] > screenWidth) {
				running = false;
			}	
			//check if head touches top border
			if(y[0] < 0) {
				running = false;
			}
			//check if head touches bottom border
			if(y[0] > screenHeight) {
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
		g.drawString("Score: "+applesEaten, (screenWidth - metrics1.stringWidth("Score: "+applesEaten))/2, g.getFont().getSize());
		//Game Over text
		g.setColor(Color.blue);
		g.setFont( new Font("Ink Free",Font.BOLD, 75));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Game Over", (screenWidth - metrics2.stringWidth("Game Over"))/2, screenHeight/2);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(running) {
			move();
			checkApple();
			checkStar();
			checkCollisions();
			System.out.println(delayStar);
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
