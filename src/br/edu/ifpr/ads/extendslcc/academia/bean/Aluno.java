/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.ads.extendslcc.academia.bean;

import java.util.Date;

/**
 *
 * @author aluno
 */
public class Aluno {
    
    private int idAluno = -1;
    private Date dataMatricula;
    private String nome;
    private String endereco;
    private Cidade cidade;
    private String telefone;
    private Date nascimento;
    private double altura;
    private double peso;
    private Turma turma;

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

    public Cidade getCidade(){
        return cidade;
    }

    public void setCidade( Cidade cidade ){
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

    public double getAltura(){
        return altura;
    }

    public void setAltura( double altura ){
        this.altura = altura;
    }

    public double getPeso(){
        return peso;
    }

    public void setPeso( double peso ){
        this.peso = peso;
    }

    public Turma getTurma(){
        return turma;
    }

    public void setTurma( Turma turma ){
        this.turma = turma;
    }
    
}
