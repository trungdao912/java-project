package base.player;

import base.physics.BoxCollider;
import base.GameObject;
import base.physics.Physics;
import base.Vector2D;
import base.enemy.Enemy;
import base.renderer.AnimationRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PlayerBullet extends GameObject implements Physics {
    Vector2D velocity;
    int damage;
    BoxCollider collider;

    public PlayerBullet() {
        super();
//        ArrayList<BufferedImage> photos = new ArrayList<>();
//        photos.add(SpriteUtils.loadImage("assets/images/player-bullets/a/0.png"));
//        photos.add(SpriteUtils.loadImage("assets/images/player-bullets/a/1.png"));
//        photos.add(SpriteUtils.loadImage("assets/images/player-bullets/a/2.png"));
//        photos.add(SpriteUtils.loadImage("assets/images/player-bullets/a/3.png"));
        this.position = new Vector2D(0, 0);
        this.velocity = new Vector2D(0,0);

    }


    @Override
    public void run() {
        Enemy enemy = GameObject.intersect(Enemy.class, this);
        System.out.println(enemy);
        if(enemy!= null) {
            enemy.takeDamage(damage);
            this.hitEnemy();
            return;
        }
        if (this.position.y < 0) {
            this.destroy();
            return;
        }
        this.position.addThis(velocity.x, velocity.y);
    }

    public void hitEnemy() {

    }

    @Override
    public BoxCollider getBoxCollider() {

        return this.collider;
    }
}
