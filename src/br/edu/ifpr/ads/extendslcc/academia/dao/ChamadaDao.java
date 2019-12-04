/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.ads.extendslcc.academia.dao;

import br.edu.ifpr.ads.extendslcc.academia.bean.Chamada;
import br.edu.ifpr.ads.extendslcc.academia.bean.Instrutor;
import br.edu.ifpr.ads.extendslcc.academia.bean.Matricula;
import java.sql.Connection;
import java.sql.Date;
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
public class ChamadaDao extends DefaultDao<Integer, Chamada>{

    public ChamadaDao( Connection con ){
        super( con );
    }

    @Override
    public boolean create( Chamada entity ){

        String sql = "INSERT INTO Chamada ( data, presente, Matricula_Turma_idTurma, Matricula_Aluno_idAluno) "
                + "VALUES ( ?, ?, ?, ? )";

        try{

            PreparedStatement query = con.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );

            prepareChamadaStatement( query, entity, 0 );

            query.executeUpdate();
            ResultSet rs = query.getGeneratedKeys();

            if( rs.next() ){
                entity.setIdChamada( rs.getInt( 1 ) );
            }

            query.close();
            return true;

        }catch( SQLException ex ){

            System.out.println( "SQL exception occured - INSERT Chamada" + ex );

        }

        return false;

    }

    @Override
    public Chamada retrieve( Integer primaryKey ){

        Chamada chamada = null;
        String sql = "SELECT * FROM Chamada WHERE idChamada = ?";

        try{

            PreparedStatement query = con.prepareStatement( sql );
            query.setInt( 1, primaryKey );

            ResultSet rs = query.executeQuery();

            while( rs.next() ){

                chamada = getChamadaFromResultSet( rs );

            }

            query.close();

        }catch( SQLException ex ){

            System.out.println( "SQL exception occured - SELECT Chamada" + ex );

        }

        return chamada;

    }

    @Override
    public boolean update( Chamada entity ){
        
        // Não deve ser alterado a referencia de qual matricula a chamada pertence
        String sql = "UPDATE Chamada SET data = ?, presente = ?  WHERE idChamada = ?";

        try{

            PreparedStatement query = con.prepareStatement( sql );
            
            int i = prepareChamadaStatement( query, entity, 0 );
            
            query.setInt( ++i, entity.getIdChamada() );

            query.executeUpdate();

            query.close();
            return true;

        }catch( SQLException ex ){

            System.out.println( "SQL exception occured - UPDATE Chamada" + ex );

        }

        return false;

    }

    @Override
    public boolean delete( Chamada entity ){

        String sql = "DELETE FROM Chamada WHERE idChamada = ?";

        try{
            PreparedStatement query = con.prepareStatement( sql );
            query.setInt( 1, entity.getIdChamada() );

            query.executeUpdate();
            query.close();
            return true;

        }catch( SQLException ex ){

            System.out.println( "SQL exception occured - DELETE Chamada" + ex );

        }

        return false;
    }

    List<Chamada> findByMatricula( Matricula matricula ){

        List<Chamada> chamadas = new ArrayList<>();

        String sql = "SELECT * FROM Chamada "
                + "WHERE Matricula_Turma_idTurma = ? AND Matricula_Aluno_idAluno = ?";

        try{

            PreparedStatement query = con.prepareStatement( sql );
            query.setInt( 1, matricula.getTurma().getIdTurma());
            query.setInt( 2, matricula.getAluno().getIdAluno() );

            ResultSet rs = query.executeQuery();

            while( rs.next() ){

                Chamada chamada = getChamadaFromResultSet( rs );

                chamadas.add( chamada );

            }

            query.close();

        }catch( SQLException ex ){

            System.out.println( "SQL exception occured - FIND Chamada BY Matricula" + ex );

        }

        return chamadas;

    }

    @Override
    public List<Chamada> findAll(){

        List<Chamada> chamadas = new ArrayList<>();
        String sql = "SELECT * FROM Chamada";

        try{

            Statement query = con.createStatement();
            ResultSet rs = query.executeQuery( sql );

            while( rs.next() ){

                Chamada chamada = getChamadaFromResultSet( rs );

                chamadas.add( chamada );

            }

            query.close();

        }catch( SQLException ex ){

            System.out.println( "SQL exception occured - FIND ALL Chamada" + ex );

        }

        return chamadas;

    }

    private Chamada getChamadaFromResultSet( ResultSet rs ) throws SQLException{

        Chamada chamada = new Chamada();
        chamada.setIdChamada( rs.getInt( "idChamada" ) );
        chamada.setData( rs.getDate( "data" ) );
        chamada.setPresente( rs.getBoolean( "presente" ) );

        MatriculaDao matriculaDao = new MatriculaDao( con );
        chamada.setMatricula( matriculaDao.retrieveByAlunoTurma( rs.getInt( "Matricula_Turma_idTurma" ), rs.getInt( "Matricula_Aluno_idAluno" ) ) );

        return chamada;

    }

    private int prepareChamadaStatement( PreparedStatement query, Chamada entity, int i ) throws SQLException{

        query.setDate( ++i, new Date( entity.getData().getTime() ) );
        query.setBoolean( ++i, entity.isPresente() );

        query.setInt( ++i, entity.getMatricula().getTurma().getIdTurma() );
        query.setInt( ++i, entity.getMatricula().getAluno().getIdAluno() );
        
        return i;

    }

}
