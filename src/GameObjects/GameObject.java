package GameObjects;
import GameObjects.CoreGameObjects.ID;

import java.awt.*;

/**
 * Base class for all game objects
 */
public abstract class GameObject {
    protected int x, y;
    protected ID id;
    protected float velX, velY;
    protected int width, height;
    protected boolean falling = true;
    protected boolean jumping = false;
    protected int damage;
    protected int exploded = 0;
    protected int speed;
    protected int jumpHeight;
    public boolean moving = false;

    protected int currenthp;

    /**
     * Sets x and y coordinates for the game oibject
     * @param x x coordinate
     * @param y y coordinate
     * @param width width of the game object
     * @param height height of the game object
     * @param id id of the game object
     */

    public GameObject(int x, int y, int width, int height, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
        this.width = width;
        this.height = height;
    }

    /**
     * Updates the game objects properties without updating it
     */
    public abstract void tick();

    /**
     * Renders the game object
     * @param g graphics
     */
    public abstract void render(Graphics g);


    /**
     * Gets the bounds (outer edges) for the game object
     * @return Rectangle (hit box)
     */
    public Rectangle getBounds(){
        return new Rectangle(x , y ,width, height);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public void setId(ID id) {
        this.id = id;
    }


    public ID getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getExploded() {
        return exploded;
    }

    public void setExploded(int exploded) {
        this.exploded = exploded;
    }
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getJumpHeight() {
        return jumpHeight;
    }

    public void setJumpHeight(int jumpHeight) {
        this.jumpHeight = jumpHeight;
    }

    public int getCurrenthp() {
        return currenthp;
    }

    public void setCurrenthp(int currenthp) {
        this.currenthp = currenthp;
    }
}
