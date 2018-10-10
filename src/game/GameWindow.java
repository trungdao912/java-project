package game;

import base.event.KeyEventPress;
import base.Setting;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


// Extend jframe Class game.GameWindow kế thừa từ class Jframe
public class GameWindow extends JFrame{
    GameCanvas canvas = new GameCanvas();
    boolean isUpPress;
    boolean isDownPress;
    boolean isLeftPress;
    boolean isRightPress;

    public GameWindow() {

        this.setSize(Setting.SCREEN_WIDTH,  Setting.SCREEN_HEIGHT);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setupEventListener();
        // init game
        this.add(canvas);
        this.setVisible(true);

    }

    private void setupEventListener() {
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    KeyEventPress.isUpPress = true;
                } else if (e.getKeyCode() == KeyEvent.VK_A) {
                    KeyEventPress.isLeftPress = true;
                } else if (e.getKeyCode() == KeyEvent.VK_D) {
                    KeyEventPress.isRightPress = true;
                } else if (e.getKeyCode() == KeyEvent.VK_S) {
                    KeyEventPress.isDownPress = true;
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    KeyEventPress.isSpacePress = true;
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    KeyEventPress.isUpPress = false;
                } else if (e.getKeyCode() == KeyEvent.VK_A) {
                    KeyEventPress.isLeftPress = false;
                } else if (e.getKeyCode() == KeyEvent.VK_D) {
                    KeyEventPress.isRightPress = false;
                } else if (e.getKeyCode() == KeyEvent.VK_S) {
                    KeyEventPress.isDownPress = false;
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    KeyEventPress.isSpacePress = false;
                }
            }
        });
    }

    public void gameLoop() {
        // 60 frames per second
        long lastTime = 0;
        long delay = 1000 / 60;
        while (true) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastTime > delay) {
                // logic
                canvas.run();
                canvas.render(); // render all to backBuffer
                this.repaint(); // render backBuffer to game
//                canvas.move();
                // render
//                this.repaint();
//                GameObject.addNewAll();
                lastTime = currentTime;
            }
        }
    }


}
