import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;

// Classe pour l'environnement du jeu

public class GamePanel extends JPanel implements ActionListener {

	// Variables
	private Map map;
	private Snake snake;
	private Apple apple;
	private Star star;
	private int delay;
	private char direction;
	private boolean running;
	private Timer timer;
	private Random random;
    
	// Constructeur
	GamePanel(){
		this.random = new Random();
		this.map = new Map();
		this.setPreferredSize(new Dimension(this.map.getScreenWidth(),this.map.getScreenHeight()));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
	}
	
	// Fonction qui permet de construire l'environnement du jeu
	public void startGame() {
		this.snake = new Snake(new int[this.map.getUnitGame()], new int[this.map.getUnitGame()]);
		this.apple = new Apple(this.map);
		this.star = new Star(this.map);
		this.running = true;
		this.direction = 'R';
		this.delay = 150;
		this.timer = new Timer (this.delay,this);
		this.timer.start();
	}
	
	// Fonction qui permet de dessiner la fenêtre
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	// Fonction qui dessine le jeu et son contenu
	public void draw(Graphics g) {
	    if(running) {
		     g.setColor(Color.red);
		     g.fillOval(this.apple.getAppleX(), this.apple.getAppleY(), this.map.getUnitSize(), this.map.getUnitSize());
		     drawEvolve(g);
		     if(this.star.getDelayStar() <= -30  && this.apple.getApplesEaten() >= 8) {
			     g.setColor(Color.yellow);
			     g.fillOval(this.star.getStarX(), this.star.getStarY(), this.map.getUnitSize(), this.map.getUnitSize());
		     }
		    g.setColor(Color.red);
			g.setFont( new Font("Ink Free",Font.BOLD, 40));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score: "+this.apple.getApplesEaten(), 
					(map.getScreenWidth() - metrics.stringWidth("Score: "+this.apple.getApplesEaten()))/2,
					g.getFont().getSize());
	    }
	    else {
	    	gameOver(g);
	    }
	}

	// Fonction qui permet le changement de couleur du serpent
	public void drawEvolve(Graphics g) {
		if(this.snake.isInvincible()) {
			float R = random.nextFloat();
			float G = random.nextFloat();
			float B = random.nextFloat();
		     for(int i = 0; i< this.snake.getBodyParts();i++) {
			     if(i == 0) {
				      g.setColor(new Color(R, G, B));
				      g.fillRect(this.snake.getX()[i], this.snake.getY()[i],  this.map.getUnitSize(), this.map.getUnitSize());
			     }
			     else {
				      g.setColor(new Color(R, G, B));
				      g.fillRect(this.snake.getX()[i], this.snake.getY()[i],  this.map.getUnitSize(), this.map.getUnitSize());
			     }
		     }
		}
		else {
			if(this.apple.getApplesEaten() % 2 == 0) {
			     for(int i = 0; i< this.snake.getBodyParts();i++) {
				     if(i == 0) {
					      g.setColor(Color.green);
					      g.fillRect(this.snake.getX()[i],  this.snake.getY()[i],  this.map.getUnitSize(), this.map.getUnitSize());
				     }
				     else {
					      g.setColor(Color.magenta);
					      g.fillRect(this.snake.getX()[i],  this.snake.getY()[i],  this.map.getUnitSize(), this.map.getUnitSize());
				     }
			     }
			}
			else if(this.apple.getApplesEaten() % 5 == 0) {
			     for(int i = 0; i< this.snake.getBodyParts();i++) {
				     if(i == 0) {
					      g.setColor(Color.yellow);
					      g.fillRect(this.snake.getX()[i],  this.snake.getY()[i],  this.map.getUnitSize(), this.map.getUnitSize());
				     }
				     else {
					      g.setColor(Color.gray);
					      g.fillRect(this.snake.getX()[i],  this.snake.getY()[i],  this.map.getUnitSize(), this.map.getUnitSize());
				     }
			     }
			}
			else {
			     for(int i = 0; i< this.snake.getBodyParts();i++) {
				     if(i == 0) {
					      g.setColor(Color.cyan);
					      g.fillRect(this.snake.getX()[i],  this.snake.getY()[i],  this.map.getUnitSize(), this.map.getUnitSize());
				     }
				     else {
					      g.setColor(Color.red);
					      g.fillRect(this.snake.getX()[i],  this.snake.getY()[i],  this.map.getUnitSize(), this.map.getUnitSize());
				     }
			     }
			}
		}

	}
	
	// Fonction qui permet de déplacer le serpent
	public void move() {
		for(int i = this.snake.getBodyParts();i>0;i--) {
			this.snake.getX()[i] = this.snake.getX()[i-1];
			this.snake.getY()[i] = this.snake.getY()[i-1];
		}
		
		switch(direction) {
		case 'U':
			this.snake.getY()[0] = this.snake.getY()[0] - this.map.getUnitSize();
			break;
		case 'D':
			this.snake.getY()[0] = this.snake.getY()[0] + this.map.getUnitSize();
			break;
		case 'L':
			this.snake.getX()[0] = this.snake.getX()[0] - this.map.getUnitSize();
			break;
		case 'R':
			this.snake.getX()[0] = this.snake.getX()[0] + this.map.getUnitSize();
			break;	
		}
	}
	
	// Fonction pour la gestion du serpent et de la pomme lorsque celle-ci est mangé
	public void checkApple() {
		if((this.snake.getX()[0] == this.apple.getAppleX()) && (snake.getY()[0] == this.apple.getAppleY())) {
			this.snake.upBodyParts();
			this.apple.upApplesEaten();
			this.apple.setNewAppleXY(map);
			speedUp();
		}
	}
	
	// Fonction qui la gestion du serpent et de l'étoile lorsque celle-ci est mangé avec le temps d'invincibilité
	public void checkStar() {
		if((this.snake.getX()[0] == this.star.getStarX()) && (this.snake.getY()[0] == this.star.getStarY())) {
			this.star.resetDelayStar();
			this.snake.setInvincible(true);
		}
		else {
			this.star.downDelayStar();
			if(this.star.getDelayStar() == 0) {
				this.snake.setInvincible(false);
			}
			else if(this.star.getDelayStar() == -30 && this.apple.getApplesEaten() >= 8 ) {
				this.star.setNewStarXY(map);
			}
		}
	}
	
	// Fonction qui augmente la vélocité du serpent
	public void speedUp() {
		if(this.delay > 50) {
			this.timer.stop();
			this.delay = this.delay - 5;
			this.timer = new Timer (this.delay,this);
			this.timer.restart();
		}
	}
	
	// Fonction qui gére la collision entre le serpent, les limites de la map ainsi que lui-même
	public void checkCollisions() {
		//checks if head collides with body
		if(this.snake.isInvincible()) {
			for(int i = this.snake.getBodyParts();i>0;i--) {
				if((this.snake.getX()[0] == this.snake.getX()[i]&& (this.snake.getY()[0] == this.snake.getY()[i]))){
					this.running = true;
				}
			}
		}
		else if(!this.snake.isInvincible()) {
			for(int i = this.snake.getBodyParts();i>0;i--) {
				if((this.snake.getX()[0] == this.snake.getX()[i]&& (this.snake.getY()[0] == this.snake.getY()[i]))){
					this.running = false;
				}
			}
		}
			if(this.snake.getX()[0] < 0) {
				this.running = false;
			}
			if(this.snake.getX()[0] > map.getScreenWidth()) {
				this.running = false;
			}	
			if(this.snake.getY()[0] < 0) {
				this.running = false;
			}
			if(this.snake.getY()[0] > map.getScreenHeight()) {
				this.running = false;
			}

		if(!this.running) {
			this.timer.stop();
		}
	}
	
	// Fonction qui affiche l'écran de fin de partie avec le score
	public void gameOver(Graphics g) {
		g.setColor(Color.green);
		g.setFont( new Font("Ink Free",Font.BOLD, 40));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Score: "+this.apple.getApplesEaten(), (map.getScreenWidth() - metrics1.stringWidth("Score: "+ this.apple.getApplesEaten()))/2, g.getFont().getSize());
		g.setColor(Color.blue);
		g.setFont( new Font("Ink Free",Font.BOLD, 75));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Game Over", (map.getScreenWidth() - metrics2.stringWidth("Game Over"))/2, map.getScreenHeight()/2);
	}
	
	// Fonction qui démarre le jeu
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(this.running) {
			move();
			checkApple();
			if(this.apple.getApplesEaten() == 7) {
				this.star.setNewStarXY(map);
			}
			checkStar();
			checkCollisions();
		}
		repaint();
	}
	
	// Classe pour gérer la direction du serpent
	public class MyKeyAdapter extends KeyAdapter{
		
		// Fonction qui attribue des touches du clavier aux directions possibles du serpent
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
