/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.ads.extendslcc.academia.view.instrutor;

import br.edu.ifpr.ads.extendslcc.academia.bean.Telefone;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author LCC
 */
public class TelefoneTableModel extends AbstractTableModel{

    private final String[] column = { "Número", "Tipo" };
    private List<Telefone> telefones;
    private JTable table;

    public TelefoneTableModel( JTable table ){
        this.telefones = new ArrayList<>();
        this.table = table;
    }

    public void addRow( Telefone c ){

        this.telefones.add( c );
        this.fireTableDataChanged();
    }

    public Telefone getTelefone( int row ){
        return this.telefones.get( row );
    }

    public void removeRow( int row ){
        this.telefones.remove( row );
        this.fireTableDataChanged();
    }

    public void removeTelefone( Telefone tel ){
        this.telefones.remove( tel );
        this.fireTableDataChanged();
    }

    public void addAll( List<Telefone> telefones ){

        this.telefones.addAll( telefones );
        this.fireTableDataChanged();

    }

    public void clear(){

        this.telefones.clear();
        this.fireTableDataChanged();

    }

    @Override
    public String getColumnName( int column ){
        return this.column[column];
    }

    @Override
    public int getRowCount(){
        return telefones.size();
    }

    @Override
    public int getColumnCount(){
        return column.length;
    }

    public List<Telefone> getTelefones(){

        return telefones;

    }

    @Override
    public boolean isCellEditable( int rowIndex, int columnIndex ){
        return table.isEnabled();
    }

    @Override
    public Object getValueAt( int row, int column ){
        switch( column ){
            case 0:
                return telefones.get( row ).getNumero();
            case 1:
                return telefones.get( row ).getTipo();
        }
        return null;
    }

    @Override
    public void setValueAt( Object valor, int linha, int coluna ){
        switch( coluna ){
            case 0:
                telefones.get( linha ).setNumero( (String) valor );
                break;
            case 1:
                try{
                    telefones.get( linha ).setTipo( (String) valor );
                }catch( Exception e ){
                }
                break;
        }
        this.fireTableRowsUpdated( linha, linha );
    }

}
