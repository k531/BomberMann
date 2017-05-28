/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kola.bombermann.modell;

import java.util.ArrayList;
import java.util.Random;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kola
 */
@XmlRootElement(name = "game")
@XmlAccessorType(XmlAccessType.FIELD)
public class Game {

    @XmlElementWrapper(name = "players")
    @XmlElement(name = "player")
    private final ArrayList<Player> players = new ArrayList<>();
    @XmlElement(name = "grid")
    private Grid grid = new Grid(15, 15);
    @XmlElement(name = "gameState")
    GameState gameState = new GameState();

    /**
     *
     */
    public Game() {
        players.add(new Player(50, 50, 30, 40, 0));
        players.add(new Player(650, 650, 30, 40, 1));
        GridObjectFactory.init(grid);
        GenerateSolidBlocks();
        GenerateDestroyableBlocks();
        gameState.setGameOver(false);
    }

    /**
     *
     */
    public void initGridObjectFactory(){
        GridObjectFactory.init(grid);
    }

    private void GenerateSolidBlocks() {
        for (int i = 0; i < 15; i++) {
            GridObjectFactory.addBlock(i, 0, false);
            GridObjectFactory.addBlock(0, i, false);
            GridObjectFactory.addBlock(i, 14, false);
            GridObjectFactory.addBlock(14, i, false);
        }
        for (int x = 2; x < 15; x += 2) {
            for (int y = 2; y < 15; y += 2) {
                GridObjectFactory.addBlock(x, y, false);
            }
        }
    }

    private void GenerateDestroyableBlocks() {
        Random rand = new Random();
        for (int x = 3; x < 12; x++) {
            for (int y = 1; y < 14; y++) {
                if ((grid.getGridPiece(x, y).getBlock() == null) && (rand.nextInt(10) < 9)) {
                    GridObjectFactory.addBlock(x, y, true);
                }
                if ((grid.getGridPiece(y, x).getBlock() == null) && (rand.nextInt(10) < 9)) {
                    GridObjectFactory.addBlock(y, x, true);
                }
            }
        }
    }

    /**
     *
     * @param deltaTime
     */
    public void update(float deltaTime) {
        for (Bomb bomb : grid.getBombs()) {
            bomb.update(deltaTime);
            if (bomb.isExploded()) {
                explodeBomb(bomb);
            }
        }
        for (Explosion explosionP : grid.getExplosionPs()) {
            explosionP.update(deltaTime);
            if (explosionP.isGone()) {
                for(Player player : players){
                    if(isPlayerCollideWithExplosions(player)){
                        player.setDead(true);
                        gameState.setGameOver(true);
                    }
                }
                explodeGone(explosionP);
            }
        }
    }

