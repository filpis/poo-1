package Jogo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Raio extends ObjetoMovimentavel{
    
    public Raio(int xPos, int yPos, int diametro, Color cor){
        super(xPos, yPos, 0, 0, cor);
    }
    
    @Override
    public void desenha(Graphics g){
        g.setColor(getCor());
    }
    
    @Override
    public Rectangle getFronteiras(){
        Rectangle raioFronteira = new Rectangle(getXPos(), getYPos(), 7, 15);
        return raioFronteira;
    }
}
