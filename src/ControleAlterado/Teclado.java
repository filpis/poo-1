package Controle;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener{
    private boolean[] tecla;
    
    public Teclado(){
        tecla = new boolean[256];
    }
    
    public boolean getTecla(int teclaCodigo){
        if(teclaCodigo < 0 || teclaCodigo > 255){
            return false;
        }
        else{
            return tecla[teclaCodigo];
        }
    }
    
    public void resetTeclado(){
        tecla = new boolean[256];
    }
    
    @Override
    public void keyTyped(KeyEvent ke){
        
    }
    
    @Override
    public void keyPressed(KeyEvent ke){
        tecla[ke.getKeyCode()] = true;
    }
    
    @Override
    public void keyReleased(KeyEvent ke){
        tecla[ke.getKeyCode()] = false;
    }
    
}
