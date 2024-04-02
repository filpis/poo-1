package Jogo;

import Controle.Teclado;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class JanelaDeJogo {
    private Timer temporizador;
    private Teclado controle;
    private final int largura = 800, altura = 800, fps = 120;
    Random r = new Random();
    private int pontos = 0, nivel = 1, vidas = 3, recorde, marcadorX, marcadorY;
    private static int vidaChefe = 30;
    File f = new File("Recorde.txt");
    private Nave naveJogador, vidaUnica, inimigoBonus;
    private Inimigo inimigo;
    private Escudo escudo;
    private Tiro tiro;
    private Raio raio1, raio2, raio3;
    private boolean novoTiroPodeAcertar = true, novoRaioPodeAcertar = true, novoInimigoBonus = true, marcadorAcerto = false;
    private ArrayList<Nave> listaVida = new ArrayList();
    private ArrayList<Nave> listaInimigoBonus = new ArrayList();
    private ArrayList<Inimigo> listaInimigo = new ArrayList();
    private ArrayList<Escudo> listaEscudo = new ArrayList();
    private ArrayList<Raio> listaRaio = new ArrayList();
    private ImageIcon fundo;
    
    public static int getVidaChefe(){
        return vidaChefe;
    }
    
    public int getNivel(){
        return nivel;
    }
    
    public ArrayList<Inimigo> getListaInimigo(){
        return listaInimigo;
    }
    
    public Inimigo getInimigo(){
        return inimigo;
    }
    
    public Teclado getControle(){
        return controle;
    }
    
    public void setInimigo(int xPos, int yPos, int xVel, int yVel, int tipo, Color cor, int largura, int altura){
        inimigo = new Inimigo(xPos, yPos, xVel, yVel, tipo, cor, largura, altura);
    }
    
    public void setNaveJogador(int xPos, int yPos, Color cor, Teclado controle){
        naveJogador = new Nave(xPos, yPos, cor, controle);
    }
    
    public final void setJogo(){
        if(getNivel() != 3 && getNivel() != 6 && getNivel() != 9 && getNivel() != 12){
            for(int linha = 0; linha < 6; linha++){
                for(int coluna = 0; coluna < 5; coluna++){
                    setInimigo((20 + (linha*100)), (20 + (coluna*30)), getNivel(), 0, coluna, null, 40, 40);
                    getListaInimigo().add(getInimigo());
                }
            }
        }
        if(getNivel() == 3 || getNivel() == 6 || getNivel() == 9 || getNivel() == 12){
            setInimigo(20, 20, 3, 0, 100, null, 150, 150);
            getListaInimigo().add(getInimigo());
        }
        if(getNivel() == 1){
            JOptionPane.showMessageDialog(null, "Bem vindo ao Space Wars!\n\nTUTORIAL:\n\n- Use as setas para direita e para esqueda para se mover;\n- Pressione espaco para atirar;\n- Os inimigos ficam mais rapidos cada nivel;\n- Chefe a cada 3 niveis;\n- Um inimigo bonus ira aparecer aleatoriamente, acerte-o para pontos extras!\n- Pressione R para resetar a pontuacao recorde");
        }
        getControle().resetTeclado();
        setNaveJogador(375, 730, null, getControle());
    }
}
