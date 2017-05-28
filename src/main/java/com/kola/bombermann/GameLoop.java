/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kola.bombermann;

import com.kola.bombermann.modell.Block;
import com.kola.bombermann.modell.Bomb;
import com.kola.bombermann.modell.ControllKeys;
import com.kola.bombermann.modell.Explosion;
import com.kola.bombermann.modell.Game;
import com.kola.bombermann.modell.Player;
import com.kola.bombermann.modell.PowerUp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javax.xml.bind.JAXBException;

/**
 *
 * @author kola
 */
public class GameLoop extends AnimationTimer {

    private long previousTime = 0;
    private float secondsElapsed = 0f;
    private GraphicsContext gc;
    private ArrayList<String> input = new ArrayList<String>();
    private ArrayList<ControllKeys> controllKeys = new ArrayList<>();
    private final Image playerImage;
    private final Image blockImage;
    private final Image bombImage;
    private final Image destroyableBlockImage;
    private final Game game;
    private final Image speedImage;
    private final Image explosionImage;

    public GameLoop(GraphicsContext gc, Game game){
        controllKeys.add(new ControllKeys("UP", "DOWN", "LEFT", "RIGHT", "ENTER"));
        controllKeys.add(new ControllKeys("W", "S", "A", "D", "SHIFT"));
        this.game = game;

        playerImage = new Image(getClass().getResourceAsStream("/images/player.png"), 30, 40, false, false);
        blockImage = new Image(getClass().getResourceAsStream("/images/block.png"), 50, 50, false, false);
        bombImage = new Image(getClass().getResourceAsStream("/images/bomb.png"), 50, 50, false, false);
        destroyableBlockImage = new Image(getClass().getResourceAsStream("/images/destroyableBlock.png"), 50, 50, false, false);
        explosionImage = new Image(getClass().getResourceAsStream("/images/middleExplosion.png"), 50, 50, false, false);
        speedImage = new Image(getClass().getResourceAsStream("/images/speed.png"), 50, 50, false, false);
        this.gc = gc;
    }

    @Override
    public void handle(long now) {
        if (previousTime == 0) {
            previousTime = now;
            return;
        }
        secondsElapsed = (now - previousTime) / 1e9f;
        if (secondsElapsed > 0.005) {
            inputProcessing(secondsElapsed);
            game.update(secondsElapsed);
            game.gridCollisionDetection();
            draw();
            previousTime = now;
        }
    }

    @Override
    public void stop() {
        previousTime = 0;
        super.stop();
    }

    private void draw() {
        gc.clearRect(0, 0, 750, 750);
        for (Block block : game.getGrid().getBlocks()) {
            if (block.isDestroyable()) {
                gc.drawImage(destroyableBlockImage, block.getX(), block.getY());
            } else {
                gc.drawImage(blockImage, block.getX(), block.getY());
            }
        }
        for (PowerUp powerUp : game.getGrid().getPowerUps()) {
            switch (powerUp.getPowerUpType()) {
                case 0:
                    gc.drawImage(speedImage, powerUp.getX(), powerUp.getY());
                    break;
                case 1:
                    gc.drawImage(speedImage, powerUp.getX(), powerUp.getY());
                    break;
                case 2:
                    gc.drawImage(speedImage, powerUp.getX(), powerUp.getY());
                    break;
                default:
                    break;
            }
        }

        for (Bomb bomb : game.getGrid().getBombs()) {
            gc.drawImage(bombImage, bomb.getX(), bomb.getY());
        }

        for (Player player : game.getPlayers()) {
            gc.drawImage(playerImage, player.getX(), player.getY());
        }

        for (Explosion explosionP : game.getGrid().getExplosionPs()) {
            gc.drawImage(explosionImage, explosionP.getX(), explosionP.getY());

        }

    }

    private void playerSpecificInputProcessing(int playerIndex, String key, float deltaTime) {
        switch (key) {
            case "UP":
                game.movePlayer(playerIndex, 0, -1);
                break;
            case "DOWN":
                game.movePlayer(playerIndex, 0, 1);
                break;
            case "LEFT":
                game.movePlayer(playerIndex, -1, 0);
                break;
            case "RIGHT":
                game.movePlayer(playerIndex, 1, 0);
                break;
            case "BOMBDEPLOY":
                game.addBomb(playerIndex, deltaTime);
                break;
        }

    }

    private void inputProcessing(float deltaTime) {
        for (int i = 0; i < controllKeys.size(); i++) {
            for (String key : controllKeys.get(i).getKeys().keySet()) {
                for (int j = input.size() - 1; j >= 0; j--) {
                    if (controllKeys.get(i).getKeys().get(key).equals(input.get(j))) {
                        playerSpecificInputProcessing(i, key, deltaTime);
                    }
                }
            }
        }
    }

    public ArrayList<String> getInput() {
        return input;
    }

    public Game getGame() {
        return game;
    }

    
    
    public void addInput(String key) {
        input.add(key);
    }

    public void removeInput(String key) {
        input.remove(key);
    }

}
