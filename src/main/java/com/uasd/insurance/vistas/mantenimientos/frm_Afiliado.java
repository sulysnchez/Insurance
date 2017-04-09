/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uasd.insurance.vistas.mantenimientos;

import com.itla.insurance.dao.AfiliadoDao;
import com.itla.insurance.dao.DB;
import com.itla.insurance.dto.AfiliadoDto;
import com.itla.insurance.dto.CiudadDto;
import com.itla.insurance.dto.OcupacionDto;
import com.itla.insurance.dto.ProvinciaDto;
import com.itla.insurance.dto.SexoDto;
import com.itla.insurance.dto.Tipo_AfiliacionDto;
import com.itla.insurance.dto.Tipo_CoberturaDto;
import com.itla.insurance.dto.Tipo_IdentificacionDto;
import com.itla.insurance.dto.Tipo_ParentezcoDto;
import com.itla.insurance.dto.Tipo_PlanDto;
import com.itla.insurance.dto.Tipo_SangreDto;
import com.uasd.insurance.utilitario.MyListRendeder;
import com.uasd.insurance.utilitario.ReportGenerator;
import com.uasd.insurance.utilitario.Utilities;
import com.uasd.insurance.utilitario.Validate;
import java.awt.Color;
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
public class frm_Afiliado extends javax.swing.JInternalFrame {

    AfiliadoDao afiliadoDao;
    AfiliadoDto afiliadoDto;
    
    /**
     * Creates new form frm_user
     */
    public frm_Afiliado() throws Exception {
        initComponents();
        
        afiliadoDao = new AfiliadoDao();
        afiliadoDto = new AfiliadoDto();
        
        //MOSTRAR NUMERO DE POLIZA Y CONTRATO
//        jlblNoPoliza.setText(afiliadoDao.GenerarPoliza());
//        jlblNoContrato.setText(afiliadoDao.GenerarContrato());
        
        LlenarCombos();
        
        jtblAfiliado.setModel(afiliadoDao.getModelAfiliado(afiliadoDao.GetAllAfiliado()));
       
        RedisenarTablaAfiliado();
        enableDependienteControls(false);
        jcmbTipoCobertura.setVisible(false);
        jlblTipoCobertura.setVisible(false);
        jcmbProvincia.setSelectedIndex(1);
    }
    
    public void LlenarCombos() throws SQLException{
        //LLENA COMBO TIPO DE IDENTIFICACION
        Object[] itemBlank = new Object[]{0,""};
        
        jcmbIdentificacion.setRenderer(new MyListRendeder());
        
        jcmbIdentificacion.addItem(itemBlank);
        
        for (Tipo_IdentificacionDto ti : afiliadoDao.GetAllTipoIdentificacion()) {
            Object[] item = new Object[]{ti.getId(), ti.getNombre()};
            jcmbIdentificacion.addItem(item);
        }
        
        //LLENA COMBO SEXO
        Object[] itemBlankSexo = new Object[]{0,""};
        
        jcmbSexo.setRenderer(new MyListRendeder());
        
        jcmbSexo.addItem(itemBlankSexo);
        
        for (SexoDto se : afiliadoDao.GetAllSexo()) {
            Object[] item = new Object[]{se.getId(), se.getNombre()};
            jcmbSexo.addItem(item);
        }
        
        //LLENA COMBO CIUDAD
        
        Object[] itemBlankCiudad = new Object[]{0,""};
        
        jcmbCiudad.setRenderer(new MyListRendeder());
        
        jcmbCiudad.addItem(itemBlankCiudad);
        
//        for (CiudadDto se : afiliadoDao.GetAllCiudad()) {
//            Object[] item = new Object[]{se.getId(), se.getNombre()};
//            jcmbCiudad.addItem(item);
//        }
        
        //LLENA COMBO PROVINCIA
        Object[] itemBlankProvincia = new Object[]{0,""};
        
        jcmbProvincia.setRenderer(new MyListRendeder());
        
        jcmbProvincia.addItem(itemBlankProvincia);
        
        for (ProvinciaDto se : afiliadoDao.GetAllProvincia()) {
            Object[] item = new Object[]{se.getId(), se.getNombre()};
            jcmbProvincia.addItem(item);
        }
        
        //LLENA COMBO OCUPACION
        Object[] itemBlankOcupacion = new Object[]{0,""};
        
        jcmbOcupacion.setRenderer(new MyListRendeder());
        
        jcmbOcupacion.addItem(itemBlankOcupacion);
        
        for (OcupacionDto se : afiliadoDao.GetAllOcupacion()) {
            Object[] item = new Object[]{se.getId(), se.getNombre()};
            jcmbOcupacion.addItem(item);
        }
        
        //LLENA COMBO TIPO DE SANGRE
        Object[] itemBlankSangre = new Object[]{0,""};
        
        jcmbTipoSangre.setRenderer(new MyListRendeder());
        
        jcmbTipoSangre.addItem(itemBlankSangre);
        
        for (Tipo_SangreDto se : afiliadoDao.GetAllTipo_Sangre()) {
            Object[] item = new Object[]{se.getId(), se.getNombre()};
            jcmbTipoSangre.addItem(item);
        }
        
        //LLENA COMBO TIPO DE AFILIACION
        Object[] itemBlankAfiliacion = new Object[]{0,""};
        
        jcmbTipoAfiliacion.setRenderer(new MyListRendeder());
        
        jcmbTipoAfiliacion.addItem(itemBlankAfiliacion);
        
        for (Tipo_AfiliacionDto se : afiliadoDao.GetAllTipo_Afiliacion()) {
            Object[] item = new Object[]{se.getId(), se.getNombre()};
            jcmbTipoAfiliacion.addItem(item);
        }
        
        //LLENA COMBO TIPO DE PLAN
        
        Object[] itemBlankPlan = new Object[]{0,""};
        
        jcmbTipoPlan.setRenderer(new MyListRendeder());
        
        jcmbTipoPlan.addItem(itemBlankPlan);
        
        for (Tipo_PlanDto se : afiliadoDao.GetAllTipo_Plan()) {
            Object[] item = new Object[]{se.getId(), se.getNombre()};
            jcmbTipoPlan.addItem(item);
        }
        
        //LLENA COMBO TIPO DE COBERTURA
        Object[] itemBlankCobertura = new Object[]{0,""};
        
        jcmbTipoCobertura.setRenderer(new MyListRendeder());
        
        jcmbTipoCobertura.addItem(itemBlankCobertura);
        
        for (Tipo_CoberturaDto se : afiliadoDao.GetAllTipo_Cobertura()) {
            Object[] item = new Object[]{se.getId(), Integer.toString(se.getPorciento()) };
            jcmbTipoCobertura.addItem(item);
        }
        
        //LLENA COMBO TIPO DE PARENTEZCO
        Object[] itemBlankParentezco = new Object[]{0,""};
        
        jcmbTipoParentezco.setRenderer(new MyListRendeder());
        
        jcmbTipoParentezco.addItem(itemBlankParentezco);
        
        for (Tipo_ParentezcoDto se : afiliadoDao.GetAllTipo_Parentezco()) {
            Object[] item = new Object[]{se.getId(), se.getNombre()};
            jcmbTipoParentezco.addItem(item);
        }
    }
    
