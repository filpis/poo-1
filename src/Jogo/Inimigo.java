package Jogo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Inimigo extends ObjetoMovimentavel {
    ImageIcon alien1 = new ImageIcon("images/alien1Skin.gif"); 
    ImageIcon alien2 = new ImageIcon("images/alien2Skin.gif"); 
    ImageIcon alien3 = new ImageIcon("images/alien3Skin.gif");
    ImageIcon alienBoss1 = new ImageIcon("images/boss1.gif");
    ImageIcon alienBoss2 = new ImageIcon("images/boss2.gif"); 
    ImageIcon alienBoss3 = new ImageIcon("images/boss3.gif");
    private int tipo, largura, altura;

    public Inimigo(int xPos, int yPos, int xVel, int yVel, int tipo, Color cor, int largura, int altura) {
        super(xPos, yPos, xVel, yVel, cor);
        setTipo(tipo);
        setLargura(largura);
        setAltura(altura);
    }
    
    public final void setTipo(int tipo){
        this.tipo = tipo;
    }
    
    public final void setLargura(int largura){
        this.largura = largura;
    }
    
    public final void setAltura(int altura){
        this.altura = altura;
    }
    
    public int getTipo(){
        return tipo;
    }
    
    public int getLargura(){
        return largura;
    }
    
    public int getAltura(){
        return altura;
    }
    
    public ImageIcon getAlien1(){
        return alien1;
    }
    
    public ImageIcon getAlien2(){
        return alien2;
    }
    
    public ImageIcon getAlien3(){
        return alien3;
    }
    
    public ImageIcon getAlienBoss1(){
        return alienBoss1;
    }
    
    public ImageIcon getAlienBoss2(){
        return alienBoss2;
    }
    
    public ImageIcon getAlienBoss3(){
        return alienBoss3;
    }
    
    @Override
    public void desenha(Graphics g) {
        if (getTipo() % 3 == 0)
            getAlien1().paintIcon(null, g, this.getXPos(), this.getYPos());
        else if(getTipo() % 3 == 1 && getTipo() != 100)
            getAlien2().paintIcon(null, g, this.getXPos(), this.getYPos());
        else if(getTipo() % 3 == 2)
            getAlien3().paintIcon(null, g, this.getXPos(), this.getYPos());
        if(getTipo() == 100)
        {
            if(JanelaDeJogo.getVidaChefe() > 20)
                getAlienBoss1().paintIcon(null, g, this.getXPos(), this.getYPos());
            else if(JanelaDeJogo.getVidaChefe() > 10)
                getAlienBoss2().paintIcon(null, g, this.getXPos(), this.getYPos());
        }
    }
    
    @Override
    public Rectangle getFronteiras(){
        Rectangle fronteiraInimigo = new Rectangle(this.getXPos(), this.getYPos(), getLargura(), getAltura());
        return fronteiraInimigo;
    }
    
    @Override
    public void movimenta() {
        setXPos(getXPos() + getXVel());
    }
}
