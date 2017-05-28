/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kola.bombermann.modell;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Class for representing the player keyboard keys to move and to plan bombs.
 * 
 * @author kola
 */
public class ControllKeys {
    private String up;
    
    private String down;
    private String left;
    private String right;
    private String bombDeploy;
    private Map<String, String> keys;

    /**
     * Constructor of the ControllKeys class that sets the controll keys.
     * 
     * @param up the key to move up
     * @param down the key to move down
     * @param left the key to move left
     * @param right the key to move right
     * @param bombDeploy the key for planning the bomb
     */
    public ControllKeys(String up, String down, String left, String right, String bombDeploy) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.bombDeploy = bombDeploy;
        keys = new HashMap<String, String>();
        setKeys();
    }
    
    /**
     *  Sets the HashMap that will store the keys as action action trigger key pair.
     * 
     */
    public void setKeys(){
        keys.put("UP", up);
        keys.put("DOWN", down);
        keys.put("LEFT", left);
        keys.put("RIGHT", right);
        keys.put("BOMBDEPLOY", bombDeploy);
    
    }

    /**
     * Returns the controll keys as action action trigger key pair.
     *
     * @return the controll keys as action action trigger key pair
     */
    public Map<String, String> getKeys() {
        return keys;
    }
    

    @Override
    public String toString() {
        return "ControllKeys{" + "up=" + up + ", down=" + down + ", left=" + left + ", right=" + right + ", bombDeploy=" + bombDeploy + '}';
    }

    
    
}
