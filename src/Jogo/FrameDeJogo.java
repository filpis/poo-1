package Jogo;

import javax.swing.JFrame;

public class FrameDeJogo extends JFrame{
    private JanelaDeJogo jogo;
    
    public FrameDeJogo(){
        super("Space Wars");
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setJogo();
        getJogo().setDoubleBuffered(true);
        this.getContentPane().add(getJogo());
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        getJogo().start();
    }
    
    public final void setJogo(){
        this.jogo = new JanelaDeJogo();
    }
    
    public final JanelaDeJogo getJogo(){
        return jogo;
    }
    
    public static void main(String[] args){
        java.awt.EventQueue.invokeLater(() -> {
            new FrameDeJogo().setVisible(true);
        });
    }
}
