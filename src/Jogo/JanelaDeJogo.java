
package Jogo;

import Controle.Teclado;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
        
public class JanelaDeJogo extends JPanel {


    private Timer temporizador;
    private Teclado controle;
    // Tamanho da tela e fps
    private final int largura = 800;
    private final int altura = 800;
    private final int framesPorSegundo = 120;

    // Contadores
    Random r = new Random();
    private int pontos = 0;
    private int nivel = 1;
    private int numeroDeVidas = 3;
    private int recorde;
    private int marcadorX, marcadorY;
    private static int vidaChefe = 30;
    File f = new File("Recorde.txt");

    // Objetos
    private Nave naveJogador;
    private Nave vidaUnica;
    private Nave inimigoBonus;
    private Inimigo inimigo;
    private Escudo escudo;
    private Tiro tiro;
    private Raio raio1, raio2, raio3;

    // Booleanos
    private boolean novoTiroPodeAcertar = true;
    private boolean novoRaioPodeAcertar = true;
    private boolean novoInimigoBonus = true;
    private boolean marcadorAcerto = false;

    // Listas 
    private final ArrayList<Nave> listaVida = new ArrayList();
    private final ArrayList<Nave> listaInimigoBonus = new ArrayList();
    private final ArrayList<Inimigo> listaInimigo = new ArrayList();
    private final ArrayList<Escudo> listaEscudo = new ArrayList();
    private final ArrayList<Raio> listaRaio = new ArrayList();
    private final ImageIcon fundo = new ImageIcon("images/backgroundSkin.jpg");
    
    public Timer getTemporizador(){
        return temporizador;
    }
    
    public final Teclado getControle(){
        return controle;
    }
    
    public final int getLargura(){
        return largura;
    }
    
    public final int getAltura(){
        return altura;
    }
    
    public int getFPS(){
        return framesPorSegundo;
    }
    
    public Random getR(){
        return r;
    }
    
    public int getPontos(){
        return pontos;
    }
    
    public int getNivel(){
        return nivel;
    }
    
    public int getNumeroDeVidas(){
        return numeroDeVidas;
    }
    
    public int getRecorde(){
        return recorde;
    }
    
    public int getMarcadorX(){
        return marcadorX;
    }
    
    public int getMarcadorY(){
        return marcadorY;
    }
    
    public static int getVidaChefe() {
        return vidaChefe;
    }
    
    public Nave getNaveJogador(){
        return naveJogador;
    }
    
    public Nave getVidaUnica(){
        return vidaUnica;
    }
    
    public Nave getInimigoBonus(){
        return inimigoBonus;
    }
    
    public Inimigo getInimigo(){
        return inimigo;
    }
    
    public Escudo getEscudo(){
        return escudo;
    }
    
    public Tiro getTiro(){
        return tiro;
    }
    
    public Raio getRaio1(){
        return raio1;
    }
    
    public Raio getRaio2(){
        return raio2;
    }
    
    public Raio getRaio3(){
        return raio3;
    }
    
    public boolean getNovoTiroPodeAcertar(){
        return novoTiroPodeAcertar;
    }
    
    public boolean getNovoRaioPodeAcertar(){
        return novoRaioPodeAcertar;
    }
    
    public boolean getNovoInimigoBonus(){
        return novoInimigoBonus;
    }
    
    public boolean getMarcadorAcerto(){
        return marcadorAcerto;
    }
    
    public ArrayList<Nave> getListaVida(){
        return listaVida;
    }
    
    public ArrayList<Nave> getListaInimigoBonus(){
        return listaInimigoBonus;
    }
    
    public ArrayList<Inimigo> getListaInimigo(){
        return listaInimigo;
    }
    
    public ArrayList<Escudo> getListaEscudo(){
        return listaEscudo;
    }
    
    public ArrayList<Raio> getListaRaio(){
        return listaRaio;
    }
    
    public ImageIcon getFundo(){
        return fundo;
    }
    
