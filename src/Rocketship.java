import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Rocketship extends GameObject {
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	
	Rocketship(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		this.speed = 10;
		if (needImage) {
		    loadImage ("rocket.png");
		}
	}
	
	void draw(Graphics g) {
		move();
		if (gotImage) {
			g.drawImage(image, x, y, width, height, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(x, y, width, height);
		}
		
	}
	
	void move() {
		if (moveUp&&y>0) {
	    	up();
	    }
		if (moveDown&&y<=LeagueInvaders.HEIGHT-height-speed) {
	    	down();
	    }
		if (moveLeft&&x>0) {
	    	left();
	    }
		if (moveRight&&x<=LeagueInvaders.WIDTH-width-speed) {
	    	right();
	    }
	}
	
	void up() {
		y-=speed;
		collisionBox.y=y;
	}
	void down() {
		y+=speed;
		collisionBox.y=y;
	}
	void left() {
		x-=speed;
		collisionBox.x=x;
	}
	void right() {
		x+=speed;
		collisionBox.x=x;
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
	
	public Projectile getProjectile() {
        return new Projectile(x+(width/2)-5, y, 10, 10);
}

}
