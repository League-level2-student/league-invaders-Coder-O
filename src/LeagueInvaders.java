import javax.swing.JFrame;

public class LeagueInvaders {
	GamePanel gamePanel;
	JFrame gameFrame;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 800;
	
	public static void main(String[] args) {
		LeagueInvaders leagueInvadersInstance = new LeagueInvaders();
		leagueInvadersInstance.setup();
	}
	
	LeagueInvaders() {
		gameFrame = new JFrame();
		gamePanel = new GamePanel();
		
	}
	
	void setup() {
		gameFrame.add(gamePanel);
		gameFrame.setSize(WIDTH, HEIGHT+20);
		gameFrame.setVisible(true);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.addKeyListener(gamePanel);
		
		
	}
}
