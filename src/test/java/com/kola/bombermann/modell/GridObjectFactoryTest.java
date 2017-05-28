/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kola.bombermann.modell;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kola
 */
public class GridObjectFactoryTest {

    /**
     * Test of init method, of class GridObjectFactory.
     */
    @Test
    public void testInit() {
        System.out.println("init");
        Grid grid = new Grid(15, 15);;
        GridObjectFactory.init(grid);
        assertEquals(grid, GridObjectFactory.getGrid());
    }

    /**
     * Test of addBomb method, of class GridObjectFactory.
     */
    @Test
    public void testAddBomb() {
        System.out.println("addBomb");
        int x = 5;
        int y = 5;
        Player player = new Player(0, 0, 50, 50, 44);
        Grid grid = new Grid(15, 15);
        GridObjectFactory.init(grid);
        GridObjectFactory.addBomb(x, y, player);
        
        Bomb bomb = new Bomb(player.getBombExplosionSize(), player.getId(), x * 50, y * 50, 50, 50);
        
        assertEquals(bomb, grid.getGridPiece(x, y).getBomb());
        
    }

    /**
     * Test of addBlock method, of class GridObjectFactory.
     */
    @Test
    public void testAddBlock() {
        System.out.println("addBlock");
        int x = 5;
        int y = 5;
        boolean explodable = true;
        Grid grid = new Grid(15, 15);
        GridObjectFactory.init(grid);
        GridObjectFactory.addBlock(x, y, explodable);
        
        Block block = new Block(x * 50, y * 50, 50, 50, explodable);
        
        assertEquals(block, grid.getGridPiece(x, y).getBlock());
    }

    /**
     * Test of addExplosion method, of class GridObjectFactory.
     */
    @Test
    public void testAddExplosion() {
        System.out.println("addExplosion");
        int x = 1;
        int y = 1;
        Direction direction = new Direction(1, 0);
        int radious = 2;
        Grid grid = new Grid(15, 15);
        GridObjectFactory.init(grid);
        GridObjectFactory.addExplosion(x, y, direction, radious);
        
        Explosion expecteds[];
        expecteds = new Explosion[2];
        Explosion actuals[];
        actuals = new Explosion[2];
        
        expecteds[0] = new Explosion(x*50, y*50, 50, 50, direction, radious);
        expecteds[1] = new Explosion((x + direction.getxDirection())*50, (y + direction.getyDirection())*50, 50, 50, direction, radious-1);
        
        actuals[0] = grid.getGridPiece(x, y).getExplosion();
        actuals[1] = grid.getGridPiece(x + direction.getxDirection(), y + direction.getyDirection()).getExplosion();
        
        assertArrayEquals(expecteds, actuals);
        
    }
    
}
