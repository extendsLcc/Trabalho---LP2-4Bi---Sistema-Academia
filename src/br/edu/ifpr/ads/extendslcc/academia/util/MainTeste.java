/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.ads.extendslcc.academia.util;

import br.edu.ifpr.ads.extendslcc.academia.bean.Instrutor;
import br.edu.ifpr.ads.extendslcc.academia.bean.Telefone;
import br.edu.ifpr.ads.extendslcc.academia.bean.Titulacao;
import br.edu.ifpr.ads.extendslcc.academia.dao.InstrutorDao;
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

//        AlunoDao alunoDao = new AlunoDao(con );;
//        
//        Aluno aluno = new Aluno( new Date(), "Lucas LCC", "address", "city", "tel", new Date( 1994, 9, 14 ), 1.65f, 65.5f, null );
//        
//        alunoDao.retrieve(1 );
        InstrutorDao instrutorDao = new InstrutorDao( con );

        Titulacao titulacao = new Titulacao();
        titulacao.setId( 1 );
        titulacao.setNome( "TIT" );
        
        Instrutor instrutor = new Instrutor( "rg", "nome", new Date(), titulacao );

        Telefone[] tels = {
            new Telefone("123", "1", instrutor ),
            new Telefone("321", "2", instrutor )
        };
        
        instrutor.addTelefone( tels[0] );
        instrutor.addTelefone( tels[1] );
        
        instrutorDao.create( instrutor );
        
    }

}
