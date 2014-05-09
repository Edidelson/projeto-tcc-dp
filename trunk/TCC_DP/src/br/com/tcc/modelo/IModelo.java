/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.modelo;

/**
 * Interface responsável por internacionalizar os objetos
 *
 * @author Edidelson
 * @version IModelo 0.1, 22/04/14
 */
public interface IModelo<T> {

    public T getCodigo();
    public boolean isInativo();
}
