import java.awt.Color;
import java.awt.Graphics;

public class Rocketship extends GameObject {

	Rocketship(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		this.speed = 10;
	}
	
	void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
		move();
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
	}
	void down() {
		y+=speed;
	}
	void left() {
		x-=speed;
	}
	void right() {
		x+=speed;
	}

}
