
package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;



public class GamePanel extends JPanel {

    // Required components. Do not remove!
    private Timer gameTimer;
    private KeyboardController controller;
    // Controls size of game window and framerate
    private final int gameWidth = 800;
    private final int gameHeight = 800;
    private final int framesPerSecond = 120;

    // Added Counters
    Random r = new Random();
    private int score = 0;
    private int level = 1;
    private int numberOfLives = 3;
    private int highScore;
    private int markerX, markerY;
    private static int bossHealth = 30;
    File f = new File("scores.txt");

    // Added Objects
    private Ship playerShip;
    private Ship singleLife;
    private Ship bonusEnemy;
    private Enemy enemy;
    private Shield shield;
    private Bullet bullet;
    private Beam beam, beam2, beam3;

    // Added Booleans
    private boolean newBulletCanFire = true;
    private boolean newBeamCanFire = true;
    private boolean newBonusEnemy = true;
    private boolean hitMarker = false;

    // Added Array Lists
    private ArrayList<Ship> lifeList = new ArrayList();
    private ArrayList<Ship> bonusEnemyList = new ArrayList();
    private ArrayList<Enemy> enemyList = new ArrayList();
    private ArrayList<Shield> shieldList = new ArrayList();
    private ArrayList<Beam> beamList = new ArrayList();
    private ImageIcon background = new ImageIcon("images/backgroundSkin.jpg");




    public static int getBossHealth() {
        return bossHealth;
    }

