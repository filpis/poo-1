package Game;
import Game.KeyboardControllers.KeyboardController;

import javax.swing.*;
import java.awt.*;

public class Ship extends ControlledGameObject {

    private static final ImageIcon SHIP_ICON = new ImageIcon("images/nav.gif");
    private static final ImageIcon BONUS_ENEMY_ICON = new ImageIcon("images/bonusEnemySkin.gif");
    private static final ImageIcon LIFE_COUNTER_SHIP_ICON = new ImageIcon("images/shipSkinSmall.gif");

    public Ship(int xPosition, int yPosition, Color color, KeyboardController control) {
        super(xPosition, yPosition, color, control);
    }

    @Override
    public void draw(Graphics g) {
        SHIP_ICON.paintIcon(null, g, this.getXPosition(), this.getYPosition());
    }

    public void bonusDraw(Graphics g) {
        BONUS_ENEMY_ICON.paintIcon(null, g, this.getXPosition(), this.getYPosition());
    }

    public void lifeDraw(Graphics g) {
        LIFE_COUNTER_SHIP_ICON.paintIcon(null, g, this.getXPosition(), this.getYPosition());
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.getXPosition(), this.getYPosition(), 50, 50);
    }

    @Override
    public void move() {
        if (control.getKeyStatus(37)) {
            xPos -= 10;
        }
        if (control.getKeyStatus(39)) {
            xPos += 10;
        }
        if (xPos > 800) {
            xPos = -50;
        }
        if (xPos < -50) {
            xPos = 800;
        }
    }
}
