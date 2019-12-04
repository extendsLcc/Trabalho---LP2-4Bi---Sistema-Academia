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
public class Aluno{

    private int idAluno = -1;
    private Date dataMatricula;
    private String nome;
    private String endereco;
    private String cidade;
    private String telefone;
    private Date nascimento;
    private float altura;
    private float peso;
    private Turma turma = null;
    private List<Turma> turmas = new LinkedList<>();

    public Aluno(){
    }

    public Aluno( Date dataMatricula, String nome, String endereco, String cidade, String telefone, Date nascimento, float altura, float peso, Turma turma ){

        this.dataMatricula = dataMatricula;
        this.nome = nome;
        this.endereco = endereco;
        this.cidade = cidade;
        this.telefone = telefone;
        this.nascimento = nascimento;
        this.altura = altura;
        this.peso = peso;
        this.turma = turma;

    }

    public void addTurma( Turma turma ){

        if( !containsTurma( turma ) ){

            turmas.add( turma );

        }

    }

    public boolean removeAluno( Turma turma ){

        if( turmas.contains( turma ) ){

            return turmas.remove( turma );

        }

        return false;

    }

    public boolean containsTurma( Turma turma ){

        boolean contains = false;

        if( turma.getIdTurma() == 0 ){

            contains = turmas.stream().anyMatch( ( t ) -> t.equals( turma ) );

        }else{

            contains = turmas.stream().anyMatch( ( t ) -> t.getIdTurma() == turma.getIdTurma() );

        }

        return contains;

    }

    public List<Turma> getTurmas(){
        return turmas;
    }

    public void setTurmas( List<Turma> turmas ){
        this.turmas = turmas;
    }

    public int getIdAluno(){
        return idAluno;
    }

    public void setIdAluno( int idAluno ){
        this.idAluno = idAluno;
    }

    public Date getDataMatricula(){
        return dataMatricula;
    }

    public void setDataMatricula( Date dataMatricula ){
        this.dataMatricula = dataMatricula;
    }

    public String getNome(){
        return nome;
    }

    public void setNome( String nome ){
        this.nome = nome;
    }

    public String getEndereco(){
        return endereco;
    }

    public void setEndereco( String endereço ){
        this.endereco = endereço;
    }

    public String getCidade(){
        return cidade;
    }

    public void setCidade( String cidade ){
        this.cidade = cidade;
    }

    public String getTelefone(){
        return telefone;
    }

    public void setTelefone( String telefone ){
        this.telefone = telefone;
    }

    public Date getNascimento(){
        return nascimento;
    }

    public void setNascimento( Date nascimento ){
        this.nascimento = nascimento;
    }

    public float getAltura(){
        return altura;
    }

    public void setAltura( float altura ){
        this.altura = altura;
    }

    public float getPeso(){
        return peso;
    }

    public void setPeso( float peso ){
        this.peso = peso;
    }

    public Turma getTurma(){
        return turma;
    }

    public void setTurma( Turma turma ){
        this.turma = turma;
    }

}
