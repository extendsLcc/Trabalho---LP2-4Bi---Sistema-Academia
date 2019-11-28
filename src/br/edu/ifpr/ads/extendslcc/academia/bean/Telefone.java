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
    
    private int idTelefone = -1;
    private String numero;
    private String tipo;
    private Instrutor instrutor;
    
    public static String[] tipos = {
        "Residencial",
        "Celular",
        "Comercial",
        "Whastapp",
        "Recados",
        "Outros"
    };
    
}
