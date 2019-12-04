/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.ads.extendslcc.academia.dao;

import java.sql.Connection;
import java.util.List;

/**
 *
 * @author LCC
 */
public abstract class DefaultDao< PK, T > implements Dao< PK, T >{
    
    protected Connection con;

    public DefaultDao( Connection con ){
        this.con = con;
    }
    
    @Override
    public abstract boolean create( T entity );

    @Override
    public abstract T retrieve( Integer primaryKey );

    @Override
    public abstract boolean update( T entity );

    @Override
    public abstract boolean delete( T entity );

    @Override
    public abstract List<T> findAll();
    
}
