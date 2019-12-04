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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LCC
 */
public class MatriculaDao extends DefaultDao<Integer, Matricula>{

    public MatriculaDao( Connection con ){
        super( con );
    }

    @Override
    public boolean create( Matricula entity ){

        String sql = "INSERT INTO Matricula (Turma_idTurma, Aluno_idAluno) "
                + "SELECT ?, ? WHERE NOT EXISTS "
                + "(SELECT * FROM Matricula WHERE Turma_idTurma = ? "
                + "and Aluno_idAluno = ?)";

        try{
            PreparedStatement query = con.prepareStatement( sql );
            int index = 0; // helper
            query.setInt( ++index, entity.getTurma().getIdTurma() );
            query.setInt( ++index, entity.getAluno().getIdAluno() );
            query.setInt( ++index, entity.getTurma().getIdTurma() );
            query.setInt( ++index, entity.getAluno().getIdAluno() );

            query.executeUpdate();

            query.close();
            return true;

        }catch( SQLException ex ){

            System.out.println( "SQL exception occured - INSERT Matricula " + ex );

        }

        return false;

    }

    // NÃO UTILIZADO, USEAR FIND BY NO LUGAR
    @Override
    public Matricula retrieve( Integer primaryKey ){
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }
    

    // NÃO EXISTEM atributos dentro da tabela, não deve ser possivel alterar o fk das tabelas
    @Override
    public boolean update( Matricula entity ){
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete( Matricula entity ){
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public Matricula retrieveByAlunoTurma( Integer alunoFk, Integer turmaFk ){
    
        TurmaDao turmaDao = new TurmaDao(con );
        Turma turma = turmaDao.retrieve( turmaFk );
        
        AlunoDao alunoDao = new AlunoDao(con );
        Aluno aluno = alunoDao.retrieve( alunoFk );
        
        return new Matricula(turma, aluno );
        
    }
    
    public List<Turma> findTurmasByAluno( Aluno aluno ){

        List<Turma> turmas = new ArrayList<>();

        String sql = "SELECT Turma.* FROM Turma AS t LEFT JOIN Matricula AS m "
                + "ON t.idTurma = Turma_idTurma AND m.Aluno_idAluno = ?";

        try{

            PreparedStatement query = con.prepareStatement( sql );
            query.setInt( 1, aluno.getIdAluno() );
            ResultSet rs = query.executeQuery();

            while( rs.next() ){

                Turma turma = new Turma();
                turma.setIdTurma( rs.getInt( "idTurma" ) );
                turma.setHorario( rs.getTime( "horario" ).toLocalTime() );
                turma.setDuracao( rs.getTime( "duracao" ).toLocalTime() );
                turma.setDataInicio( rs.getDate( "dataInicio" ) );
                turma.setDataFim( rs.getDate( "dataFim" ) );

                int atividadeId = rs.getInt( "Atividade_idAtividade" );

                if( atividadeId != 0 ){

                    AtividadeDao atividadeDao = new AtividadeDao( this.con );
                    turma.setAtividade( atividadeDao.retrieve( atividadeId ) );

                }else{

                    turma.setAtividade( null );

                }

                int instrutorId = rs.getInt( "Instrutor_idInstrutor" );

                if( instrutorId != 0 ){

                    InstrutorDao instrutorDao = new InstrutorDao( this.con );
                    turma.setInstrutor( instrutorDao.retrieve( instrutorId ) );

                }else{

                    turma.setInstrutor( null );

                }

                aluno.addTurma( turma );
                turma.addAluno( aluno );

            }

            query.close();

        }catch( SQLException ex ){

            System.out.println( "SQL exception occured - FIND TURMA BY ALUNO Matricula " + ex );

        }

        return turmas;

    }

    public List<Aluno> findAlunosByTurma( Turma turma ){

        List<Aluno> alunos = new ArrayList<>();

        String sql = "SELECT Aluno.* FROM Aluno AS a LEFT JOIN Matricula AS m "
                + "ON a.idAluno = Aluno_idAluno AND m.Aluno_idAluno = ?";

        try{

            PreparedStatement query = con.prepareStatement( sql );
            query.setInt( 1, turma.getIdTurma());
            ResultSet rs = query.executeQuery();

            while( rs.next() ){

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
                    aluno.setTurma( turmaDao.retrieve( turmaId ) );

                }else{

                    aluno.setTurma( null );

                }

                turma.addAluno( aluno );
                aluno.addTurma( turma );

            }

            query.close();

        }catch( SQLException ex ){

            System.out.println( "SQL exception occured - FIND ALUNO BY TURMA Matricula " + ex );

        }

        return alunos;

    }

    @Override
    public List<Matricula> findAll(){
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

}
