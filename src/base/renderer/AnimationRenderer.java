package base.renderer;

import base.GameObject;
import base.counter.FrameCounter;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class AnimationRenderer extends Renderer {
    ArrayList<BufferedImage> images;
    int currentImage = 0;
    FrameCounter frameCounter;
    public AnimationRenderer(ArrayList<BufferedImage> images) {
        this.frameCounter = new FrameCounter(5);
        this.images = images;
    }

    public AnimationRenderer(String ...urls) {
        this.frameCounter = new FrameCounter(5);
        ArrayList<BufferedImage> images = SpriteUtils.loadImages(urls);
        this.images = images;
    }

    public AnimationRenderer(ArrayList<BufferedImage> images, int frameCount) {
        this.images = images;
        this.frameCounter = new FrameCounter(frameCount);
    }

    @Override
    public void render(Graphics g, GameObject master) {
        if (images.size() > 0) {
            BufferedImage image = images.get(currentImage);
            float x = master.position.x - image.getWidth() * 1/2;
            float y = master.position.y - image.getHeight() * 1/2;
            g.drawImage(image, (int)x, (int)y, null);
        }
            if (this.frameCounter.run()) {
                currentImage++;
                if (currentImage >= images.size() - 1) {
                    currentImage = 0;
                }
                this.frameCounter.reset();
            }
    }
}
