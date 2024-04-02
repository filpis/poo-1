package Jogo;

import java.awt.Color;

public abstract class ObjetoMovimentavel extends ObjetoDoJogo implements Movimentavel{
    private int xVel;
    private int yVel;
    
    public ObjetoMovimentavel(int xPos, int yPos, int xVel, int yVel, Color cor){
        super(xPos, yPos, cor);
        setXVel(xVel);
        setYVel(yVel);
    }
    
    public int getXVel(){
        return xVel;
    }
    
    public int getYVel(){
        return yVel;
    }
    
    public final void setXVel(int xVel){
        this.xVel = xVel;
    }
    
    public final void setYVel(int yVel){
        this.yVel = yVel;
    }
    
    @Override
    public void movimenta(){
        setXPos(getXPos() + getXVel());
        setYPos(getYPos() + getYVel());
    }
}
