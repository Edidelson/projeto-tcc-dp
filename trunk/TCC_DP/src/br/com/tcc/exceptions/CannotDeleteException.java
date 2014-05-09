/*
 * Copyright (c) 1998-2012 Zap Informática, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Zap
 * Informática, Inc. ("Confidential Information"). You shall not disclose 
 * such Confidential Information and shall use it only in accordance with 
 * the terms of the license agreement you entered into with Zap Informática.
 */
package br.com.tcc.exceptions;

/**
 * Classe para tratar as exceções geradas pelo programa.<br><br>
 * @version    1.00, 07 Março 2012
 * @author     Edidelson Lucien
 */
public class CannotDeleteException extends RuntimeException {

    private String mensagem;
    
    public CannotDeleteException() {
        this.mensagem = "O registro não pode ser excluído, pois está associado a outro registro";
    }
    
    public CannotDeleteException(String mensagem) {
        this.mensagem = mensagem;
    }
    
    @Override
    public String getMessage() {
        return mensagem;
    }
}
