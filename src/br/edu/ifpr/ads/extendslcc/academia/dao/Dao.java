/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.ads.extendslcc.academia.dao;

import java.util.List;


/**
 *
 * @author aluno
 */
public interface Dao<PK, T> {
    
    public boolean create( T entity );
    public T retrieve( Integer primaryKey );
    public boolean update( T entity );
    public boolean delete( T entity );
    public List<T> findAll();
    
}