    public void RedisenarTablaAfiliado(){
        //jtblAfiliado.setTableHeader(null);
        jtblAfiliado.removeColumn(jtblAfiliado.getColumnModel().getColumn(4));
        jtblAfiliado.removeColumn(jtblAfiliado.getColumnModel().getColumn(4));
        jtblAfiliado.removeColumn(jtblAfiliado.getColumnModel().getColumn(4));
        jtblAfiliado.removeColumn(jtblAfiliado.getColumnModel().getColumn(4));
        jtblAfiliado.removeColumn(jtblAfiliado.getColumnModel().getColumn(4));
        jtblAfiliado.removeColumn(jtblAfiliado.getColumnModel().getColumn(4));
        jtblAfiliado.removeColumn(jtblAfiliado.getColumnModel().getColumn(4));
        jtblAfiliado.removeColumn(jtblAfiliado.getColumnModel().getColumn(4));
        jtblAfiliado.removeColumn(jtblAfiliado.getColumnModel().getColumn(4));
        jtblAfiliado.removeColumn(jtblAfiliado.getColumnModel().getColumn(4));
        jtblAfiliado.removeColumn(jtblAfiliado.getColumnModel().getColumn(4));
        jtblAfiliado.removeColumn(jtblAfiliado.getColumnModel().getColumn(4));
        jtblAfiliado.removeColumn(jtblAfiliado.getColumnModel().getColumn(4));
        jtblAfiliado.removeColumn(jtblAfiliado.getColumnModel().getColumn(4));
        jtblAfiliado.removeColumn(jtblAfiliado.getColumnModel().getColumn(4));
        jtblAfiliado.removeColumn(jtblAfiliado.getColumnModel().getColumn(4));
        jtblAfiliado.removeColumn(jtblAfiliado.getColumnModel().getColumn(4));
    }
    
