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
@Table(name = "lancamento")
@NamedQueries({
    @NamedQuery(name = "Lancamento.getAll", query = "SELECT l FROM Lancamento l")
})
public class Lancamento implements Serializable, IModelo<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cp_lancamento")
    private Integer codigo;

    @Override
    public Integer getCodigo() {
        return codigo;
    }

    @Override
    public boolean isInativo() {
        return false;
    }

}
