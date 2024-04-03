package Game;

import interfaces.Moveable;
import java.awt.Color;

public abstract class MovingGameObject extends GameObject implements Moveable {

    protected int xVel;
    protected int yVel;

    public MovingGameObject(int xPosition, int yPosition, int xVelocity, int yVelocity, Color color) {
        super(xPosition, yPosition, color);
        this.xVel = xVelocity;
        this.yVel = yVelocity;
    }

    public int getXVelocity() {
        return xVel;
    }

    public int getYVelocity() {
        return yVel;
    }

    public void setXVelocity(int xVelocity) {
        this.xVel = xVelocity;
    }

    public void setYVelocity(int yVelocity) {
        this.yVel = yVelocity;
    }

    @Override
    public void move() {
        this.xPos += xVel;
        this.yPos += yVel;
    }
}

//package Game;
//
//import interfaces.Moveable;
//
//import java.awt.Color;
//
//
//public abstract class MovingGameObject extends GameObject implements Moveable {
//
//    int xVel;
//    int yVel;
//
//    // Constructor for any non controllable object
//    public MovingGameObject(int xPosition, int yPosition, int xVelocity, int yVelocity, Color color)
//    {
//        super(xPosition, yPosition, color);
//        this.xVel = xVelocity;
//        this.yVel = yVelocity;
//
//    }
//
//    public int getXVelocity()
//    {
//        return xVel;
//    }
//    public int getYVelocity()
//    {
//        return yVel;
//    }
//    public void setXVelocity(int xVelocity)
//    {
//        this.xVel = xVelocity;
//    }
//    public void setYVelocity(int yVelocity)
//    {
//        this.yVel = yVelocity;
//    }
//    @Override
//
//    public void move()
//    {
//        this.xPos += xVel;
//        this.yPos += yVel;
//    }
//
//}
