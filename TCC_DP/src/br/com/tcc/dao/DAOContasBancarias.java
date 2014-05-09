/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.dao;

import br.com.tcc.modelo.ContasBancarias;
import br.com.tcc.modelo.IModelo;
import java.util.List;

/**
 *
 * @author Edidelson
 */
public class DAOContasBancarias extends DAOGenerico {

    @Override
    public ContasBancarias consultar(int codigo) {
        return consultar(codigo);
    }

    @Override
    public ContasBancarias consultar(int codigo, boolean comInativo) {
        return (ContasBancarias) super.consultar(ContasBancarias.class, codigo, comInativo);
    }

    @Override
    public <T extends IModelo> T consultarCodigo(String codigo, boolean comInativo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ContasBancarias> getLista() {
        return super.getLista("ContasBancarias.getAll");
    }

}
