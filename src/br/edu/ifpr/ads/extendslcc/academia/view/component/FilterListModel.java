/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.ads.extendslcc.academia.view.component;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.swing.DefaultListModel;

/**
 *
 * @author LCC
 */
public class FilterListModel<T> extends DefaultListModel<T>{
    
    private List<T> hiddenElements = new LinkedList<T>();

    public List<T> getHiddenElements(){
        return hiddenElements;
    }
    
    public void removeHiddenElement( T obj ){
        hiddenElements.remove( obj );
        removeElement( obj );
        fireIntervalRemoved( obj, 0, this.getSize() );
    }
    
    public FilterListModel( List<T> elements ){
        
        this.hiddenElements.addAll( elements );
        
        this.hiddenElements.forEach( e -> this.addElement( e ) );
        
    }
    
    public void filter( String matchingText ){

        // Poderia ser mais eficiente
        this.removeAllElements();
        matchingText.indexOf( matchingText );
        hiddenElements.stream()
                .filter( e -> e.toString().toLowerCase().contains( matchingText.toLowerCase() ) )
                .sorted( ( e, e2 ) -> {
                    return e.toString().indexOf( matchingText ); // < e2.toString().indexOf( matchingText );
                } )
                .forEach( e -> this.addElement( e ) );
        
    }
    
}