    /**
     *
     */
    public void gridCollisionDetection() {
        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
                if (grid.getGridPiece(x, y).isModified()) {
                    if (grid.getGridPiece(x, y).getExplosion() != null) {
                        if (grid.getGridPiece(x, y).getBlock() != null && grid.getGridPiece(x, y).getBlock().isDestroyable()) {
                            grid.getGridPiece(x, y).setBlock(null);
                            GridObjectFactory.addPowerUp(x, y);
                        } else if (grid.getGridPiece(x, y).getBomb() != null) {
                            explodeBomb(grid.getGridPiece(x, y).getBomb());
                        } else if (grid.getGridPiece(x, y).getPowerUp() != null) {
                            grid.getGridPiece(x, y).setPowerUp(null);
                        }
                    }
                    grid.getGridPiece(x, y).setModified(false);
                }
            }
        }

    }

    private void explodeBomb(Bomb bomb) {
        int x = bomb.getX() / 50;
        int y = bomb.getY() / 50;
        GridObjectFactory.addExplosion(x, y, new Direction(1, 0), bomb.getExplosionSize());
        GridObjectFactory.addExplosion(x, y, new Direction(0, 1), bomb.getExplosionSize());
        GridObjectFactory.addExplosion(x, y, new Direction(-1, 0), bomb.getExplosionSize());
        GridObjectFactory.addExplosion(x, y, new Direction(0, -1), bomb.getExplosionSize());
        grid.getGridPiece(x, y).setBomb(null);
        players.get(bomb.getPlayerId()).bombExploded();
    }

    private void explodeGone(Explosion explosionP) {
        int x = explosionP.getX() / 50;
        int y = explosionP.getY() / 50;
        grid.getGridPiece(x, y).setExplosion(null);
    }

    /**
     *
     * @param i
     * @param xDirection
     * @param yDirection
     */
    public void movePlayer(int i, int xDirection, int yDirection) {

        Player tmp = new Player(players.get(i).getX() + (players.get(i).getSpeed() * xDirection), players.get(i).getY() + (players.get(i).getSpeed() * yDirection), 30, 40, i);
        if (!isPlayerCollideWithGridBlocks(tmp) && !isPlayerCollideWitgGridBombs(tmp)) {
            players.get(i).move(xDirection, yDirection);
            powerUpsCheck(tmp);
        }
    }

    private boolean isPlayerCollideWithGridBlocks(Player player) {
        int startX = player.getX() / 50;
        int startY = player.getY() / 50;

        for (int x = startX; x <= startX + 1; x++) {
            for (int y = startY; y <= startY + 1; y++) {
                if (grid.getGridPiece(x, y).getBlock() != null && player.isCollide(grid.getGridPiece(x, y).getBlock())) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isPlayerCollideWitgGridBombs(Player player) {

        for (Bomb bomb : grid.getBombs()) {
            if (player.isCollide(bomb)) {
                if (bomb.getPlayerId() == player.getId() && bomb.isStartCollision()) {
                    break;
                }
                return true;
            } else if (bomb.getPlayerId() == player.getId()) {
                bomb.setStartCollision(false);
            }
        }
        return false;
    }

    private boolean isPlayerCollideWithExplosions(Player player) {
        int startX = player.getX() / 50;
        int startY = player.getY() / 50;
        for (int x = startX; x <= startX + 1; x++) {
            for (int y = startY; y <= startY + 1; y++) {
                if (grid.getGridPiece(x, y).getExplosion() != null && player.isCollide(grid.getGridPiece(x, y).getExplosion())) {

                    return true;
                }
            }
        }

        return false;
    }

    private void powerUpsCheck(Player player) {
        int startX = player.getX() / 50;
        int startY = player.getY() / 50;

        for (int x = startX; x <= startX + 1; x++) {
            for (int y = startY; y <= startY + 1; y++) {
                if (grid.getGridPiece(x, y).getPowerUp() != null && player.isCollide(grid.getGridPiece(x, y).getPowerUp())) {
                    players.get(player.getId()).addPowerUp(grid.getGridPiece(x, y).getPowerUp().getPowerUpType());
                    grid.getGridPiece(x, y).setPowerUp(null);
                }
            }
        }

    }

    /**
     *
     * @param playerIndex
     * @param now
     */
    public void addBomb(int playerIndex, float now) {
        if (players.get(playerIndex).canPlanBomb() && !players.get(playerIndex).isDead()) {
            int x = (players.get(playerIndex).getX() + players.get(playerIndex).getWidth() / 2);
            int y = (players.get(playerIndex).getY() + players.get(playerIndex).getHeight() / 2);
            x /= 50;
            y /= 50;
            if (grid.getGridPiece(x, y).getBomb() == null) {
                GridObjectFactory.addBomb(x, y, players.get(playerIndex));
                players.get(playerIndex).planBomb();
            }
        }
    }

    /**
     *
     * @return
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     *
     * @return
     */
    public Grid getGrid() {
        return grid;
    }

    /**
     *
     * @return
     */
    public GameState getGameState() {
        return gameState;
    }
    

}
