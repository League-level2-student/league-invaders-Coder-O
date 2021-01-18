import java.awt.Rectangle;

public class GameObject {
	int x;
	int y;
	int width;
	int height;
	int speed = 0;
	boolean isActive = true;
	boolean moveUp = false;
	boolean moveDown = false;
	boolean moveLeft = false;
	boolean moveRight = false;
	Rectangle collisionBox;
	
	GameObject(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		collisionBox = new Rectangle(x, y, width, height);
		
	}
	
	void update() {
		collisionBox.setBounds(x, y, width, height);
	}
}
