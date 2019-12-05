/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.ads.extendslcc.academia.bean;

import java.time.LocalTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author aluno
 */
public class Turma{

    private int idTurma = -1;
    private LocalTime horario;
    private LocalTime duracao;
    private Date dataInicio;
    private Date dataFim;
    private Atividade atividade;
    private Instrutor instrutor;
    private List<Aluno> alunos = new LinkedList<>();

    public Turma(){
    }

    public Turma( LocalTime horario, LocalTime duracao, Date dataInicio, Date dataFim, Atividade atividade, Instrutor instrutor ){
        this.horario = horario;
        this.duracao = duracao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.atividade = atividade;
        this.instrutor = instrutor;
    }

    public void addAluno( Aluno aluno ){
        
        if( !containsAluno( aluno ) ){
            
            alunos.add( aluno );
            
        }
        
    }
    
    public boolean removeAluno( Aluno aluno ){
        
        if( alunos.contains( aluno ) ){
            
            return alunos.remove(aluno );
            
        }
        
        return false;
        
    }

    public boolean containsAluno( Aluno aluno ){

        boolean contains = false;

        if( aluno.getIdAluno() == 0 ){

            contains = alunos.stream().anyMatch( ( alun ) -> alun.equals( aluno ) );

        }else{

            contains = alunos.stream().anyMatch( ( alun ) -> alun.getIdAluno() == aluno.getIdAluno() );

        }

        return contains;

    }

    public List<Aluno> getAlunos(){
        return alunos;
    }

    public void setAlunos( List<Aluno> alunos ){
        this.alunos = alunos;
    }
    
    public int getIdTurma(){
        return idTurma;
    }

    public void setIdTurma( int idTurma ){
        this.idTurma = idTurma;
    }

    public LocalTime getHorario(){
        return horario;
    }

    public void setHorario( LocalTime horario ){
        this.horario = horario;
    }

    public LocalTime getDuracao(){
        return duracao;
    }

    public void setDuracao( LocalTime duracao ){
        this.duracao = duracao;
    }

    public Date getDataInicio(){
        return dataInicio;
    }

    public void setDataInicio( Date dataInicio ){
        this.dataInicio = dataInicio;
    }

    public Date getDataFim(){
        return dataFim;
    }

    public void setDataFim( Date dataFim ){
        this.dataFim = dataFim;
    }

    public Atividade getAtividade(){
        return atividade;
    }

    public void setAtividade( Atividade atividade ){
        this.atividade = atividade;
    }

    public Instrutor getInstrutor(){
        return instrutor;
    }

    public void setInstrutor( Instrutor instrutor ){
        this.instrutor = instrutor;
    }

    @Override
    public String toString(){
        return "Turma " + this.getDataInicio().getYear() + " - " + this.getAtividade().getNome() + " | " + this.getInstrutor().getNome();
    }
    
}
