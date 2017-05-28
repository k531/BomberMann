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
 * Class for representing the playable character.
 *
 * @author kola
 */
@XmlRootElement(name = "player")
@XmlAccessorType(XmlAccessType.FIELD)
public class Player extends GameObject {
    @XmlElement(name = "id")
    private int id;
    @XmlElement(name = "bombexplosionsize")
    private int bombExplosionSize = 2;
    @XmlElement(name = "speed")
    private int speed = 3;
    @XmlElement(name = "amountofbomb")
    private int amountOfBomb = 1;
    @XmlElement(name = "dead")
    private boolean dead = false;
    @XmlElement(name = "numberofplannedbombs")
    private int numberOfPlannedBombs = 0;

    public Player(){}
    
    /**
     * Construtor of the Player class that sets this player parameters.
     *
     * @param x the x position of the player
     * @param y the y position of the player
     * @param width the width of the player
     * @param height the height of the player
     * @param id the id for identifying the player
     */
    public Player(int x, int y, int width, int height, int id) {
        super(x, y, width, height);
        this.id = id;
    }

    /**
     * Move the player to the direction.
     *
     * @param x direction of the move
     * @param y direction of the move
     */
    public void move(int x, int y) {
        setX(getX() + x * speed);
        setY(getY() + y * speed);
    }

    /**
     * Returns the explosion size of the player bombs.
     * 
     * @return the explosion size of the player bombs
     */
    public int getBombExplosionSize() {
        return bombExplosionSize;
    }

    /**
     * Incrementing the player bombs explosion size.
     */
    public void addBombExplosionSize() {
        bombExplosionSize ++;
    }

    /**
     * Returns the speed of the player;
     *
     * @return the speed of the player
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Incrementing the player speed.
     */
    public void addSpeed() {
        speed++;
    }

    /**
     * Returns the amount of the bombs that the player can place.
     *
     * @return the amount of the bombs that the player can place
     */
    public int getAmountOfBomb() {
        return amountOfBomb;
    }

    /**
     * Incrementing th amount of bombs that the player can place
     */
    public void addAmountOfBomb() {
        amountOfBomb++;
    }

    /**
     * Returns true if the player dead.
     *
     * @return true if the player dead
     */
    public boolean isDead() {
        return dead;
    }

    /**
     * Sets the player dead condition.
     *
     * @param dead wheter the player dead or not
     */
    public void setDead(boolean dead) {
        this.dead = dead;
    }

    /**
     * Returns the number of the planned bombs of the player.
     *
     * @return the number of the planned bombs of the player
     */
    public int getNumberOfPlannedBombs() {
        return numberOfPlannedBombs;
    }

    /**
     * Incrementing the number of bombs that planned by the player.
     */
    public void planBomb() {
        numberOfPlannedBombs++;
    }

    /**
     * Decrementing the number of bombs that planned by the player.
     */
    public void bombExploded() {
        numberOfPlannedBombs--;
    }

    /**
     * Returns true if the player can plan bomb.
     *
     * @return true if the player can plan bomb
     */
    public boolean canPlanBomb() {
        if (amountOfBomb > numberOfPlannedBombs) {
            return true;
        }

        return false;
    }

    /**
     * Returns he player Id.
     *
     * @return the player Id
     */
    public int getId() {
        return id;
    }

    /**
     * Applying the powerup affect to the player
     * @param powerUpType the type of the powerup that the player picked up
     */
    public void addPowerUp(int powerUpType) {
        switch (powerUpType) {
            case 0:
                addSpeed();
                break;
            case 1:
                addAmountOfBomb();
                break;
            case 2:
                addBombExplosionSize();
                break;
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
        final Player other = (Player) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.bombExplosionSize != other.bombExplosionSize) {
            return false;
        }
        if (this.speed != other.speed) {
            return false;
        }
        if (this.amountOfBomb != other.amountOfBomb) {
            return false;
        }
        if (this.dead != other.dead) {
            return false;
        }
        if (this.numberOfPlannedBombs != other.numberOfPlannedBombs) {
            return false;
        }
        return true;
    }
    
    

}