    public void LimpiarCampos(){
        jlblNoPoliza.setText("0");
        jlblNoContrato.setText("0");
        jtxtCedTitular.setText("");
        jftxtIdentificacion.setText("");
        jtxtDireccion.setText("");
        jtxtEdad.setText("");
        jtxtNombre.setText("");
        jtxtSeguroSocial.setText("");
        jftxtTelefono.setText("");
//        jdtcFechaNacimiento.setDate(new Date(01, 01, 01));
        jdtcFechaNacimiento.setDate(null);
        jcmbCiudad.setSelectedIndex(0);
        jcmbIdentificacion.setSelectedIndex(0);
        jcmbOcupacion.setSelectedIndex(0);
        jcmbProvincia.setSelectedIndex(0);
        jcmbSexo.setSelectedIndex(0);
        jcmbTipoAfiliacion.setSelectedIndex(0);
        jcmbTipoCobertura.setSelectedIndex(0);
        jcmbTipoParentezco.setSelectedIndex(0);
        jcmbTipoPlan.setSelectedIndex(0);
        jcmbTipoSangre.setSelectedIndex(0);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnllabel = new javax.swing.JPanel();
        jlblPoliza = new javax.swing.JLabel();
        jlblNoPoliza = new javax.swing.JLabel();
        jlblContrato = new javax.swing.JLabel();
        jlblNoContrato = new javax.swing.JLabel();
        jlblNombre = new javax.swing.JLabel();
        jtxtNombre = new javax.swing.JTextField();
        jlblFechaNacimiento = new javax.swing.JLabel();
        jlblEdad = new javax.swing.JLabel();
        jtxtEdad = new javax.swing.JTextField();
        jlblTipoAfiliacion = new javax.swing.JLabel();
        jlblTipoPlan = new javax.swing.JLabel();
        jlblSexo = new javax.swing.JLabel();
        jlblIdentificacion = new javax.swing.JLabel();
        jlblTipoSangre = new javax.swing.JLabel();
        jlblTipoCobertura = new javax.swing.JLabel();
        jlblDireccion = new javax.swing.JLabel();
        jtxtDireccion = new javax.swing.JTextField();
        jlblCiudad = new javax.swing.JLabel();
        jlblProvincia = new javax.swing.JLabel();
        jlblSeguroSocial = new javax.swing.JLabel();
        jtxtSeguroSocial = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jlblOcupacion = new javax.swing.JLabel();
        jpnlAfiliacion = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jlblCedTitular = new javax.swing.JLabel();
        jlblParentezco = new javax.swing.JLabel();
        jtxtCedTitular = new javax.swing.JTextField();
        jcmbTipoParentezco = new javax.swing.JComboBox();
        jlblTelefono = new javax.swing.JLabel();
        jftxtTelefono = new javax.swing.JFormattedTextField();
        jdtcFechaNacimiento = new com.toedter.calendar.JDateChooser();
        jftxtIdentificacion = new javax.swing.JFormattedTextField();
        jcmbIdentificacion = new javax.swing.JComboBox();
        jcmbSexo = new javax.swing.JComboBox();
        jcmbProvincia = new javax.swing.JComboBox();
        jcmbTipoSangre = new javax.swing.JComboBox();
        jcmbOcupacion = new javax.swing.JComboBox();
        jcmbTipoAfiliacion = new javax.swing.JComboBox();
        jcmbTipoPlan = new javax.swing.JComboBox();
        jcmbTipoCobertura = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jbttImprimir = new javax.swing.JButton();
        jbttGuardar = new javax.swing.JButton();
        jbttSalir = new javax.swing.JButton();
        jbttNuevo = new javax.swing.JButton();
        jbttNuevo1 = new javax.swing.JButton();
        jcmbCiudad = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblAfiliado = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jtxtBuscar = new javax.swing.JTextField();

        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jpnllabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jlblPoliza.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jlblPoliza.setText("No. Póliza");

        jlblNoPoliza.setFont(new java.awt.Font("Palatino Linotype", 1, 18)); // NOI18N
        jlblNoPoliza.setForeground(new java.awt.Color(153, 0, 0));
        jlblNoPoliza.setText("0");

        jlblContrato.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jlblContrato.setText("No. Contrato");

        jlblNoContrato.setFont(new java.awt.Font("Palatino Linotype", 1, 18)); // NOI18N
        jlblNoContrato.setForeground(new java.awt.Color(153, 0, 0));
        jlblNoContrato.setText("0");

        jlblNombre.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jlblNombre.setText("Nombre");

        jtxtNombre.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        jtxtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtNombreActionPerformed(evt);
            }
        });

        jlblFechaNacimiento.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jlblFechaNacimiento.setText("Fecha de Nacimiento");

        jlblEdad.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jlblEdad.setText("Edad");

        jtxtEdad.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N

        jlblTipoAfiliacion.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jlblTipoAfiliacion.setText("Tipo de Afiliación");

        jlblTipoPlan.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jlblTipoPlan.setText("Tipo de Plan");

        jlblSexo.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jlblSexo.setText("Sexo");

        jlblIdentificacion.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jlblIdentificacion.setText("Identificación");

        jlblTipoSangre.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jlblTipoSangre.setText("Tipo de Sangre");

        jlblTipoCobertura.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jlblTipoCobertura.setText("Cobertura");

        jlblDireccion.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jlblDireccion.setText("Dirección");

        jtxtDireccion.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N

        jlblCiudad.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jlblCiudad.setText("Ciudad");

        jlblProvincia.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jlblProvincia.setText("Provincia");

        jlblSeguroSocial.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jlblSeguroSocial.setText("No. Seguro social");

        jtxtSeguroSocial.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Palatino Linotype", 2, 18)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon("C:\\Users\\Sulenni\\Downloads\\user (3).png")); // NOI18N
        jLabel8.setText("FORMULARIO AFILIADO");

        jlblOcupacion.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jlblOcupacion.setText("Ocupación");

        jpnlAfiliacion.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(204, 204, 204)));

        jLabel9.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel9.setText("Datos de la Afiliación");

        jlblCedTitular.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jlblCedTitular.setText("Cédula del Títular");

        jlblParentezco.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jlblParentezco.setText("Parentezco");

        jtxtCedTitular.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N

        jcmbTipoParentezco.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        jcmbTipoParentezco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmbTipoParentezcoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnlAfiliacionLayout = new javax.swing.GroupLayout(jpnlAfiliacion);
        jpnlAfiliacion.setLayout(jpnlAfiliacionLayout);
        jpnlAfiliacionLayout.setHorizontalGroup(
            jpnlAfiliacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlAfiliacionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnlAfiliacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnlAfiliacionLayout.createSequentialGroup()
                        .addComponent(jlblCedTitular)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtCedTitular))
                    .addGroup(jpnlAfiliacionLayout.createSequentialGroup()
                        .addComponent(jlblParentezco)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcmbTipoParentezco, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnlAfiliacionLayout.createSequentialGroup()
                .addContainerGap(74, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(35, 35, 35))
        );
        jpnlAfiliacionLayout.setVerticalGroup(
            jpnlAfiliacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlAfiliacionLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpnlAfiliacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblCedTitular)
                    .addComponent(jtxtCedTitular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnlAfiliacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblParentezco)
                    .addComponent(jcmbTipoParentezco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jlblTelefono.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jlblTelefono.setText("Teléfono");

        jftxtTelefono.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N

        jdtcFechaNacimiento.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N

        jftxtIdentificacion.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N

        jcmbIdentificacion.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        jcmbIdentificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmbIdentificacionActionPerformed(evt);
            }
        });

        jcmbSexo.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        jcmbSexo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmbSexoActionPerformed(evt);
            }
        });

        jcmbProvincia.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        jcmbProvincia.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcmbProvinciaItemStateChanged(evt);
            }
        });
        jcmbProvincia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmbProvinciaActionPerformed(evt);
            }
        });

        jcmbTipoSangre.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        jcmbTipoSangre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmbTipoSangreActionPerformed(evt);
            }
        });

        jcmbOcupacion.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        jcmbOcupacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmbOcupacionActionPerformed(evt);
            }
        });

        jcmbTipoAfiliacion.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        jcmbTipoAfiliacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcmbTipoAfiliacionItemStateChanged(evt);
            }
        });
        jcmbTipoAfiliacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmbTipoAfiliacionActionPerformed(evt);
            }
        });

        jcmbTipoPlan.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        jcmbTipoPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmbTipoPlanActionPerformed(evt);
            }
        });

        jcmbTipoCobertura.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        jcmbTipoCobertura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmbTipoCoberturaActionPerformed(evt);
            }
        });

        jbttImprimir.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jbttImprimir.setIcon(new javax.swing.ImageIcon("C:\\Users\\Sulenni\\Downloads\\printer.png")); // NOI18N
        jbttImprimir.setText("Imprimir");
        jbttImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbttImprimirActionPerformed(evt);
            }
        });

        jbttGuardar.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jbttGuardar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Sulenni\\Downloads\\confirm.png")); // NOI18N
        jbttGuardar.setText("Guardar");
        jbttGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbttGuardarActionPerformed(evt);
            }
        });

        jbttSalir.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jbttSalir.setIcon(new javax.swing.ImageIcon("C:\\Users\\Sulenni\\Downloads\\cancel.png")); // NOI18N
        jbttSalir.setText("Salir");
        jbttSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbttSalirActionPerformed(evt);
            }
        });

        jbttNuevo.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jbttNuevo.setIcon(new javax.swing.ImageIcon("C:\\Users\\Sulenni\\Downloads\\plus (1).png")); // NOI18N
        jbttNuevo.setText("Nuevo");
        jbttNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbttNuevoActionPerformed(evt);
            }
        });

        jbttNuevo1.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jbttNuevo1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Sulenni\\Downloads\\prohibition.png")); // NOI18N
        jbttNuevo1.setText("Eliminar");
        jbttNuevo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbttNuevo1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jbttImprimir)
                .addGap(21, 21, 21)
                .addComponent(jbttNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbttNuevo1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbttGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbttSalir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbttImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbttGuardar)
                    .addComponent(jbttSalir)
                    .addComponent(jbttNuevo)
                    .addComponent(jbttNuevo1))
                .addContainerGap())
        );

        jcmbCiudad.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        jcmbCiudad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcmbCiudadItemStateChanged(evt);
            }
        });
        jcmbCiudad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmbCiudadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnllabelLayout = new javax.swing.GroupLayout(jpnllabel);
        jpnllabel.setLayout(jpnllabelLayout);
        jpnllabelLayout.setHorizontalGroup(
            jpnllabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnllabelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jpnllabelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jpnllabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnllabelLayout.createSequentialGroup()
                        .addGroup(jpnllabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlblIdentificacion)
                            .addComponent(jlblDireccion)
                            .addComponent(jlblFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlblTipoAfiliacion)
                            .addComponent(jlblTipoPlan)
                            .addComponent(jlblTipoCobertura, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlblOcupacion)
                            .addComponent(jlblProvincia))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpnllabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpnllabelLayout.createSequentialGroup()
                                .addGroup(jpnllabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jtxtNombre, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpnllabelLayout.createSequentialGroup()
                                        .addComponent(jdtcFechaNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jlblEdad)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtxtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jlblTipoSangre)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jcmbTipoSangre, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jpnllabelLayout.createSequentialGroup()
                                        .addComponent(jcmbIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jftxtIdentificacion)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jlblSexo)
                                        .addGap(19, 19, 19)
                                        .addComponent(jcmbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jpnllabelLayout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addGroup(jpnllabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jpnllabelLayout.createSequentialGroup()
                                                .addComponent(jcmbProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jlblCiudad)
                                                .addGap(242, 242, 242))
                                            .addGroup(jpnllabelLayout.createSequentialGroup()
                                                .addComponent(jtxtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(7, 7, 7)
                                                .addComponent(jlblTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jftxtTelefono)))
                                        .addGap(1, 1, 1)))
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnllabelLayout.createSequentialGroup()
                                .addGroup(jpnllabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jpnllabelLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jcmbCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jpnllabelLayout.createSequentialGroup()
                                        .addGroup(jpnllabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jcmbTipoPlan, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jcmbTipoAfiliacion, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jcmbOcupacion, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jcmbTipoCobertura, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(13, 13, 13)
                                        .addComponent(jpnlAfiliacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(19, 19, 19))))
                    .addGroup(jpnllabelLayout.createSequentialGroup()
                        .addGroup(jpnllabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpnllabelLayout.createSequentialGroup()
                                .addComponent(jlblNombre)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jpnllabelLayout.createSequentialGroup()
                                .addGroup(jpnllabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlblPoliza)
                                    .addComponent(jlblContrato))
                                .addGap(35, 35, 35)
                                .addGroup(jpnllabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpnllabelLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel8)
                                        .addGap(11, 11, 11))
                                    .addGroup(jpnllabelLayout.createSequentialGroup()
                                        .addGroup(jpnllabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jlblNoPoliza, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jlblNoContrato, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(29, 29, 29)
                                        .addComponent(jlblSeguroSocial)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtxtSeguroSocial)))))
                        .addGap(18, 18, 18))))
        );
        jpnllabelLayout.setVerticalGroup(
            jpnllabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnllabelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnllabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpnllabelLayout.createSequentialGroup()
                        .addComponent(jlblPoliza)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpnllabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblContrato)
                            .addComponent(jlblSeguroSocial)
                            .addComponent(jtxtSeguroSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpnllabelLayout.createSequentialGroup()
                        .addComponent(jlblNoPoliza)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlblNoContrato)))
                .addGap(6, 6, 6)
                .addGroup(jpnllabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblNombre)
                    .addComponent(jtxtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnllabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpnllabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlblIdentificacion)
                        .addComponent(jcmbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlblSexo))
                    .addGroup(jpnllabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jftxtIdentificacion)
                        .addComponent(jcmbIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpnllabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnllabelLayout.createSequentialGroup()
                        .addGroup(jpnllabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblFechaNacimiento)
                            .addComponent(jlblEdad)
                            .addComponent(jtxtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlblTipoSangre)
                            .addComponent(jcmbTipoSangre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpnllabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblDireccion)
                            .addComponent(jtxtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlblTelefono)
                            .addComponent(jftxtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jdtcFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnllabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnllabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jcmbProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlblProvincia))
                    .addGroup(jpnllabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlblCiudad)
                        .addComponent(jcmbCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnllabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnllabelLayout.createSequentialGroup()
                        .addComponent(jpnlAfiliacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(26, 26, 26))
                    .addGroup(jpnllabelLayout.createSequentialGroup()
                        .addGroup(jpnllabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblOcupacion)
                            .addComponent(jcmbOcupacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpnllabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblTipoAfiliacion)
                            .addComponent(jcmbTipoAfiliacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpnllabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblTipoPlan, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcmbTipoPlan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpnllabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblTipoCobertura)
                            .addComponent(jcmbTipoCobertura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jtblAfiliado.setAutoCreateRowSorter(true);
        jtblAfiliado.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jtblAfiliado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtblAfiliado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtblAfiliadoMousePressed(evt);
            }
        });
        jtblAfiliado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtblAfiliadoKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jtblAfiliado);

        jLabel1.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Sulenni\\Downloads\\search.png")); // NOI18N
        jLabel1.setText("Consultas");

        jtxtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtBuscarActionPerformed(evt);
            }
        });
        jtxtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxtBuscarKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jtxtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpnllabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnllabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtNombreActionPerformed

    private void jbttImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbttImprimirActionPerformed
        ReportGenerator.allAfiliado();