    public void setTemporizador(int framesPorSegundo){
        this.temporizador = new Timer(1000 / framesPorSegundo, new ActionListener() {

            private int numeroDeFrame = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the game's state and repaint the screen
                atualizaEstadoDoJogo(numeroDeFrame++);
                repaint();
            }
        });
    }
    
    public final void setControle(){
        this.controle = new Teclado();
    }
    
    public void setPontos(int pontos){
        this.pontos = pontos;
    }
    
    public void setNivel(int nivel){
        this.nivel = nivel;
    }
    
    public void setNumeroDeVidas(int numeroDeVidas){
        this.numeroDeVidas = numeroDeVidas;
    }
    
    public void setRecorde(int recorde){
        this.recorde = recorde;
    }
    
    public void setMarcadorX(int marcadorX){
        this.marcadorX = marcadorX;
    }
    
    public void setMarcadorY(int marcadorY){
        this.marcadorY = marcadorY;
    }
    
    public void setVidaChefe(int vidaChefe){
        JanelaDeJogo.vidaChefe = vidaChefe;
    }
    
    public void setNaveJogador(int xPos, int yPos, Color cor, Teclado controle){
        this.naveJogador = new Nave(xPos, yPos, cor, controle);
    }
    
    public void setVidaUnica(int xPos, int yPos, Color cor, Teclado controle){
        this.vidaUnica = new Nave(xPos, yPos, cor, controle);
    }
    
    public void setInimigoBonus(int xPos, int yPos, Color cor, Teclado controle){
        this.inimigoBonus = new Nave(xPos, yPos, cor, controle);
    }
    
    public void setInimigo(int xPos, int yPos, int xVel, int yVel, int tipo, Color cor, int largura, int altura){
        this.inimigo = new Inimigo(xPos, yPos, xVel, yVel, tipo, cor, largura, altura);
    }
    
    public void setEscudo(int xPos, int yPos, int largura, int altura, Color cor){
        this.escudo = new Escudo(xPos, yPos, largura, altura, cor);
    }
    
    public void setTiro(int xPos, int yPos, int diametro, Color cor){
        this.tiro = new Tiro(xPos, yPos, diametro, cor);
    }
    
    public void setRaio1(int xPos, int yPos, int diametro, Color cor){
        this.raio1 = new Raio(xPos, yPos, diametro, cor);
    }
    
    public void setRaio2(int xPos, int yPos, int diametro, Color cor){
        this.raio2 = new Raio(xPos, yPos, diametro, cor);
    }
    
    public void setRaio3(int xPos, int yPos, int diametro, Color cor){
        this.raio3 = new Raio(xPos, yPos, diametro, cor);
    }
    
    public void setNovoTiroPodeAcertar(boolean novoTiroPodeAcertar){
        this.novoTiroPodeAcertar = novoTiroPodeAcertar;
    }
    
    public void setNovoRaioPodeAcertar(boolean novoRaioPodeAcertar){
        this.novoRaioPodeAcertar = novoRaioPodeAcertar;
    }
    
    public void setNovoInimigoBonus(boolean novoInimigoBonus){
        this.novoInimigoBonus = novoInimigoBonus;
    }
    
    public void setMarcadorAcerto(boolean marcadorAcerto){
        this.marcadorAcerto = marcadorAcerto;
    }
    

    public final void setupJogo() {

        // Prepara inimigos para niveis comuns
        if (getNivel() != 3 && getNivel() != 6 && getNivel() != 9 && getNivel() != 12) {
            for (int linha = 0; linha < 6; linha++) {
                for (int coluna = 0; coluna < 5; coluna++) {
                    setInimigo((20 + (linha * 100)), (20 + (coluna * 60)), getNivel(), 0, coluna, null, 40, 40); // Velocidade do inimigo aumenta a cada nível
                    getListaInimigo().add(getInimigo());
                }
            }
        }
        // Prepara inimigos para niveis de chefe
        if (getNivel() == 3 || getNivel() == 6 || getNivel() == 9 || getNivel() == 12) {
            setInimigo(20, 20, 3, 0, 100, null, 150, 150);
            getListaInimigo().add(getInimigo());
        }
        // Tutorial
        if (getNivel() == 1) {
            JOptionPane.showMessageDialog(null, "Bem vindo ao Space Wars!\n\nTURORIAL:\n\n- Use as setas da esquerda e da direita para se mover\n- Pressione espaco para atirar\n- Os inimigos ficam mais rapidos a cada nivel"
                    + "\n- Chefe a cada 3 niveis\n- Um inimigo bonsu vai aparecer aleatoriamente, acerte-o para pontos extras!\n- Pressione R para resetar o recorde\n");
        }
        getControle().resetTeclado();

        setNaveJogador(375, 600, null, getControle());

        // Define o contador de vidas da Nave
        for (int column = 0; column < getNumeroDeVidas(); column++) {
            setVidaUnica(48 + (column * 20), 10, Color.WHITE, null);
            getListaVida().add(getVidaUnica());
        }

        // Define 3 linhas e 3 colunas de escudos
        for (int row = 0;
                row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                setEscudo(100 + (column * 250), 550 - (row * 10), 70, 10, Color.RED);
                getListaEscudo().add(getEscudo());
            }
        }
    }
    
    @Override
    public void paint(Graphics g) {

        getFundo().paintIcon(null, g, 0, -150);

        // Faz uma string que mostra "+100" ao acertar um inimigo
        if (getTiro() != null) {
            if (getMarcadorAcerto()) {
                g.setColor(Color.WHITE);
                if (getNivel() != 3 && getNivel() != 6 && getNivel() != 9 && getNivel() != 12) {
                    setMarcadorY(getMarcadorY() - 1);
                    g.drawString("+ 100", getMarcadorX() + 20, getMarcadorY());
                } else {
                    setMarcadorY(getMarcadorY()+1);
                    g.drawString("- 1", getMarcadorX() + 75, getMarcadorY());
                }
            }
        }
        getNaveJogador().desenha(g);

        // Desenha 3 escudos igualmente espaçados 
        for (int index = 0; index < getListaEscudo().size(); index++) {
            getListaEscudo().get(index).desenha(g);
        }

        //Desenha 3 tipos diferentes de aliens
        try {
            for (int index = 0; index < getListaInimigo().size(); index++) {
                getListaInimigo().get(index).desenha(g);
            }

        } catch (IndexOutOfBoundsException e) {
        }

        // Desenha um tiro ao apertar espaço
        if (getControle().getTecla(32)) {
            if (getNovoTiroPodeAcertar()) {
                setTiro(getNaveJogador().getXPos() + 22, getNaveJogador().getYPos() - 20, 0, Color.RED);
                setNovoTiroPodeAcertar(false);
            }
        }
        if (getTiro() != null) {
            getTiro().desenha(g);
        }

        // Gera raios aleatorios dos inimigos
        if (getNivel() != 3 && getNivel() != 6 && getNivel() != 9 && getNivel() != 12) {
            if (getNovoRaioPodeAcertar()) {
                for (int index = 0; index < getListaInimigo().size(); index++) {
                    if (getR().nextInt(30) == index) {
                        setRaio1(getListaInimigo().get(index).getXPos(), getListaInimigo().get(index).getYPos(), 0, Color.YELLOW);
                        getListaRaio().add(getRaio1());
                    }
                    setNovoRaioPodeAcertar(false);
                }
            }
        }
        // Gera raios mais rapidamente para os chefes
        if (getNivel() == 3 || getNivel() == 6 || getNivel() == 9 || getNivel() == 12) {
            if (getNovoRaioPodeAcertar()) {
                for (int index = 0; index < getListaInimigo().size(); index++) {
                    if (getR().nextInt(5) == index) {
                        setRaio1(getListaInimigo().get(index).getXPos() + 75, getListaInimigo().get(index).getYPos() + 140, 0, Color.YELLOW);
                        setRaio2(getListaInimigo().get(index).getXPos(), getListaInimigo().get(index).getYPos() + 110, 0, Color.YELLOW);
                        setRaio3(getListaInimigo().get(index).getXPos() + 150, getListaInimigo().get(index).getYPos() + 110, 0, Color.YELLOW);
                        getListaRaio().add(getRaio1());
                        getListaRaio().add(getRaio2());
                        getListaRaio().add(getRaio3());
                    }
                    setNovoRaioPodeAcertar(false);
                }
            }
        }
        // Desenha os raios
        for (int index = 0; index < getListaRaio().size(); index++) {
            getListaRaio().get(index).desenha(g);
        }
        // Gera inimigos bonus aleatorios
        if (getNovoInimigoBonus()) {
            if (getR().nextInt(3000) == 1500) {
                setInimigoBonus(-50, 30, Color.RED, null);
                getListaInimigoBonus().add(getInimigoBonus());
                setNovoInimigoBonus(false);
            }
        }
        // Desenha inimigo bonus
        for (int index = 0; index < getListaInimigoBonus().size(); index++) {
            getListaInimigoBonus().get(index).desenhaBonus(g);
        }

        // Mostra os pontos da tela
        g.setColor(Color.WHITE);
        g.drawString("Pontos: " + getPontos(), 260, 20);

        // Mostra as vidas na tela
        g.setColor(Color.WHITE);
        g.drawString("Vidas:", 11, 20);
        for (int index = 0; index < getListaVida().size(); index++) {
            getListaVida().get(index).desenhaVidaNave(g);
        }
        // Mostra o nivel na tela
        g.setColor(Color.WHITE);
        g.drawString("Nivel " + getNivel(), 750, 20);

        // Mostra o recorde na tela
        g.setColor(Color.WHITE);
        g.drawString("Recorde: " + getRecorde(), 440, 20);

        // Desenha a barra de vida para o chefe
        if (getNivel() == 3 || getNivel() == 6 || getNivel() == 9 || getNivel() == 12) {
            g.setColor(Color.WHITE);
            g.drawString("Vida do Chefe: " + getVidaChefe(), 352, 600);
        }
    }

    
    public void atualizaEstadoDoJogo(int frameNumber) {

        // Permite a movimentação do jogador
        getNaveJogador().movimenta();

        // Atualiza recorde
        try {
            Scanner fileScan = new Scanner(f);
            while (fileScan.hasNextInt()) {
                String nextLine = fileScan.nextLine();
                Scanner lineScan = new Scanner(nextLine);
                setRecorde(lineScan.nextInt());
            }
        } catch (FileNotFoundException e) {
        }
        // Opção para resetar recorde
        if (getControle().getTecla(82)) {
            int answer = JOptionPane.showConfirmDialog(null, "Gostaria de resetar o recorde?", ":)", 0);
            getControle().resetTeclado();
            if (answer == 0) {
                try {
                    String scoreString = Integer.toString(0);
                    try (PrintWriter pw = new PrintWriter(new FileOutputStream(f, false))) {
                        pw.write(scoreString);
                    }
                } catch (FileNotFoundException e) {
                }
            }
        }
        // Atualiza o recorde quando a pontuação ultrapassa o recorde atual
        try {
            if (getPontos() > getRecorde()) {
                String scoreString = Integer.toString(getPontos());
                try (PrintWriter pw = new PrintWriter(new FileOutputStream(f, false))) {
                    pw.write(scoreString);
                }
            }
        } catch (FileNotFoundException e) {
        }

        // Faz os inimigos moverem
        if ((getListaInimigo().get(getListaInimigo().size() - 1).getXPos() + getListaInimigo().get(getListaInimigo().size() - 1).getXVel()) > 760 || (getListaInimigo().get(0).getXPos() + getListaInimigo().get(0).getXVel()) < 0) {
            for (int index = 0; index < getListaInimigo().size(); index++) {
                getListaInimigo().get(index).setXVel(getListaInimigo().get(index).getXVel() * -1);
                getListaInimigo().get(index).setYPos(getListaInimigo().get(index).getYPos() + 10);
            }
        } else {
            for (int index = 0; index < getListaInimigo().size(); index++) {
                getListaInimigo().get(index).movimenta();
            }
        }

        // Movimenta tiros
        if (getTiro() != null) {
            getTiro().setYPos(getTiro().getYPos() - 15);
            if (getTiro().getYPos() < 0) {
                setNovoTiroPodeAcertar(true);
            }

            // Procura colisões
            for (int index = 0; index < getListaInimigo().size(); index++) {
                if (getTiro().estaColidindo(getListaInimigo().get(index))) {
                    setTiro(0, 0, 0, null);
                    setNovoTiroPodeAcertar(true);
                    if (getNivel() != 3 && getNivel() != 6 && getNivel() != 9 && getNivel() != 12) {
                        setPontos(getPontos() + 100);
                        setMarcadorAcerto(true);
                        setMarcadorX(getListaInimigo().get(index).getXPos()); // Posciona o "+100"
                        setMarcadorY(getListaInimigo().get(index).getYPos());
                        getListaInimigo().remove(index);

                    }
                    //Pontuação nos níveis com chefe
                    if (getNivel() == 3 || getNivel() == 6 || getNivel() == 9 || getNivel() == 12) {
                        setMarcadorAcerto(true);
                        setMarcadorX(getListaInimigo().get(index).getXPos()); // Posiciona o "-1"
                        setMarcadorY(getListaInimigo().get(index).getYPos() + 165);
                        setVidaChefe(getVidaChefe() - 1);
                        if (getVidaChefe() == 0) {
                            getListaInimigo().remove(index);
                            setPontos(getPontos()+9000);
                        }
                    }
                }
            }

            // Procura colisoes entre escudos e tiros
            for (int index = 0; index < getListaEscudo().size(); index++) {
                if (getTiro().estaColidindo(getListaEscudo().get(index))) {
                    if (getListaEscudo().get(index).getCor() == Color.RED) {
                        getListaEscudo().get(index).setCor(Color.ORANGE);
                        setTiro(0, 0, 0, null);
                        setNovoTiroPodeAcertar(true);
                    } else if (getListaEscudo().get(index).getCor() == Color.ORANGE) {
                        getListaEscudo().get(index).setCor(Color.YELLOW);
                        setTiro(0, 0, 0, null);
                        setNovoTiroPodeAcertar(true);
                    } else if (getListaEscudo().get(index).getCor() == Color.YELLOW) {
                        getListaEscudo().get(index).setCor(Color.WHITE);
                        setTiro(0, 0, 0, null);
                        setNovoTiroPodeAcertar(true);
                    } else if (getListaEscudo().get(index).getCor() == Color.WHITE) {
                        getListaEscudo().remove(index);
                        setTiro(0, 0, 0, null);
                        setNovoTiroPodeAcertar(true);
                    }
                }
            }
        }
        // Movimenta inimigo bonus
        if (!getListaInimigoBonus().isEmpty()) {
            for (int index = 0; index < getListaInimigoBonus().size(); index++) {
                getListaInimigoBonus().get(index).setXPos(getListaInimigoBonus().get(index).getXPos() + (2));
                if (getListaInimigoBonus().get(index).getXPos() > getLargura()) {
                    getListaInimigoBonus().remove(index);
                    setNovoInimigoBonus(true);
                }
            }
            // Colisao entre inimigo bonus e tiros
            for (int index = 0; index < getListaInimigoBonus().size(); index++) {
                if (getTiro() != null) {
                    if (getListaInimigoBonus().get(index).estaColidindo(getTiro())) {
                        getListaInimigoBonus().remove(index);
                        setTiro(0, 0, 0, null);
                        setNovoTiroPodeAcertar(true);
                        setNovoInimigoBonus(true);
                        setPontos(getPontos()+5000); 
                    }
                }
            }
        }

        // Movimenta raios
        if (getNivel() != 3 && getNivel() != 6 && getNivel() != 9 && getNivel() != 12) {
            if (getRaio1() != null) {
                for (int index = 0; index < getListaRaio().size(); index++) {
                    getListaRaio().get(index).setYPos(getListaRaio().get(index).getYPos() + (4));
                    if (getListaRaio().get(index).getYPos() > getAltura()) {
                        getListaRaio().remove(index);
                    }
                }
            }
        }
        // Movimenta raios nas fases com chefe
        if (getNivel() == 3 || getNivel() == 6 || getNivel() == 9 || getNivel() == 12) {
            if (getRaio1() != null) {
                for (int index = 0; index < getListaRaio().size(); index++) {
                    getListaRaio().get(index).setYPos(getListaRaio().get(index).getYPos() + (2 * getNivel())); 
                    if (getListaRaio().get(index).getYPos() > getAltura()) {
                        getListaRaio().remove(index);
                    }
                }
            }
        }

        // Colisao entre raios e escudos
        try {
            for (int j = 0; j < getListaEscudo().size(); j++) {
                for (int index = 0; index < getListaRaio().size(); index++) {
                    if (getListaRaio().get(index).estaColidindo(getListaEscudo().get(j))) {
                        if (getListaEscudo().get(j).getCor() == Color.RED) {
                            getListaEscudo().get(j).setCor(Color.ORANGE);
                            getListaRaio().remove(index);
                        } else if (getListaEscudo().get(j).getCor() == Color.ORANGE) {
                            getListaEscudo().get(j).setCor(Color.YELLOW);
                            getListaRaio().remove(index);
                        } else if (getListaEscudo().get(j).getCor() == Color.YELLOW) {
                            getListaEscudo().get(j).setCor(Color.WHITE);
                            getListaRaio().remove(index);
                        } else if (getListaEscudo().get(j).getCor() == Color.WHITE) {
                            getListaEscudo().remove(j);
                            getListaRaio().remove(index);
                        }
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
        }

        // Colisao entre jogador e raio
        for (int index = 0; index < getListaRaio().size(); index++) {
            if (getListaRaio().get(index).estaColidindo(getNaveJogador())) {
                getListaRaio().remove(index);
                getListaVida().remove(getListaVida().size() - 1); // Removes life if hit by bullet
            }
        }

        if (getListaRaio().isEmpty()) {
            setNovoRaioPodeAcertar(true);
        }

        //Destroi escudos com colisao com alien
        for (int input = 0; input < getListaInimigo().size(); input++) {
            for (int j = 0; j < getListaEscudo().size(); j++) {
                if (getListaInimigo().get(input).estaColidindo(getListaEscudo().get(j))) {
                    getListaEscudo().remove(j);
                }
            }
            // Se aliens ultrapassarem essa YPos(), nivel reseta
            if (getListaInimigo().get(input).getYPos() + 50 >= 750) {
                getListaInimigo().clear();
                getListaEscudo().clear();
                getListaVida().clear();
                getListaRaio().clear();
                setVidaChefe(30);
                setNumeroDeVidas(getNumeroDeVidas()-1);
                setupJogo();
            }
        }

        //Atualiza o contador de vidas
        if (getNaveJogador().getEstaColidindo()) {
            int index = getListaVida().size() - 1;
            getListaVida().remove(index);
        } 
        // Termina o jogo se as vidas acabarem
        else if (getListaVida().isEmpty()) {
            int answer = JOptionPane.showConfirmDialog(null, "Gostaria de jogar de novo?", "Você perdeu com " + pontos + " pontos", 0);
            // Caso queira jogar novamente, reseta os elementos do jogo
            if (answer == 0) {
                getListaVida().clear();
                getListaInimigo().clear();
                getListaEscudo().clear();
                getListaRaio().clear();
                getListaInimigoBonus().clear();
                setPontos(0);
                setNivel(1);
                setVidaChefe(30);
                setNumeroDeVidas(3);
                setNovoTiroPodeAcertar(true);
                setNovoRaioPodeAcertar(true);
                setNovoInimigoBonus(true);
                setupJogo();
            }
            if (answer == 1) {
                System.exit(0);
            }
        }

        // Vai para o proximo nivel
        if (getListaInimigo().isEmpty()) {
            getListaRaio().clear();
            getListaEscudo().clear();
            getListaInimigoBonus().clear();
            getListaVida().clear();
            setNivel(getNivel()+1);
            setVidaChefe(30);
            setupJogo();
        }
    }
   
    public JanelaDeJogo() {
        // Define o tamanho da janela
        this.setSize(getLargura(), getAltura());
        this.setPreferredSize(new Dimension(getLargura(), getAltura()));
        this.setBackground(Color.BLACK);

        // Registra o Teclado como KeyListener
        setControle();
        this.addKeyListener(getControle());

        this.setupJogo();
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    public void start() {
        // Define um temporizador para repetir a cada 20 ms
        setTemporizador(getFPS());
        Timer gameTimerHitMarker = new Timer(1000, (ActionEvent e) -> {
            // Atualiza o estado do jogo
            setMarcadorAcerto(false);
        } // Acompanha o numero de frames produzidos
        );

        getTemporizador().setRepeats(true);
        getTemporizador().start();
        gameTimerHitMarker.setRepeats(true);
        gameTimerHitMarker.start();
    }

}
