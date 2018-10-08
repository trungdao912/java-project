package base.enemy;

import base.*;
import base.action.Action;
import base.action.ActionRepeat;
import base.action.ActionSequences;
import base.action.ActionWait;
import base.physics.BoxCollider;
import base.physics.Physics;
import base.renderer.AnimationRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Enemy extends GameObject implements Physics {
    Action action;
    BoxCollider collider;

    public Enemy() {
        super();
//        ArrayList<BufferedImage> images = new ArrayList<>();
//        images.add(SpriteUtils.loadImage("assets/images/enemies/level0/pink/0.png"));
//        images.add(SpriteUtils.loadImage("assets/images/enemies/level0/pink/1.png"));
//        images.add(SpriteUtils.loadImage("assets/images/enemies/level0/pink/2.png"));
//        images.add(SpriteUtils.loadImage("assets/images/enemies/level0/pink/3.png"));
        ArrayList<BufferedImage> images = SpriteUtils.loadImages("assets/images/enemies/level0/pink/0.png",
                "assets/images/enemies/level0/pink/1.png",
                "assets/images/enemies/level0/pink/2.png",
                "assets/images/enemies/level0/pink/3.png");
        this.renderer = new AnimationRenderer(images);
        this.position = new Vector2D(200, 100);
        this.collider = new BoxCollider(28, 28);
        this.defineAction();
    }

    void defineAction() {
        ActionWait actionWait = new ActionWait(20);
        Action actionFire = new Action() {
            @Override
            public void run(GameObject master) {
                fire();
                this.isDone = true;
            }

            @Override
            public void reset() {
                this.isDone = false;
            }
        };

        ActionSequences actionSequences = new ActionSequences(actionWait, actionFire);
        ActionRepeat actionRepeat = new ActionRepeat(actionSequences);
        this.action = actionRepeat;
    }

    @Override
    public void run() {
        this.action.run(this);
    }

    public void fire() {
            EnemyBullet bullet = GameObject.recycle(EnemyBullet.class);
            bullet.position.set(this.position.x, this.position.y + 5);
    }

    @Override
    public BoxCollider getBoxCollider() {

        return this.collider;
    }
}
