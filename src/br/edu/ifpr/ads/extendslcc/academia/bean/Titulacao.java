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
public class Titulacao implements Type{
    
    private int idTitulacao = -1;
    private String nome;

    public Titulacao(){
    }

    public Titulacao( String nome ){
        this.nome = nome;
    }

    public int getIdTitulacao(){
        return idTitulacao;
    }

    public void setIdTitulacao( int idTitulacao ){
        this.idTitulacao = idTitulacao;
    }

    public String getNome(){
        return nome;
    }

    public void setNome( String nome ){
        this.nome = nome;
    }

    @Override
    public String toString(){
        return nome;
    }

    @Override
    public void setId( int id ) {

        this.setIdTitulacao(id);
        
    }

    @Override
    public int getId() {

        return this.getIdTitulacao();
        
    }

}
