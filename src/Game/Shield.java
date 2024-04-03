package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Shield extends GameObject {

    private int width;
    private int height;
    private static final int SHIELD_WIDTH = 90;
    private static final int SHIELD_HEIGHT = 10;

    public Shield(int xPosition, int yPosition, int width, int height, Color color) {
        super(xPosition, yPosition, color);
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(this.getXPosition(), this.getYPosition(), width, height);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.getXPosition(), this.getYPosition(), width, height);
    }
}


