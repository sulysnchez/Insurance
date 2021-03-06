/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uasd.insurance.vistas.mantenimientos.componentes;

import com.itla.insurance.dto.AfiliadoDto;

/**
 *
 * @author Sulenni
 */
public class pnlDependiente extends javax.swing.JPanel implements iPnlInternoAfiliado{

    /**
     * Creates new form pnlDependiente
     */
    public pnlDependiente() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnlAfiliacion = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jlblCedTitular = new javax.swing.JLabel();
        jlblParentezco = new javax.swing.JLabel();
        jtxtCedTitular = new javax.swing.JTextField();
        jcmbParentezco = new javax.swing.JComboBox<>();

        jpnlAfiliacion.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(204, 204, 204)));

        jLabel8.setText("Datos de la Afiliación");

        jlblCedTitular.setText("Cédula del Títular");

        jlblParentezco.setText("Parentezco");

        javax.swing.GroupLayout jpnlAfiliacionLayout = new javax.swing.GroupLayout(jpnlAfiliacion);
        jpnlAfiliacion.setLayout(jpnlAfiliacionLayout);
        jpnlAfiliacionLayout.setHorizontalGroup(
            jpnlAfiliacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlAfiliacionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnlAfiliacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlblCedTitular)
                    .addComponent(jlblParentezco))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnlAfiliacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtxtCedTitular)
                    .addComponent(jcmbParentezco, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jpnlAfiliacionLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel8)
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jpnlAfiliacionLayout.setVerticalGroup(
            jpnlAfiliacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlAfiliacionLayout.createSequentialGroup()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnlAfiliacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblCedTitular)
                    .addComponent(jtxtCedTitular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnlAfiliacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblParentezco)
                    .addComponent(jcmbParentezco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jpnlAfiliacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jpnlAfiliacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel8;
    private javax.swing.JComboBox<String> jcmbParentezco;
    private javax.swing.JLabel jlblCedTitular;
    private javax.swing.JLabel jlblParentezco;
    private javax.swing.JPanel jpnlAfiliacion;
    private javax.swing.JTextField jtxtCedTitular;
    // End of variables declaration//GEN-END:variables

    @Override
    public AfiliadoDto save(AfiliadoDto afiliado) {
        afiliado.setCedTitular(jtxtCedTitular.getText());
        afiliado.setIdParentezco(Integer.parseInt(jcmbParentezco.getItemAt(jcmbParentezco.getSelectedIndex())));
        return afiliado;
    }
}
