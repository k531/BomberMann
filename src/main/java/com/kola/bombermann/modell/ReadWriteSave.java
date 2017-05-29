/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kola.bombermann.modell;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Class for saving or loading save files.
 *
 * @author kola
 */
public class ReadWriteSave {

    /**
     * Read the saved file and return with it.
     *
     * @param file the save file
     * @return the loaded Game object
     */
    public static Game readXML(File file) {

        InputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            JAXBContext jaxbContext = JAXBContext.newInstance(Game.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (Game) unmarshaller.unmarshal(fileInputStream);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadWriteSave.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JAXBException ex) {
            Logger.getLogger(ReadWriteSave.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(ReadWriteSave.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    /**
     * Saving the game to the specific folder.
     *
     * @param game the game that will be saved
     * @param fileName the name of the file where to be saved
     * @throws JAXBException
     */
    public static void writeXML(Game game, String fileName) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Game.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(game, new File(fileName));

    }

}
