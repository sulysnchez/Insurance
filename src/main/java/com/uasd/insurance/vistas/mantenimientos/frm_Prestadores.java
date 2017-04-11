/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uasd.insurance.vistas.mantenimientos;

import com.itla.insurance.dao.AfiliadoDao;
import com.itla.insurance.dto.AfiliadoDto;
import com.itla.insurance.dto.AnalisisDto;
import com.itla.insurance.dto.EspecialidadDto;
import com.itla.insurance.dto.InstitucionDto;
import com.itla.insurance.dto.Prestador_ServicioDto;
import com.itla.insurance.dto.PrestadoresDto;
import com.itla.insurance.dto.Tipo_IdentificacionDto;
import com.itla.insurance.dto.Tipo_PssDto;
import com.uasd.insurance.utilitario.MyListRendeder;
import com.uasd.insurance.vistas.principal.frm_Principal;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Giame
 */
public class frm_Prestadores extends javax.swing.JInternalFrame {

    AfiliadoDao afiliadoDao;
    frm_Principal principal;
    PrestadoresDto prestadorSeleccionado;
    
    /**
     * Creates new form frm_Prestadores
     */
    public frm_Prestadores() {
        initComponents();
        try {
            afiliadoDao = new AfiliadoDao();
        } catch (Exception ex) {
            Logger.getLogger(frm_Prestadores.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setPreferredSize(new Dimension(pnCampos.getWidth(), this.getHeight()));
        repaint();
        revalidate();
        HabilitarCampos(true);
        LlenarCombos();
        LlenarTablaPrestadores();
        DisenarTablaServicios();
    }

    public frm_Prestadores(frm_Principal principal) {
        initComponents();
        prestadorSeleccionado = new PrestadoresDto();
        this.principal = principal;
        
        try {
            afiliadoDao = new AfiliadoDao();
        } catch (Exception ex) {
            Logger.getLogger(frm_Prestadores.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setPreferredSize(new Dimension(pnCampos.getWidth(), this.getHeight()));
        repaint();
        revalidate();
        HabilitarCampos(true);
        LlenarCombos();
        LlenarTablaPrestadores();
        LlenarPrestadorSeleccionado();
        DisenarTablaServicios();
        PasarATablaServicios();
    }
   
    private void LlenarTablaPrestadores(){
        
        try {
            jtblPrestadores.setModel(afiliadoDao.getModelPrestador(afiliadoDao.GetAllPrestador()));
        } catch (SQLException ex) {
            Logger.getLogger(frm_Prestadores.class.getName()).log(Level.SEVERE, null, ex);
        }
        RedisenarTablaServicios();
        SeleccionarPrimero();
    }
    private void SeleccionarPrimero(){
        jtblPrestadores.setRowSelectionInterval(0, 0);
        LlenarPrestadorSeleccionado();
    }
    private void RedisenarTablaServicios(){
        jtblPrestadores.removeColumn(jtblPrestadores.getColumnModel().getColumn(2));
        jtblPrestadores.removeColumn(jtblPrestadores.getColumnModel().getColumn(2));
        jtblPrestadores.removeColumn(jtblPrestadores.getColumnModel().getColumn(2));
        jtblPrestadores.removeColumn(jtblPrestadores.getColumnModel().getColumn(2));
        jtblPrestadores.removeColumn(jtblPrestadores.getColumnModel().getColumn(2));
 
    }
    private void LlenarPrestadorSeleccionado(){
        prestadorSeleccionado.setCodigo((String)jtblPrestadores.getModel().getValueAt(jtblPrestadores.getSelectedRow(), 0));
        prestadorSeleccionado.setNombre(((String) jtblPrestadores.getModel().getValueAt(jtblPrestadores.getSelectedRow(), 1)));
        prestadorSeleccionado.setId_especialidad(((Integer) jtblPrestadores.getModel().getValueAt(jtblPrestadores.getSelectedRow(), 2)));
        prestadorSeleccionado.setId_institucion(((Integer) jtblPrestadores.getModel().getValueAt(jtblPrestadores.getSelectedRow(), 3)));
        prestadorSeleccionado.setId_tipo_pss((Integer) jtblPrestadores.getModel().getValueAt(jtblPrestadores.getSelectedRow(), 4));
        prestadorSeleccionado.setId(((Integer) jtblPrestadores.getModel().getValueAt(jtblPrestadores.getSelectedRow(), 5)));
        prestadorSeleccionado.setTelefono((String) jtblPrestadores.getModel().getValueAt(jtblPrestadores.getSelectedRow(), 6));
        
        try {
            prestadorSeleccionado.setServicios(afiliadoDao.GetPrestadorById(prestadorSeleccionado.getId()).getServicios());
        } catch (SQLException ex) {
            Logger.getLogger(frm_Prestadores.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        PasarACampos(prestadorSeleccionado);
    }
    private void PasarACampos(PrestadoresDto prest){
        jtxtCodigo.setText(prest.getCodigo());
        jtxtNombre.setText(prest.getNombre());
        PasarACombos();
        PasarATablaServicios();
    }
    private void PasarATablaServicios(){
        LimpiarTablaServicios();
        
        for (Prestador_ServicioDto servicio : prestadorSeleccionado.getServicios()) {
            Object[] registro = new Object[5];
            registro[0] = servicio.getId_servicio();
            registro[1] = servicio.getNombre_servicio();
            registro[2] = servicio.getPrecio();
            registro[3] = prestadorSeleccionado.getId();
            registro[4] = servicio.getId();
            
        
            ((DefaultTableModel) jTable1.getModel()).addRow(registro);
            
            
        }
    }
    private void PasarACombos(){
         int id = 0;
            for (int i = 0;i<jcmbEspecialidad.getItemCount();i++) {
                
                Object[] ob = (Object[]) jcmbEspecialidad.getItemAt(i);
                id= (int)ob[0];
                if(id==prestadorSeleccionado.getId_especialidad()){ 
                    jcmbEspecialidad.setSelectedIndex(i);
                }
            }
            id = 0;
            for (int i = 0;i<jcmbInstitucion.getItemCount();i++) {
                
                Object[] ob = (Object[]) jcmbInstitucion.getItemAt(i);
                id= (int)ob[0];
                if(id==prestadorSeleccionado.getId_institucion()){ 
                    jcmbInstitucion.setSelectedIndex(i);
                }
            }
            id = 0;
            for (int i = 0;i<jcmbTipoPSS.getItemCount();i++) {
                
                Object[] ob = (Object[]) jcmbTipoPSS.getItemAt(i);
                id= (int)ob[0];
                if(id==prestadorSeleccionado.getId_tipo_pss()){ 
                    jcmbTipoPSS.setSelectedIndex(i);
                }
            }
    }
    private void HabilitarCampos(boolean estatus){
        
        jtxtCodigo.setEnabled(estatus);
        jtxtNombre.setEnabled(estatus);
        jcmbEspecialidad.setEnabled(estatus);
        jcmbInstitucion.setEnabled(estatus);
        jcmbTipoPSS.setEnabled(estatus);
    }
    
    private void DisenarTablaServicios(){
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("IdServicio");
        modelo.addColumn("Servicio");
        modelo.addColumn("Precio");
        modelo.addColumn("IdPrestador");
        modelo.addColumn("Id");
        
        jTable1.setModel(modelo);
        
        
        jTable1.removeColumn(jtblPrestadores.getColumnModel().getColumn(1));
    }
    public void AgregarServicio(Prestador_ServicioDto preser){
        
        
        Object[] registro = new Object[5];
        
        registro[0] = preser.getId_servicio();
        registro[1] = preser.getNombre_servicio();
        registro[2] = preser.getPrecio();
        registro[3] = prestadorSeleccionado.getId();
        registro[4] = preser.getId();
        
        prestadorSeleccionado.getServicios().add(preser);
        ((DefaultTableModel) jTable1.getModel()).addRow(registro);
    }
    public List<Prestador_ServicioDto> ExtraerServicios(){
        
        List<Prestador_ServicioDto> lista = new ArrayList<Prestador_ServicioDto>();
        Prestador_ServicioDto preser;
        for (int i = 0; i < ((DefaultTableModel)jTable1.getModel()).getRowCount(); i++) {
            
            preser = new Prestador_ServicioDto();
            preser.setId_servicio((Integer)jTable1.getModel().getValueAt(i, 0));
            preser.setNombre_servicio((String)jTable1.getModel().getValueAt(i, 1));
            preser.setPrecio((Float)jTable1.getModel().getValueAt(i, 2));
            preser.setId_prestador((Integer)jTable1.getModel().getValueAt(i, 3));
            preser.setId((Integer)jTable1.getModel().getValueAt(i, 4));
            
            lista.add(preser);
            
        }
        
        return lista;
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnPrincipal = new javax.swing.JPanel();
        pnServicios = new javax.swing.JPanel();
        pnDatos = new javax.swing.JPanel();
        pnCampos = new javax.swing.JPanel();
        jlblAnalisis = new javax.swing.JLabel();
        jlblNombre = new javax.swing.JLabel();
        jtxtNombre = new javax.swing.JTextField();
        jlblNombre1 = new javax.swing.JLabel();
        jtxtCodigo = new javax.swing.JTextField();
        jcmbEspecialidad = new javax.swing.JComboBox();
        jlblNombre2 = new javax.swing.JLabel();
        jlblNombre3 = new javax.swing.JLabel();
        jlblNombre4 = new javax.swing.JLabel();
        jcmbTipoPSS = new javax.swing.JComboBox();
        jcmbInstitucion = new javax.swing.JComboBox();
        pnListaServicios = new javax.swing.JPanel();
        jlblServicio = new javax.swing.JLabel();
        pnTablaServiciosAsignados = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jbttNuevo2 = new javax.swing.JButton();
        jbttNuevo3 = new javax.swing.JButton();
        jbttGuardar1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtblPrestadores = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);

        pnPrincipal.setLayout(new java.awt.BorderLayout());

        pnDatos.setPreferredSize(new java.awt.Dimension(300, 487));

        jlblAnalisis.setFont(new java.awt.Font("Palatino Linotype", 2, 18)); // NOI18N
        jlblAnalisis.setText("FORMULARIO PRESTADORES");

        jlblNombre.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jlblNombre.setText("Nombre");

        jtxtNombre.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        jtxtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtNombreActionPerformed(evt);
            }
        });

        jlblNombre1.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jlblNombre1.setText("Código");

        jcmbEspecialidad.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        jcmbEspecialidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmbEspecialidadActionPerformed(evt);
            }
        });

