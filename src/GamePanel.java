import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	final int HINT = 3;
	int currentState = MENU;
	
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	
	
	Font titleFont;
	Font hintFont;
	
	Timer frameDraw;
	Timer alienSpawn;
	
	Rocketship rocket = new Rocketship(250, 700, 50, 50);
	
	ObjectManager objectManager = new ObjectManager(rocket);
	
	GamePanel() {
		titleFont = new Font("Arial", Font.PLAIN, 48);
		hintFont = new Font("Arial", Font.PLAIN, 25);
		frameDraw = new Timer(1000/60,this);
		frameDraw.start();
		
		if (needImage) {
		    loadImage ("space.png");
		}
		
	}
	
	void updateMenuState() {
		
	}
	void updateGameState() {
		objectManager.update();
		if(!rocket.isActive) {
			currentState = END;
		}
	}
	void updateEndState() {
	
	}
	
	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("LEAGUE INVADERS", 26, 200);
		
		g.setFont(hintFont);
		g.drawString("Press ENTER to start", 123, 350);
		
		g.drawString("Press SPACE for intstructions", 80, 500);
		
		
	}
	
	void drawHintState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("LEAGUE INVADERS", 26, 200);
		
		g.setFont(hintFont);
		g.drawString("Press UP, DOWN, LEFT, or RIGHT to", 35, 350);
		g.drawString("move your rocket, press SPACE to shoot.", 20, 375);
		
		g.drawString("Enemies will fall from the top of the screen.", 10, 425);
		g.drawString("Your goal is to shoot as many of them as", 20, 450);
		g.drawString("possible without letting any touch your", 30, 475);
		g.drawString("rocket. The game ends when you press", 25, 500);
		g.drawString("ENTER or your rocket collides with an", 30, 525);
		g.drawString("enemy.", 200, 550);
		
		g.drawString("Press ENTER to continue.", 100, 650);
	}
	void drawGameState(Graphics g) {
		
		if (gotImage) {
			g.drawImage(image, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
		} else {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		}
		
		g.setFont(hintFont);
		g.setColor(Color.WHITE);
		g.drawString(""+objectManager.getScore(), LeagueInvaders.WIDTH-40, LeagueInvaders.HEIGHT-25);
		objectManager.draw(g);
		
	}
	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("Game Over", 121, 200);
		
		g.setFont(hintFont);
		g.drawString("You killed " + objectManager.getScore() + " enemies", 120, 350);
		
		g.drawString("Press ENTER to restart", 118, 500);
	}
	
	void startGame() {
		alienSpawn = new Timer(1000, objectManager);
		alienSpawn.start();
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		if(currentState == MENU){
		    drawMenuState(g);
		}else if(currentState == GAME){
		    drawGameState(g);
		}else if(currentState == END){
		    drawEndState(g);
		} else if (currentState == HINT) {
			drawHintState(g);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(currentState == MENU){
		    updateMenuState();
		}else if(currentState == GAME){
		    updateGameState();
		}else if(currentState == END){
		    updateEndState();
		}
		
		
		repaint();

		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (currentState == END) {
		        currentState = MENU;
		        rocket = new Rocketship(250, 700, 50, 50);
		        objectManager = new ObjectManager(rocket);
		    } else if (currentState == HINT) {
		    	currentState = MENU;
		    } else {
		        currentState++;
		        if (currentState == GAME) {
		        	startGame();
		        } else if (currentState == END) {
		        	alienSpawn.stop();
		        }
		    }
		} else if(e.getKeyCode()==KeyEvent.VK_SPACE && currentState == MENU) { 
			currentState = HINT;
		} else if (currentState == GAME) {
		
			if (e.getKeyCode()==KeyEvent.VK_UP) {
			    
			    rocket.moveUp = true;
			} else if (e.getKeyCode()==KeyEvent.VK_DOWN) {
			    
			    rocket.moveDown = true;
			} else if (e.getKeyCode()==KeyEvent.VK_LEFT) {
			    
			    rocket.moveLeft = true;
			} else if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
			    
			    rocket.moveRight = true;
			} else if (e.getKeyCode()==KeyEvent.VK_SPACE) {
				objectManager.addProjectile(rocket.getProjectile());
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (currentState == GAME) {
			if (e.getKeyCode()==KeyEvent.VK_UP) {
			    rocket.moveUp = false;
			} else if (e.getKeyCode()==KeyEvent.VK_DOWN) {
			    rocket.moveDown = false;
			} else if (e.getKeyCode()==KeyEvent.VK_LEFT) {
			    rocket.moveLeft = false;
			} else if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
			    rocket.moveRight = false;
			}
		}
	}
	
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}
}
