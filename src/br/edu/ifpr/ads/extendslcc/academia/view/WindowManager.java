/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.ads.extendslcc.academia.view;

import br.ifpr.edu.adslcc.view.categoria.ViewCategoria;
import br.ifpr.edu.adslcc.view.cidade.ViewCidade;
import br.ifpr.edu.adslcc.view.estado.ViewEstado;
import br.ifpr.edu.adslcc.view.marca.ViewMarca;
import br.ifpr.edu.adslcc.view.proprietario.ViewProprietario;
import br.edu.ifpr.ads.extendslcc.academia.view.Atividade.ViewAtividade;

/**
 *
 * @author LCC
 */
public class WindowManager{

    private static ViewProprietario windowProprietario = new ViewProprietario();
    private static ViewAtividade windowVeiculo = new ViewAtividade();
    private static ViewCategoria windowCategoria = new ViewCategoria();
    private static ViewMarca windowMarca = new ViewMarca();
    private static ViewEstado windowEstado = new ViewEstado();
    private static ViewCidade windowCidade = new ViewCidade();

    public static ViewProprietario getWindowProprietario(){
        return windowProprietario;
    }

    public static ViewAtividade getWindowVeiculo(){
        return windowVeiculo;
    }

    public static ViewCategoria getWindowCategoria(){
        return windowCategoria;
    }

    public static ViewMarca getWindowMarca(){
        return windowMarca;
    }

    public static ViewEstado getWindowEstado(){
        return windowEstado;
    }

    public static ViewCidade getWindowCidade(){
        return windowCidade;
    }

    public static void openWindowProprietario(){
        
        windowProprietario.setVisible( true );
        
    }
    
    public static void openWindowVeiculo(){
        
        windowVeiculo.setVisible( true );
        
    }

    public static void openWindowCategoria(){
        
        WindowManager.windowCategoria.setVisible( true );
        
    }

    public static void openWindowMarca(){
        
        WindowManager.windowMarca.setVisible( true );
        
    }

    public static void openWindowEstado(){
        
        WindowManager.windowEstado.setVisible( true );
        
    }

    public static void openWindowCidade(){
        
        WindowManager.windowCidade.setVisible( true );
        
    }

}