//        String rutaReporteAfiliado = "C:/Users/Sulenni/Documents/NetBeansProjects/Insurance/src/main/java/com/uasd/insurance/vistas/reportes/reporteAfiliado.jrxml";
//        JasperReport reporte = null;
//        
//        try {
//            reporte = JasperCompileManager.compileReport(rutaReporteAfiliado);
//        } catch (JRException ex) {
//            Logger.getLogger(frm_Afiliado.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        DB datos;
//        try {
//            datos = new DB();
//        } catch (SQLException ex) {
//            Logger.getLogger(frm_Afiliado.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        Map<String,Object> parametro = new HashMap<String, Object>();
//        JasperPrint imprimir = null;
//        
//        try {
//            imprimir = JasperFillManager.fillReport(reporte, parametro, DB.conexion);
//        } catch (JRException ex) {
//            Logger.getLogger(frm_Afiliado.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        File nombre = new File("C:/Users/Sulenni/Documents/");
//        nombre.mkdirs();
//        
//        try {
//            JasperExportManager.exportReportToPdfFile(imprimir, "C:/Users/Sulenni/Documents/ReporteAfiliado.pdf");
//        } catch (JRException ex) {
//            Logger.getLogger(frm_Afiliado.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        File  ruta = new File ("C:/Users/Sulenni/Documents/ReporteAfiliado.pdf");
//
//        try {
//            Desktop.getDesktop().open(ruta);
//        } catch (IOException ex) {
//            Logger.getLogger(frm_Afiliado.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_jbttImprimirActionPerformed

    private void jbttSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbttSalirActionPerformed
        dispose();
    }//GEN-LAST:event_jbttSalirActionPerformed

    private void jbttGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbttGuardarActionPerformed
