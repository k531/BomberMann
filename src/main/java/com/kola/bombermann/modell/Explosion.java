/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kola.bombermann.modell;

import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class for representing a peace of a whole explosion.
 * 
 * @author kola
 */
@XmlRootElement(name = "explosion")
@XmlAccessorType(XmlAccessType.FIELD)
public class Explosion extends GameObject{
   
    @XmlElement(name = "timetoexplode")
    private float timeToExplode = 0.4f;
    @XmlElement(name = "gone")
    private boolean gone = false;
    @XmlElement(name = "direction")
    private Direction direction;
    @XmlElement(name = "explosionsize")
    private int explosionSize;

    public Explosion(){}

    /**
     * Constructor of the Explosion class that sets the explosion parameters.
     *
     * @param x the x psition of the explosion
     * @param y the y position of the explosion
     * @param width the width of the explosion
     * @param height the height of the explosion
     * @param direction the explosion direction where the explosion will spread
     * @param explosionSize the explosion size of the whole explosion
     */
    public Explosion(int x, int y, int width, int height, Direction direction, int explosionSize) {
        super(x, y, width, height);
        this.direction = direction;
        this.explosionSize = explosionSize;
    }

    /**
     *  Returns true if the explosion alive or false if expired.
     * 
     * @return true if the explosion alive or false if expired
     */
    public boolean isGone() {
        return gone;
    }
    
    /**
     * Updates the explosion remaining time to expire.
     * 
     * @param deltaTime the time between the las update
     */
    public void update (float deltaTime){
        timeToExplode -= deltaTime;
        if(timeToExplode < 0 && gone == false)
            gone = true;
    }

    /**
     * Returns the explosion size of the explosion.
     * 
     * @return the explosion size of the explosion
     */
    public int getExplosionSize() {
        return explosionSize;
    }

    /**
     * Returns the direction of this eyplosion.
     * 
     * @return the direction of this eyplosion
     */
    public Direction getDirection() {
        return direction;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Explosion other = (Explosion) obj;
        if (Float.floatToIntBits(this.timeToExplode) != Float.floatToIntBits(other.timeToExplode)) {
            return false;
        }
        if (this.gone != other.gone) {
            return false;
        }
        if (this.explosionSize != other.explosionSize) {
            return false;
        }
        if (!Objects.equals(this.direction, other.direction)) {
            return false;
        }
        return true;
    }
    
    
    
   
}
