package Jogo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Tiro extends ObjetoMovimentavel{
    int diametro;
    
    public Tiro(int xPos, int yPos, int diametro, Color cor){
        super(xPos, yPos, 0, 0, cor);
        setDiametro(diametro);
    }
    
    public int getDiametro(){
        return diametro;
    }
    
    public final void setDiametro(int diametro){
        this.diametro = diametro;
    }
    
    @Override
    public void desenha(Graphics g){
        g.setColor(getCor());
        g.fillRect(this.getXPos(), this.getYPos(), 7, 15);
    }
    
    @Override
    public Rectangle getFronteiras(){
        Rectangle fronteiraTiro = new Rectangle(getXPos(), getYPos(), 7, 15);
        return fronteiraTiro;
    }
}
