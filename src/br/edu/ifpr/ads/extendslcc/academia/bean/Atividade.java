/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.ads.extendslcc.academia.bean;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author aluno
 */
public class Atividade {
    
    private int idAtividade = -1;
    private String nome;
    private List<Turma> turmas = new LinkedList<>();

    public Atividade(){
    }
    
    public Atividade( String nome ){
        this.nome = nome;
    }

    
    public boolean addTurma( Turma turma ){

        if( !this.turmas.contains( turma ) ){

            this.turmas.add( turma );
            return true;
        }

        return false;

    }

    public boolean removeTurma( Turma turma ){

        if( this.turmas.contains( turma ) ){

            this.turmas.remove( turma );
            return true;
        }

        return false;

    }

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

    @Override
    public String toString(){
        return nome;
    }
    
}
