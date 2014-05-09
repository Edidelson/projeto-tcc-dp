/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.tcc.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Edidelson
 */
@Entity
@Table(name="contas")
@NamedQueries({
    @NamedQuery(name = "ContasBancarias.getAll", query = "SELECT c FROM ContasBancarias c")
})
public class ContasBancarias implements Serializable, IModelo <Integer>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="cp_contas")
    private Integer codigo;
    @Column(name = "fg_inativo")
    private boolean inativo;

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public void setInativo(boolean inativo) {
        this.inativo = inativo;
    }
    
    @Override
    public Integer getCodigo() {
        return codigo;
    }

    @Override
    public boolean isInativo() {
        return inativo;
    }
}
