
package Game;

import interfaces.Drawable;

import java.awt.Color;
import java.awt.Rectangle;

public abstract class GameObject implements Drawable {

    int xPos;
    int yPos;
    Color color;
    boolean isColliding;
    
    public GameObject(){};
    
    public GameObject(int xPosition, int yPosition, Color color) {
        this.xPos = xPosition;
        this.yPos = yPosition;
        this.color = color;
    }

    public abstract Rectangle getBounds();

    public int getXPosition() {
        return xPos;
    }

    public int getYPosition() {
        return yPos;
    }

    public Color getColor() {
        return color;
    }

    public void setXPosition(int xPosition) {
        this.xPos = xPosition;
    }

    public void setYPosition(int yPosition) {
        this.yPos = yPosition;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isColliding(GameObject other) {
        isColliding = other.getBounds().intersects(this.getBounds());
        return isColliding;
    }
}
