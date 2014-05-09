/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 *
 * @author Edidelson
 */
public class FXMLDocumentController implements Initializable, ControlledScreen {

    ScreensController myController;
    @FXML
    private Button btCadastroContasBancarias;

    /**
     *
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @FXML
    public void vaiParaContasBancarias(ActionEvent event) {
        myController.setScreen(ScreensFramework.contasBancarias);
        Popup popup = new Popup();
//        FXMLLoader myLoader = new FXMLLoader(getClass()
//                .getResource(ScreensFramework.contasBancariasFile));
//        try {
//            Parent loadParent = (Parent) myLoader.load();
//            Scene scene = new Scene(loadParent);
//            Stage stage = new Stage();
//            
//            stage.setScene(scene);
//            stage.show();
//           
//        } catch (IOException ex) {
//            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
    @FXML
    private void initializaTela(KeyEvent event){
         btCadastroContasBancarias.defaultButtonProperty();
         btCadastroContasBancarias.setClip(myController);
    }
}
