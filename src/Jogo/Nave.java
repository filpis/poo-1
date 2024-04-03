package Jogo;

import Controle.Teclado;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Nave extends ObjetoControlavel {
    ImageIcon nave = new ImageIcon("images/shipSkin.gif");
    ImageIcon inimigoBonus = new ImageIcon("images/bonusEnemySkin.gif");
    ImageIcon vidaNave = new ImageIcon("images/shipSkinSmall.gif");
    
    public Nave(int xPos, int yPos, Color cor, Teclado controle){
        super(xPos, yPos, cor, controle);
    }
    
    public ImageIcon getInimigoBonus(){
        return inimigoBonus;
    }
    
    public ImageIcon getVidaNave(){
        return vidaNave;
    }
    
    public ImageIcon getNave(){
        return nave;
    }
    
    public void desenhaBonus(Graphics g){
        getInimigoBonus().paintIcon(null, g, this.getXPos(), this.getYPos());
    }
    
    public void desenhaVidaNave(Graphics g){
        getVidaNave().paintIcon(null, g, this.getXPos(), this.getYPos());
    }
    
    @Override
    public void desenha(Graphics g){
        getNave().paintIcon(null, g, this.getXPos(), this.getYPos());
    }
    
    @Override
    public Rectangle getFronteiras(){
        Rectangle fronteiraNave = new Rectangle(this.getXPos(), this.getYPos(), 50, 50);
        return fronteiraNave;
    }
    
    @Override
    public void movimenta(){
        if(getControle().getTecla(37))
            setXPos(getXPos() - 10);
        if(getControle().getTecla(39))
            setXPos(getXPos() + 10);
        if(getXPos() > 800)
            setXPos(-50);
        if(getXPos() < -50)
            setXPos(800);
    }
}