        jlblNombre2.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jlblNombre2.setText("Especialidad");

        jlblNombre3.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jlblNombre3.setText("Tipo PSS");

        jlblNombre4.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jlblNombre4.setText("Institución");

        jcmbTipoPSS.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        jcmbTipoPSS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmbTipoPSSActionPerformed(evt);
            }
        });

        jcmbInstitucion.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        jcmbInstitucion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmbInstitucionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnCamposLayout = new javax.swing.GroupLayout(pnCampos);
        pnCampos.setLayout(pnCamposLayout);
        pnCamposLayout.setHorizontalGroup(
            pnCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCamposLayout.createSequentialGroup()
                .addGroup(pnCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnCamposLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jlblAnalisis))
                    .addGroup(pnCamposLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnCamposLayout.createSequentialGroup()
                                .addGroup(pnCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlblNombre4)
                                    .addComponent(jlblNombre3))
                                .addGroup(pnCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnCamposLayout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addComponent(jcmbInstitucion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(pnCamposLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jcmbTipoPSS, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(pnCamposLayout.createSequentialGroup()
                                .addGroup(pnCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlblNombre1)
                                    .addComponent(jlblNombre))
                                .addGap(18, 18, 18)
                                .addGroup(pnCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtxtNombre)
                                    .addComponent(jtxtCodigo)))
                            .addGroup(pnCamposLayout.createSequentialGroup()
                                .addComponent(jlblNombre2)
                                .addGap(18, 18, 18)
                                .addComponent(jcmbEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        pnCamposLayout.setVerticalGroup(
            pnCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCamposLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jlblAnalisis)
                .addGap(18, 18, 18)
                .addGroup(pnCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblNombre1)
                    .addComponent(jtxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblNombre)
                    .addComponent(jtxtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblNombre2)
                    .addComponent(jcmbEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblNombre4)
                    .addComponent(jcmbInstitucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcmbTipoPSS, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblNombre3))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        jlblServicio.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jlblServicio.setText("Servicios");

        pnTablaServiciosAsignados.setLayout(new java.awt.BorderLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        pnTablaServiciosAsignados.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jButton1.setText("-");

        jButton2.setText("+");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jbttNuevo2.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jbttNuevo2.setText("Nuevo");
        jbttNuevo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbttNuevo2ActionPerformed(evt);
            }
        });

        jbttNuevo3.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jbttNuevo3.setText("Eliminar");
        jbttNuevo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbttNuevo3ActionPerformed(evt);
            }
        });

        jbttGuardar1.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jbttGuardar1.setText("Guardar");
        jbttGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbttGuardar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnListaServiciosLayout = new javax.swing.GroupLayout(pnListaServicios);
        pnListaServicios.setLayout(pnListaServiciosLayout);
        pnListaServiciosLayout.setHorizontalGroup(
            pnListaServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnListaServiciosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnListaServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnTablaServiciosAsignados, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(pnListaServiciosLayout.createSequentialGroup()
                        .addComponent(jlblServicio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnListaServiciosLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbttNuevo2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbttNuevo3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbttGuardar1)))
                .addContainerGap())
        );
        pnListaServiciosLayout.setVerticalGroup(
            pnListaServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnListaServiciosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnListaServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlblServicio)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnTablaServiciosAsignados, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnListaServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbttGuardar1)
                    .addComponent(jbttNuevo3)
                    .addComponent(jbttNuevo2))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnDatosLayout = new javax.swing.GroupLayout(pnDatos);
        pnDatos.setLayout(pnDatosLayout);
        pnDatosLayout.setHorizontalGroup(
            pnDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnListaServicios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnCampos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnDatosLayout.setVerticalGroup(
            pnDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnCampos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnListaServicios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setLayout(new java.awt.BorderLayout());

        jtblPrestadores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        jtblPrestadores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jtblPrestadoresMouseReleased(evt);
            }
        });
        jtblPrestadores.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtblPrestadoresKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(jtblPrestadores);

        jPanel1.add(jScrollPane2, java.awt.BorderLayout.PAGE_END);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        javax.swing.GroupLayout pnServiciosLayout = new javax.swing.GroupLayout(pnServicios);
        pnServicios.setLayout(pnServiciosLayout);
        pnServiciosLayout.setHorizontalGroup(
            pnServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnServiciosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnServiciosLayout.setVerticalGroup(
            pnServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnServiciosLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnServiciosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnPrincipal.add(pnServicios, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnPrincipal, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtNombreActionPerformed

    private void jcmbEspecialidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmbEspecialidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcmbEspecialidadActionPerformed

    private void jcmbTipoPSSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmbTipoPSSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcmbTipoPSSActionPerformed

    private void jcmbInstitucionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmbInstitucionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcmbInstitucionActionPerformed

    private void jbttNuevo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbttNuevo2ActionPerformed
        prestadorSeleccionado = new PrestadoresDto();
        
        jtxtCodigo.setText("");
        jtxtNombre.setText("");
        jcmbEspecialidad.setSelectedIndex(0);
        jcmbInstitucion.setSelectedIndex(0);
        jcmbTipoPSS.setSelectedIndex(0);
        
        LimpiarTablaServicios();
    
    }//GEN-LAST:event_jbttNuevo2ActionPerformed

    private void jbttNuevo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbttNuevo3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbttNuevo3ActionPerformed

    private void jbttGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbttGuardar1ActionPerformed
        prestadorSeleccionado.setCodigo(jtxtCodigo.getText());
        prestadorSeleccionado.setNombre(jtxtNombre.getText());
        
        int id;
        Object[] ob; 

        ob = (Object[]) jcmbEspecialidad.getSelectedItem();
        id = (int) ob[0];
        prestadorSeleccionado.setId_especialidad(id);
        
        ob = (Object[]) jcmbInstitucion.getSelectedItem();
        id = (int) ob[0];
        prestadorSeleccionado.setId_institucion(id);
        
        ob = (Object[]) jcmbTipoPSS.getSelectedItem();
        id = (int) ob[0];
        prestadorSeleccionado.setId_tipo_pss(id);
        
        prestadorSeleccionado.setServicios(ExtraerServicios());
        if(prestadorSeleccionado.getId()==0){
            try {
                
                prestadorSeleccionado = afiliadoDao.InsertPrestador(prestadorSeleccionado);
                
                //LlenarTablaPrestadores();
        
                for (Prestador_ServicioDto servicio : prestadorSeleccionado.getServicios()) {
                    if(servicio.getId()==0){
                        
                            afiliadoDao.InsertPrestadorServicio(servicio);
                        
                    }else{
                        
                            afiliadoDao.UpdatePrestadorServicio(servicio);
                       
                    }
                }
        
            } catch (Exception ex) {
                Logger.getLogger(frm_Prestadores.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            try {
                
                prestadorSeleccionado = afiliadoDao.UpdatePrestador(prestadorSeleccionado);
                
                //LlenarTablaPrestadores();
        
                for (Prestador_ServicioDto servicio : prestadorSeleccionado.getServicios()) {
                    if(servicio.getId()==0){
                        
                            afiliadoDao.InsertPrestadorServicio(servicio);
                        
                    }else{
                        
                            afiliadoDao.UpdatePrestadorServicio(servicio);
                       
                    }
                }
                
            } catch (Exception ex) {
                Logger.getLogger(frm_Prestadores.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
    }//GEN-LAST:event_jbttGuardar1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        frm_BuscarServicio frm = new frm_BuscarServicio(this);
        principal.agregarFormulario(frm);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jtblPrestadoresMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblPrestadoresMouseReleased
        LlenarPrestadorSeleccionado();
    }//GEN-LAST:event_jtblPrestadoresMouseReleased

    private void jtblPrestadoresKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtblPrestadoresKeyPressed
        LlenarPrestadorSeleccionado();
    }//GEN-LAST:event_jtblPrestadoresKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton jbttGuardar1;
    private javax.swing.JButton jbttNuevo2;
    private javax.swing.JButton jbttNuevo3;
    private javax.swing.JComboBox jcmbEspecialidad;
    private javax.swing.JComboBox jcmbInstitucion;
    private javax.swing.JComboBox jcmbTipoPSS;
    private javax.swing.JLabel jlblAnalisis;
    private javax.swing.JLabel jlblNombre;
    private javax.swing.JLabel jlblNombre1;
    private javax.swing.JLabel jlblNombre2;
    private javax.swing.JLabel jlblNombre3;
    private javax.swing.JLabel jlblNombre4;
    private javax.swing.JLabel jlblServicio;
    private javax.swing.JTable jtblPrestadores;
    private javax.swing.JTextField jtxtCodigo;
    private javax.swing.JTextField jtxtNombre;
    private javax.swing.JPanel pnCampos;
    private javax.swing.JPanel pnDatos;
    private javax.swing.JPanel pnListaServicios;
    private javax.swing.JPanel pnPrincipal;
    private javax.swing.JPanel pnServicios;
    private javax.swing.JPanel pnTablaServiciosAsignados;
    // End of variables declaration//GEN-END:variables

    private void LlenarCombos() {
        //LLENA COMBO TIPO DE ESPECIALIDAD
        Object[] itemBlank = new Object[]{0,""};
        
        jcmbEspecialidad.setRenderer(new MyListRendeder());
        
        jcmbEspecialidad.addItem(itemBlank);
        
        try {
            for (EspecialidadDto ti : afiliadoDao.GetAllEspecialidad()) {
                Object[] item = new Object[]{ti.getId(), ti.getNombre()};
                jcmbEspecialidad.addItem(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(frm_Prestadores.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //LLENA COMBO TIPO DE INSTITUCION
        
        jcmbInstitucion.setRenderer(new MyListRendeder());
        
        jcmbInstitucion.addItem(itemBlank);
        
        try {
            for (InstitucionDto ti : afiliadoDao.GetAllInstitucion()) {
                Object[] item = new Object[]{ti.getId(), ti.getNombre()};
                jcmbInstitucion.addItem(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(frm_Prestadores.class.getName()).log(Level.SEVERE, null, ex);
        }
        //LLENA COMBO TIPO DE TIPO PSS
        
        jcmbTipoPSS.setRenderer(new MyListRendeder());
        
        jcmbTipoPSS.addItem(itemBlank);
        
        try {
            for (Tipo_PssDto ti : afiliadoDao.GetAllTipoPss()) {
                Object[] item = new Object[]{ti.getId(), ti.getNombre()};
                jcmbTipoPSS.addItem(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(frm_Prestadores.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    private void LimpiarTablaServicios() {
        
        DefaultTableModel dtm = ((DefaultTableModel)jTable1.getModel());
        int rowCount  = dtm.getRowCount();
        
        for (int i = rowCount-1; i>=0; i--) {
            dtm.removeRow(i);
        }
    }
}
