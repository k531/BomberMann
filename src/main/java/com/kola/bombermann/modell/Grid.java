/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kola.bombermann.modell;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class for representing a grid field.
 * 
 * @author kola
 */
@XmlRootElement(name = "grid")
@XmlAccessorType(XmlAccessType.FIELD)
public class Grid {
    
    @XmlElementWrapper(name = "gridpieces")
    @XmlElement(name = "gridpiece")
    private GridPiece grid[][];
    @XmlElement(name = "gridwidth")
    private int width;
    @XmlElement(name = "gridheight")
    private int height;
    

    public Grid(){}

    /**
     * Constructor of the Grid class tha will create the given specific size of grid.
     *
     * @param width the width of the grid
     * @param height the height of the grid
     */
    public Grid(int width, int height){
        grid = new GridPiece[width][height];
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                grid[x][y] = new GridPiece();
            }
        }
        this.width = width;
        this.height = height;
    }

    /**
     * Returns all the bombs in the grid.
     *
     * @return all the bombs in the grid
     */
    public ArrayList<Bomb> getBombs(){
        ArrayList<Bomb> bombs = new ArrayList<>();
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                if(grid[x][y].getBomb() != null){
                    bombs.add(grid[x][y].getBomb());
                }
            }
        }
        return bombs;
    }

    /**
     * Returns all the block in the grid.
     * @return all the block in the grid
     */
    public ArrayList<Block> getBlocks(){
        ArrayList<Block> blocks = new ArrayList<>();
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                if(grid[x][y].getBlock()!= null){
                    blocks.add(grid[x][y].getBlock());
                }
            }
        }
        return blocks;
    }

    /**
     * Returns all the explosion in the grid.
     *
     * @return all the explosion in the grid
     */
    public ArrayList<Explosion> getExplosionPs(){
        ArrayList<Explosion> explosionPs = new ArrayList<>();
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                if(grid[x][y].getExplosion()!= null){
                    explosionPs.add(grid[x][y].getExplosion());
                }
            }
        }
        return explosionPs;
    }

    /**
     * Returns all the powerup in the grid.
     *
     * @return all the powerup in the grid
     */
    public ArrayList<PowerUp> getPowerUps(){
        ArrayList<PowerUp> powerUps = new ArrayList<>();
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                if(grid[x][y].getPowerUp()!= null){
                    powerUps.add(grid[x][y].getPowerUp());
                }
            }
        }
        return powerUps;
    }
    
    /**
     * Returns a gried piece.
     *
     * @param x the gried piece position in the grid
     * @param y the gried piece position in the grid
     * @return a gried piece
     */
    public GridPiece getGridPiece(int x, int y){
        return grid[x][y];
    }

    /**
     * Returns the width of the grid.
     * 
     * @return the width of the grid
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of the grid.
     *
     * @return the height of the grid
     */
    public int getHeight() {
        return height;
    }

    
}
