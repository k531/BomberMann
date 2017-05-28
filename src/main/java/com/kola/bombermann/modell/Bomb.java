/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kola.bombermann.modell;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class for representing an explodable bomb.
 * 
 * @author kola
 */
@XmlRootElement(name = "bomb")
@XmlAccessorType(XmlAccessType.FIELD)
public class Bomb extends GameObject {

    @XmlElement(name = "timetoexplode")
    private float timeToExplode = 3;
    @XmlElement(name = "explosionSize")
    private int explosionSize;
    @XmlElement(name = "playerId")
    private int playerId;
    @XmlElement(name = "startCollision")
    private boolean startCollision = true;
    @XmlElement(name = "exploded")
    private boolean exploded = false;

    public Bomb(){}

    /**
     * Constructor of Bomb class that sets the bomb parameters that not default.
     * 
     * @param explosionSize the explosion size of the bomb
     * @param playerId the player id to identify the bomb owner
     * @param x the x position of the bomb
     * @param y the y position of the bomb
     * @param width the width of the bomb
     * @param height the height of the bomb
     */
    public Bomb(int explosionSize, int playerId, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.playerId = playerId;
        this.explosionSize = explosionSize;
    }

    /**
     *Returns the explosioan size of this Bomb.
     * 
     * @return the explosioan size of this Bomb
     */
    public int getExplosionSize() {
        return explosionSize;
    }

    /**
     *Returns the player Id of this Bomb.
     * 
     * @return the player Id of this Bomb
     */
    public int getPlayerId() {
        return playerId;
    }

    /**
     * Returns true if the bomb owner player just planned this bomb or false if already moved away so the starting collision gone.
     *
     * @return true if the bomb owner player just planned this bomb or false if already moved away so the starting collision gone
     */
    public boolean isStartCollision() {
        return startCollision;
    }

    /**
     * Sets wheter the bomb owner player just planned this bomb or already moved away so the starting collision gone.
     *
     * @param startCollision wheter the bomb owner player just planned this bomb or already moved away so the starting collision gone
     */
    public void setStartCollision(boolean startCollision) {
        this.startCollision = startCollision;
    }

    /**
     * Returns true if  this bomb exploded or false if not exploded yet
     * 
     * @return true if  this bomb exploded or false if not exploded yet
     */
    public boolean isExploded() {
        return exploded;
    }

    /**
     *  Update the remaining time of the bomb to explode.
     * 
     * @param deltaTime the time betwen the last update
     */
    public void update(float deltaTime) {
        timeToExplode -= deltaTime;
        if (timeToExplode < 0 && exploded == false) {
            exploded = true;
        }
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
        final Bomb other = (Bomb) obj;
        if (Float.floatToIntBits(this.timeToExplode) != Float.floatToIntBits(other.timeToExplode)) {
            return false;
        }
        if (this.explosionSize != other.explosionSize) {
            return false;
        }
        if (this.playerId != other.playerId) {
            return false;
        }
        if (this.startCollision != other.startCollision) {
            return false;
        }
        if (this.exploded != other.exploded) {
            return false;
        }
        return true;
    }

    
}
