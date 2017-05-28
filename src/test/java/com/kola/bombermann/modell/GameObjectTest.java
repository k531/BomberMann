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
public class GameObjectTest {
    
    /**
     * Test of isCollide method, of class GameObject.
     */
    @Test
    public void testIsCollide() {
        System.out.println("isCollide");
        GameObject other = new GameObject(20, 20, 50, 50);
        GameObject instance = new GameObject(0, 0, 50, 50);
        assertTrue(instance.isCollide(other));
    }

    
}
