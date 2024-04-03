
package Game;

import javax.swing.*;
import java.awt.*;


public class Enemy extends MovingGameObject {

    ImageIcon alien1 = new ImageIcon("images/alien1Skin.gif");
    ImageIcon alien2 = new ImageIcon("images/alien2Skin.gif");
    ImageIcon alien3 = new ImageIcon("images/alien3Skin.gif");
    ImageIcon alienBoss = new ImageIcon("images/boss1.gif");
    ImageIcon alienBoss2 = new ImageIcon("images/boss2.gif");
    ImageIcon alienBoss3 = new ImageIcon("images/boss3.gif");
    
    private int enemytype, width, height;

    
    public Enemy(int xPosition, int yPosition, int xVelocity, int yVelocity, int enemyType, Color color, int width, int height) {
        super(xPosition, yPosition, xVelocity, yVelocity, color);
        this.enemytype = enemyType;
        this.width = width;
        this.height = height;
    }
    
    @Override
    // Draws alien
    public void draw(Graphics g) {
        if (this.enemytype % 3 == 0) {
            alien1.paintIcon(null, g, this.getXPosition(), this.getYPosition());
        } else if (this.enemytype % 3 == 1 && this.enemytype != 100) {
            alien2.paintIcon(null, g, this.getXPosition(), this.getYPosition());
        } else if (this.enemytype % 3 == 2) {
            alien3.paintIcon(null, g, this.getXPosition(), this.getYPosition());
        } if (this.enemytype == 100)
        {
            if(GamePanel. getBossHealth()>20){
                alienBoss.paintIcon(null, g, this.getXPosition(), this.getYPosition());
            }
            else if(GamePanel.getBossHealth()>10){
                alienBoss2.paintIcon(null, g, this.getXPosition(), this.getYPosition());
            }
            else if(GamePanel.getBossHealth()>0){
                alienBoss3.paintIcon(null, g, this.getXPosition(), this.getYPosition());
            }
        }
    }

    @Override
    public Rectangle getBounds() {
        Rectangle enemyHitBox = new Rectangle(this.getXPosition(), this.getYPosition(), width, height);
        return enemyHitBox;
    }

    @Override
    public void move() {
        xPos += xVel;
    }

}
