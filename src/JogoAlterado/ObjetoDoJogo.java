package Jogo;

import java.awt.Color;
import java.awt.Rectangle;

public abstract class ObjetoDoJogo implements Desenhavel{
    
    private int xPos;
    private int yPos;
    private Color cor;
    private boolean estaColidindo;
    
    public ObjetoDoJogo(){};
    
    public ObjetoDoJogo(int xPos, int yPos, Color cor){
        setXPos(xPos);
        setYPos(yPos);
        setCor(cor);
    }
    
    public abstract Rectangle getFronteiras();
    
    public int getXPos(){
        return xPos;
    }
    
    public int getYPos(){
        return yPos;
    }
    
    public Color getCor(){
        return cor;
    }
    
    public boolean getEstaColidindo(){
        return estaColidindo;
    }
    
    public final void setXPos(int xPos){
        this.xPos = xPos;
    }
    
    public final void setYPos(int yPos){
        this.yPos = yPos;
    }
    
    public final void setCor(Color cor){
        this.cor = cor;
    }
    
    public final void setEstaColidindo(boolean estaColidindo){
        this.estaColidindo = estaColidindo;
    }
    
    public boolean estaColidindo(ObjetoDoJogo outroObjeto){
        setEstaColidindo(outroObjeto.getFronteiras().intersects(this.getFronteiras()));
        return getEstaColidindo();
    }
}
