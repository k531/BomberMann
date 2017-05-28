/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kola.bombermann.modell;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Class for representing the state of the game.
 * 
 * @author kola
 */
public class GameState {
    
    private BooleanProperty gameOver = new SimpleBooleanProperty();

    /**
     * Returns true if the game is over.
     *
     * @return true if the game is over
     */
    public boolean getGameOver() {
        return gameOver.get();
    }

    /**
     * Sets the condition of the game is over or not.
     * 
     * @param gameOver wheter the game is over or not
     */
    public void setGameOver(boolean gameOver) {
        this.gameOver.set(gameOver);
    }
    
    /**
     * Returns he game state as BooleanProperty.
     * 
     * @return the game state as BooleanProperty
     */
    public BooleanProperty getGameOverProperty(){
        return gameOver;
    }
    
        
        
    
}
