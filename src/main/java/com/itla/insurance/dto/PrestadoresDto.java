/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itla.insurance.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sulenni
 */
public class PrestadoresDto {
    private int id;
    private String nombre;
    private int id_especialidad;
    private int id_institucion;
    private String telefono;
    private String direccion;
    private String codigo;
    private int id_tipo_pss;
    private List<Prestador_ServicioDto> Servicios;

    public PrestadoresDto() {
        Servicios = new ArrayList<>();
    }

    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the id_especialidad
     */
    public int getId_especialidad() {
        return id_especialidad;
    }

    /**
     * @param id_especialidad the id_especialidad to set
     */
    public void setId_especialidad(int id_especialidad) {
        this.id_especialidad = id_especialidad;
    }

    /**
     * @return the id_institucion
     */
    public int getId_institucion() {
        return id_institucion;
    }

    /**
     * @param id_institucion the id_institucion to set
     */
    public void setId_institucion(int id_institucion) {
        this.id_institucion = id_institucion;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the id_tipo_pss
     */
    public int getId_tipo_pss() {
        return id_tipo_pss;
    }

    /**
     * @param id_tipo_pss the id_tipo_pss to set
     */
    public void setId_tipo_pss(int id_tipo_pss) {
        this.id_tipo_pss = id_tipo_pss;
    }

    /**
     * @return the Servicios
     */
    public List<Prestador_ServicioDto> getServicios() {
        return Servicios;
    }

    /**
     * @param Servicios the Servicios to set
     */
    public void setServicios(List<Prestador_ServicioDto> Servicios) {
        this.Servicios = Servicios;
    }
}
