import javax.swing.JFrame;

// Classe avec JFrame pour gérer l'interface

public class GameFrame extends JFrame{
	
	GameFrame(){
		
		// Instanciation de l'interface
		this.add(new GamePanel());
		this.setTitle("Snake");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);;
		this.setLocationRelativeTo(null);
	}

}
