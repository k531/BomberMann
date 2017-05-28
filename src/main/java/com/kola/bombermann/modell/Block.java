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
 * Class for representing a single block element.
 * 
 * @author kola
 */
@XmlRootElement(name = "block")
@XmlAccessorType(XmlAccessType.FIELD)
public class Block extends GameObject{

    @XmlElement(name = "destroyable")
    private boolean destroyable;


    public Block(){}

    /**
     * Constructor of Block class that sets the default parameters.
     * 
     * @param x the x position of the block
     * @param y the y position of the block
     * @param width the width of this block
     * @param height the height of this block
     * @param destroyable whether destroyable or not
     */
    public Block(int x, int y, int width, int height, boolean destroyable) {
        super(x, y, width, height);
        this.destroyable = destroyable;
    }

    /**
     * Returns true if the block destroyable.
     * 
     * @return whether destroyable or not
     */
    public boolean isDestroyable() {
        return destroyable;
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
        final Block other = (Block) obj;
        if (this.destroyable != other.destroyable) {
            return false;
        }
        return true;
    }

    
    
}
