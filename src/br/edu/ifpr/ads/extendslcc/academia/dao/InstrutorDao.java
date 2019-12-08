/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.ads.extendslcc.academia.dao;

import br.edu.ifpr.ads.extendslcc.academia.bean.Instrutor;
import br.edu.ifpr.ads.extendslcc.academia.bean.Telefone;
import br.edu.ifpr.ads.extendslcc.academia.bean.Titulacao;
import br.edu.ifpr.ads.extendslcc.academia.bean.Turma;
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
public class InstrutorDao extends DefaultDao<Integer, Instrutor>{
    
    public InstrutorDao( Connection con ){
        super( con );
    }
    
    @Override
    public boolean create( Instrutor entity ){
        
        String sql = "INSERT INTO Instrutor "
                + "( rg, nome, nascimento, Titulacao_idTitulacao )"
                + " VALUES ( ?, ?, ?, ? )";
        
        try{
            
            PreparedStatement query = con.prepareStatement( sql, PreparedStatement.RETURN_GENERATED_KEYS );
            query.setString( 1, entity.getRg() );
            query.setString( 2, entity.getNome() );
            query.setDate( 3, new Date( entity.getNascimento().getTime() ) );
            
            if( entity.getTitulacao().getIdTitulacao() == -1 ){
                
                TitulacaoDao titulacaoDao = new TitulacaoDao( this.con );
                titulacaoDao.create( entity.getTitulacao() );
                
            }
            
            query.setInt( 4, entity.getTitulacao().getIdTitulacao() );
            
            query.executeUpdate();
            ResultSet rs = query.getGeneratedKeys();
            
            while( rs.next() ){
                
                entity.setIdInstrutor( rs.getInt( 1 ) );
                
            }
            
            List<Turma> turmas = entity.getTurmas();
            TurmaDao turmaDao = new TurmaDao( this.con );
            turmas.stream()
                    .filter( turma -> turma.getIdTurma() == -1 )
                    .forEach( turma -> turmaDao.create( turma ) );
            
            List<Telefone> telefones = entity.getTelefones();
            TelefoneDao telefoneDao = new TelefoneDao( this.con );
            telefones.stream()
                    .filter( telefone -> telefone.getIdTelefone() == -1 )
                    .forEach( (telefone) -> {telefoneDao.create( telefone ); System.out.println( "Creating telefone: " + telefone + " from instrutor " + entity );} );
            
            query.close();
            
            return true;
            
        }catch( SQLException e ){
            
            System.out.println( "SQL exception occured - INSERT Instrutor " + e );
            
        }
        
        return false;
        
    }
    
    @Override
    public Instrutor retrieve( Integer primaryKey ){
        
        Instrutor instrutor = null;
        String sql = "SELECT * FROM Instrutor WHERE idInstrutor = ?";
        
        try{
            
            PreparedStatement query = this.con.prepareStatement( sql );
            query.setInt( 1, primaryKey );
            
            ResultSet rs = query.executeQuery();
            
            while( rs.next() ){
                
                instrutor = new Instrutor();
                instrutor.setIdInstrutor( rs.getInt( "idInstrutor" ) );
                instrutor.setRg( rs.getString( "rg" ) );
                instrutor.setNome( rs.getString( "nome" ) );
                instrutor.setNascimento( rs.getDate( "nascimento" ) );
                
                TurmaDao turmaDao = new TurmaDao( this.con );
                List<Turma> turmas = turmaDao.findByInstrutor( instrutor );
                final Instrutor instruc = instrutor;
                turmas.forEach( turma -> turma.setInstrutor( instruc ) );
                instrutor.setTurmas( turmas );
                
                TelefoneDao telefoneDao = new TelefoneDao( this.con );
                List<Telefone> telefones = telefoneDao.findByInstrutor( instrutor );
                telefones.forEach( telefone -> telefone.setInstrutor( instruc ) );
                instrutor.setTelefones( telefones );
                
            }
            
            query.close();
            
        }catch( SQLException ex ){
            
            System.out.println( "SQL exception occured - SELECT Instrutor " + ex );
            
        }
        
        return instrutor;
        
    }
    
