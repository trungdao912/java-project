package base.player;

import base.GameObject;
import base.renderer.AnimationRenderer;
import base.renderer.Renderer;

import java.awt.*;

public class PlayerAnimator extends Renderer {

    Renderer straightAnimation;
    Renderer leftAnimation;
    Renderer rightAnimation;

    public PlayerAnimator() {
        this.straightAnimation = new AnimationRenderer("assets/images/players/straight/0.png",
                "assets/images/players/straight/1.png",
                "assets/images/players/straight/2.png",
                "assets/images/players/straight/3.png",
                "assets/images/players/straight/4.png",
                "assets/images/players/straight/5.png",
                "assets/images/players/straight/6.png");
        this.leftAnimation = new AnimationRenderer("assets/images/players/left/0.png",
                "assets/images/players/left/1.png",
                "assets/images/players/left/2.png",
                "assets/images/players/left/3.png",
                "assets/images/players/left/4.png",
                "assets/images/players/left/5.png");
        this.rightAnimation = new AnimationRenderer("assets/images/players/right/0.png",
                "assets/images/players/right/1.png",
                "assets/images/players/right/2.png",
                "assets/images/players/right/3.png",
                "assets/images/players/right/4.png",
                "assets/images/players/right/5.png");
    }

    @Override
    public void render(Graphics g, GameObject master) {
        Player player = (Player)master;
        if(player.velocity.x > 0) {
            this.rightAnimation.render(g, master);
        } else if (player.velocity.x < 0) {
            this.leftAnimation.render(g, master);
        } else {
            this.straightAnimation.render(g, master);
        }
    }
}
