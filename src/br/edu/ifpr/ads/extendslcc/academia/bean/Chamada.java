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
public class Chamada {
    
    private int idChamada;
    private Date data;
    private boolean presente;
    private Matricula matricula;

    public int getIdChamada(){
        return idChamada;
    }

    public void setIdChamada( int idChamada ){
        this.idChamada = idChamada;
    }

    public Date getData(){
        return data;
    }

    public void setData( Date data ){
        this.data = data;
    }

    public boolean isPresente(){
        return presente;
    }

    public void setPresente( boolean presente ){
        this.presente = presente;
    }

    public Matricula getMatricula(){
        return matricula;
    }

    public void setMatricula( Matricula matricula ){
        this.matricula = matricula;
    }
    
}
