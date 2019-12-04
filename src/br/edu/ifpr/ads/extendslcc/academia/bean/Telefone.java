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
public class Telefone {
    
    public static final String[] TIPOS_TELEFONE = {
        "Residencial",
        "Celular",
        "Comercial",
        "Whastapp",
        "Recados",
        "Outros"
    };
    
    private int idTelefone = -1;
    private String numero;
    private int tipo;
    private Instrutor instrutor;

    public Telefone(){
    }

    public Telefone( String numero, int tipo, Instrutor instrutor ){
        this.numero = numero;
        this.tipo = tipo;
        this.instrutor = instrutor;
    }

    public String getNumero(){
        return numero;
    }

    public void setNumero( String numero ){
        this.numero = numero;
    }

    public int getIdTelefone(){
        return idTelefone;
    }

    public void setIdTelefone( int idTelefone ){
        this.idTelefone = idTelefone;
    }

    public int getTipo(){
        return tipo;
    }

    public void setTipo( int tipo ){
        this.tipo = tipo;
    }

    public Instrutor getInstrutor(){
        return instrutor;
    }

    public void setInstrutor( Instrutor instrutor ){
        this.instrutor = instrutor;
    }

    public String getTipoTelefone(){
        
        return Telefone.TIPOS_TELEFONE[tipo];
        
    }
    
}
