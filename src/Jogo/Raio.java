package Jogo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Raio extends ObjetoMovimentavel{
    
    public Raio(int xPos, int yPos, int diametro, Color cor){
        super(xPos, yPos, 0, 0, cor);
    }
    
    @Override
    public void desenha(Graphics g){
        g.setColor(getCor());
        g.fillRect(this.getXPos(), this.getYPos(), 7, 15);
    }
    
    @Override
    public Rectangle getFronteiras(){
        Rectangle raioFronteira = new Rectangle(getXPos(), getYPos(), 7, 15);
        return raioFronteira;
    }
}
