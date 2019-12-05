/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.ads.extendslcc.academia.bean;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author aluno
 */
public class Instrutor{

    private int idInstrutor = -1;
    private String rg;
    private String nome;
    private Date nascimento;
    private Titulacao titulacao;
    private List<Telefone> telefones = new LinkedList<>();
    private List<Turma> turmas = new LinkedList<>();

    public Instrutor(){
    }
    
    public Instrutor( String rg, String nome, Date nascimento, Titulacao titulacao ){
        this.rg = rg;
        this.nome = nome;
        this.nascimento = nascimento;
        this.titulacao = titulacao;
    }

    public boolean addTelefone( Telefone telefone ){

        if( !this.telefones.contains( telefone ) ){

            this.telefones.add( telefone );
            return true;
        }

        return false;

    }

    public boolean removeTelefone( Telefone telefone ){

        if( this.telefones.contains( telefone ) ){

            this.telefones.remove( telefone );
            return true;
        }

        return false;

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

    public List<Telefone> getTelefones(){
        return telefones;
    }

    public void setTelefones( List<Telefone> telefones ){
        this.telefones = telefones;
    }

    public List<Turma> getTurmas(){
        return turmas;
    }

    public void setTurmas( List<Turma> turmas ){
        this.turmas = turmas;
    }

    public int getIdInstrutor(){
        return idInstrutor;
    }

    public void setIdInstrutor( int idInstrutor ){
        this.idInstrutor = idInstrutor;
    }

    public String getRg(){
        return rg;
    }

    public void setRg( String rg ){
        this.rg = rg;
    }

    public String getNome(){
        return nome;
    }

    public void setNome( String nome ){
        this.nome = nome;
    }

    public Date getNascimento(){
        return nascimento;
    }

    public void setNascimento( Date nascimento ){
        this.nascimento = nascimento;
    }

    public Titulacao getTitulacao(){
        return titulacao;
    }

    public void setTitulacao( Titulacao titulacao ){
        this.titulacao = titulacao;
    }

    @Override
    public String toString(){
        return  nome;
    }

}
