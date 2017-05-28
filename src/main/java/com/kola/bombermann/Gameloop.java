/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*package com.kola.bombermann;

import com.kola.bombermann.modell.ControllKeys;
import com.kola.bombermann.modell.Explosion;
import com.kola.bombermann.modell.GameObject;
import com.kola.bombermann.modell.InputKeyReaderWriter;
import com.kola.bombermann.modell.MapHolder;
import com.kola.bombermann.modell.Player;
import com.kola.bombermann.modell.PowerUp;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Gameloop extends AnimationTimer {

    private long previousTime = 0;
    private float secondsElapsed = 0f;
    private MapHolder mapHolder = new MapHolder();
    private GraphicsContext gc;
    private double x = 0;
    private ArrayList<String> input = new ArrayList<String>();
    private Image player;
    private Image mapBlock;
    private Image bomb;
    private Image mapDestroyableBlock;
    private Image middleExplosion;
    private Image horizontalExplosion;
    private Image verticalExplosion;
    private Image leftEnd;
    private Image rightEnd;
    private Image upEnd;
    private Image downEnd;

    private Image speed;
    private ArrayList<ControllKeys> controllKeys = new ArrayList<>();

    public Gameloop(GraphicsContext gc) {
        controllKeys.add(InputKeyReaderWriter.readKeys(1));
        controllKeys.add(InputKeyReaderWriter.readKeys(2));

        player = new Image(getClass().getResourceAsStream("/images/player.png"), 30, 40, false, false);
        mapBlock = new Image(getClass().getResourceAsStream("/images/block.png"), 50, 50, false, false);
        bomb = new Image(getClass().getResourceAsStream("/images/bomb.png"), 50, 50, false, false);
        mapDestroyableBlock = new Image(getClass().getResourceAsStream("/images/destroyableBlock.png"), 50, 50, false, false);
        middleExplosion = new Image(getClass().getResourceAsStream("/images/middleExplosion.png"), 50, 50, false, false);
        horizontalExplosion = new Image(getClass().getResourceAsStream("/images/balraJobbra.png"), 50, 50, false, false);
        verticalExplosion = new Image(getClass().getResourceAsStream("/images/leFel.png"), 50, 50, false, false);
        leftEnd = new Image(getClass().getResourceAsStream("/images/balveg.png"), 50, 50, false, false);
        rightEnd = new Image(getClass().getResourceAsStream("/images/jobbveg.png"), 50, 50, false, false);
        upEnd = new Image(getClass().getResourceAsStream("/images/felveg.png"), 50, 50, false, false);
        downEnd = new Image(getClass().getResourceAsStream("/images/leveg.png"), 50, 50, false, false);
        speed = new Image(getClass().getResourceAsStream("/images/speed.png"), 50, 50, false, false);
        this.gc = gc;
    }

    private void draw() {
        gc.clearRect(0, 0, 750, 750);

        for (GameObject cSolidBlock : mapHolder.getMapSolidBlocks()) {
            gc.drawImage(mapBlock, cSolidBlock.getX(), cSolidBlock.getY());
        }

        for (GameObject cDestroyableBlock : mapHolder.getMapDestroyableBlocks()) {
            gc.drawImage(mapDestroyableBlock, cDestroyableBlock.getX(), cDestroyableBlock.getY());
        }
        for (PowerUp cpPowerUp : mapHolder.getMapPowerUps()) {
            gc.drawImage(speed, cpPowerUp.getX(), cpPowerUp.getY());
        }

        for (GameObject cBomb : mapHolder.getMapBombs()) {
            gc.drawImage(bomb, cBomb.getX(), cBomb.getY());
        }

        for (Player cPlayer : mapHolder.getPlayers()) {
            gc.drawImage(player, cPlayer.getX(), cPlayer.getY());
        }

        for (Explosion cExplosion : mapHolder.getMapExplosions()) {
            for(GameObject cGameObject : cExplosion.getHorizontalExplosion()){
                gc.fillRect(cGameObject.getX(), cGameObject.getY(), cGameObject.getWidth(), cGameObject.getHeight());
            }
            
           for(GameObject cGameObject : cExplosion.getVerticalExplosion()){
                gc.fillRect(cGameObject.getX(), cGameObject.getY(), cGameObject.getWidth(), cGameObject.getHeight());
            }
        }

    }


    private void controllPlayer(int playerIndex, String key, long now) {
        switch (key) {
            case "UP":
                mapHolder.movePlayer(playerIndex, 0, -1);
                break;
            case "DOWN":
                mapHolder.movePlayer(playerIndex, 0, 1);
                break;
            case "LEFT":
                mapHolder.movePlayer(playerIndex, -1, 0);
                break;
            case "RIGHT":
                mapHolder.movePlayer(playerIndex, 1, 0);
                break;
            case "BOMBDEPLOY":
                mapHolder.addBomb(playerIndex, now);
                break;
        }

    }

    private void controllInput(long now) {
        for (int i = 0; i < controllKeys.size(); i++) {
            for (String key : controllKeys.get(i).getKeys().keySet()) {
                for (int j = input.size() - 1; j >= 0; j--) {
                    if (controllKeys.get(i).getKeys().get(key).equals(input.get(j))) {
                        controllPlayer(i, key, now);
                    }
                }
            }
        }

    }
    

    private void update(long now) {
        mapHolder.update(now);
    }

    @Override
    public void handle(long now) {
        if (previousTime == 0) {
            previousTime = now;
            return;
        }
        secondsElapsed = (now - previousTime) / 1e9f;
        if (secondsElapsed > 0.005) {
            controllInput((long) (now / 1e9f));
            update((long) (now / 1e9f));
            previousTime = now;
        }
        draw();
    }

    @Override
    public void stop() {
        previousTime = 0;
        super.stop();
    }

    public ArrayList<String> getInput() {
        return input;
    }

    public void addInput(String key) {
        input.add(key);
    }

    public void removeInput(String key) {
        input.remove(key);
    }

}
*/