/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.view;

import java.net.URL;
import java.util.ResourceBundle; 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Edidelson
 */
public class PrincipalController implements Initializable, ControlledScreen {
    
    ScreensController myController;
    
    /**
     * 
     * Initializes the controller class.
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
    }

    @FXML
    public void vaiParaReceitasDespesas(ActionEvent event) {
        myController.setScreen(ScreensFramework.receitasDespesas);
    }

    @FXML
    public void vaiParaLancamento(ActionEvent event) {
        myController.setScreen(ScreensFramework.lancamento);
    }
}
