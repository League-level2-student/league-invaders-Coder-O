import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener {
	Rocketship rocket;
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Alien> aliens = new ArrayList<Alien>();
	Random random = new Random();
	
	int score = 0;
	
	ObjectManager(Rocketship rocket) {
		this.rocket = rocket;
	}
	
	void addProjectile(Projectile projectile) {
		projectiles.add(projectile);
	}
	
	void addAlien() {
		aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH-50),0,50,50));
	}
	
	int getScore() {
		return score;
	}
	
	void update() {
		for (int i = 0; i < aliens.size(); i++) {
			aliens.get(i).update();
			if (aliens.get(i).y > LeagueInvaders.HEIGHT) {
				aliens.get(i).isActive = false;
				
			}
		}
		
		for (Projectile projectile : projectiles) {
			projectile.update();
			if(projectile.y < 0) {
				projectile.isActive = false;
			}
		}
		checkCollision();
		if(!rocket.isActive) {
			
		} else {
			purgeObjects();
		}
		
	}

	void checkCollision() {
		//Checks if any Aliens intersect with any Projectiles or the Rocket, setting both resulting objects' isActive to false if so
		for (Alien alien : aliens) {
			
			for (Projectile projectile : projectiles) {
				if(alien.collisionBox.intersects(projectile.collisionBox)){
					alien.isActive = false;
					projectile.isActive = false;
					score++;
				}
			}
			
			if(alien.collisionBox.intersects(rocket.collisionBox)){
				alien.isActive = false;
				rocket.isActive = false;
			}
		}
	}
	
	void draw(Graphics g) {
		//Draws the Rocket, all Aliens, and all Projectiles
		rocket.draw(g);
		for (Alien alien : aliens) {
			alien.draw(g);
		}
		for (Projectile projectile : projectiles) {
			projectile.draw(g);
		}
	}
	
	void purgeObjects() {
		//If any aliens or projectiles are inactive, deletes them
		for (int i = 0; i < aliens.size(); i++) {
			if(!aliens.get(i).isActive) {
				aliens.remove(i);
				i--;
			}
			
		}
		
		for (int i = 0; i < projectiles.size(); i++) {
			if(!projectiles.get(i).isActive) {
				projectiles.remove(i);
				i--;
			}
			
		}
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		addAlien();
	}
}
