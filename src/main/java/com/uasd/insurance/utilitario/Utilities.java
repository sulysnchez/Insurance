/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uasd.insurance.utilitario;

import java.util.Date;
import javax.swing.JTable;

/**
 *
 * @author Sulenni
 */
public class Utilities {
    public static Integer CalcularEdad(Date fechaNacimiento){
       
        Date fechaActual = new Date();
        int difAno = fechaActual.getYear()-fechaNacimiento.getYear();
        int difMes = fechaActual.getMonth()-fechaNacimiento.getMonth();
        int difDia = fechaActual.getDay()-fechaNacimiento.getDay();
        if(difMes<0||(difMes==0&&difDia<0)){
            difAno-=1;
        }
        return difAno;
    }
    public static void SelectFirst(JTable table){
        table.setRowSelectionInterval(0, 0);
    }
}
