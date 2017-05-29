/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kola.bombermann;

import com.kola.bombermann.modell.Game;
import com.kola.bombermann.modell.ReadWriteSave;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.xml.bind.JAXBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author kola
 */
public class BomberMann implements Initializable {
    
    final static Logger logger = LoggerFactory.getLogger(BomberMann.class);

    @FXML
    private Canvas playArea;
    @FXML
    private Label player2Score;
    @FXML
    private Button playB;
    @FXML
    private Button save;
    
    @FXML
    private Button newGame;
    @FXML
    private Button loadGame;
    @FXML
    private Button exit;
    
    @FXML
    private Label winnerName;
    
    @FXML
    private Button backMenu;

    private boolean inGame = false;

    private GameLoop gameloop;
    private Game game;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private void play() {
        if (inGame) {
            inGame = false;
            playB.setText("PLAY");
            gameloop.stop();
            logger.info("Game paused.");
        } else {
            inGame = true;
            playB.setText("STOP");
            gameloop.start();
            logger.info("Game started.");
        }
    }

    @FXML
    private void onKeyPressed(KeyEvent event) {
        String code = event.getCode().toString();

        if (!gameloop.getInput().contains(code)) {
            gameloop.addInput(code);
        }
    }

    @FXML
    private void onKeyReleased(KeyEvent event) {
        String code = event.getCode().toString();
        gameloop.removeInput(code);
    }
    
    private void makeGameStateListener(){
        game.getGameState().getGameOverProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                gameloop.stop();
                try {
                    goWinnerScreen();
                } catch (IOException ex) {
                    logger.error(ex.getMessage());
                }
                
            }
      });
    }

    private void makeNewGame(){
        game = new Game();
        makeGameStateListener();
        gameloop = new GameLoop(playArea.getGraphicsContext2D(), game);
    }
    
    private void loadGame(){
        String savePath = System.getProperty("user.home") + "/.BoberMann";
        game = ReadWriteSave.readXML(savePath + "/save.xml");
        game.initGridObjectFactory();
        makeGameStateListener();
        gameloop = new GameLoop(playArea.getGraphicsContext2D(), game);
    }
    @FXML
    private void startNewGame(MouseEvent event) throws IOException {
        Stage stage; 
        Parent root;
        stage=(Stage) newGame.getScene().getWindow();
        FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/fxml/Game.fxml"));
        root = fXMLLoader.load();
        fXMLLoader.<BomberMann>getController().makeNewGame();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        logger.info("New game started.");
    }

    @FXML
    private void loadSave(MouseEvent event) throws IOException {
        Stage stage; 
        Parent root;
        stage=(Stage) newGame.getScene().getWindow();
        FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/fxml/Game.fxml"));
        root = fXMLLoader.load();
        fXMLLoader.<BomberMann>getController().loadGame();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        logger.info("Loading existing game.");
    }

    @FXML
    private void exitGame(MouseEvent event) {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
        logger.info("The game closed.");
    }
    
    @FXML
    private void saveGame(){
        try {
            String savePath = System.getProperty("user.home") + "/.BoberMann";
            Path path = Paths.get(savePath);
            File tmpDir = new File(savePath);
            tmpDir.mkdir();
            ReadWriteSave.writeXML(game, savePath + "/save.xml");
        } catch (JAXBException ex) {
            logger.error(ex.getMessage());
        }
        logger.info("Saving game state.");
       
    }
    @FXML
    private void backToMenu() throws IOException{
        Stage stage; 
        Parent root;
        stage=(Stage) backMenu.getScene().getWindow();
        FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/fxml/MainMenu.fxml"));
        root = fXMLLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        logger.info("Changed the scene to main menu.");
    }
    
    private void setWinner(int id){
            winnerName.setText("Player " + id + " Wins");
            logger.info("The game winner is the Player " + id);
    }
    
    @FXML
    private void goWinnerScreen() throws IOException{
        int id;
        if(game.getPlayers().get(0).isDead())
            id = 2;
        else 
            id = 1;
        Stage stage; 
        Parent root;
        stage=(Stage) playArea.getScene().getWindow();
        FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/fxml/WinnerScreen.fxml"));
        root = fXMLLoader.load();
        fXMLLoader.<BomberMann>getController().setWinner(id);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        logger.info("Changed the scene to winner scene.");
    }

}
