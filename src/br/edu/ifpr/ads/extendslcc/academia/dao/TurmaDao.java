/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.ads.extendslcc.academia.dao;

import br.edu.ifpr.ads.extendslcc.academia.bean.Aluno;
import br.edu.ifpr.ads.extendslcc.academia.bean.Instrutor;
import br.edu.ifpr.ads.extendslcc.academia.bean.Matricula;
import br.edu.ifpr.ads.extendslcc.academia.bean.Turma;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author aluno
 */
public class TurmaDao extends DefaultDao< Integer, Turma>{

    public TurmaDao( Connection con ){
        super( con );
    }

    @Override
    public boolean create( Turma entity ){

        String sql = "INSERT INTO Turma "
                + "( horario, duracao, dataInicio, dataFim, Atividade_idAtividade, Instrutor_idInstrutor )"
                + " VALUES ( ?, ?, ?, ?, ?, ? )";

        try{

            PreparedStatement query = con.prepareStatement( sql, PreparedStatement.RETURN_GENERATED_KEYS );

            prepareTurmaStatement( query, entity, 0 );

            query.executeUpdate();
            ResultSet rs = query.getGeneratedKeys();

            while( rs.next() ){

                entity.setIdTurma( rs.getInt( 1 ) );

            }

            query.close();

            AlunoDao alunoDao = new AlunoDao( con );
            MatriculaDao matriculaDao = new MatriculaDao( con );
            entity.getAlunos().forEach( ( alun ) -> {

                if( alun.getIdAluno() == -1 ){

                    alunoDao.create( alun );

                }

                matriculaDao.create( new Matricula( entity, alun ) );

            } );

            return true;

        }catch( SQLException e ){

            System.out.println( "SQL exception occured - INSERT Turma " + e );

        }

        return false;

    }

    @Override
    public Turma retrieve( Integer primaryKey ){

        Turma turma = null;
        String sql = "SELECT * FROM Turma WHERE idTurma = ?";

        try{

            PreparedStatement query = con.prepareStatement( sql );
            query.setInt( 1, primaryKey );
            ResultSet rs = query.executeQuery();

            while( rs.next() ){

                turma = this.getTurmaFromResultSet( rs );

            }

            query.close();

        }catch( Exception e ){

            System.out.println( "SQL exception occured - SELECT Turma " + e );

        }

        return turma;

    }

    @Override
    public boolean update( Turma entity ){

        String sql = "UPDATE Turma SET horario = ?, duracao = ?, dataInicio = ?,"
                + " dataFim = ?, Atividade_idAtividade = ?, Instrutor_idInstrutor = ?"
                + " WHERE idTurma = ?";

        try{

            PreparedStatement query = con.prepareStatement( sql );

            int i = prepareTurmaStatement( query, entity, 0 );

            query.setInt( ++i, entity.getIdTurma() );

            query.executeUpdate();
            query.close();

            return true;

        }catch( SQLException ex ){

            System.out.println( "SQL exception occured - UPDATE Turma " + ex );

        }

        return false;

    }

    @Override
    public boolean delete( Turma entity ){

        String sql = "DELETE FROM Turma WHERE idTurma = ?";
        String sqlMatricula = "DELETE FROM Matricula WHERE Turma_idTurma = ?";

        try{

            PreparedStatement queryMatricula = con.prepareStatement( sqlMatricula );
            queryMatricula.setInt( 1, entity.getIdTurma() );

            queryMatricula.executeUpdate();
            queryMatricula.close();

            PreparedStatement queryTurma = con.prepareStatement( sql );
            queryTurma.setInt( 1, entity.getIdTurma() );

            queryTurma.executeUpdate();
            queryTurma.close();
            return true;

        }catch( SQLException ex ){

            System.out.println( "SQL exception occured - DELETE Turma " + ex );

        }

        return false;

    }

    @Override
    public List<Turma> findAll(){
        List<Turma> turmas = new LinkedList<>();
        String sql = "SELECT * FROM Turma";

        try{

            Statement query = con.createStatement();
            ResultSet rs = query.executeQuery( sql );

            while( rs.next() ){

                turmas.add( this.getTurmaFromResultSet( rs ) );

            }

            query.close();

        }catch( Exception e ){

            System.out.println( "SQL exception occured - FindAll Turma " + e );

        }

        return turmas;

    }

