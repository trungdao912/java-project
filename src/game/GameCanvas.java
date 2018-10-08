package game;

import base.*;
import base.enemy.Enemy;
import base.player.Player;
import base.player.PlayerBullet;

import javax.swing.*;
import java.awt.*;

public class GameCanvas extends JPanel {
    Background background;
    Player player;
    PlayerBullet playerBullet;
//    public static ArrayList<Enemy> enemies;
//    public static ArrayList<PlayerBullet> playerPlayerBullets;

    public GameCanvas() {
        this.background = GameObject.create(Background.class);
        this.player = GameObject.create(Player.class);
//        this.enemies = new ArrayList<>();

        Enemy enemy = GameObject.create(Enemy.class);

//        for (int i = 0; i < 4; i ++) {
//            Enemy newEnemy = new Enemy();
//            newEnemy.position = new Vector2D(i*100,30);
//            enemies.add(newEnemy);
//        }
//        playerPlayerBullets = new ArrayList<>();
}

    public void run() {
//        this.background.run();
//        this.player.run();
////        for (Enemy enemy: enemies) {
////            enemy.run();
////        }
//
//        for (PlayerBullet playerBullet: playerPlayerBullets) {
//            playerBullet.run();
//        }
        GameObject.runAll();
    }
//

    public void render(Graphics g) {
//        this.background.render(g);
//        this.player.render(g);
////        PlayerBullet.render(g);
//        for (PlayerBullet playerBullet: playerPlayerBullets) {
//            playerBullet.render(g);
//        }
//        for (Enemy enemy: enemies) {
//            enemy.render(g);
//        }
        GameObject.renderAll(g);
    }

    @Override
    protected void paintComponent(Graphics g) {
        this.render(g);
    }
}
