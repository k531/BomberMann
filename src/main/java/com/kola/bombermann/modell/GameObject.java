/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kola.bombermann.modell;

/**
 *
 * @author kola
 */
public class GameObject {

    private int x;
    private int y;
    private int width;
    private int height;
    private int ImageId;

    public GameObject(){}
    
    /**
     * Constructor of the GameObject class that sets the parameters of this object.
     *
     * @param x the x position of the gameobject
     * @param y the y position of the gameobject
     * @param width the width of the gameobject
     * @param height the height of the gameobject
     */
    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns true if they collide.
     * 
     * @param other the other gameobject that can collide with this gameobject
     * @return true if they collide
     */
    public boolean isCollide(GameObject other) {
        return ((this.getX() > (other.getX() - this.getWidth())
                && this.getX() < (other.getX() + other.getWidth()))
                && (this.getY() < (other.getY() + other.getHeight())
                && this.getY() > (other.getY() - this.getHeight())));
    }

    /**
     * Returns the x position of this object.
     * 
     * @return the x position of this object
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the x position of this object.
     * 
     * @param x the x position of this object
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns the y position of this object.
     * 
     * @return the y position of this object
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the y position of this object.
     * 
     * @param y the y position of this object
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Returns the height of this object.
     * 
     * @return the height of this object
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height of this object.
     * 
     * @param height the height of this object
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Returns the width of this object.
     * 
     * @return the width of this object
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the width of this object.
     * 
     * @param width the width of this object
     */
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GameObject other = (GameObject) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        if (this.width != other.width) {
            return false;
        }
        if (this.height != other.height) {
            return false;
        }
        if (this.ImageId != other.ImageId) {
            return false;
        }
        return true;
    }
    
    

}
