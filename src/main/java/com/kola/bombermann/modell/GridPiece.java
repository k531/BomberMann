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
 * Class for reprsenting a grid piece.
 *
 * @author kola
 */
@XmlRootElement(name = "gridpiece")
@XmlAccessorType(XmlAccessType.FIELD)
public class GridPiece {

    @XmlElement(name = "block")
    private Block block;
    @XmlElement(name = "bomb")
    private Bomb bomb;
    @XmlElement(name = "explosion")
    private Explosion explosion;
    @XmlElement(name = "powerup")
    private PowerUp powerUp;
    
    @XmlElement(name = "modified")
    private boolean modified = false;

    public GridPiece() {

    }

    /**
     * Returns the block that the grid piece hold.
     *
     * @return the block that the grid piece hold
     */
    public Block getBlock() {
        return block;
    }

    /**
     * Sets the block that the grid piece hold.
     *
     * @param block the block that the grid piece hold
     */
    public void setBlock(Block block) {
        this.block = block;
        modified = true;
    }

    /**
     * Returns the bomb that the grid piece hold.
     *
     * @return the bomb that the grid piece hold
     */
    public Bomb getBomb() {
        return bomb;
    }

    /**
     * Sets the bomb that the grid piece hold.
     *
     * @param bomb the block that the grid piece hold
     */
    public void setBomb(Bomb bomb) {
        this.bomb = bomb;
        modified = true;
    }

    /**
     * Sets the block that the grid piece hold.
     *
     * @return the explosion that the grid piece hold
     */
    public Explosion getExplosion() {
        return explosion;
    }

    /**
     * Sets the explosion that the grid piece hold.
     *
     * @param explosion the explosion that the grid piece hold
     */
    public void setExplosion(Explosion explosion) {
        this.explosion = explosion;
        modified = true;
    }

    /**
     * Sets the powerup that the grid piece hold.
     *
     * @return the powerup that the grid piece hold
     */
    public PowerUp getPowerUp() {
        return powerUp;
    }

    /**
     * Sets the powerup that the grid piece hold.
     *
     * @param powerUp the powerup that the grid piece hold
     */
    public void setPowerUp(PowerUp powerUp) {
        this.powerUp = powerUp;
        modified = true;
    }

    /**
     * Returns true if the gried piece was modified.
     *
     * @return true if the gried piece was modified
     */
    public boolean isModified() {
        return modified;
    }

    /**
     * Returns true if the gried piece was modified.
     *
     * @param modified wheter the gried piece was modified or not
     */
    public void setModified(boolean modified) {
        this.modified = modified;
    }
    
    

}
