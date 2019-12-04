/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.ads.extendslcc.academia.util;

import br.edu.ifpr.ads.extendslcc.academia.bean.Aluno;
import br.edu.ifpr.ads.extendslcc.academia.dao.AlunoDao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author LCC
 */
public class MainTeste{
    
    public static void main( String[] args ) throws SQLException{
        
        Connection con = ConnectionFactory.createConnectionToMySQL();
        
        AlunoDao alunoDao = new AlunoDao(con );
        
        Aluno aluno = new Aluno( new Date(), "Lucas LCC", "address", "city", "tel", new Date( 1994, 9, 14 ), 1.65f, 65.5f, null );
        
        alunoDao.retrieve(1 );
        
    }
    
}
