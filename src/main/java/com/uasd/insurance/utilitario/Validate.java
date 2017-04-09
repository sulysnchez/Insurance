/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uasd.insurance.utilitario;

import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Sulenni
 */
public class Validate {
    public static boolean ValidateTextFields(JPanel panel){
        JPanel pn = panel;
        boolean empty = false;
        
        for (Component component : pn.getComponents()) {
            if (component instanceof JTextField){
                JTextField tf = (JTextField)component;
                
                if(tf.getText().isEmpty()){
                    empty = true;
                }
//                    JOptionPane.showMessageDialog(null, "No puede guardar el nombre en blanco.!.!. ");      
            }
        }
        
        return empty;
    }
    
    public static boolean ValidateComboBox(JPanel panel){
        JPanel pn = panel;
        boolean empty = false;
        
        for (Component component : pn.getComponents()) {
            if (component instanceof JComboBox){
                JComboBox bc = (JComboBox)component;
                
                Object[] comboSelected = (Object[]) bc.getSelectedItem();
                int keySelected = (int) comboSelected[0];
                
                if(keySelected!=0){
                    empty = true;
                } else{
                    bc.requestFocus();
                    break;
                }
//                    JOptionPane.showMessageDialog(null, "No puede guardar en blanco.!.!. ");      
            }
        }
        
        return empty;
    }
    
    public static boolean ValidateAll(JPanel panel){
        JPanel pn = panel;
        boolean empty = true;
        
        for (Component component : pn.getComponents()) {
            if (component instanceof JComboBox){
                JComboBox bc = (JComboBox)component;
                
                Object[] comboSelected = (Object[]) bc.getSelectedItem();
                int keySelected = (int) comboSelected[0];
                
                if(keySelected==0){
                    empty = false;
                    break;
                } 
            } else{
                if (component instanceof JTextField){
                    JTextField tf = (JTextField)component;
                
                    if(tf.getText().isEmpty()){
                        empty = false;
                        break;
                    }
                }
            }
        }       
        return empty;
    }
}