//        if(Validate.ValidateAll(jpnllabel)){
        if(true){
//            afiliadoDto.setNo_Poliza(Integer.parseInt(jlblNoPoliza.getText()));
//            afiliadoDto.setNo_Contrato(jlblNoContrato.getText());
            afiliadoDto.setNo_SeguroSocial(Integer.parseInt(jtxtSeguroSocial.getText()));
            afiliadoDto.setNombre(jtxtNombre.getText());
            //afiliadoDto.setIdTipoIdentificacion(Integer.parseInt(jcmbIdentificacion.getName())); 
            int id;
            Object[] ob; 

            ob = (Object[]) jcmbIdentificacion.getSelectedItem();
            id = (int) ob[0];
            afiliadoDto.setIdTipoIdentificacion(id);

            afiliadoDto.setIdentificacion(jftxtIdentificacion.getText());
           // afiliadoDto.setIdSexo(Integer.parseInt(jcmbSexo.getName()));
            ob = (Object[]) jcmbSexo.getSelectedItem();
            id = (int) ob[0];
            afiliadoDto.setIdSexo(id);

            afiliadoDto.setFechaNacimiento(jdtcFechaNacimiento.getDate());
            afiliadoDto.setEdad(Integer.parseInt(jtxtEdad.getText()));
            //afiliadoDto.setIdTipoSangre(Integer.parseInt(jcmbProvincia.getName())); 
            ob = (Object[]) jcmbTipoSangre.getSelectedItem();
            id = (int) ob[0];
            afiliadoDto.setIdTipoSangre(id);        

            afiliadoDto.setDireccion(jtxtDireccion.getText());
           // afiliadoDto.setIdCiudad(Integer.parseInt(jcmbCiudad.getName()));
            ob = (Object[]) jcmbCiudad.getSelectedItem();
            id = (int) ob[0];
            afiliadoDto.setIdCiudad(id);

           // afiliadoDto.setIdProvincia(Integer.parseInt(jcmbProvincia.getName()));
            ob = (Object[]) jcmbProvincia.getSelectedItem();
            id = (int) ob[0];
            afiliadoDto.setIdProvincia(id);

            //afiliadoDto.setIdTipoAfiliacion(Integer.parseInt(jcmbTipoAfiliacion.getName()));
            ob = (Object[]) jcmbTipoAfiliacion.getSelectedItem();
            id = (int) ob[0];
            afiliadoDto.setIdTipoAfiliacion(id);

            //afiliadoDto.setIdTipoPlan(Integer.parseInt(jcmbTipoPlan.getName()));
            ob = (Object[]) jcmbTipoPlan.getSelectedItem();
            id = (int) ob[0];
            afiliadoDto.setIdTipoPlan(id);

            //afiliadoDto.setIdTipoCobertura(Integer.parseInt(jcmbTipoCobertura.getName()));
            ob = (Object[]) jcmbTipoCobertura.getSelectedItem();
            id = (int) ob[0];
            afiliadoDto.setIdTipoCobertura(id);

            afiliadoDto.setCedTitular(jtxtCedTitular.getText());
            //afiliadoDto.setIdParentezco(Integer.parseInt(jcmbTipoParentezco.getName()));
            ob = (Object[]) jcmbTipoParentezco.getSelectedItem();
            id = (int) ob[0];
            afiliadoDto.setIdParentezco(id);

            //afiliadoDto.setIdOcupacion(Integer.parseInt(jcmbOcupacion.getName()));
            ob = (Object[]) jcmbOcupacion.getSelectedItem();
            id = (int) ob[0];
            afiliadoDto.setIdOcupacion(id);

            afiliadoDto.setTelefono(jftxtTelefono.getText());

            try {
                afiliadoDao.insertAfiliado(afiliadoDto);
            } catch (Exception ex) {
                Logger.getLogger(frm_Afiliado.class.getName()).log(Level.SEVERE, null, ex);
            }

            //jtblAfiliado.repaint();
            try {
                jtblAfiliado.setModel(afiliadoDao.getModelAfiliado(afiliadoDao.GetAllAfiliado()));
               // afiliadoDao.getModelAfiliado(afiliadoDao.GetAllAfiliado()).fireTableDataChanged();
                RedisenarTablaAfiliado();
            } catch (SQLException ex) {
                Logger.getLogger(frm_Afiliado.class.getName()).log(Level.SEVERE, null, ex);
            }

            LimpiarCampos();

            try {
                jlblNoPoliza.setText(afiliadoDao.GenerarPoliza());
            } catch (SQLException ex) {
                Logger.getLogger(frm_Afiliado.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                jlblNoContrato.setText(afiliadoDao.GenerarContrato());
            } catch (SQLException ex) {
                Logger.getLogger(frm_Afiliado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.!.!. ");
        }
    }//GEN-LAST:event_jbttGuardarActionPerformed

    private void jcmbIdentificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmbIdentificacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcmbIdentificacionActionPerformed

    private void jcmbSexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmbSexoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcmbSexoActionPerformed

    private void jcmbProvinciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmbProvinciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcmbProvinciaActionPerformed

    private void jcmbTipoSangreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmbTipoSangreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcmbTipoSangreActionPerformed

    private void jcmbOcupacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmbOcupacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcmbOcupacionActionPerformed

    private void jcmbTipoAfiliacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmbTipoAfiliacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcmbTipoAfiliacionActionPerformed

    private void jcmbTipoPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmbTipoPlanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcmbTipoPlanActionPerformed

    private void jcmbTipoCoberturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmbTipoCoberturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcmbTipoCoberturaActionPerformed

    private void jcmbTipoParentezcoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmbTipoParentezcoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcmbTipoParentezcoActionPerformed

    private void jtxtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtBuscarActionPerformed

    private void jtblAfiliadoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblAfiliadoMousePressed
        jtblAfiliado.setSelectionBackground(Color.BLUE);
        jtblAfiliado.setSelectionForeground(Color.white);
              
        jlblNoPoliza.setText(Integer.toString((int) jtblAfiliado.getModel().getValueAt(jtblAfiliado.getSelectedRow(), 0)));
        jftxtIdentificacion.setText((String) (jtblAfiliado.getModel().getValueAt(jtblAfiliado.getSelectedRow(), 1)));
        jtxtNombre.setText((String) (jtblAfiliado.getModel().getValueAt(jtblAfiliado.getSelectedRow(), 2)));
        jlblNoContrato.setText((String) jtblAfiliado.getModel().getValueAt(jtblAfiliado.getSelectedRow(), 3));
        //ID
        jtxtSeguroSocial.setText(Integer.toString((int) (jtblAfiliado.getModel().getValueAt(jtblAfiliado.getSelectedRow(), 5))));
        int id = 0;
            for (int i = 0;i<jcmbIdentificacion.getItemCount();i++) {
                
                Object[] ob = (Object[]) jcmbIdentificacion.getItemAt(i);
                id= (int)ob[0];
                if(id==(Integer.parseInt(Integer.toString((int) jtblAfiliado.getModel().getValueAt(jtblAfiliado.getSelectedRow(), 6))))){
                    jcmbIdentificacion.setSelectedIndex(i);
                }
            }
        
            for (int i = 0;i<jcmbSexo.getItemCount();i++) {
                
                Object[] ob = (Object[]) jcmbSexo.getItemAt(i);
                id= (int)ob[0];
                if(id==(int) (jtblAfiliado.getModel().getValueAt(jtblAfiliado.getSelectedRow(), 7))){
                    jcmbSexo.setSelectedIndex(i);
                }
            }
        
        jdtcFechaNacimiento.setDate((java.util.Date) jtblAfiliado.getModel().getValueAt(jtblAfiliado.getSelectedRow(), 8));
        jtxtEdad.setText(Integer.toString((int) jtblAfiliado.getModel().getValueAt(jtblAfiliado.getSelectedRow(), 9)));
        jtxtEdad.setText(Utilities.CalcularEdad(jdtcFechaNacimiento.getDate()).toString());
            for (int i = 0;i<jcmbTipoSangre.getItemCount();i++) {
                
                Object[] ob = (Object[]) jcmbTipoSangre.getItemAt(i);
                id= (int)ob[0];
                if(id==(int) (jtblAfiliado.getModel().getValueAt(jtblAfiliado.getSelectedRow(), 10))){
                    jcmbTipoSangre.setSelectedIndex(i);
                }
            }
        jtxtDireccion.setText((String) (jtblAfiliado.getModel().getValueAt(jtblAfiliado.getSelectedRow(), 11)));
            for (int i = 0;i<jcmbCiudad.getItemCount();i++) {
                
                Object[] ob = (Object[]) jcmbCiudad.getItemAt(i);
                id= (int)ob[0];
                if(id==(int) (jtblAfiliado.getModel().getValueAt(jtblAfiliado.getSelectedRow(), 12))){
                    jcmbCiudad.setSelectedIndex(i);
                }
            }
        
            for (int i = 0;i<jcmbProvincia.getItemCount();i++) {
                
                Object[] ob = (Object[]) jcmbProvincia.getItemAt(i);
                id= (int)ob[0];
                if(id==(int) (jtblAfiliado.getModel().getValueAt(jtblAfiliado.getSelectedRow(), 13))){
                    jcmbProvincia.setSelectedIndex(i);
                }
            }
            
            for (int i = 0;i<jcmbTipoAfiliacion.getItemCount();i++) {
                
                Object[] ob = (Object[]) jcmbTipoAfiliacion.getItemAt(i);
                id= (int)ob[0];
                if(id==(int) (jtblAfiliado.getModel().getValueAt(jtblAfiliado.getSelectedRow(), 14))){
                    jcmbTipoAfiliacion.setSelectedIndex(i);
                }
            }
        
            for (int i = 0;i<jcmbTipoPlan.getItemCount();i++) {
                
                Object[] ob = (Object[]) jcmbTipoPlan.getItemAt(i);
                id= (int)ob[0];
                if(id==(int) (jtblAfiliado.getModel().getValueAt(jtblAfiliado.getSelectedRow(), 15))){
                    jcmbTipoPlan.setSelectedIndex(i);
                }
            }
            
            for (int i = 0;i<jcmbTipoCobertura.getItemCount();i++) {
                
                Object[] ob = (Object[]) jcmbTipoCobertura.getItemAt(i);
                id= (int)ob[0];
                if(id==(int) (jtblAfiliado.getModel().getValueAt(jtblAfiliado.getSelectedRow(), 16))){
                    jcmbTipoCobertura.setSelectedIndex(i);
                }
            }
        
        jtxtCedTitular.setText((String) (jtblAfiliado.getModel().getValueAt(jtblAfiliado.getSelectedRow(), 17)));
        
            for (int i = 0;i<jcmbTipoParentezco.getItemCount();i++) {
                
                Object[] ob = (Object[]) jcmbTipoParentezco.getItemAt(i);
                id= (int)ob[0];
                if(id==(int) (jtblAfiliado.getModel().getValueAt(jtblAfiliado.getSelectedRow(), 18))){
                    jcmbTipoParentezco.setSelectedIndex(i);
                }
            }
            
            for (int i = 0;i<jcmbOcupacion.getItemCount();i++) {
                
                Object[] ob = (Object[]) jcmbOcupacion.getItemAt(i);
                id= (int)ob[0];
                if(id==(int) (jtblAfiliado.getModel().getValueAt(jtblAfiliado.getSelectedRow(), 19))){
                    jcmbOcupacion.setSelectedIndex(i);
                }
            }
      
        jftxtTelefono.setText((String) (jtblAfiliado.getModel().getValueAt(jtblAfiliado.getSelectedRow(), 20)));
        
        llenarAfiliado();
    }//GEN-LAST:event_jtblAfiliadoMousePressed

    private void llenarAfiliado(){
        
        afiliadoDto.setId((int) jtblAfiliado.getModel().getValueAt(jtblAfiliado.getSelectedRow(), 4));
        afiliadoDto.setNo_Poliza(Integer.parseInt(jlblNoPoliza.getText()));
            afiliadoDto.setNo_Contrato(jlblNoContrato.getText());
            afiliadoDto.setNo_SeguroSocial(Integer.parseInt(jtxtSeguroSocial.getText()));
            afiliadoDto.setNombre(jtxtNombre.getText());
            //afiliadoDto.setIdTipoIdentificacion(Integer.parseInt(jcmbIdentificacion.getName())); 
            int id;
            Object[] ob; 

            ob = (Object[]) jcmbIdentificacion.getSelectedItem();
            id = (int) ob[0];
            afiliadoDto.setIdTipoIdentificacion(id);

            afiliadoDto.setIdentificacion(jftxtIdentificacion.getText());
           // afiliadoDto.setIdSexo(Integer.parseInt(jcmbSexo.getName()));
            ob = (Object[]) jcmbSexo.getSelectedItem();
            id = (int) ob[0];
            afiliadoDto.setIdSexo(id);

            afiliadoDto.setFechaNacimiento(jdtcFechaNacimiento.getDate());
            afiliadoDto.setEdad(Integer.parseInt(jtxtEdad.getText()));
            //afiliadoDto.setIdTipoSangre(Integer.parseInt(jcmbProvincia.getName())); 
            ob = (Object[]) jcmbTipoSangre.getSelectedItem();
            id = (int) ob[0];
            afiliadoDto.setIdTipoSangre(id);        

            afiliadoDto.setDireccion(jtxtDireccion.getText());
           // afiliadoDto.setIdCiudad(Integer.parseInt(jcmbCiudad.getName()));
            ob = (Object[]) jcmbCiudad.getSelectedItem();
            id = (int) ob[0];
            afiliadoDto.setIdCiudad(id);

           // afiliadoDto.setIdProvincia(Integer.parseInt(jcmbProvincia.getName()));
            ob = (Object[]) jcmbProvincia.getSelectedItem();
            id = (int) ob[0];
            afiliadoDto.setIdProvincia(id);

            //afiliadoDto.setIdTipoAfiliacion(Integer.parseInt(jcmbTipoAfiliacion.getName()));
            ob = (Object[]) jcmbTipoAfiliacion.getSelectedItem();
            id = (int) ob[0];
            afiliadoDto.setIdTipoAfiliacion(id);

            //afiliadoDto.setIdTipoPlan(Integer.parseInt(jcmbTipoPlan.getName()));
            ob = (Object[]) jcmbTipoPlan.getSelectedItem();
            id = (int) ob[0];
            afiliadoDto.setIdTipoPlan(id);

            //afiliadoDto.setIdTipoCobertura(Integer.parseInt(jcmbTipoCobertura.getName()));
            ob = (Object[]) jcmbTipoCobertura.getSelectedItem();
            id = (int) ob[0];
            afiliadoDto.setIdTipoCobertura(id);

            afiliadoDto.setCedTitular(jtxtCedTitular.getText());
            //afiliadoDto.setIdParentezco(Integer.parseInt(jcmbTipoParentezco.getName()));
            ob = (Object[]) jcmbTipoParentezco.getSelectedItem();
            id = (int) ob[0];
            afiliadoDto.setIdParentezco(id);

            //afiliadoDto.setIdOcupacion(Integer.parseInt(jcmbOcupacion.getName()));
            ob = (Object[]) jcmbOcupacion.getSelectedItem();
            id = (int) ob[0];
            afiliadoDto.setIdOcupacion(id);

            afiliadoDto.setTelefono(jftxtTelefono.getText());

    }
    private void jbttNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbttNuevoActionPerformed
        LimpiarCampos();
//        try {
//            jlblNoPoliza.setText(afiliadoDao.GenerarPoliza());
//        } catch (SQLException ex) {
//            Logger.getLogger(frm_Afiliado.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        try {
//            jlblNoContrato.setText(afiliadoDao.GenerarContrato());
//        } catch (SQLException ex) {
//            Logger.getLogger(frm_Afiliado.class.getName()).log(Level.SEVERE, null, ex);
//        }
                
    }//GEN-LAST:event_jbttNuevoActionPerformed

    private void jtxtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtBuscarKeyTyped
        try {
            jtblAfiliado.setModel(afiliadoDao.filtraModelAfiliado(jtxtBuscar.getText()));
        } catch (SQLException ex) {
            Logger.getLogger(frm_Afiliado.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        RedisenarTablaAfiliado();
    }//GEN-LAST:event_jtxtBuscarKeyTyped

    private void jbttNuevo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbttNuevo1ActionPerformed
       
        afiliadoDao.deleteAfiliado(afiliadoDto);
        try {
            jtblAfiliado.setModel(afiliadoDao.getModelAfiliado(afiliadoDao.GetAllAfiliado()));
        } catch (SQLException ex) {
            Logger.getLogger(frm_Afiliado.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        RedisenarTablaAfiliado();
    }//GEN-LAST:event_jbttNuevo1ActionPerformed

    private void jtblAfiliadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtblAfiliadoKeyReleased
        if(evt.getKeyCode()==40||evt.getKeyCode()==38){
            jtblAfiliado.setSelectionBackground(Color.BLUE);
            jtblAfiliado.setSelectionForeground(Color.white);

            jlblNoPoliza.setText(Integer.toString((int) jtblAfiliado.getModel().getValueAt(jtblAfiliado.getSelectedRow(), 0)));
            jftxtIdentificacion.setText((String) (jtblAfiliado.getModel().getValueAt(jtblAfiliado.getSelectedRow(), 1)));
            jtxtNombre.setText((String) (jtblAfiliado.getModel().getValueAt(jtblAfiliado.getSelectedRow(), 2)));
            jlblNoContrato.setText((String) jtblAfiliado.getModel().getValueAt(jtblAfiliado.getSelectedRow(), 3));
            //ID
            jtxtSeguroSocial.setText(Integer.toString((int) (jtblAfiliado.getModel().getValueAt(jtblAfiliado.getSelectedRow(), 5))));
            int id = 0;
                for (int i = 0;i<jcmbIdentificacion.getItemCount();i++) {

                    Object[] ob = (Object[]) jcmbIdentificacion.getItemAt(i);
                    id= (int)ob[0];
                    if(id==(Integer.parseInt(Integer.toString((int) jtblAfiliado.getModel().getValueAt(jtblAfiliado.getSelectedRow(), 6))))){
                        jcmbIdentificacion.setSelectedIndex(i);
                    }
                }

                for (int i = 0;i<jcmbSexo.getItemCount();i++) {

                    Object[] ob = (Object[]) jcmbSexo.getItemAt(i);
                    id= (int)ob[0];
                    if(id==(int) (jtblAfiliado.getModel().getValueAt(jtblAfiliado.getSelectedRow(), 7))){
                        jcmbSexo.setSelectedIndex(i);
                    }
                }

            jdtcFechaNacimiento.setDate((java.util.Date) jtblAfiliado.getModel().getValueAt(jtblAfiliado.getSelectedRow(), 8));
            jtxtEdad.setText(Integer.toString((int) jtblAfiliado.getModel().getValueAt(jtblAfiliado.getSelectedRow(), 9)));
                for (int i = 0;i<jcmbTipoSangre.getItemCount();i++) {

                    Object[] ob = (Object[]) jcmbTipoSangre.getItemAt(i);
                    id= (int)ob[0];
                    if(id==(int) (jtblAfiliado.getModel().getValueAt(jtblAfiliado.getSelectedRow(), 10))){
                        jcmbTipoSangre.setSelectedIndex(i);
                    }
                }
            jtxtDireccion.setText((String) (jtblAfiliado.getModel().getValueAt(jtblAfiliado.getSelectedRow(), 11)));
                for (int i = 0;i<jcmbCiudad.getItemCount();i++) {

                    Object[] ob = (Object[]) jcmbCiudad.getItemAt(i);
                    id= (int)ob[0];
                    if(id==(int) (jtblAfiliado.getModel().getValueAt(jtblAfiliado.getSelectedRow(), 12))){
                        jcmbCiudad.setSelectedIndex(i);
                    }
                }

                for (int i = 0;i<jcmbProvincia.getItemCount();i++) {

                    Object[] ob = (Object[]) jcmbProvincia.getItemAt(i);
                    id= (int)ob[0];
                    if(id==(int) (jtblAfiliado.getModel().getValueAt(jtblAfiliado.getSelectedRow(), 13))){
                        jcmbProvincia.setSelectedIndex(i);
                    }
                }

                for (int i = 0;i<jcmbTipoAfiliacion.getItemCount();i++) {

                    Object[] ob = (Object[]) jcmbTipoAfiliacion.getItemAt(i);
                    id= (int)ob[0];
                    if(id==(int) (jtblAfiliado.getModel().getValueAt(jtblAfiliado.getSelectedRow(), 14))){
                        jcmbTipoAfiliacion.setSelectedIndex(i);
                    }
                }

                for (int i = 0;i<jcmbTipoPlan.getItemCount();i++) {

                    Object[] ob = (Object[]) jcmbTipoPlan.getItemAt(i);
                    id= (int)ob[0];
                    if(id==(int) (jtblAfiliado.getModel().getValueAt(jtblAfiliado.getSelectedRow(), 15))){
                        jcmbTipoPlan.setSelectedIndex(i);
                    }
                }

                for (int i = 0;i<jcmbTipoCobertura.getItemCount();i++) {

                    Object[] ob = (Object[]) jcmbTipoCobertura.getItemAt(i);
                    id= (int)ob[0];
                    if(id==(int) (jtblAfiliado.getModel().getValueAt(jtblAfiliado.getSelectedRow(), 16))){
                        jcmbTipoCobertura.setSelectedIndex(i);
                    }
                }

            jtxtCedTitular.setText((String) (jtblAfiliado.getModel().getValueAt(jtblAfiliado.getSelectedRow(), 17)));

                for (int i = 0;i<jcmbTipoParentezco.getItemCount();i++) {

                    Object[] ob = (Object[]) jcmbTipoParentezco.getItemAt(i);
                    id= (int)ob[0];
                    if(id==(int) (jtblAfiliado.getModel().getValueAt(jtblAfiliado.getSelectedRow(), 18))){
                        jcmbTipoParentezco.setSelectedIndex(i);
                    }
                }

                for (int i = 0;i<jcmbOcupacion.getItemCount();i++) {

                    Object[] ob = (Object[]) jcmbOcupacion.getItemAt(i);
                    id= (int)ob[0];
                    if(id==(int) (jtblAfiliado.getModel().getValueAt(jtblAfiliado.getSelectedRow(), 19))){
                        jcmbOcupacion.setSelectedIndex(i);
                    }
                }

            jftxtTelefono.setText((String) (jtblAfiliado.getModel().getValueAt(jtblAfiliado.getSelectedRow(), 20)));

            llenarAfiliado();
        }
    }//GEN-LAST:event_jtblAfiliadoKeyReleased

    private void enableDependienteControls(boolean b){
        jcmbTipoParentezco.setEnabled(b);
        jtxtCedTitular.setEnabled(b);
    }
    private void jcmbProvinciaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcmbProvinciaItemStateChanged
        Object[] ob = (Object[]) jcmbProvincia.getSelectedItem();
        int id = (int) ob[0];
        try {
            jcmbCiudad.removeAllItems();
            Object[] itemBlankCiudad = new Object[]{0,""};
            jcmbCiudad.setRenderer(new MyListRendeder());
            jcmbCiudad.addItem(itemBlankCiudad);
            
            for (CiudadDto se : afiliadoDao.GetAllCiudadByProvincia(id)) {
                Object[] item = new Object[]{se.getId(), se.getNombre()};
                jcmbCiudad.addItem(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(frm_Afiliado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jcmbProvinciaItemStateChanged

    private void jcmbTipoAfiliacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcmbTipoAfiliacionItemStateChanged
        Object[] ob = (Object[]) jcmbTipoAfiliacion.getSelectedItem();
        int id = (int) ob[0];
        if(id==1){
            enableDependienteControls(false);
        }else if(id==2){
            enableDependienteControls(true);
        }
    }//GEN-LAST:event_jcmbTipoAfiliacionItemStateChanged

    private void jcmbCiudadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcmbCiudadItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jcmbCiudadItemStateChanged

    private void jcmbCiudadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmbCiudadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcmbCiudadActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbttGuardar;
    private javax.swing.JButton jbttImprimir;
    private javax.swing.JButton jbttNuevo;
    private javax.swing.JButton jbttNuevo1;
    private javax.swing.JButton jbttSalir;
    private javax.swing.JComboBox jcmbCiudad;
    private javax.swing.JComboBox jcmbIdentificacion;
    private javax.swing.JComboBox jcmbOcupacion;
    private javax.swing.JComboBox jcmbProvincia;
    private javax.swing.JComboBox jcmbSexo;
    private javax.swing.JComboBox jcmbTipoAfiliacion;
    private javax.swing.JComboBox jcmbTipoCobertura;
    private javax.swing.JComboBox jcmbTipoParentezco;
    private javax.swing.JComboBox jcmbTipoPlan;
    private javax.swing.JComboBox jcmbTipoSangre;
    private com.toedter.calendar.JDateChooser jdtcFechaNacimiento;
    private javax.swing.JFormattedTextField jftxtIdentificacion;
    private javax.swing.JFormattedTextField jftxtTelefono;
    private javax.swing.JLabel jlblCedTitular;
    private javax.swing.JLabel jlblCiudad;
    private javax.swing.JLabel jlblContrato;
    private javax.swing.JLabel jlblDireccion;
    private javax.swing.JLabel jlblEdad;
    private javax.swing.JLabel jlblFechaNacimiento;
    private javax.swing.JLabel jlblIdentificacion;
    private javax.swing.JLabel jlblNoContrato;
    private javax.swing.JLabel jlblNoPoliza;
    private javax.swing.JLabel jlblNombre;
    private javax.swing.JLabel jlblOcupacion;
    private javax.swing.JLabel jlblParentezco;
    private javax.swing.JLabel jlblPoliza;
    private javax.swing.JLabel jlblProvincia;
    private javax.swing.JLabel jlblSeguroSocial;
    private javax.swing.JLabel jlblSexo;
    private javax.swing.JLabel jlblTelefono;
    private javax.swing.JLabel jlblTipoAfiliacion;
    private javax.swing.JLabel jlblTipoCobertura;
    private javax.swing.JLabel jlblTipoPlan;
    private javax.swing.JLabel jlblTipoSangre;
    private javax.swing.JPanel jpnlAfiliacion;
    private javax.swing.JPanel jpnllabel;
    private javax.swing.JTable jtblAfiliado;
    private javax.swing.JTextField jtxtBuscar;
    private javax.swing.JTextField jtxtCedTitular;
    private javax.swing.JTextField jtxtDireccion;
    private javax.swing.JTextField jtxtEdad;
    private javax.swing.JTextField jtxtNombre;
    private javax.swing.JTextField jtxtSeguroSocial;
    // End of variables declaration//GEN-END:variables
}
