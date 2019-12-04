/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.ads.extendslcc.academia.dao;

import br.edu.ifpr.ads.extendslcc.academia.bean.Atividade;
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
public class AtividadeDao extends DefaultDao<Integer, Atividade>{
    
    public AtividadeDao( Connection con ){
        super( con );
    }

    @Override
    public boolean create( Atividade entity ){

        String sql = "INSERT INTO Atividade "
                + "( nome )"
                + " VALUES ( ? )";

        try{

            PreparedStatement query = con.prepareStatement( sql, PreparedStatement.RETURN_GENERATED_KEYS );
            query.setString( 1, entity.getNome());

            query.executeUpdate();
            ResultSet rs = query.getGeneratedKeys();

            while( rs.next() ){

                entity.setIdAtividade(rs.getInt( 1 ) );

            }

            query.close();
            return true;

        }catch( SQLException e ){

            System.out.println( "SQL exception occured - INSERT Atividade " + e );

        }

        return false;

    }

    @Override
    public Atividade retrieve( Integer primaryKey ){

        Atividade atividade = null;
        String sql = "SELECT * FROM Atividade WHERE idAtividade= ?";

        try{

            PreparedStatement query = this.con.prepareStatement( sql );
            query.setInt( 1, primaryKey );

            ResultSet rs = query.executeQuery();

            while( rs.next() ){

                atividade = new Atividade();
                atividade.setNome( rs.getString( "nome" ) );

            }

            query.close();

        }catch( SQLException ex ){

            System.out.println( "SQL exception occured - SELECT Atividade " + ex );

        }

        return atividade;

    }

    @Override
    public boolean update( Atividade entity ){

        String sql = "UPDATE Atividade SET nome = ? WHERE idAtividade = ?";

        try{

            PreparedStatement query = con.prepareStatement( sql );
            query.setString( 1, entity.getNome() );

            query.executeUpdate();
            query.close();

            return true;

        }catch( SQLException ex ){

            System.out.println( "SQL exception occured - UPDATE Atividade " + ex );

        }

        return false;

    }

    @Override
    public boolean delete( Atividade entity ){

        String sql = "DELETE FROM Atividade WHERE idAtividade = ?";

        try{

            PreparedStatement query = con.prepareStatement( sql );
            query.setInt( 1, entity.getIdAtividade());

            query.executeUpdate();
            query.close();
            return true;

        }catch( SQLException ex ){

            System.out.println( "SQL exception occured - DELETE Atividade " + ex );

        }

        return false;

    }

    @Override
    public List<Atividade> findAll(){

        List<Atividade> titulacoes = new ArrayList<>();

        String sql = "SELECT * FROM Atividade";

        try{
            
            Statement query = con.createStatement();
            ResultSet rs = query.executeQuery( sql );

            while( rs.next() ){
                
                Atividade atividade = new Atividade();
                atividade.setIdAtividade(rs.getInt( "idAtividade" ) );
                atividade.setNome( rs.getString( "nome" ) );
                titulacoes.add( atividade );
                
            }
            
            query.close();

        }catch( SQLException ex ){
            
            System.out.println( "SQL exception occured - FIND ALL Atividade " + ex );
            
        }
        
        return titulacoes;

    }
    
}
