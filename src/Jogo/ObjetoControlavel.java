package Jogo;

import Controle.Teclado;
import java.awt.Color;

public abstract class ObjetoControlavel extends ObjetoDoJogo implements Movimentavel{
    private Teclado controle;
    
    public ObjetoControlavel(int xPos, int yPos, Color cor, Teclado controle){
        super(xPos, yPos, cor);
        setControle(controle);
    }
    
    public final void setControle(Teclado controle){
        this.controle = controle;
    }
    
    public Teclado getControle(){
        return controle;
    }
}
