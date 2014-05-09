/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.tcc.dao;

import br.com.tcc.modelo.IModelo;
import br.com.tcc.modelo.Lancamento;
import java.util.List;

/**
 *
 * @author Edidelson
 */
public class DAOLancamento extends DAOGenerico{

    @Override
    public Lancamento consultar(int codigo) {
        return consultar(codigo);
    }

    @Override
    public Lancamento consultar(int codigo, boolean comInativo) {
        return (Lancamento) super.consultar(Lancamento.class, codigo, comInativo);
    }

    @Override
    public Lancamento consultarCodigo(String codigo, boolean comInativo) {
        return null;
    }

    @Override
    public List<Lancamento> getLista() {
       return super.getLista("Lancamento.getAll");
    }   
}