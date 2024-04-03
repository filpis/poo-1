
package Game;

import java.awt.*;
public class Beam extends MovingGameObject {

    private static final int WIDTH = 7;
    private static final int HEIGHT = 15;

    public Beam(int xPosition, int yPosition, Color color) {
        super(xPosition, yPosition, 0, 0, color);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(getXPosition(), getYPosition(), WIDTH, HEIGHT);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(getXPosition(), getYPosition(), WIDTH, HEIGHT);
    }
}