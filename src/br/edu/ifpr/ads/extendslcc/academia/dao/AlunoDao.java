/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.ads.extendslcc.academia.dao;

import br.edu.ifpr.ads.extendslcc.academia.bean.Aluno;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author aluno
 */
public class AlunoDao implements Dao<Integer, Aluno> {

    private Connection con;

    public AlunoDao(Connection con) {
        this.con = con;
    }

    @Override
    public boolean create(Aluno entity) {
        
        String sql = "INSERT INTO aluno "
                + "( dataMatricula, nome, endereco, telefone, nascimento, altura, peso, Turma_id )"
                + " VALUES ( ?, ?, ?, ?, ?, ?, ?, ? )";

        try {

            PreparedStatement query = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS );
            query.setDate( 1, new Date( entity.getDataMatricula().getTime() ) );
            query.setString( 2, entity.getNome() );
            query.setString( 3, entity.getEndereco());
            query.setString( 4, entity.getTelefone() );
            query.setDate( 5, new Date( entity.getNascimento().getTime() ));
            query.setDouble( 6, entity.getAltura() );
            query.setDouble( 7, entity.getPeso());

            if ( entity.getTurma().getIdTurma() == -1 ) {
                
                TurmaDao turmaDao = new TurmaDao( this.con );
                turmaDao.create( entity.getTurma() );
                
            }
            
            query.setInt( 8, entity.getTurma().getIdTurma() );
            
            ResultSet rs = query.executeQuery();
            
            while ( rs.next() ) {

                entity.setIdAluno(rs.getInt( 1 ));
                
            }
            
            query.close();
            
            return true;
            
        } catch (SQLException e) {

            System.out.println("SQL exception occured - INSERT" + ex);

        }
        
        return false;
        
    }

    @Override
    public Aluno retrive(Integer primaryKey) {
        
        String sql = "SELECT * FROM aluno WHERE aluno.id = ?";
        
        try {
            
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt( 1, primaryKey );
            ResultSet rs = query.executeQuery();
            
            while ( rs.next() ) {

                Aluno aluno = new Aluno();
                aluno.setIdAluno( rs.getInt( "id" ) );
                aluno.setDataMatricula(rs.getDate("dataMatricula" ) );
                aluno.setNome( rs.getString("nome" ) );
                aluno.setEndereco(rs.getInt( "id" ) );
                aluno.setIdAluno( rs.getInt( "id" ) );
                aluno.setIdAluno( rs.getInt( "id" ) );
                
            }
            
        } catch (Exception e) {
        }

    }

    @Override
    public boolean update(Aluno entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Aluno entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Aluno> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
