package base.enemy;

import base.*;
import base.physics.BoxCollider;
import base.physics.Physics;
import base.player.Player;
import base.renderer.SingleImageRenderer;
import tklibs.SpriteUtils;

public class EnemyBullet extends GameObject implements Physics {
    BoxCollider collider;
    Vector2D velocity;
    int damage;

    public EnemyBullet() {
        this.renderer = new SingleImageRenderer(SpriteUtils.loadImage("assets/images/enemies/bullets/blue.png"));
        this.position = new Vector2D(0,0);
        this.collider = new BoxCollider(16, 16);
        this.velocity = new Vector2D(0, 1);
        this.damage = 4;
    }

    public void run() {
        this.position.addThis(this.velocity.x, this.velocity.y);
        Player player = GameObject.intersect(Player.class, this);
        if (player != null) {
            this.destroy();
            player.takeDamge(4);
        }
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.collider;
    }
}
