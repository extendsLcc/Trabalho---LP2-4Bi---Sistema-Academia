/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.ads.extendslcc.academia.dao;

import br.edu.ifpr.ads.extendslcc.academia.bean.Aluno;
import br.edu.ifpr.ads.extendslcc.academia.bean.Matricula;
import br.edu.ifpr.ads.extendslcc.academia.bean.Turma;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author aluno
 */
public class AlunoDao extends DefaultDao<Integer, Aluno>{

    public AlunoDao( Connection con ){

        super( con );

    }

    @Override
    public boolean create( Aluno entity ){

        String sql = "INSERT INTO Aluno "
                + "( dataMatricula, nome, endereco, telefone, nascimento, altura, peso, Turma_idTurma )"
                + " VALUES ( ?, ?, ?, ?, ?, ?, ?, ? )";

        try{

            PreparedStatement query = con.prepareStatement( sql, PreparedStatement.RETURN_GENERATED_KEYS );

            prepareAlunoStatement( query, entity, 0 );

            query.executeUpdate();
            ResultSet rs = query.getGeneratedKeys();

            while( rs.next() ){

                entity.setIdAluno( rs.getInt( 1 ) );

            }

            query.close();

            TurmaDao turmaDao = new TurmaDao( con );
            MatriculaDao matriculaDao = new MatriculaDao( con );
            entity.getTurmas().forEach( ( turm ) -> {

                if( turm.getIdTurma() == -1 ){

                    turmaDao.create( turm );

                }

                matriculaDao.create( new Matricula( turm, entity ) );

            } );

            return true;

        }catch( SQLException e ){

            System.out.println( "SQL exception occured - INSERT Aluno " + e );

        }

        return false;

    }

    @Override
    public Aluno retrieve( Integer primaryKey ){

        Aluno aluno = null;
        String sql = "SELECT * FROM Aluno WHERE idAluno = ?";

        try{

            PreparedStatement query = con.prepareStatement( sql );
            query.setInt( 1, primaryKey );
            ResultSet rs = query.executeQuery();

            while( rs.next() ){

                aluno = this.getAlunoFromResultSet( rs );

            }

            query.close();

        }catch( Exception e ){

            System.out.println( "SQL exception occured - SELECT Aluno " + e );

        }

        return aluno;

    }

    @Override
    public boolean update( Aluno entity ){

        String sql = "UPDATE Aluno SET dataMatricula = ?, nome = ?, endereco = ?,"
                + " telefone = ?, nascimento = ?, altura = ?, peso = ?, Turma_idTurma = ?"
                + " WHERE idAluno = ?";

        try{

            PreparedStatement query = con.prepareStatement( sql );

            int i = prepareAlunoStatement( query, entity, 0 );
            
            query.setInt( ++i, entity.getIdAluno() );

            query.executeUpdate();
            query.close();

            return true;

        }catch( SQLException ex ){

            System.out.println( "SQL exception occured - UPDATE Aluno " + ex );

        }

        return false;

    }

    @Override
    public boolean delete( Aluno entity ){

        String sql = "DELETE FROM Aluno WHERE idAluno = ?";
        String sqlMatricula = "DELETE FROM Matricula WHERE Aluno_idAluno = ?";

        try{

            PreparedStatement queryMatricula = con.prepareStatement( sqlMatricula );
            queryMatricula.setInt( 1, entity.getIdAluno());

            queryMatricula.executeUpdate();
            queryMatricula.close();
            
            PreparedStatement queryAluno = con.prepareStatement( sql );
            queryAluno.setInt( 1, entity.getIdAluno() );

            queryAluno.executeUpdate();
            queryAluno.close();
            return true;

        }catch( SQLException ex ){

            System.out.println( "SQL exception occured - DELETE Aluno " + ex );

        }

        return false;

    }

    @Override
    public List<Aluno> findAll(){
        List<Aluno> alunos = new LinkedList<>();
        String sql = "SELECT * FROM Aluno";

        try{

            Statement query = con.createStatement();
            ResultSet rs = query.executeQuery( sql );

            while( rs.next() ){

                alunos.add( this.getAlunoFromResultSet( rs ) );

            }

            query.close();

        }catch( Exception e ){

            System.out.println( "SQL exception occured - FindAll Aluno " + e );

        }

        return alunos;

    }

    private Aluno getAlunoFromResultSet( ResultSet rs ) throws SQLException{

        Aluno aluno = new Aluno();
        aluno.setIdAluno( rs.getInt( "idAluno" ) );
        aluno.setDataMatricula( rs.getDate( "dataMatricula" ) );
        aluno.setNome( rs.getString( "nome" ) );
        aluno.setEndereco( rs.getString( "endereco" ) );
        aluno.setTelefone( rs.getString( "telefone" ) );
        aluno.setNascimento( rs.getDate( "nascimento" ) );
        aluno.setAltura( rs.getFloat( "altura" ) );
        aluno.setPeso( rs.getFloat( "peso" ) );

        int turmaId = rs.getInt( "Turma_idTurma" );

        if( turmaId != 0 ){

            TurmaDao turmaDao = new TurmaDao( this.con );
            aluno.setTurma( turmaDao.findTurmaMonitor( turmaId ) );

        }else{

            aluno.setTurma( null );

        }

        MatriculaDao matriculaDao = new MatriculaDao( con );
        List<Turma> turmas = matriculaDao.findTurmasByAluno( aluno );
        turmas.forEach(
                turma -> matriculaDao.findAlunosByTurma( turma )
                        .forEach(
                                alun -> turma.addAluno( alun )
                        )
        );

        /*
        MatriculaDao dao = new MatriculaDao(con);
        List<Turma> turmas = dao.findTurmasByAluno( aluno );
        for (int i = 0; i < turmas.size(); i++) {
            Turma turma = turmas.get(i);
            List<Aluno> alunos = dao.findAlunosByTurma(turma);
            for (int j = 0; j < alunos.size(); j++) {
                turma.addAluno(alunos.get(i));
            }
        }*/
        return aluno;

    }

    private int prepareAlunoStatement( PreparedStatement query, Aluno entity, int index ) throws SQLException{

        query.setDate( ++index, new Date( entity.getDataMatricula().getTime() ) );
        query.setString( ++index, entity.getNome() );
        query.setString( ++index, entity.getEndereco() );
        query.setString( ++index, entity.getTelefone() );
        query.setDate( ++index, new Date( entity.getNascimento().getTime() ) );
        query.setDouble( ++index, entity.getAltura() );
        query.setDouble( ++index, entity.getPeso() );

        if( entity.getTurma() != null ){

            if( entity.getTurma().getIdTurma() == -1 ){

                TurmaDao turmaDao = new TurmaDao( this.con );
                turmaDao.create( entity.getTurma() );

            }

            query.setInt( ++index, entity.getTurma().getIdTurma() );

        }else{

            query.setNull( ++index, Types.INTEGER );

        }

        return index;
        
    }

}
