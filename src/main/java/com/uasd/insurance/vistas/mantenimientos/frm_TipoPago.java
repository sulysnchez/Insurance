/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uasd.insurance.vistas.mantenimientos;

import com.itla.insurance.dao.AfiliadoDao;
import com.itla.insurance.dao.DB;
import com.itla.insurance.dto.AfiliadoDto;
import com.itla.insurance.dto.AnalisisDto;
import com.itla.insurance.dto.Tipo_PagoDto;
import com.uasd.insurance.utilitario.ReportGenerator;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 *
 * @author Sulenni
 */
public class frm_TipoPago extends javax.swing.JInternalFrame {
        AfiliadoDao afiliadoDao;
        Tipo_PagoDto tipoPagoDto;
    /**
     * Creates new form frm_TipoPago
     */
    public frm_TipoPago() throws Exception {
        initComponents();
        
        afiliadoDao = new AfiliadoDao();
        tipoPagoDto = new Tipo_PagoDto();
        
        
        jtblTipoPago.setModel(afiliadoDao.getModelTipoPago(afiliadoDao.GetAllTipoPago()));
        RedisenarTablaTipoPago();
        SeleccionarPrimero();
    }
    
    public void RedisenarTablaTipoPago(){
        //jtblAfiliado.setTableHeader(null);
        jtblTipoPago.removeColumn(jtblTipoPago.getColumnModel().getColumn(1));

    }
    
