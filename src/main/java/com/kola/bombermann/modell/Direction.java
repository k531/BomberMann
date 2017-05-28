/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kola.bombermann.modell;

/**
 *Class for representing a direcion of an object.
 * 
 * @author kola
 */
public class Direction {
    
    private int xDirection;
    private int yDirection;
    
    /**
     * Constructor of the Direction class that sets the x and x directions.
     *
     * @param xDirection
     * @param yDirection
     */
    public Direction(int xDirection, int yDirection)
    {
        this.xDirection = xDirection;
        this.yDirection = yDirection;
    }

    /**
     *Returns the x direction of this object.
     * 
     * @return the x direction of this object
     */
    public int getxDirection() {
        return xDirection;
    }

    /**
     * Sets the x direction of this object.
     * 
     * @param xDirection the x direction of this object
     */
    public void setxDirection(int xDirection) {
        this.xDirection = xDirection;
    }

    /**
     * Returns the y direction of this object.
     * 
     * @return the y direction of this object
     */
    public int getyDirection() {
        return yDirection;
    }

    /**
     * Sets the y direction of this object.
     * 
     * @param yDirection the y direction of this object
     */
    public void setyDirection(int yDirection) {
        this.yDirection = yDirection;
    }
    
    
}
