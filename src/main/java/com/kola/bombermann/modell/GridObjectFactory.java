/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kola.bombermann.modell;

import java.util.Random;


/**
 * Class for creatin grids objects.
 *
 * @author kola
 */
public class GridObjectFactory {
    
    static Grid grid;
    
    /**
     * Initializing the grid to where to make objects.
     *
     * @param grid the grid to where to make objects.
     */
    public static void init(Grid grid){
        GridObjectFactory.grid = grid;
    }
    
    /**
     * Adding a bomb by the specific player. 
     *
     * @param x the x position in the grid
     * @param y the y position in the grid
     * @param player the player that planned the bomb
     */
    public static void addBomb(int x, int y, Player player){
        Bomb bomb = new Bomb(player.getBombExplosionSize(), player.getId(), x * 50, y * 50, 50, 50);
        grid.getGridPiece(x, y).setBomb(bomb);
    }
    
    /**
     * Adding a Block. 
     *
     * @param x the x position in the grid
     * @param y the y position in the grid
     * @param explodable wheter the block explodable or not
     */
    public static void addBlock(int x, int y, boolean explodable){
        Block block = new Block(x * 50, y * 50, 50, 50, explodable);
        grid.getGridPiece(x, y).setBlock(block);
    }
    
    /**
     * Adding an explosion. 
     * 
     * @param x the x position in the grid
     * @param y the y position in the grid
     * @param direction the direction which direction the xplosion will spread
     * @param radious the explosion radious
     */
    public static void addExplosion(int x, int y, Direction direction, int radious){
        if(radious == 0){
            return;
        }
        if(grid.getGridPiece(x, y).getBlock() != null){
            if(grid.getGridPiece(x, y).getBlock().isDestroyable()){
                Explosion explosionP = new Explosion(x * 50, y * 50, 50, 50, direction, radious);
                grid.getGridPiece(x, y).setExplosion(explosionP);
            }
            return;
        }
        
        Explosion explosionP = new Explosion(x * 50, y * 50, 50, 50, direction, radious);
        grid.getGridPiece(x, y).setExplosion(explosionP);
        addExplosion(x + direction.getxDirection(), y + direction.getyDirection(), direction, radious-1);
        
    }
    
    /**
     * Adding a powerup. 
     *
     * @param x the x position in the grid
     * @param y the y position in the grid
     */
    public static void addPowerUp(int x, int y){
        Random rand = new Random();
        if (rand.nextInt(9)  < 4) {
            PowerUp powerUp = new PowerUp(rand.nextInt(3), x * 50, y * 50, 50, 50);
            grid.getGridPiece(x, y).setPowerUp(powerUp);
        }
    }

    /**
     * Returns the grid where the factory add objects.
     *
     * @return the grid where the factory add objects
     */
    public static Grid getGrid() {
        return grid;
    }
    
    
}