    @Override
    public boolean update( Instrutor entity ){
        
        String sql = "UPDATE Instrutor SET rg = ?, nome = ?, nascimento = ?,"
                + " Titulacao_idTitulacao = ? WHERE idInstrutor = ?";
        
        try{
            
            PreparedStatement query = con.prepareStatement( sql );
            query.setString( 1, entity.getRg() );
            query.setString( 2, entity.getNome() );
            query.setDate( 3, new Date( entity.getNascimento().getTime() ) );
            query.setInt( 4, entity.getTitulacao().getIdTitulacao() );
            query.setInt( 5, entity.getIdInstrutor() );
            
            TelefoneDao telefoneDao = new TelefoneDao(con );
            entity.getTelefones().stream()
                    .filter( tel -> tel.getIdTelefone() == -1 )
                    .forEach( tel -> telefoneDao.create( tel ));
            System.out.println( "Updating Telefones" );
            entity.getTelefones().stream()
                    .filter( tel -> tel.getIdTelefone() != -1 )
                    .forEach( tel -> telefoneDao.update( tel ));
            
            query.executeUpdate();
            query.close();
            
            return true;
            
        }catch( SQLException ex ){
            
            System.out.println( "#" );
            ex.printStackTrace();
            System.out.println( "SQL exception occured - UPDATE Instrutor " + ex );
            
        }
        
        return false;
        
    }
    
    @Override
    public boolean delete( Instrutor entity ){
        
        String sql = "DELETE FROM Instrutor WHERE idInstrutor = ?";
        
        try{
            
            TelefoneDao telefoneDao = new TelefoneDao( con );
            entity.getTelefones().forEach( tel -> telefoneDao.delete( tel ) );
            
            PreparedStatement query = con.prepareStatement( sql );
            query.setInt( 1, entity.getIdInstrutor() );
            
            query.executeUpdate();
            query.close();
            return true;
            
        }catch( SQLException ex ){
            
            System.out.println( "SQL exception occured - DELETE Instrutor " + ex );
            
        }
        
        return false;
        
    }
    
    @Override
    public List<Instrutor> findAll(){
        
        List<Instrutor> instrutores = new ArrayList<>();
        
        String sql = "SELECT * FROM Instrutor";
        
        try{
            Statement query = con.createStatement();
            ResultSet rs = query.executeQuery( sql );
            
            while( rs.next() ){
                
                Instrutor instrutor = new Instrutor();
                instrutor.setIdInstrutor( rs.getInt( "idInstrutor" ) );
                instrutor.setRg( rs.getString( "rg" ) );
                instrutor.setNome( rs.getString( "nome" ) );
                instrutor.setNascimento( rs.getDate( "nascimento" ) );
                
                TitulacaoDao titulacaoDao = new TitulacaoDao( this.con );
                Titulacao titulacao = titulacaoDao.retrieve( rs.getInt( "Titulacao_idTitulacao" ) );
                instrutor.setTitulacao( titulacao );
                
                TurmaDao turmaDao = new TurmaDao( con );
                List<Turma> turmas = turmaDao.findByInstrutor( instrutor );
                turmas.forEach( turma -> turma.setInstrutor( instrutor ) );
                instrutor.setTurmas( turmas );
                
                TelefoneDao telefoneDao = new TelefoneDao( this.con );
                List<Telefone> telefones = telefoneDao.findByInstrutor( instrutor );
                telefones.forEach( telefone -> telefone.setInstrutor( instrutor ) );
                instrutor.setTelefones( telefones );
                
                instrutores.add( instrutor );
                
            }
            
            query.close();
            
        }catch( SQLException ex ){
            
            System.out.println( "SQL exception occured - FIND ALL Instrutor " + ex );
            
        }
        
        return instrutores;
        
    }
    
    public List<Instrutor> findByTitulacao( Titulacao titulacao ){
        
        List<Instrutor> instrutores = new ArrayList<>();
        
        String sql = "SELECT * FROM Instrutor WHERE Titulacao_idTitulacao = ?";
        
        try{
            
            PreparedStatement query = con.prepareStatement( sql );
            query.setInt( 1, titulacao.getIdTitulacao() );
            ResultSet rs = query.executeQuery( sql );
            
            while( rs.next() ){
                
                Instrutor instrutor = new Instrutor();
                instrutor.setIdInstrutor( rs.getInt( "idInstrutor" ) );
                instrutor.setRg( rs.getString( "rg" ) );
                instrutor.setNome( rs.getString( "nome" ) );
                instrutor.setNascimento( rs.getDate( "nascimento" ) );
                instrutor.setTitulacao( titulacao );
                
                TurmaDao turmaDao = new TurmaDao( con );
                List<Turma> turmas = turmaDao.findByInstrutor( instrutor );
                final Instrutor instruct = instrutor;
                turmas.forEach( turma -> turma.setInstrutor( instruct ) );
                instrutor.setTurmas( turmas );
                
                TelefoneDao telefoneDao = new TelefoneDao( con );
                List<Telefone> telefones = telefoneDao.findByInstrutor( instrutor );
                telefones.forEach( telefone -> telefone.setInstrutor( instruct ) );
                instrutor.setTelefones( telefones );
                
                instrutores.add( instrutor );
                
            }
            
            query.close();
            
        }catch( SQLException ex ){
            
            System.out.println( "SQL exception occured - FIND Instrutor BY Titulacao " + ex );
            
        }
        
        return instrutores;
        
    }
    
}
