/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.view;

import java.util.HashMap;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * Classe responsável por realizar a comunicação entre os screens
 *
 * @author Edidelson
 */
public class ScreensController extends StackPane {

    private HashMap<String, Node> screens = new HashMap<>();

    public ScreensController() {
        super();
    }

    /**
     * adiciona a tela para a collection
     *
     * @param nome
     * @param screen
     */
    public void addScreen(String nome, Node screen) {
        screens.put(nome, screen);
    }

    /**
     * retorna o Node com o nome da tela
     *
     * @param nome
     * @return
     */
    public Node getScreen(String nome) {
        return screens.get(nome);
    }

    /**
     * carrega o arquivo fxml, adicione a tela para a coleção e, finalmente,
     * injeta o screenPane ao controlador
     *
     * @param nome
     * @param resource
     * @return
     */
    public boolean loadScreen(String nome, String resource) {
        try {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resource));
            Parent loadParent = (Parent) myLoader.load();
            ControlledScreen controlledScreen = ((ControlledScreen) myLoader.getController());
            controlledScreen.setScreenParent(this);
            addScreen(nome, loadParent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Este método tenta exibir a tela com um nome pré-definido. Primeiro, ele
     * se a tela já foi carregado. Então, se aqui é mais do que um tela a nova
     * tela está sendo adicionado em segundo lugar, e em seguida, a tela atual é
     * removido. Se não houver qualquer tela que está sendo exibida, a nova tela
     * é apenas adicionado à raiz.
     *
     * @param nome
     * @return
     */
    public boolean setScreen(final String nome) {
        if (screens.get(nome) != null) { //tela carregada
            final DoubleProperty opacity = opacityProperty();

            if (!getChildren().isEmpty()) { //existe mais de uma tela?
                Timeline fade = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
                        new KeyFrame(new Duration(1000), new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent t) {
                                getChildren().remove(0);//remove a tela anterior
                                getChildren().add(0, screens.get(nome));// adiciona a nova tela
                                Timeline fadeIn = new Timeline(
                                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                                        new KeyFrame(new Duration(800), new KeyValue(opacity, 1.0)));
                                fadeIn.play();
                            }
                        }, new KeyValue(opacity, 0.0)));
                fade.play();
            } else {
                setOpacity(0.0);
                getChildren().add(screens.get(nome));//ninguém mais foi exibido, em seguida, basta mostrar
                Timeline fadeIn = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                        new KeyFrame(new Duration(2500), new KeyValue(opacity, 1.0)));
                fadeIn.play();
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * Este método irá remover a tela com o nome dado a partir da coleção de
     * telas
     *
     * @param nome
     * @return
     */
    public boolean unloadScreen(String nome) {
        if (screens.remove(nome) == null) {
            return false;
        } else {
            return true;
        }
    }
}
