/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.tcc.dao;

import br.com.tcc.modelo.IModelo;
import br.com.tcc.modelo.ReceitasDespesas;
import java.util.List;

/**
 *
 * @author Edidelson
 */
public class DAOReceitasDespesas extends DAOGenerico{

    @Override
    public ReceitasDespesas consultar(int codigo) {
        return consultar(codigo);
    }

    @Override
    public ReceitasDespesas consultar(int codigo, boolean comInativo) {
        return (ReceitasDespesas) super.consultar(ReceitasDespesas.class, codigo, comInativo);
    }

    @Override
    public <T extends IModelo> T consultarCodigo(String codigo, boolean comInativo) {
    return null;
    }

    @Override
    public List<ReceitasDespesas> getLista() {
       return super.getLista("ReceitasDespesas.getAll");
    }
    
}
