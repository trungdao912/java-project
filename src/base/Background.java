package base;

import base.renderer.SingleImageRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;

public class Background extends GameObject {
    public Background() {
        super();
        BufferedImage image = SpriteUtils.loadImage("assets/images/background/0.png");
        this.renderer = new SingleImageRenderer("assets/images/background/0.png");
        this.position = new Vector2D(0,  -(image.getHeight() - Setting.SCREEN_HEIGHT));
        this.anchor.set(0, 0);
    }

    @Override
    public void run() {
        if (this.position.y < -200) {
            this.position.y = this.position.y + 10;
        }
    }
}
