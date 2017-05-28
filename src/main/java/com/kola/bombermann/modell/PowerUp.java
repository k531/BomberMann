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
 * Class for representing the powerups.
 *
 * @author kola
 */
@XmlRootElement(name = "powerup")
@XmlAccessorType(XmlAccessType.FIELD)
public class PowerUp extends GameObject {

    @XmlElement(name = "poweruptype")
    private int powerUpType;

    public PowerUp(){}

    /**
     * Constructor of the class PowerUp that sets the powerup parameters.
     *
     * @param powerUpType the type of the powerup
     * @param x the x position of the powerup
     * @param y the y position of the powerup
     * @param width height of the powerup object
     * @param height widht of the powerup object
     */
    public PowerUp(int powerUpType, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.powerUpType = powerUpType;
    }

    /**
     * Returns the type of the powerup.
     * @return the type of the powerup
     */
    public int getPowerUpType() {
        return powerUpType;
    }

}
