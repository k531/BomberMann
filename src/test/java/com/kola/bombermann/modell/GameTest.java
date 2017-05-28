/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kola.bombermann.modell;

import java.util.ArrayList;
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
public class GameTest {


    /**
     * Test of initGridObjectFactory method, of class Game.
     */
    @Test
    public void testInitGameObjectFactory() {
        System.out.println("initGameObjectFactory");
        Game instance = new Game();
        instance.initGridObjectFactory();
        assertEquals(instance.getGrid(), GridObjectFactory.getGrid());
    }

    /**
     * Test of update method, of class Game.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        float deltaTime = 4.0F;
        Game instance = new Game();
        GridObjectFactory.addBomb(0, 0, new Player(0, 0, 0, 0, 0));
        instance.update(deltaTime);
        assertEquals(null, instance.getGrid().getGridPiece(0, 0).getBomb());
    }

    /**
     * Test of gridCollisionDetection method, of class Game.
     */
    @Test
    public void testGridCollisionDetection() {
        System.out.println("gridCollisionDetection");
        Game instance = new Game();
        GridObjectFactory.addExplosion(1, 1, new Direction(1, 0), 1);
        GridObjectFactory.addBlock(1, 1, true);
        
        instance.gridCollisionDetection();
        assertEquals(null, instance.getGrid().getGridPiece(1, 1).getBlock());
    }

    /**
     * Test of movePlayer method, of class Game.
     */
    @Test
    public void testMovePlayer() {
        System.out.println("movePlayer");
        int i = 0;
        int xDirection = 1;
        int yDirection = 0;
        Game instance = new Game();
        
        Player player = new Player(instance.getPlayers().get(i).getX(), instance.getPlayers().get(i).getY(), instance.getPlayers().get(i).getWidth(), instance.getPlayers().get(i).getHeight(), instance.getPlayers().get(i).getId());
        instance.movePlayer(i, xDirection, yDirection);
        player.move(xDirection, yDirection);
        assertEquals(player.getX(), instance.getPlayers().get(i).getX());
    }

    /**
     * Test of addBomb method, of class Game.
     */
    @Test
    public void testAddBomb() {
        System.out.println("addBomb");
        int playerIndex = 0;
        float now = 0.0F;
        Game instance = new Game();
        instance.addBomb(playerIndex, now);
        Bomb bomb = new Bomb(2, playerIndex, instance.getPlayers().get(playerIndex).getX() / 50, instance.getPlayers().get(playerIndex).getY() / 50, 50, 50);
        assertEquals(bomb, instance.getGrid().getGridPiece(instance.getPlayers().get(playerIndex).getX() / 50, instance.getPlayers().get(playerIndex).getY() / 50).getBomb());
    }

    

    /**
     * Test of getGameState method, of class Game.
     */
    @Test
    public void testGetGameState() {
        System.out.println("getGameState");
        Game instance = new Game();
        GridObjectFactory.addExplosion(1, 1, new Direction(0, 1), 5);
        instance.update( 1.0f);
        assertTrue(instance.gameState.getGameOver());

    }
    
}
