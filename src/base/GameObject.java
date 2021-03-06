package base;

import base.enemy.Enemy;
import base.physics.Physics;
import base.renderer.Renderer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameObject {
    public Vector2D position;
    public Renderer renderer;
    public boolean isActive;
    public Vector2D anchor;
    public Vector2D velocity;

    public static ArrayList<GameObject> gameObjects = new ArrayList<>();
    //gameObjects chứa tất cả các game object mới được tạo
    public static ArrayList<GameObject> newGameObjects = new ArrayList<>();
    //newGameObjects chứa tất cả các game object mới sau khi chạy game được thêm
    public static BufferedImage backBuffer = new BufferedImage(Setting.SCREEN_WIDTH, Setting.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    public static Graphics backBufferGraphics = backBuffer.createGraphics();
    public static Enemy createEnemy() {
        Enemy newEnemy = new Enemy();
        gameObjects.add(newEnemy);
        return newEnemy;
    }

    public GameObject() {
        this.position = new Vector2D();
        this.isActive = true;
        this.anchor = new Vector2D(0.5f,0.5f);
    }

    public static <E extends  GameObject> E recycle(Class<E> childClass) {
        //1.Kiểm tra có gameObject thỏa mãn yêu cầu ko - có thì dùng lại - ko có thì tạo mới - trả về gameObject
        for (GameObject go : gameObjects) {
            if (!go.isActive && childClass.isAssignableFrom(go.getClass())) {
                go.isActive = true;
                return (E)go;
            }
        }
        return create(childClass);
    }

    public static <E extends GameObject> E intersect(Class<E> childClass, Physics physics) {
        for (GameObject go : gameObjects) {
            if(go.isActive && childClass.isAssignableFrom(go.getClass()) && go instanceof Physics) {
                Physics physicsGo = (Physics) go;
                // Kiểm tra va chạm
                boolean intersected = physics.getBoxCollider().intersect(physicsGo, (GameObject) physics);
                if (intersected) {
                    return (E) physicsGo;
                }
            }
        }
        return null;
    }

    // Đưa vào 1 class và trả ra 1 class
    public static <E extends GameObject> E create(Class<E> childClass) {
        try {
            GameObject newGameObject = childClass.newInstance();
            newGameObjects.add(newGameObject);
            return (E)newGameObject;
        } catch (Exception e) {
            return null;
        }
    }

    public static void runAll() {
        for (GameObject go : gameObjects) {
            go.run();
        }

//        for (int i = 0; i < gameObjects.size(); i++) {
//            GameObject go = gameObjects.get(i);
//            if (go.isActive) {
//                go.run();
//            }
//        }
        gameObjects.addAll(newGameObjects);
        newGameObjects.clear();
//        System.out.println(gameObjects.size());

    }


    public static void addNewAll() {
        gameObjects.addAll(newGameObjects);
    }

//    public static void renderAll(Graphics g) {
//        for (GameObject go : gameObjects) {
//            if (go.isActive) {
//                go.render(g);
//            }
//        }
//    gameObjects.addAll(newGameObjects);
//        newGameObjects.clear();
//}

//        for (int i = 0; i < gameObjects.size(); i++) {
//            GameObject go = gameObjects.get(i);
//            if (go.isActive) {
//                go.render(g);
//            }
//        }




    public static void renderBackBufferToGame(Graphics g) {
        g.drawImage(backBuffer, 0, 0, null);
    }




    public static void renderAllToBackBuffer() {
        for (GameObject go: gameObjects) {
            if (go.isActive) {
                go.render(backBufferGraphics);
            }
        }
    }

    public void destroy() {
        this.isActive = false;
    }

    public void run() {

    }

    public void render(Graphics g) {
        if (this.renderer != null) {
            this.renderer.render(g, this);
        }
    }
}
