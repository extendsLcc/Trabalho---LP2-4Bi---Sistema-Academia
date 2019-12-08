/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.ads.extendslcc.academia.view.instrutor;

import br.edu.ifpr.ads.extendslcc.academia.bean.Telefone;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 *
 * @author LCC
 */
// COMBOCELL EDITOR
public class ComboTipoTelefone extends DefaultCellEditor{

    private DefaultComboBoxModel model;

    public ComboTipoTelefone(){
        super( new JComboBox() );
        this.model = (DefaultComboBoxModel) ( (JComboBox) getComponent() ).getModel();
        
        for( String tipo : Telefone.TIPOS_TELEFONE ){

            model.addElement( tipo );

        }
    }

    @Override
    public Component getTableCellEditorComponent( JTable table, Object value, boolean isSelected, int row, int column ){


        return super.getTableCellEditorComponent( table, value, isSelected, row, column );
    }
}