    public final void setupGame() {

        if (level != 3 && level != 6 && level != 9 && level != 12) {
            for (int row = 0; row < 6; row++) {
                for (int column = 0; column < 5; column++) {
                    enemy = new Enemy((20 + (row * 100)), (20 + (column * 60)), level, 0, column, null, 40, 40); // Enemy speed will increase each level
                    enemyList.add(enemy);
                }
            }
        }
        if (level == 3 || level == 6 || level == 9 || level == 12) {
            enemy = new Enemy(20, 20, 3, 0, 100, null, 150, 150);
            enemyList.add(enemy);
        }
        if (level == 1) {
            JOptionPane.showMessageDialog(null, "Seja bem-vindo ao Space Intruders!\n\nInstruções do jogo:\n\n- Use as setas pra esquerda e direita para mover a nave\n- Precione espaço para atirar\n- A cada nível, os inimigos ficam mais rápidos"
                    + "\n- Um inimigo bônus aparecerá aleatoriamente\n- Atire para ganhar pontos extras!\n- Pressione R para redefinir a pontuação mais alta!\n- TENHA UMA BOA JOGATINA!");
        }
        controller.resetController();

        playerShip = new Ship(375, 730, null, controller);

        for (int column = 0; column < numberOfLives; column++) {
            singleLife = new Ship(48 + (column * 20), 10, Color.WHITE, null);
            lifeList.add(singleLife);
        }

        for (int row = 0;
                row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                shield = new Shield(100 + (column * 250), 650 - (row * 10), 70, 10, Color.RED);
                shieldList.add(shield);
            }
        }
    }


    @Override
    public void paint(Graphics g) {

        background.paintIcon(null, g, 0, -150);

        if (bullet != null) {
            if (hitMarker) {
                g.setColor(Color.WHITE);
                if (level != 3 && level != 6 && level != 9 && level != 12) {
                    g.drawString("+ 100", markerX + 20, markerY -= 1);
                } else {
                    g.drawString("- 1", markerX + 75, markerY += 1);
                }
            }
        }
        // Draws the player's ship
        playerShip.draw(g);

        // Draws 3 evenly-spaced shields
        for (int index = 0; index < shieldList.size(); index++) {
            shieldList.get(index).draw(g);
        }

        //Draws 3 different kinds of aliens
        try {
            for (int index = 0; index < enemyList.size(); index++) {
                enemyList.get(index).draw(g);
            }

        } catch (IndexOutOfBoundsException e) {
        }

        // Draw a bullet on space bar press
        if (controller.getKeyStatus(32)) {
            if (newBulletCanFire) {
                bullet = new Bullet(playerShip.getXPosition() + 22, playerShip.getYPosition() - 20, 0, Color.RED);
//                AudioPlayer.player.start(bulletSoundAudio); // Plays bullet sound
                newBulletCanFire = false;
            }
        }
        // Only attempts to draw bullet after key press
        if (bullet != null) {
            bullet.draw(g);
        }

        // Generates random beams shot from enemies
        if (level != 3 && level != 6 && level != 9 && level != 12) {
            if (newBeamCanFire) {
                for (int index = 0; index < enemyList.size(); index++) {
                    if (r.nextInt(30) == index) {
                        beam = new Beam(enemyList.get(index).getXPosition(), enemyList.get(index).getYPosition(),  Color.YELLOW);
                        beamList.add(beam);
//                        AudioPlayer.player.start(beamSoundAudio); // Plays beam sound for normal enemies
                    }
                    newBeamCanFire = false;
                }
            }
        }
        // Generates beams at a faster rate for boss
        if (level == 3 || level == 6 || level == 9 || level == 12) {
            if (newBeamCanFire) {
                for (int index = 0; index < enemyList.size(); index++) {
                    if (r.nextInt(5) == index) {
                        beam = new Beam(enemyList.get(index).getXPosition() + 75, enemyList.get(index).getYPosition() + 140, Color.YELLOW);
                        beam2 = new Beam(enemyList.get(index).getXPosition(), enemyList.get(index).getYPosition() + 110, Color.YELLOW);
                        beam3 = new Beam(enemyList.get(index).getXPosition() + 150, enemyList.get(index).getYPosition() + 110,  Color.YELLOW);
                        beamList.add(beam);
                        beamList.add(beam2);
                        beamList.add(beam3);
//                        AudioPlayer.player.start(beamSoundAudio); // Plays beam sound for boss
                    }
                    newBeamCanFire = false;
                }
            }
        }
        // Draws the generated beams
        for (int index = 0; index < beamList.size(); index++) {
            beamList.get(index).draw(g);
        }
        // Generates random bonus enemy
        if (newBonusEnemy) {
            if (r.nextInt(3000) == 1500) {
                bonusEnemy = new Ship(-50, 30, Color.RED, null);
                bonusEnemyList.add(bonusEnemy);
                newBonusEnemy = false;
            }
        }
        // Draws bonus enemy
        for (int index = 0; index < bonusEnemyList.size(); index++) {
            bonusEnemyList.get(index).bonusDraw(g);
        }

        // Sets the score display
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 260, 20);

        // Sets the life counter display
        g.setColor(Color.WHITE);
        g.drawString("Lives:", 11, 20);
        for (int index = 0; index < lifeList.size(); index++) {
            lifeList.get(index).lifeDraw(g);
        }
        // Sets level display
        g.setColor(Color.WHITE);
        g.drawString("Level " + level, 750, 20);

        // Sets Highscore display
        g.setColor(Color.WHITE);
        g.drawString("Highscore: " + highScore, 440, 20);

        // Draws a health display for boss level
        if (level == 3 || level == 6 || level == 9 || level == 12) {
            g.setColor(Color.WHITE);
            g.drawString("Boss Health: " + bossHealth, 352, 600);
        }
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// UPDATE GAME STATE

    public void updateGameState(int frameNumber) {

        // Allows player to move left and right
        playerShip.move();

        // Updates highscore
        try {
            Scanner fileScan = new Scanner(f);
            while (fileScan.hasNextInt()) {
                String nextLine = fileScan.nextLine();
                Scanner lineScan = new Scanner(nextLine);
                highScore = lineScan.nextInt();
            }
        } catch (FileNotFoundException e) {
        }
        // Adds option to reset highScore
        if (controller.getKeyStatus(82)) {
            int answer = JOptionPane.showConfirmDialog(null, "Definir como pontuação mais alta?", ":)", 0);
            controller.resetController();
            if (answer == 0) {
                try {
                    String scoreString = Integer.toString(0);
                    PrintWriter pw = new PrintWriter(new FileOutputStream(f, false));
                    pw.write(scoreString);
                    pw.close();
                } catch (FileNotFoundException e) {
                }
            }
        }
        // Updates the high score text file if your score exceeds the previous high score
        try {
            if (score > highScore) {
                String scoreString = Integer.toString(score);
                PrintWriter pw = new PrintWriter(new FileOutputStream(f, false));
                pw.write(scoreString);
                pw.close();
            }
        } catch (FileNotFoundException e) {
        }

        // Makes enemies move and change direction at borders
        if ((enemyList.get(enemyList.size() - 1).getXPosition() + enemyList.get(enemyList.size() - 1).getXVelocity()) > 760 || (enemyList.get(0).getXPosition() + enemyList.get(0).getXVelocity()) < 0) {
            for (int index = 0; index < enemyList.size(); index++) {
                enemyList.get(index).setXVelocity(enemyList.get(index).getXVelocity() * -1);
                enemyList.get(index).setYPosition(enemyList.get(index).getYPosition() + 10);
            }
        } else {
            for (int index = 0; index < enemyList.size(); index++) {
                enemyList.get(index).move();
            }
        }

        // Move bullet
        if (bullet != null) {
            bullet.setYPosition(bullet.getYPosition() - 15);
            if (bullet.getYPosition() < 0) {
                newBulletCanFire = true;
            }

            // Checks for collisions with normal enemies
            for (int index = 0; index < enemyList.size(); index++) {
                if (bullet.isColliding(enemyList.get(index))) {
//                    AudioPlayer.player.start(hitSoundAudio); // Plays hitmarker sound if you hit an enemy
                    bullet = new Bullet(0, 0, 0, null);
                    newBulletCanFire = true;
                    // Updates score for normal levels
                    if (level != 3 && level != 6 && level != 9 && level != 12) {
                        score += 100;
                        hitMarker = true;
                        markerX = enemyList.get(index).getXPosition(); // Gets positions that the "+ 100" spawns off of
                        markerY = enemyList.get(index).getYPosition();
                        enemyList.remove(index);

                    }
                    // Updates score for boss levels
                    if (level == 3 || level == 6 || level == 9 || level == 12) {
                        hitMarker = true;
                        markerX = enemyList.get(index).getXPosition(); // Gets positions that the "- 1" spawns off of
                        markerY = enemyList.get(index).getYPosition() + 165;
                        bossHealth -= 1;
                        if (bossHealth == 0) {
                            enemyList.remove(index);
                            score += 9000;// Bonus score for defeating boss
                        }
                    }
                }
            }

            // Checks for collisions with shield and bullets
            for (int index = 0; index < shieldList.size(); index++) {
                if (bullet.isColliding(shieldList.get(index))) {
                    // Each if statement changes color of the shield, indicating "strength"
                    // STRONG
                    if (shieldList.get(index).getColor() == Color.RED) {
                        shieldList.get(index).setColor(Color.ORANGE);
//                        AudioPlayer.player.start(shieldSoundAudio); // Plays sound if shield takes damage
                        bullet = new Bullet(0, 0, 0, null);
                        newBulletCanFire = true;
                    // GOOD
                    } else if (shieldList.get(index).getColor() == Color.ORANGE) {
                        shieldList.get(index).setColor(Color.YELLOW);
//                        AudioPlayer.player.start(shieldSoundAudio);
                        bullet = new Bullet(0, 0, 0, null);
                        newBulletCanFire = true;
                    // OKAY
                    } else if (shieldList.get(index).getColor() == Color.YELLOW) {
                        shieldList.get(index).setColor(Color.WHITE);
//                        AudioPlayer.player.start(shieldSoundAudio);
                        bullet = new Bullet(0, 0, 0, null);
                        newBulletCanFire = true;
                    // WEAK, BREAKS ON HIT
                    } else if (shieldList.get(index).getColor() == Color.WHITE) {
                        shieldList.remove(index);
//                        AudioPlayer.player.start(shieldSoundAudio);
                        bullet = new Bullet(0, 0, 0, null);
                        newBulletCanFire = true;
                    }
                }
            }
        }
        // Moves bonus enemy
        if (!bonusEnemyList.isEmpty()) {
            for (int index = 0; index < bonusEnemyList.size(); index++) {
                bonusEnemyList.get(index).setXPosition(bonusEnemyList.get(index).getXPosition() + (2));
                if (bonusEnemyList.get(index).getXPosition() > 800) {
                    bonusEnemyList.remove(index);
                    newBonusEnemy = true;
                }
            }
            // bonus enemy and bullet collsion
            for (int index = 0; index < bonusEnemyList.size(); index++) {
                if (bullet != null) {
                    if (bonusEnemyList.get(index).isColliding(bullet)) {
                        bonusEnemyList.remove(index);
                        bullet = new Bullet(0, 0, 0, null);
                        newBulletCanFire = true;
                        newBonusEnemy = true;
//                        AudioPlayer.player.start(bonusSoundAudio); // Plays sound if player hits a bonus enemy
                        score += 5000; // add bonus to score on hit
                    }
                }
            }
        }

        if (level != 3 && level != 6 && level != 9 && level != 12) {
            if (beam != null) {
                for (int index = 0; index < beamList.size(); index++) {
                    beamList.get(index).setYPosition(beamList.get(index).getYPosition() + (4));
                    if (beamList.get(index).getYPosition() > 800) {
                        beamList.remove(index);
                    }
                }
            }
        }
        // Moves beams at a faster speed for boss
        if (level == 3 || level == 6 || level == 9 || level == 12) {
            if (beam != null) {
                for (int index = 0; index < beamList.size(); index++) {
                    beamList.get(index).setYPosition(beamList.get(index).getYPosition() + (2 * level)); //Boss beam speed will increase each level
                    if (beamList.get(index).getYPosition() > 800) {
                        beamList.remove(index);
                    }
                }
            }
        }

        // Checks for beam and shield collisions
        try {
            for (int j = 0; j < shieldList.size(); j++) {
                for (int index = 0; index < beamList.size(); index++) {
                    if (beamList.get(index).isColliding(shieldList.get(j))) {
                        // STRONG
                        if (shieldList.get(j).getColor() == Color.RED) {
                            shieldList.get(j).setColor(Color.ORANGE);
//                            AudioPlayer.player.start(shieldSoundAudio); // Plays sound if shield takes damage
                            beamList.remove(index);
                        // GOOD
                        } else if (shieldList.get(j).getColor() == Color.ORANGE) {
                            shieldList.get(j).setColor(Color.YELLOW);
//                            AudioPlayer.player.start(shieldSoundAudio);
                            beamList.remove(index);
                        // OKAY
                        } else if (shieldList.get(j).getColor() == Color.YELLOW) {
                            shieldList.get(j).setColor(Color.WHITE);
//                            AudioPlayer.player.start(shieldSoundAudio);
                            beamList.remove(index);
                        // WEAK, BREAKS ON HIT
                        } else if (shieldList.get(j).getColor() == Color.WHITE) {
                            shieldList.remove(j);
//                            AudioPlayer.player.start(shieldSoundAudio);
                            beamList.remove(index);
                        }
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
        }

        for (int index = 0; index < beamList.size(); index++) {
            if (beamList.get(index).isColliding(playerShip)) {
                beamList.remove(index);
                lifeList.remove(lifeList.size() - 1); // Removes life if hit by bullet
            }
        }

        if (beamList.isEmpty()) {
            newBeamCanFire = true;
        }

        //Destroys shields if aliens collide with them
        for (int input = 0; input < enemyList.size(); input++) {
            for (int j = 0; j < shieldList.size(); j++) {
                if (enemyList.get(input).isColliding(shieldList.get(j))) {
                    shieldList.remove(j);
                }
            }
            // If aliens exceed this X Position, you reset the level and lose a life
            if (enemyList.get(input).getYPosition() + 50 >= 750) {
                enemyList.clear();
                shieldList.clear();
                lifeList.clear();
                beamList.clear();
                bossHealth = 30;
                numberOfLives -= 1;
//                AudioPlayer.player.start(deathSoundAudio); // Plays death sound when enemies reach bottom
                setupGame();
            }
        }

        //Updates the life counter display
        if (playerShip.isColliding) {
            int index = lifeList.size() - 1;
            lifeList.remove(index);
        }
        else if (lifeList.isEmpty()) {
            String[] options = {"Continuar", "Sair"};

            int answer = JOptionPane.showConfirmDialog(null, "Jogar novamente?", score + " pontos", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
            if (answer == 0) {
                lifeList.clear();
                enemyList.clear();
                shieldList.clear();
                beamList.clear();
                bonusEnemyList.clear();
                score = 0;
                level = 1;
                bossHealth = 30;
                numberOfLives = 3;
                newBulletCanFire = true;
                newBeamCanFire = true;
                newBonusEnemy = true;
                setupGame();
            }
            if (answer == 1) {
                System.exit(0);
            }
        }

        if (enemyList.isEmpty()) {
            beamList.clear();
            shieldList.clear();
            bonusEnemyList.clear();
            lifeList.clear();
            level += 1;
            bossHealth = 30;
            setupGame();
        }


    }

    public GamePanel() {
        this.setSize(gameWidth, gameHeight);
        this.setPreferredSize(new Dimension(gameWidth, gameHeight));
        this.setBackground(Color.BLACK);

        controller = new KeyboardController();
        this.addKeyListener(controller);

        this.setupGame();
        this.setFocusable(true);
        this.requestFocusInWindow();
    }


    public void start() {
        gameTimer = new Timer(1000 / framesPerSecond, new ActionListener() {


            private int frameNumber = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                updateGameState(frameNumber++);
                repaint();
            }
        });
        Timer gameTimerHitMarker = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                hitMarker = false;
            }
        });

        gameTimer.setRepeats(true);
        gameTimer.start();
        gameTimerHitMarker.setRepeats(true);
        gameTimerHitMarker.start();
    }

}
