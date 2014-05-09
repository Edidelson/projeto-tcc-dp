/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.tcc.dao;

import br.com.tcc.modelo.IModelo;
import com.zap.arca.event.IDataController;
import java.util.List;

/**
 *
 * @author Everton
 */
public interface IDAOGenerico extends IDataController {

    final String M_ADICIONAR = "Registro adicionado com sucesso";
    final String M_ALTERAR = "Registro alterado com sucesso";
    final String M_REMOVER = "Registro removido com sucesso";
    
    /**
     * Adiciona um objeto no banco de dados
     * @param m objeto que implemente IModelo
     */
    void adicionar(IModelo m);

    /**
     * Atera um objeto salvo no banco de dados
     * @param m objeto que implemente IModelo
     */
    void alterar(IModelo m);

    /**
     * Consulta um objeto armazenado no banco de dados pelo código
     * @param <T>
     * @param codigo código do objeto a consultar
     * @return um objeto que implemente IModelo
     * @deprecated substituído por consultar(int, boolean);
     */
    @Deprecated
    <T extends IModelo> T consultar(int codigo);

    /**
     * Consulta um objeto armazenado no banco de dados pelo código
     * @param <T>
     * @param codigo - Int - código do objeto a consultar
     * @param comInativo - boolean - Se TRUE retornará objetos inativos, se FALSE retornará NULL para esses objetos
     * @return um objeto que implemente IModelo resultante da consulta
     */
    <T extends IModelo> T consultar(int codigo, boolean comInativo);

     /**
     * Consulta um objeto armazenado no banco de dados pelo código
     * @param <T>
     * @param codigo - String - código do objeto a consultar
     * @param comInativo - boolean - Se TRUE retornará objetos inativos, se FALSE retornará NULL para esses objetos
     * @return um objeto que implemente IModelo resultante da consulta
     */
    <T extends IModelo> T consultarCodigo(String codigo, boolean comInativo);
    
    /**
     * Retorna uma lista com todos os objetos de um tipo amarzenados no banco de dados
     * @param <T>
     * @return List de objetos de um tipo que implemente IModelo
     */
    <T extends IModelo> List<T> getLista();

    /**
     * Remove um registro no banco de dados caso este já não esteja associado a outros registros
     * @param m objeto que implemente IModelo a ser removido
     */
    void remover(IModelo m);
}