    public Turma findTurmaMonitor( Integer pk ){

        Turma turma = null;
        String sql = "SELECT * FROM Turma WHERE idTurma = ?";

        try{

            PreparedStatement query = con.prepareStatement( sql );
            query.setInt( 1, pk );
            ResultSet rs = query.executeQuery();

            while( rs.next() ){

                turma = new Turma();
                turma.setIdTurma( rs.getInt( "idTurma" ) );
                turma.setHorario( rs.getTime( "horario" ).toLocalTime() );
                turma.setDuracao( rs.getTime( "duracao" ).toLocalTime() );
                turma.setDataInicio( rs.getDate( "dataInicio" ) );
                turma.setDataFim( rs.getDate( "dataFim" ) );

            }

            query.close();

        }catch( Exception e ){

            System.out.println( "SQL exception occured - SELECT Turma " + e );

        }

        return turma;

    }

    List<Turma> findByInstrutor( Instrutor instrutor ){

        List<Turma> turmas = new LinkedList<>();
        String sql = "SELECT * FROM Turma WHERE Instrutor_idInstrutor = ?";

        try{

            PreparedStatement query = con.prepareStatement( sql );
            query.setInt( 1, instrutor.getIdInstrutor() );
            ResultSet rs = query.executeQuery();

            while( rs.next() ){

                Turma turma = new Turma();
                turma.setIdTurma( rs.getInt( "idTurma" ) );
                turma.setHorario( rs.getTime( "horario" ).toLocalTime() );
                turma.setDuracao( rs.getTime( "duracao" ).toLocalTime() );
                turma.setDataInicio( rs.getDate( "dataInicio" ) );
                turma.setDataFim( rs.getDate( "dataFim" ) );

                int atividadeId = rs.getInt( "Atividade_idAtividade" );
                AtividadeDao atividadeDao = new AtividadeDao( this.con );
                turma.setAtividade( atividadeDao.retrieve( atividadeId ) );

                turma.setInstrutor( instrutor );

                MatriculaDao matriculaDao = new MatriculaDao( con );
                System.out.println( "Turma Dao" );
                List<Aluno> alunos = matriculaDao.findAlunosByTurma( turma );
                alunos.forEach(
                        aluno -> matriculaDao.findTurmasByAluno( aluno )
                                .forEach(
                                        turm -> aluno.addTurma( turm )
                                )
                );

                turmas.add( turma );

            }

            query.close();

        }catch( Exception e ){

            System.out.println( "SQL exception occured - Find Turma By Instrutor  " + e );

        }

        return turmas;

    }

    private Turma getTurmaFromResultSet( ResultSet rs ) throws SQLException{

        Turma turma = new Turma();
        turma.setIdTurma( rs.getInt( "idTurma" ) );
        turma.setHorario( rs.getTime( "horario" ).toLocalTime() );
        turma.setDuracao( rs.getTime( "duracao" ).toLocalTime() );
        turma.setDataInicio( rs.getDate( "dataInicio" ) );
        turma.setDataFim( rs.getDate( "dataFim" ) );

        int atividadeId = rs.getInt( "Atividade_idAtividade" );
        AtividadeDao atividadeDao = new AtividadeDao( this.con );
        turma.setAtividade( atividadeDao.retrieve( atividadeId ) );

        int instrutorId = rs.getInt( "Instrutor_idInstrutor" );
        InstrutorDao instrutorDao = new InstrutorDao( this.con );
        turma.setInstrutor( instrutorDao.retrieve( instrutorId ) );

        MatriculaDao matriculaDao = new MatriculaDao( con );
        List<Aluno> alunos = matriculaDao.findAlunosByTurma( turma );
        alunos.forEach(
                aluno -> matriculaDao.findTurmasByAluno( aluno )
                        .forEach(
                                turm -> aluno.addTurma( turm )
                        )
        );

        return turma;

    }

    private int prepareTurmaStatement( PreparedStatement query, Turma entity, int index ) throws SQLException{

        query.setTime( ++index, Time.valueOf( entity.getDuracao() ) );
        query.setTime( ++index, Time.valueOf( entity.getDuracao() ) );
        query.setDate( ++index, new Date( entity.getDataInicio().getTime() ) );
        query.setDate( ++index, new Date( entity.getDataFim().getTime() ) );

        if( entity.getAtividade().getIdAtividade() == -1 ){

            AtividadeDao atividadeDao = new AtividadeDao( this.con );
            atividadeDao.create( entity.getAtividade() );

        }

        if( entity.getInstrutor().getIdInstrutor() == -1 ){

            InstrutorDao instrutorDao = new InstrutorDao( this.con );
            instrutorDao.create( entity.getInstrutor() );

        }

        query.setInt( ++index, entity.getAtividade().getIdAtividade() );
        query.setInt( ++index, entity.getInstrutor().getIdInstrutor() );

        return index;

    }

}
