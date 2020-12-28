import javax.swing.JFrame;

public class LeagueInvaders {
	JFrame gameFrame;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 800;
	
	public static void main(String[] args) {
		LeagueInvaders leagueInvadersInstance = new LeagueInvaders();
		leagueInvadersInstance.setup();
	}
	
	LeagueInvaders() {
		gameFrame = new JFrame();
		
	}
	
	void setup() {
		gameFrame.setSize(WIDTH, HEIGHT);
		gameFrame.setVisible(true);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
