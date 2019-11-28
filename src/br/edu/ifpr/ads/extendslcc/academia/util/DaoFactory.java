/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpr.edu.adslcc.util;

import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author aluno
 */
public class DaoFactory {
    
    private static Connection con;
    
    public static boolean createConnection(){
    
        try{
            
            con = ConnectionFactory.createConnectionToMySQL();
            return true;
            
        }catch( SQLException ex ){
            
            ex.printStackTrace();
            JOptionPane.showMessageDialog( null, "Erro ao estabelecer conexão com o bando de dados" );
            return false;
            
        }
    
    }
    
    public static boolean destroyConnection(){
    
        try{
            
            con.close();
            return true;
            
        }catch( SQLException ex ){
            
            ex.printStackTrace();
            JOptionPane.showMessageDialog( null, "Erro ao fechar a conexão com o bando de dados" );
            return false;
            
        }
    
    }
    
}
