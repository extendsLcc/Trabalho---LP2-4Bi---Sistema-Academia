/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.ads.extendslcc.academia.view;


/**
 *
 * @author LCC
 */
public class WindowManagerOld{

    private static ViewProprietario windowProprietario = new ViewProprietario();
    private static ViewVeiculo windowVeiculo = new ViewVeiculo();
    private static ViewCategoria windowCategoria = new ViewCategoria();
    private static ViewMarca windowMarca = new ViewMarca();
    private static ViewEstado windowEstado = new ViewEstado();
    private static ViewCidade windowCidade = new ViewCidade();

    public static ViewProprietario getWindowProprietario(){
        return windowProprietario;
    }

    public static ViewVeiculo getWindowVeiculo(){
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
        
        WindowManagerOld.windowCategoria.setVisible( true );
        
    }

    public static void openWindowMarca(){
        
        WindowManagerOld.windowMarca.setVisible( true );
        
    }

    public static void openWindowEstado(){
        
        WindowManagerOld.windowEstado.setVisible( true );
        
    }

    public static void openWindowCidade(){
        
        WindowManagerOld.windowCidade.setVisible( true );
        
    }

}
