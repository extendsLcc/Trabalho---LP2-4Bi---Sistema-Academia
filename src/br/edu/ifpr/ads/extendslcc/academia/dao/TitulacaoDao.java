/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.ads.extendslcc.academia.dao;

import br.edu.ifpr.ads.extendslcc.academia.bean.Titulacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LCC
 */
public class TitulacaoDao extends DefaultDao<Integer, Titulacao>{
    
    public TitulacaoDao( Connection con ){
        super( con );
    }

    @Override
    public boolean create( Titulacao entity ){

        String sql = "INSERT INTO Titulacao "
                + "( nome )"
                + " VALUES ( ? )";

        try{

            PreparedStatement query = con.prepareStatement( sql, PreparedStatement.RETURN_GENERATED_KEYS );
            query.setString( 1, entity.getNome());

            query.executeUpdate();
            ResultSet rs = query.getGeneratedKeys();

            while( rs.next() ){

                entity.setIdTitulacao(rs.getInt( 1 ) );

            }

            query.close();
            return true;

        }catch( SQLException e ){

            System.out.println( "SQL exception occured - INSERT Titulacao " + e );

        }

        return false;

    }

    @Override
    public Titulacao retrieve( Integer primaryKey ){

        Titulacao titulacao = null;
        String sql = "SELECT * FROM Titulacao WHERE idTitulacao= ?";

        try{

            PreparedStatement query = this.con.prepareStatement( sql );
            query.setInt( 1, primaryKey );

            ResultSet rs = query.executeQuery();

            while( rs.next() ){

                titulacao = new Titulacao();
                titulacao.setIdTitulacao( rs.getInt( "idTitulacao" ) );
                titulacao.setNome( rs.getString( "nome" ) );

            }

            query.close();

        }catch( SQLException ex ){

            System.out.println( "SQL exception occured - SELECT Titulacao " + ex );

        }

        return titulacao;

    }

    @Override
    public boolean update( Titulacao entity ){

        String sql = "UPDATE Titulacao SET nome = ? WHERE idTitulacao = ?";

        try{

            PreparedStatement query = con.prepareStatement( sql );
            query.setString( 1, entity.getNome() );
            query.setInt(2, entity.getIdTitulacao());

            query.executeUpdate();
            query.close();

            return true;

        }catch( SQLException ex ){

            System.out.println( "SQL exception occured - UPDATE Titulacao " + ex );

        }

        return false;

    }

    @Override
    public boolean delete( Titulacao entity ){

        String sql = "DELETE FROM Titulacao WHERE idTitulacao = ?";

        try{

            PreparedStatement query = con.prepareStatement( sql );
            query.setInt( 1, entity.getIdTitulacao());

            query.executeUpdate();
            query.close();
            return true;

        }catch( SQLException ex ){

            System.out.println( "SQL exception occured - DELETE Titulacao " + ex );

        }

        return false;

    }

    @Override
    public List<Titulacao> findAll(){

        List<Titulacao> titulacoes = new ArrayList<>();

        String sql = "SELECT * FROM Titulacao";

        try{
            
            Statement query = con.createStatement();
            ResultSet rs = query.executeQuery( sql );

            while( rs.next() ){
                
                Titulacao titulacao = new Titulacao();
                titulacao.setIdTitulacao(rs.getInt( "idTitulacao" ) );
                titulacao.setNome( rs.getString( "nome" ) );
                titulacoes.add( titulacao );
                
            }
            
            query.close();

        }catch( SQLException ex ){
            
            System.out.println( "SQL exception occured - FIND ALL Titulacao " + ex );
            
        }
        
        return titulacoes;

    }
    
}