    public void LimpiarCampos(){
        jtxtNombre.setText("");
        jlblId.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtxtNombre = new javax.swing.JTextField();
        jlblId = new javax.swing.JLabel();
        jlblAnalisis = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jbttNuevo = new javax.swing.JButton();
        jbttGuardar = new javax.swing.JButton();
        jbttSalir = new javax.swing.JButton();
        jbttImprimir = new javax.swing.JButton();
        jbttNuevo1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblTipoPago = new javax.swing.JTable();
        jtxtBuscarTipoPago = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setBorder(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel1.setText("ID");

        jLabel2.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel2.setText("Nombre");

        jtxtNombre.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        jtxtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtNombreActionPerformed(evt);
            }
        });

        jlblId.setFont(new java.awt.Font("Palatino Linotype", 1, 18)); // NOI18N
        jlblId.setForeground(new java.awt.Color(153, 0, 0));

        jlblAnalisis.setFont(new java.awt.Font("Palatino Linotype", 2, 18)); // NOI18N
        jlblAnalisis.setText("FORMULARIO TIPO PAGO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jlblId, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 141, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jtxtNombre)
                                .addContainerGap())))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jlblAnalisis)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlblAnalisis)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblId, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtxtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(169, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jbttNuevo.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jbttNuevo.setText("Nuevo");
        jbttNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbttNuevoActionPerformed(evt);
            }
        });

        jbttGuardar.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jbttGuardar.setText("Guardar");
        jbttGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbttGuardarActionPerformed(evt);
            }
        });

        jbttSalir.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jbttSalir.setText("Salir");
        jbttSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbttSalirActionPerformed(evt);
            }
        });

        jbttImprimir.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jbttImprimir.setText("Imprimir");
        jbttImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbttImprimirActionPerformed(evt);
            }
        });

        jbttNuevo1.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jbttNuevo1.setText("Eliminar");
        jbttNuevo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbttNuevo1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbttImprimir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbttNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbttNuevo1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbttGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbttSalir)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 4, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbttGuardar)
                            .addComponent(jbttSalir)
                            .addComponent(jbttNuevo1)
                            .addComponent(jbttNuevo)))
                    .addComponent(jbttImprimir))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jtblTipoPago.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jtblTipoPago.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtblTipoPago.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtblTipoPagoMousePressed(evt);
            }
        });
        jtblTipoPago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtblTipoPagoKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jtblTipoPago);

        jtxtBuscarTipoPago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxtBuscarTipoPagoKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        jLabel4.setText("Consultas");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jtxtBuscarTipoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(0, 5, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtBuscarTipoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtNombreActionPerformed

    private void jbttNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbttNuevoActionPerformed
        
//            try {
//                jlblId.setText(afiliadoDao.GenerarIdAnalisis());
//            } catch (SQLException ex) {
//                Logger.getLogger(frm_Analisis.class.getName()).log(Level.SEVERE, null, ex);
//            }
            
            LimpiarCampos();
            
            
    }//GEN-LAST:event_jbttNuevoActionPerformed

    private void jbttGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbttGuardarActionPerformed
        for (Component component : jPanel1.getComponents()) {
            if (component instanceof JTextField){
                JTextField tf = (JTextField)component;
                if(tf.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "No puede guardar el nombre en blanco.!.!. ");
                } else{
                    
                    if (jlblId.getText().isEmpty()){
                        try {
                            tipoPagoDto.setNombre(jtxtNombre.getText());
                            afiliadoDao.insertTipoPago(tipoPagoDto);
                            } catch (Exception ex) {
                                Logger.getLogger(frm_TipoPago.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        try {
                            jtblTipoPago.setModel(afiliadoDao.getModelTipoPago(afiliadoDao.GetAllTipoPago()));
                        } catch (SQLException ex) {
                            Logger.getLogger(frm_TipoPago.class.getName()).log(Level.SEVERE, null, ex);
                        }
                            RedisenarTablaTipoPago();
                            SeleccionarPrimero();
                            jtblTipoPago.getSelectionModel().addSelectionInterval(0, 0);
                            jtxtNombre.setText((String) (jtblTipoPago.getModel().getValueAt(jtblTipoPago.getSelectedRow(), 0)));
                            jlblId.setText(Integer.toString((int) jtblTipoPago.getModel().getValueAt(jtblTipoPago.getSelectedRow(), 1)));
                   
                    } else {
                        try {
                            tipoPagoDto.setId(Integer.parseInt(jlblId.getText()));
                            tipoPagoDto.setNombre(jtxtNombre.getText());
                            afiliadoDao.updateTipoPago(tipoPagoDto);
                            
                            } catch (Exception ex) {
                                Logger.getLogger(frm_TipoPago.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        try {
                            jtblTipoPago.setModel(afiliadoDao.getModelTipoPago(afiliadoDao.GetAllTipoPago()));
                        } catch (SQLException ex) {
                            Logger.getLogger(frm_TipoPago.class.getName()).log(Level.SEVERE, null, ex);
                        }
                            RedisenarTablaTipoPago();
                            SeleccionarPrimero();
                    }                        
               
                }
            }
        }
        
    }//GEN-LAST:event_jbttGuardarActionPerformed

    private void jbttSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbttSalirActionPerformed
        dispose();
    }//GEN-LAST:event_jbttSalirActionPerformed

    private void jtblTipoPagoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblTipoPagoMousePressed
            
        PasarACampos();
        
    }//GEN-LAST:event_jtblTipoPagoMousePressed

    private void PasarACampos(){
        jtxtNombre.setText((String) (jtblTipoPago.getModel().getValueAt(jtblTipoPago.getSelectedRow(), 0)));
        jlblId.setText(Integer.toString((int) jtblTipoPago.getModel().getValueAt(jtblTipoPago.getSelectedRow(), 1)));
        
        tipoPagoDto.setId(Integer.parseInt(jlblId.getText()));
        tipoPagoDto.setNombre(jtxtNombre.getText());
    }
    private void jtxtBuscarTipoPagoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtBuscarTipoPagoKeyTyped

        
        try {
                jtblTipoPago.setModel(afiliadoDao.filtraModelTipoPago(jtxtBuscarTipoPago.getText()));
            } catch (SQLException ex) {
                Logger.getLogger(frm_TipoPago.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            RedisenarTablaTipoPago();
            SeleccionarPrimero();
    }//GEN-LAST:event_jtxtBuscarTipoPagoKeyTyped

    private void jbttImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbttImprimirActionPerformed
        //ReportGenerator.allAnalisis();
    }//GEN-LAST:event_jbttImprimirActionPerformed

    private void jbttNuevo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbttNuevo1ActionPerformed

        afiliadoDao.deleteTipoPago(tipoPagoDto);
        try {
            jtblTipoPago.setModel(afiliadoDao.getModelTipoPago(afiliadoDao.GetAllTipoPago()));
        
        } catch (SQLException ex) {
            Logger.getLogger(frm_Afiliado.class.getName()).log(Level.SEVERE, null, ex);
        }

        RedisenarTablaTipoPago();
        SeleccionarPrimero();
    }//GEN-LAST:event_jbttNuevo1ActionPerformed

    private void jtblTipoPagoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtblTipoPagoKeyReleased
        if(evt.getKeyCode()==40||evt.getKeyCode()==38){
            
            PasarACampos();
        }
    }//GEN-LAST:event_jtblTipoPagoKeyReleased

    private void SeleccionarPrimero(){
        try {
            if(jtblTipoPago.getModel().getValueAt(0, 0)!=null){
            jtblTipoPago.setRowSelectionInterval(0, 0);
            PasarACampos();
        }
        } catch (Exception e) {
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbttGuardar;
    private javax.swing.JButton jbttImprimir;
    private javax.swing.JButton jbttNuevo;
    private javax.swing.JButton jbttNuevo1;
    private javax.swing.JButton jbttSalir;
    private javax.swing.JLabel jlblAnalisis;
    private javax.swing.JLabel jlblId;
    private javax.swing.JTable jtblTipoPago;
    private javax.swing.JTextField jtxtBuscarTipoPago;
    private javax.swing.JTextField jtxtNombre;
    // End of variables declaration//GEN-END:variables
}
