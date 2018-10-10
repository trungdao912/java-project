package base.player;

import base.physics.BoxCollider;
import base.renderer.SingleImageRenderer;

public class PlayerBulletType1 extends PlayerBullet {
    public PlayerBulletType1() {
        super();
        this.renderer = new SingleImageRenderer("assets/images/player-bullets/a/0.png");
        this.collider = new BoxCollider(24, 24);
        this.damage = 1;
    }

    @Override
    public void hitEnemy() {
        this.destroy();
    }
}
