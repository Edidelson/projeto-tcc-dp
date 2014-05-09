/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.view;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Edidelson
 */
public class ScreensFramework extends Application {

    public static String principal = "main";
    public static String principalFile = "FXMLDocument.fxml";
    public static String contasBancarias = "contasBancarias";
    public static String contasBancariasFile = "ContasBancarias.fxml";
    public static String receitasDespesas = "receitasDespesas";
    public static String receitasDespesasFile = "ReceitasDespesas.fxml";
    public static String lancamento = "lancamento";
    public static String lancamentoFile = "Lancamento.fxml";

    @Override
    public void start(Stage stage) throws Exception {
        ScreensController mainController = new ScreensController();
        mainController.loadScreen(ScreensFramework.principal, ScreensFramework.principalFile);
        mainController.loadScreen(ScreensFramework.contasBancarias, ScreensFramework.contasBancariasFile);
        mainController.loadScreen(ScreensFramework.receitasDespesas, ScreensFramework.receitasDespesasFile);
        mainController.loadScreen(ScreensFramework.lancamento, ScreensFramework.lancamentoFile);
        
        mainController.setScreen(ScreensFramework.principal);
        
        Group root = new Group();
        root.getChildren().addAll(mainController);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
