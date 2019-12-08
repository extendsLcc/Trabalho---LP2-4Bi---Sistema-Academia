/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.ads.extendslcc.academia.dao;

import br.edu.ifpr.ads.extendslcc.academia.bean.Instrutor;
import br.edu.ifpr.ads.extendslcc.academia.bean.Telefone;
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
public class TelefoneDao extends DefaultDao<Integer, Telefone>{

    public TelefoneDao( Connection con ){
        super( con );
    }

    @Override
    public boolean create( Telefone entity ){

        String sql = "INSERT INTO Telefone ( numero, tipo, Instrutor_idInstrutor ) "
                + "VALUES ( ?, ?, ? )";

        try{
            
            PreparedStatement query = con.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
            query.setString( 1, entity.getNumero() );
            query.setString( 2, entity.getTipo() );
            
            if( entity.getInstrutor().getIdInstrutor() == -1){
                
                InstrutorDao instrutorDao = new InstrutorDao( con );
                instrutorDao.create( entity.getInstrutor() );
                
            }
            
            query.setInt( 3, entity.getInstrutor().getIdInstrutor() );

            query.executeUpdate();
            ResultSet rs = query.getGeneratedKeys();

            if( rs.next() ){
                entity.setIdTelefone( rs.getInt( 1 ) );
            }

            query.close();
            return true;

        }catch( SQLException ex ){

            System.out.println( "SQL exception occured - INSERT Telefone" + ex );

        }

        return false;

    }

    @Override
    public Telefone retrieve( Integer primaryKey ){

        Telefone telefone = null;
        String sql = "SELECT * FROM Telefone WHERE idTelefone = ?";

        try{
            PreparedStatement query = con.prepareStatement( sql );
            query.setInt( 1, primaryKey );

            ResultSet rs = query.executeQuery();

            while( rs.next() ){

                telefone = new Telefone();
                telefone.setIdTelefone( rs.getInt( "idTelefone" ) );
                telefone.setNumero( rs.getString( "numero" ) );
                telefone.setTipo( rs.getString( "tipo" ) );

                InstrutorDao instrutorDao = new InstrutorDao( con );
                Instrutor instrutor = instrutorDao.retrieve( rs.getInt( "Instrutor_idInstrutor" ) );
                telefone.setInstrutor( instrutor );

            }

            query.close();

        }catch( SQLException ex ){

            System.out.println( "SQL exception occured - SELECT Telefone" + ex );

        }

        return telefone;

    }

    @Override
    public boolean update( Telefone entity ){

        String sql = "UPDATE Telefone SET numero = ?, tipo = ?, Instrutor_idInstrutor = ?  WHERE idTelefone = ?";

        try{

            PreparedStatement query = con.prepareStatement( sql );
            query.setString( 1, entity.getNumero() );
            query.setString( 2, entity.getTipo() );
            query.setInt( 3, entity.getInstrutor().getIdInstrutor() );
            query.setInt( 4, entity.getIdTelefone() );

            query.executeUpdate();

            query.close();
            return true;

        }catch( SQLException ex ){

            System.out.println( "SQL exception occured - UPDATE Telefone" + ex );

        }

        return false;

    }

    @Override
    public boolean delete( Telefone entity ){

        String sql = "DELETE FROM Telefone WHERE idTelefone = ?";

        try{
            PreparedStatement query = con.prepareStatement( sql );
            query.setInt( 1, entity.getIdTelefone() );

            query.executeUpdate();
            query.close();
            
            entity.getInstrutor().removeTelefone( entity );
            
            return true;

        }catch( SQLException ex ){

            System.out.println( "SQL exception occured - DELETE Telefone" + ex );

        }

        return false;
    }

    List<Telefone> findByInstrutor( Instrutor Instrutor ){

        List<Telefone> telefones = new ArrayList<>();

        String sql = "SELECT * FROM Telefone "
                + "WHERE Instrutor_idInstrutor = ?";

        try{

            PreparedStatement query = con.prepareStatement( sql );
            query.setInt( 1, Instrutor.getIdInstrutor() );

            ResultSet rs = query.executeQuery();

            while( rs.next() ){

                Telefone telefone = new Telefone();
                telefone.setIdTelefone( rs.getInt( "idTelefone" ) );
                telefone.setNumero( rs.getString( "numero" ) );
                telefone.setTipo( rs.getString( "tipo" ) );
                telefone.setInstrutor( Instrutor );
                        
                telefones.add( telefone );

            }

            query.close();

        }catch( SQLException ex ){

            System.out.println( "SQL exception occured - FIND Telefone BY Instrutor" + ex );

        }

        return telefones;

    }

    @Override
    public List<Telefone> findAll(){

        List<Telefone> telefones = new ArrayList<>();
        String sql = "SELECT * FROM Telefone";

        try{

            Statement query = con.createStatement();
            ResultSet rs = query.executeQuery( sql );

            while( rs.next() ){

                Telefone telefone = new Telefone();
                telefone.setIdTelefone( rs.getInt( "idTelefone" ) );
                telefone.setNumero( rs.getString( "numero" ) );
                telefone.setTipo( rs.getString( "tipo" ) );

                InstrutorDao instrutorDao = new InstrutorDao( con );
                Instrutor instrutor = instrutorDao.retrieve( rs.getInt( "Instrutor_idInstrutor" ) );
                telefone.setInstrutor( instrutor );

                telefones.add( telefone );

            }

            query.close();

        }catch( SQLException ex ){

            System.out.println( "SQL exception occured - FIND ALL Telefone" + ex );

        }

        return telefones;

    }

}
