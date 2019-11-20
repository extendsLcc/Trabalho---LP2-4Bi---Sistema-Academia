/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.ads.extendslcc.academia.view;

/**
 *  Uma inerface para definir as operações básicas de um controllador de entidades
 * 
 * @author aluno
 */
public interface Controller {
    /** 
     * 
     *  Responsavel criação da entidade no banco de dados
     * 
     * @return boolean Retorna se a operação foi concluida com sucesso ou não
     */
    boolean create();
    boolean update();
    boolean delete();
    boolean validateFields();
    
}
