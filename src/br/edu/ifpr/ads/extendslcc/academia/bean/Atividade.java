/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.ads.extendslcc.academia.bean;

/**
 *
 * @author aluno
 */
class Atividade {
    
    private int idAtividade;
    private String nome;

    public int getIdAtividade(){
        return idAtividade;
    }

    public void setIdAtividade( int idAtividade ){
        this.idAtividade = idAtividade;
    }

    public String getNome(){
        return nome;
    }

    public void setNome( String nome ){
        this.nome = nome;
    }
    
}
