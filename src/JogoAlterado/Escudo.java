package Jogo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Escudo extends ObjetoDoJogo{
    private int largura, altura;
    
    public Escudo(int xPos, int yPos, int largura, int altura, Color cor){
        super(xPos, yPos, cor);
        setLargura(largura);
        setAltura(altura);
    }
    
    public final void setLargura(int largura){
        this.largura = largura;
    }
    
    public final void setAltura(int altura){
        this.altura = altura;
    }
    
    public int getLargura(){
        return largura;
    }
    
    public int getAltura(){
        return altura;
    }
    
    @Override
    public void desenha(Graphics g){
        g.setColor(getCor());
        g.fillRect(this.getXPos(), this.getYPos(), 90, 10);
    }
    
    @Override
    public Rectangle getFronteiras() {
        Rectangle fronteiraEscudo = new Rectangle(this.getXPos(), this.getYPos(), 90, 10);
        return fronteiraEscudo;
    }
}
