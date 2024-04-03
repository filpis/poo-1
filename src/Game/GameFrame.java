
package Game;

import javax.swing.*;
import java.awt.*;


public class GameFrame extends JFrame
{
    private GamePanel game;
    Image icon = new ImageIcon("images/logo.png").getImage();

    public GameFrame()
    {



        super("Space Wars");
        setIconImage(icon);
        
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        

        game = new GamePanel();
        game.setDoubleBuffered(true);

        this.getContentPane().add(game);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        game.start();
    }
    

}
